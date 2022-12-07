
var cookieModule = cookieModule;
var apiclient = (function (){

    var numganador = function (callback){
        $.ajax({
            type: "GET",
            url: "/Nganador",
            contentType: "application/json; charset=utf-8",
            dataType: "text",
    		success: function(data) {
				callback(data);
    		}
        })
    }
    var time = function (callback){
        $.ajax({
            type: "GET",
            url: "/Time",
            contentType: "application/json; charset=utf-8",
            dataType: "text",
    		success: function(data) {
				callback(data);
    		}
        })
    }
    var putApuesta = function (dataApuesta) {
        var putPromise = $.ajax({
            url: "/Papuestas" ,
            type: "PUT",
            data: JSON.stringify(dataApuesta),
            contentType: "application/json"
        })
    }
    var crear = function (newUser) {
            $.ajax({
                url:"/ruleta/addUser",
                type: 'POST',
                data: JSON.stringify(newUser),
                contentType: "application/json",
                success: function () {
                    location.href = "index.html";
                }
            })
    }
    var validarUser = function(user){
    		var valor = user.correo;
    		$.ajax({
                 url:"/ruleta/User",
                 type: 'POST',
                 data: JSON.stringify(user),
                 contentType: "application/json",
                 success: function () {
                    cookieModule.setCookies("usuario", valor, 0.1);
                    console.log("Usuario reconocido");
                    location.href ="inicio.html";
                 },
                 error: function () {
                     Swal.fire({
                         icon: 'error',
                         title: 'Oops...',
                         text: 'Credenciales incorrectas!',
                     });
                 }
             })
    }
    var getUser = function (user, callbackuser) {
            $.ajax({
                url: "/ruleta/getUser",
                type: "POST",
                async: false,
                data: JSON.stringify(user),
                contentType: "text/plain",
                success: function (data) {
                	console.log("se obtiene el user");
                    callbackuser(data);
                }
            })
    }
    var logout = function(user){
        cookieModule.setCookies("usuario","",0.1);
        validarUser(user);
        location.href ="index.html";

    }

    return{
		numganador: numganador,
		putApuesta: putApuesta,
		crear: crear,
		validarUser: validarUser,
		getUser: getUser,
        logout: logout,
        time: time
    }
    
})();