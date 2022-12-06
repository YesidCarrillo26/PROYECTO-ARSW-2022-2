var stomp = (function () {

    var topic = 0;
    var stompClient = null;
    var lugar = 0;

    var chips = new Array();
    var bets = new Array();
    var dataApuesta = {
        user: null,
        apuesta: null,
        valor: null
    };

    var limpiar = function () {
            var ncoins = bets.length;
            for (var i = 0; i < ncoins; i++) {
                document.body.removeChild(chips[bets[i]].pop());
            }
            
            bets = new Array();
    }

    var apuesta = function(data){
    	apiclient.putApuesta(data);
    }

    // PASO 1: SELECCIONA LA CASILLA PARA PONER COINT
    var casilla = function(casilleroVal){
	    if (rouletteModule.momento()) {
			apuesta(dataApuesta);
			var lugar = document.getElementById(casilleroVal.value);
            connectAndSubscribe(lugar);
            if(window.PointerEvent){
                lugar.addEventListener("pointerdown", function (event){
                    var point = getMousePosition(event);
                    colocarcoints(point);
                    stompClient.send("/topic"+topic, {}, JSON.stringify(point));
                });
            }
		}
    }
    // PASO 2: SE SUSCRIBE AL COLOCAR LA COINT
    var connectAndSubscribe = function (topico) {    
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            //stompClient.subscribe('/topic/user', function (eventbody) {
            stompClient.subscribe('/topic '+topico, function (eventbody) {
                colocarcoints(lugar);
            });
        });

    }
    // PASO 3: SE COLOCA LA COINT
    var colocarcoints = function(lugar){
        var _x;
		var _y;
		_x = lugar.offsetLeft - 240;
		_y = lugar.offsetTop - 130;
			
		var img = document.createElement('img');
		img.src = "../imagen/fichaAzul.png";
		img.style.zIndex = "0";
		img.style.position = "absolute";
			
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


    var apostar = function (casilleroVal) {
    		console.log("click apuesta");
			dataApuesta.user = "pepito";
            dataApuesta.apuesta = String(casilleroVal.value);
            dataApuesta.valor = "100";
            console.log(JSON.stringify(dataApuesta));
           	console.log("valor boolean" + " " + rouletteModule.momento());
           	colocarcoints(casilleroVal);
     }

    
    

       
    var connect = function (){
        var canvas = document.getElementById("canvas");
        //Escoger si se va a conectar por puntos o por poligonos
        var option = document.getElementById("connectionType");
        connectAndSubscribe(topic);
        if(window.PointerEvent){
            canvas.addEventListener("pointerdown", function (event){
                var point = getMousePosition(event);
                addPointToCanvas(point);
                stompClient.send("/topic"+topic, {}, JSON.stringify(point));
            });
        }
    }

    var publish = function(px,py){
        var pt=new Point(px,py);
        console.info("publishing point at "+pt);
        //addPointToCanvas(pt);
        colocarcoints(casilleroVal);
        stompClient.send("/topic" + topic, {}, JSON.stringify(pt));
        //publicar el evento
    }

c
    return{
        connect: connect,
        disconnect: disconnect,
		publish: publish,
        connectAndSubscribe, connectAndSubscribe,
    	limpiar: limpiar,
        casilla, casilla
    }

})();