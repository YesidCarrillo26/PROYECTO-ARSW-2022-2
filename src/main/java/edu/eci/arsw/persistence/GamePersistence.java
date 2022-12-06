package edu.eci.arsw.persistence;

public interface GamePersistence {

    public int getNGanador();

    public void pagar();
    void apostar(String user, String numero, int valor);

	public long getTime();
}
