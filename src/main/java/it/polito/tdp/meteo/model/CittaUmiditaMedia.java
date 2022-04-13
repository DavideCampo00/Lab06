package it.polito.tdp.meteo.model;

public class CittaUmiditaMedia {
	private String citta;
	private double umiditaMedia;
	
	public CittaUmiditaMedia(String citta, double umiditaMedia) {
		super();
		this.citta = citta;
		this.umiditaMedia = umiditaMedia;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
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
