package edu.eci.arsw.models;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import edu.eci.arsw.DB.RuletaDB;
import edu.eci.arsw.exceptions.RuletaPersistenceException;

public class Game extends Thread{
	private int ganador;
	private Tablero table;
	private Random random;
    private Game ruleta = null;
    private long time;
    private boolean pago = true;
    private  ArrayList<Apuesta> apuestas;
	public Game(){
        table = new Tablero();
        apuestas = new ArrayList<>();
	}
    @Override
    public void run() {
        random = new Random();
        ganador = random.nextInt(37);
    	long initTime = System.currentTimeMillis();
    	while(true) {
    		time = (System.currentTimeMillis() - initTime)/1000;
    		if(time>22 && pago) {
    			try {
					pay();
				} catch (RuletaPersistenceException | SQLException e) {
					e.printStackTrace();
				}
    		}
    		if(time > 50) {
    	        ganador = random.nextInt(37);
    			pago = true;
    			initTime = System.currentTimeMillis();
    		}
    	}
    }
    
    public long getTime() {
    	return time;
    }
    public void actualizarApuestas() {
        for(Apuesta apuesta: apuestas) {
            if((apuesta.getNum()).equals("2to1-1")) {
                for(int numero: table.getFil3()) {
                    if(numero == ganador) {
                        apuesta.setState(true);
                    }
                }
            }
            if((apuesta.getNum()).equals("2to1-2")) {
                for(int numero: table.getFil2()) {
                    if(numero == ganador) {
                        apuesta.setState(true);
                    }
                }
            }
            if((apuesta.getNum()).equals("2to1-3")) {
                for(int numero: table.getFil1()) {
                    if(numero == ganador) {
                        apuesta.setState(true);
                    }
                }
            }
            if((apuesta.getNum()).equals("1to12")) {
                for(int numero: table.getA112()) {
                    if(numero == ganador) {
                        apuesta.setState(true);
                    }
                }
            }
            if((apuesta.getNum()).equals("13to24")) {
                for(int numero: table.getA212()) {
                    if(numero == ganador) {
                        apuesta.setState(true);
                    }
                }
            }
            if((apuesta.getNum()).equals("25to36")) {
                for(int numero: table.getA312()) {
                    if(numero == ganador) {
                        apuesta.setState(true);
                    }
                }
            }
            if((apuesta.getNum()).equals("1to18")) {
                for(int numero: table.getN118()) {
                    if(numero == ganador) {
                        apuesta.setState(true);
                    }
                }
            }
            if((apuesta.getNum()).equals("19to36")) {
                for(int numero: table.getN1936()) {
                    if(numero == ganador) {
                        apuesta.setState(true);
                    }
                }
            }
            if((apuesta.getNum()).equals("even")) {
                if(ganador % 2 == 0) {
                    apuesta.setState(true);
                }
            }
            if((apuesta.getNum()).equals("odd")) {
                if(ganador % 2 != 0) {
                    apuesta.setState(true);
                }
            }
            if((apuesta.getNum()).equals("red")) {
                for(int numero: table.getRed()) {
                    if(numero == ganador) {
                        apuesta.setState(true);
                    }
                }
            }
            if((apuesta.getNum()).equals("black")) {
                for(int numero: table.getBlack()) {
                    if(numero == ganador) {
                        apuesta.setState(true);
                    }
                }
            }
           if((apuesta.getNum()).equals(String.valueOf(ganador))) {
               apuesta.setState(true);
           }
        }
    }
    
    
    public void pay() throws RuletaPersistenceException, SQLException{
        actualizarApuestas();
        RuletaDB ruletaDB = new RuletaDB();
        for(Apuesta apuesta: apuestas) {
            if(apuesta.getState()) {
                ruletaDB.realizaConexion();
                ruletaDB.actualizarApuesta(apuesta.getUser(), apuesta.getValor());
            }
            else {
                ruletaDB.realizaConexion();
                ruletaDB.actualizarApuesta(apuesta.getUser(), -1*apuesta.getValor());
            }
        }
        pago = false;
        limpieza();
    }
    public void limpieza() {
        apuestas.clear();
    }
    public Game getRuleta() {
        if (ruleta == null){
            ruleta = new Game();
        }
        return ruleta;
    }

    public Random getRandom() {
        return random;
    }
    public void setRandom(Random random) {
        this.random = random;
    }
    public int getGanador() {
		return ganador;
	}
	public Tablero getTable() {
        return table;
    }
    public void setTable(Tablero table) {
        this.table = table;
    }
    public void setGanador(int ganador) {
		this.ganador = ganador;
	}

    public void addApuesta(Apuesta apuesta) {
        apuestas.add(apuesta);
    }

}
