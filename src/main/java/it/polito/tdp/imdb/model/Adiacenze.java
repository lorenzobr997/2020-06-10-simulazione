package it.polito.tdp.imdb.model;

public class Adiacenze {
	
	private int a1;
	private int a2;
	private int p;
	
	public Adiacenze(int a1, int a2, int p) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.p = p;
	}

	public int getA1() {
		return a1;
	}

	public void setA1(int a1) {
		this.a1 = a1;
	}

	public int getA2() {
		return a2;
	}

	public void setA2(int a2) {
		this.a2 = a2;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	

}
