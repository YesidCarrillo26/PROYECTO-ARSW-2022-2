var apiclient = apiclient;
var module = (function () {
    var user = {
        id:null,
        name: null,
        fecha: null,
        telefono: null,
        correo: null,
        password1: null
    };

    var usuario;
    var id;
    var name;
    var fecha;
    var telefono;
    var correo;
    var password;
    var password1;
    var ajaxinser = function(newUser){
        apiclient.crear(newUser);
    }
    var validar = function(user){
    	apiclient.validarUser(user);
    }

    var logout = function(user){
        apiclient.logout(user);
    }

    var insert = function(){
            user.name = document.getElementById("username").value;
            user.fecha = document.getElementById("fecha").value;
            user.telefono = document.getElementById("telefono").value;
            user.correo = document.getElementById("correo").value;
            user.password1 = document.getElementById("password").value;
            password = document.getElementById("password1").value;
            if (user.name != null && user.fecha != null && user.telefono != null && user.password1 && user.telefono > 0 && user.password1 == password) {
                if ((user.correo).includes('@') === true) {
                    if ((user.correo).includes('.') === true) {
                        ajaxinser(user);
                        Swal.fire(
                                'Operacion Exitosa',
                                'Se registro satisfactoriamente!',
                                'success'
                                )
                    }
                    else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Datos incorrectos!',
                        });
                    }

                }
                else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Datos incorrectos!',
                    });
                }




            }
            else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Datos incorrectos!',
                });
            }
    }
    
    var login = function(){
            user.correo = document.getElementById("username").value;
            user.password1 = document.getElementById("password").value;
            if (user.correo != null && user.password1 != null){
                validar(user);
            }
    }

    var exit = function(){
        logout(user);
    }


   
    return{
        insert: insert,
        login: login,
        ajaxinser: ajaxinser,
        validar: validar,
        exit: exit
       
    }
})();