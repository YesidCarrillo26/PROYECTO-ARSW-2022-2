package edu.eci.arsw.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import edu.eci.arsw.models.Game;
import edu.eci.arsw.persistence.GamePersistence;

@Service
public class GameService {

    @Autowired
    GamePersistence game;
    
    @Autowired
    public GameService() {
        
    }
    public int getNGanador() {
        return game.getNGanador();
    }
    public void pagar() {
        game.pagar();
    }
    public void apostar(String user, String numero, int valor) {
        game.apostar(user, numero, valor);
    
    }
    public long getTime() {
    	return game.getTime();
    }
}
