var apiclient = apiclient;
var inicioModule = inicioModule;
var cookieModule = cookieModule;
var module = module;
var user = {id:null, name: null, fecha: null, telefono: null, correo: null, password1: null, points: null};
var $inner = $('.inner'),
    $data = $('.data'),
    $mask = $('.mask'),
    $us = $('.us'),
    $cn = $('.cn'),
    maskDefault = 'Realiza tus apuestas',
    timer = 9000;
var red = [32,19,21,25,34,27,36,30,23,5,16,1,14,9,18,7,12,3];
var usuario;
var ganadores = [];
var validador = true;
var chips = new Array();
var bets = new Array();
var prueba = 0;
var tiempo = null;
var apuesta = 100;
    var dataApuesta = {
           user: null,
        apuesta: null,
        valor: null
    };
    var dataruleta = {
    	user: null,
    	time: null,
    	ganador: null
    };
 time = 50000;
var rouletteModule = (function () {
	var numeroG;
	var fun = function(num){
		var numeroG = parseInt(num);
		ganadores.push(numeroG);
    }
	var ganador = function(){
		apiclient.numganador(fun);
	}
	var callbacktime = function(time){
		tiempo = (time*1000)+2000;
	}
	var time = function(){
		apiclient.time(callbacktime);
	}
	var limpiar = function(){
		inicioModule.limpiar();
	}
	var apostando = function(){
		validador = true;
	}
	var jugando = function(){
		validador = false;
	}
	var momento = function(){
		return validador;
	}
	var callbackuser = function(userjson){
		usuario = userjson.split(":");
	}
	var getUser = function(user, callbackuser){
		apiclient.getUser(user, callbackuser);
	}
	var limpiarSokect = function () {
            var ncoins = bets.length;
            for (var i = 0; i < ncoins; i++) {
                document.body.removeChild(chips[bets[i]].pop());
            }
            bets = new Array();
    }
	var actualizarDatos = function(){
		getUser(user, callbackuser);
		console.log(usuario);
		$us.text("Usuario: " + usuario[0]);
		$cn.text("Coints: " +  usuario[2] );
	}
	var temporizador = function(){
		user.correo = cookieModule.getCookies("usuario");
		connectAndSubscribe();
		time();
		ganador();
		actualizarDatos();
		val = 0;
		var seg = setInterval(function(){
				if(tiempo < 20000){
					$mask.text((20000-tiempo)/1000);
					tiempo += 1000;
					apostando();
				}
				else{
				//Girando ruleta
					if(tiempo < 50000){
						jugando();
						tiempo += 1000;
						$mask.text("Girando!");
						$inner.attr('data-spinto', ganadores[ganadores.length - 1]).find('li:nth-child('+ ganadores[ganadores.length - 1] +') input').prop('checked','checked');
						if($.inArray(parseInt(ganadores[ganadores.length - 1]), red) !== -1){ color = 'red'} else { color = 'black'};
	                    if(parseInt(ganadores[ganadores.length - 1]) == 0){color = 'green'};
						//Resultados ruleta
						if(tiempo > 30000){
		                    $('.result-number').text(ganadores[ganadores.length - 1]);
		                    $('.result-color').text(color);
		                    $('.result').css({'background-color': ''+color+''});
		                    $data.addClass('reveal');
		                    $inner.addClass('rest');
							if(val == 0){
		                    	$thisResult = '<li class="previous-result color-'+ color +'"><span class="previous-number">'+ ganadores[ganadores.length - 1] +'</span><span class="previous-color">'+ color +'</span></li>';
		                    	$('.previous-list').prepend($thisResult);
		                    	val = 1;
							}
		               
	                    }
					}
					else{
					//Cuando acaba el tiempo
						$inner.attr('data-spinto','').removeClass('rest');
						$data.removeClass('reveal');
						tiempo = 0;
						val = 0;
						$mask.text("");
						actualizarDatos();
						ganador();
						limpiar();
						limpiarSokect();
					}
				}
        }, 1000);
	}
	var changeapuesta = function(valor){
		apuesta = valor;
	}
	var apostar = function(casilleroVal){
		if(momento()){
			if(usuario[2] > 0 && usuario[2] >= apuesta){
				dataApuesta.user = usuario[1];
				dataApuesta.apuesta = String(casilleroVal.value);
				dataApuesta.valor = apuesta;
				stompClient.send("/topic/coints", {}, JSON.stringify(dataApuesta));
				inicioModule.apostar(usuario[1], casilleroVal,  apuesta);
				usuario[2] = usuario[2]-apuesta;
				$cn.text("Coints: " +  usuario[2] );
			}
			else{
				Swal.fire({
                         icon: 'info',
                         title: 'Coints insuficientes',
                         text: 'Coints insuficientes',
                });
			}
		}
	}
	var connectAndSubscribe = function () {    
        console.log('SE CONECTO');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/coints', function (eventbody) {
            	var usuari = (JSON.parse(eventbody.body)).user;
				var casilleroVal = (JSON.parse(eventbody.body)).apuesta;
				var valor = (JSON.parse(eventbody.body)).valor;
				if(usuari != usuario[1]){
						var lugar = document.getElementById(casilleroVal);
						var _x;
						var _y;
						_x = lugar.offsetLeft - 240;
						_y = lugar.offsetTop - 130;
						if(valor == 100){
							var img = document.createElement('img');
							img.src = "../imagen/fichaAzul.png";
							img.style.zIndex = "0";
							img.style.position = "absolute";
						}
						if(valor == 500){
							var img = document.createElement('img');
							img.src = "../imagen/fichaAmarilla.png";
							img.style.zIndex = "0";
							img.style.position = "absolute";
						}
						if(valor == 1000){
							var img = document.createElement('img');
							img.src = "../imagen/fichaVerde.png";
							img.style.zIndex = "0";
							img.style.position = "absolute";
						}
						if(valor == 5000){
							var img = document.createElement('img');
							img.src = "../imagen/fichaRoja.png";
							img.style.zIndex = "0";
							img.style.position = "absolute";
						}
						if(valor == 10000){
							var img = document.createElement('img');
							img.src = "../imagen/fichaNaranja.png";
							img.style.zIndex = "0";
							img.style.position = "absolute";
						}
						var rX = Math.floor(Math.random() * (10 - (-10) + 1)) + -10;
						var rY = Math.floor(Math.random() * (10 - (-10) + 1)) + -10;
						
						img.style.left = (_x + rX + 250) + "px";
						img.style.top = (_y + rY + 150) + "px";
						
						img.style.width = "20px";
						img.style.pointerEvents = "none";
						document.body.appendChild(img);
						
						if (chips[casilleroVal.value] == null){
						    chips[casilleroVal.value] = new Array(0);
						}
						chips[casilleroVal.value].push(img);
						bets.push(casilleroVal.value);
				} 
            });
        });

    }
    var disconnect = function (){
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }
	
    return{
    	ganador: ganador,
    	fun: fun,
    	temporizador: temporizador,
    	limpiar: limpiar,
    	apostando: apostando,
    	jugando: jugando,
    	momento: momento,
    	getUser: getUser, 
    	actualizarDatos: actualizarDatos,
    	apostar: apostar,
    	changeapuesta: changeapuesta,
    	disconnect: disconnect,
    	connectAndSubscribe: connectAndSubscribe,
    	callbacktime: callbacktime,
    	time: time
    
    }

})();