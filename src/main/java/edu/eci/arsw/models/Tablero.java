package edu.eci.arsw.models;

public class Tablero {
	public int[][] table;
	public int[] fil1;
	public int[] fil2;
	public int[] fil3;
	public int[] n118;
	public int[] n1936;
	public int[] a112;
	public int[] a212;
	public int[] a312;
	public int[] red;
	public int[] black;
    public Tablero(){
		int [][] table ={{0, 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, -1}, 
						{0, 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35, -2}, 
						{0, 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34, -3},
						{0, -5, -5, -5, -5, -6, -6, -6, -6, -7, -7, -7, -7, -50},
						{0, -8, -8, -9, -9, -10, -10, -11, -11, -12, -12, -13, -13 -50}};
		this.table=table;
		int [] n118 = {1, 2 ,3 ,4 ,5 ,6 ,7 , 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
		int [] n1936 = {19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36};
		int[] a112 = {1, 4, 7, 10, 11, 8, 5, 2, 3, 6, 9, 12};
		int[] a212 = {13, 16, 19, 22, 23, 20, 17, 14, 15, 18, 21, 24};
		int[] a312 = {25, 28, 31, 34, 35, 32, 29, 26, 27, 30, 33, 36};
	    int[] red = {32,19,21,25,34,27,36,30,23,5,16,1,14,9,18,7,12,3};
	    int[] black = {6,2,4,8,11,10,13,15,17,20,22,24,26,29,28,33,31,35};
		this.n118=n118;
		this.n1936=n1936;
		this.a112=a112;
		this.a212=a212;
		this.a312=a312;
		this.red=red;
		this.black=black;
	    fil1 = table[0];
	    fil2 = table[1];
	    fil3 = table[2];
	}
    public int[] getRed() {
        return red;
    }
    public int[] getBlack() {
        return black;
    }
    public void setBlack(int[] black) {
        this.black = black;
    }
    public void setRed(int[] red) {
        this.red = red;
    }
	public int[] getN118() {
        return n118;
    }
    public void setN118(int[] n118) {
        this.n118 = n118;
    }
    public int[] getN1936() {
        return n1936;
    }
    public void setN1936(int[] n1936) {
        this.n1936 = n1936;
    }
    public int[] getA112() {
        return a112;
    }
    public void setA112(int[] a112) {
        this.a112 = a112;
    }
    public int[] getA212() {
        return a212;
    }
    public void setA212(int[] a212) {
        this.a212 = a212;
    }
    public int[] getA312() {
        return a312;
    }
    public void setA312(int[] a312) {
        this.a312 = a312;
    }
    public int[][] getTable() {
		return table;
	}
	public int[] getFil1() {
        return fil1;
    }
    public void setFil1(int[] fil1) {
        this.fil1 = fil1;
    }
    public int[] getFil2() {
        return fil2;
    }
    public void setFil2(int[] fil2) {
        this.fil2 = fil2;
    }
    public int[] getFil3() {
        return fil3;
    }
    public void setFil3(int[] fil3) {
        this.fil3 = fil3;
    }
    public void setTable(int[][] table) {
		this.table = table;
	}
	
}
