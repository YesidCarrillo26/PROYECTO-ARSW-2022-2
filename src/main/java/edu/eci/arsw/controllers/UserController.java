package edu.eci.arsw.controllers;

import edu.eci.arsw.exceptions.RuletaPersistenceException;
import edu.eci.arsw.models.Usuario;
import edu.eci.arsw.services.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/ruleta")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public ResponseEntity insertUsers(@RequestBody String body) throws NoSuchAlgorithmException {
        //obtener datos que se enviarán a través del API
        JSONObject obj = new JSONObject(body);
        String password = obj.getString("password1");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String psswdHash = sb.toString();
        Usuario us = new Usuario(obj.getString("name"), obj.getString("fecha"),obj.getInt("telefono"), obj.getString("correo"), psswdHash);
        try {
            userService.crearUser(us);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping(path = "/User", method = RequestMethod.POST)
    public ResponseEntity postUsers(@RequestBody String body) throws RuletaPersistenceException, NoSuchAlgorithmException, SQLException {

        JSONObject obj = new JSONObject(body);
        String correo = obj.getString("correo");
        String password = obj.getString("password1");
        Usuario u = userService.getUsuario(correo);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String psswdHash = sb.toString();
        try {
            if((u.getPassword()).equals(psswdHash)) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            else {

                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }


        } catch (Exception  ex) { ex.printStackTrace();
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(path = "/getUser", method = RequestMethod.POST)
    public ResponseEntity getUsuario(@RequestBody String body) throws RuletaPersistenceException, SQLException {
        //obtener datos que se enviarán a través del API
        JSONObject obj = new JSONObject(body);
        String correo = obj.getString("correo");
        Usuario u = userService.getUsuario(correo);
        String nombre = u.getName();
        String correo1 = u.getCorreo();
        int points = u.getPoints();
        String result = nombre+":"+correo1+":"+points;
        try {
            return new ResponseEntity<>(result,HttpStatus.CREATED);

        } catch (Exception  ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
