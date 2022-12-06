var apiclient = apiclient;
var rouletteModule = rouletteModule;
var inicioModule = (function () {
    var chips = new Array();
    var bets = new Array();
    var dataApuesta = {
        user: null,
        apuesta: null,
        valor: null
    };
    var stompClient = null;
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
    var colocarcoints = function(casilleroVal){
	    if (rouletteModule.momento()) {
	    	console.log("----Intenta colocar los coints");
			apuesta(dataApuesta);
			var lugar = document.getElementById(casilleroVal.value);
			var _x;
			var _y;
			_x = lugar.offsetLeft - 240;
			_y = lugar.offsetTop - 130;
			if(dataApuesta.valor == 100){
				var img = document.createElement('img');
				img.src = "../imagen/fichaAzul.png";
				img.style.zIndex = "0";
				img.style.position = "absolute";
			}
			if(dataApuesta.valor == 500){
				var img = document.createElement('img');
				img.src = "../imagen/fichaAmarilla.png";
				img.style.zIndex = "0";
				img.style.position = "absolute";
			}
			if(dataApuesta.valor == 1000){
				var img = document.createElement('img');
				img.src = "../imagen/fichaVerde.png";
				img.style.zIndex = "0";
				img.style.position = "absolute";
			}
			if(dataApuesta.valor == 5000){
				var img = document.createElement('img');
				img.src = "../imagen/fichaRoja.png";
				img.style.zIndex = "0";
				img.style.position = "absolute";
			}
			if(dataApuesta.valor == 10000){
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
    }
    var apostar = function (correo, casilleroVal, apuesta) {
			dataApuesta.user = correo;
			dataApuesta.apuesta = String(casilleroVal.value);
			dataApuesta.valor = apuesta;
           	colocarcoints(casilleroVal);
     }

    return{
    	limpiar: limpiar,
    	apostar: apostar,
    	apuesta: apuesta,
    	colocarcoints: colocarcoints
    }
    
})();