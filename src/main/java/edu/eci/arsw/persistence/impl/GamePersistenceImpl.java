package edu.eci.arsw.persistence.impl;


import org.springframework.stereotype.Component;

import edu.eci.arsw.exceptions.RuletaPersistenceException;
import edu.eci.arsw.models.Apuesta;
import edu.eci.arsw.models.Game;
import edu.eci.arsw.persistence.GamePersistence;

import java.sql.SQLException;

@Component
public class GamePersistenceImpl implements GamePersistence{
    private Game juego;
    public GamePersistenceImpl() {
        juego = new Game();
        juego.start();
    }
    
    @Override
    public int getNGanador() {
        return juego.getGanador();
    }

    @Override
    public void pagar() {
        try {
            juego.pay();
        } catch (RuletaPersistenceException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void apostar(String user, String numero, int valor) {
        Apuesta apuesta = new Apuesta(numero, valor, false, user);
        juego.addApuesta(apuesta);
    }

	@Override
	public long getTime() {
		return juego.getTime();
	}

}
