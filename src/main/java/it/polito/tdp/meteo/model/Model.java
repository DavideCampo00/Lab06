package it.polito.tdp.meteo.model;

import java.util.List;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	private MeteoDAO dao;
	private List<Citta> sequenza;

	public Model() {
		dao=new MeteoDAO();
	
	}
	
	

	// of course you can change the String output with what you think works best
	public List<CittaUmiditaMedia> getUmiditaMedia(int mese) {
		
		return this.dao.getUmiditaCitta(mese);
	}
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(int mese) {
		return "TODO!";
	}
	public void cerca_ricorsiva() {
		
	}
	

}
