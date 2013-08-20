package de.discountconverter;

public class Bruch {
	
	int z�hler;
	int nenner;
	
	public Bruch(int z�hler, int nenner){
		this.z�hler = z�hler;
		this.nenner = nenner;
	}

	private void k�rzen(int z�hler, int nenner) {
		nenner = nenner/ggt();
		z�hler = z�hler/ggt();
		
	}

	private int ggt() {
		int ergebnis = 1;
		int a = nenner;
		int b = z�hler;
		while(b != 0){
			ergebnis = a % b;
			a = b;
			b = ergebnis;
		}
		return a;
	}

	public int getZ�hler() {
		return z�hler;
	}

	public void setZ�hler(int z�hler) {
		this.z�hler = z�hler;
	}

	public int getNenner() {
		return nenner;
	}

	public void setNenner(int nenner) {
		this.nenner = nenner;
	}

}
