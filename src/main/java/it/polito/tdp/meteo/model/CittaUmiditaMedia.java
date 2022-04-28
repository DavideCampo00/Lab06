package it.polito.tdp.meteo.model;

public class CittaUmiditaMedia {
	private Citta citta;
	private double umiditaMedia;
	
	public CittaUmiditaMedia(Citta citta, double umiditaMedia) {
		super();
		this.citta = citta;
		this.umiditaMedia = umiditaMedia;
	}

	public Citta getCitta() {
		return citta;
	}

	public void setCitta(Citta citta) {
		this.citta = citta;
	}

	public double getUmiditaMedia() {
		return umiditaMedia;
	}

	public void setUmiditaMedia(double umiditaMedia) {
		this.umiditaMedia = umiditaMedia;
	}

	@Override
	public String toString() {
		return citta +" "+ umiditaMedia+"\n" ;
	}
	
	
	

}
