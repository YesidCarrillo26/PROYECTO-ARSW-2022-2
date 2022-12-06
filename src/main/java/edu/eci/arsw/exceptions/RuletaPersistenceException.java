package edu.eci.arsw.exceptions;

public class RuletaPersistenceException extends Exception{

    public RuletaPersistenceException(String message) {
        super(message);
    }

    public RuletaPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
