package edu.eci.arsw.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.services.GameService;

@Service
@RestController
public class GameControler {
    
    @Autowired
    GameService gameService;
    
    @RequestMapping(value = "/Nganador", method = RequestMethod.GET)
    public ResponseEntity<?> getNGanador(){
        try {
            int numeroG;
            numeroG = gameService.getNGanador();
            return new ResponseEntity<>(numeroG, HttpStatus.ACCEPTED);
        } catch (Exception ex){
            return new ResponseEntity<>("No hay apuestas", HttpStatus.NOT_FOUND);
        }

    }
    @RequestMapping(value = "/Time", method = RequestMethod.GET)
    public ResponseEntity<?> getTime(){
        try {
            long time;
            time = gameService.getTime();
            return new ResponseEntity<>(time, HttpStatus.ACCEPTED);
        } catch (Exception ex){
            return new ResponseEntity<>("No hay apuestas", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/Papuestas", method = RequestMethod.PUT)
    public ResponseEntity<?> Apuesta(@RequestBody String body){
        try {
            JSONObject obj = new JSONObject(body);
            gameService.apostar(obj.getString("user"), obj.getString("apuesta"), obj.getInt("valor"));
            return new ResponseEntity<>("llego el put de apuestas", HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>("ERROR AL CREAR SALA", HttpStatus.FORBIDDEN);
        }
    }

}
