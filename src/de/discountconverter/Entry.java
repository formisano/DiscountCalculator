package de.discountconverter;

import java.text.DecimalFormat;

public class Entry {
	
	private long id;
	private double preis;
	private int rabatt;
	private double ersparnis;
	private double nPreis;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public double getPreis() {
		return preis;
	}
	
	public void setPreis(double preis) {
		this.preis = preis;
	}
	
	public int getRabatt() {
		return rabatt;
	}
	
	public void setRabatt(int rabatt) {
		this.rabatt = rabatt;
	}
	
	public double getErsparnis() {
		return ersparnis;
	}
	
	public void setErsparnis(double ersparnis) {
		this.ersparnis = ersparnis;
	}
	
	public double getNeuerPreis() {
		return nPreis;
	}
	
	public void setNeuerPreis(double neuerPreis) {
		this.nPreis = neuerPreis;
	}
	

	public String toString(){
		DecimalFormat df = new DecimalFormat("#.##");
		
		String s1 = df.format(preis);
		String s2 = df.format(ersparnis);
		String s3 = df.format(nPreis);
		
		return String.format("\n Preis: %s €\n"+
				" Rabatt: %d %% \n"+
				" Ersparnis: %s € \n"+
				" neuer Preis: %s € \n ", s1, rabatt, s2, s3);	
	}
}
