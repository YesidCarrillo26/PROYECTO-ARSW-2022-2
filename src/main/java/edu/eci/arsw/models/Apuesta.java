package edu.eci.arsw.models;

public class Apuesta {
	private String num;
	private int valor;
	private Boolean state;
	private String user;

	private Apuesta apuesta;
	public Apuesta(String num, int valor, Boolean state, String user) {
		this.num=num;
		this.valor=valor;
		this.state=state;
		this.user=user;
	}
	
	public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}

	public Apuesta getApuesta(){
		if(apuesta == null){
			apuesta = new Apuesta(this.num, this.valor, this.state,this.user);
		}
		return apuesta;
	}

}
