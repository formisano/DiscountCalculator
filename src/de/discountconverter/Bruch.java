package de.discountconverter;

public class Bruch {
	
	int zähler;
	int nenner;
	
	public Bruch(int zähler, int nenner){
		this.zähler = zähler;
		this.nenner = nenner;
	}

	private void kürzen(int zähler, int nenner) {
		nenner = nenner/ggt();
		zähler = zähler/ggt();
		
	}

	private int ggt() {
		int ergebnis = 1;
		int a = nenner;
		int b = zähler;
		while(b != 0){
			ergebnis = a % b;
			a = b;
			b = ergebnis;
		}
		return a;
	}

	public int getZähler() {
		return zähler;
	}

	public void setZähler(int zähler) {
		this.zähler = zähler;
	}

	public int getNenner() {
		return nenner;
	}

	public void setNenner(int nenner) {
		this.nenner = nenner;
	}

}
