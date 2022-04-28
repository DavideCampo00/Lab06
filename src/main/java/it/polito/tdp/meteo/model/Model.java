package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	private MeteoDAO dao;
	private List<Citta> result;
	private double min;
	private Set<Citta> setCitta;
	private int mese;

	public Model() {
		dao = new MeteoDAO();
		result = new ArrayList<Citta>();
		setCitta = this.getCitta();

	}

	// of course you can change the String output with what you think works best
	public List<CittaUmiditaMedia> getUmiditaMedia(int mese) {

		return this.dao.getUmiditaMedia(mese);
	}

	// of course you can change the String output with what you think works best
	public List<Citta> trovaSequenza(int mese) {
		this.mese = mese;
		List<Citta> parz = new ArrayList<Citta>();
		min = 1000000000;
		cerca_ricorsiva(parz, 0);
		return result;

	}

	public void cerca_ricorsiva(List<Citta> parziale, int livello) {
		if (livello == NUMERO_GIORNI_TOTALI) {
			for (Citta c : parziale) {
				if (c.getCounter() > 6)
					return;
			}
			for (Citta c : parziale) {
				if (c.getCounter() == 0)
					return;
			}

			double costo = calcolaCosto(parziale);
			if (costo < min) {
				min = costo;
				result = new ArrayList<Citta>(parziale);
			}

			return;
		} else {
			for (Citta c : setCitta) {
				boolean b = false;
				if (parziale.size() == 0) {
					parziale.add(c);
					c.increaseCounter();
					b = true;
				} else if (parziale.size() == 1 || parziale.size() == 2) {
					if (c.getNome().equals(parziale.get(parziale.size() - 1).getNome())) {
						parziale.add(c);
						c.increaseCounter();
						b = true;
					}
				} else if (parziale.size() >= 3 && parziale.size() < 15) {

					if (c.getCounter() < NUMERO_GIORNI_CITTA_MAX) {
						if (c.getNome().equals(parziale.get(parziale.size() - 1).getNome())) {
							parziale.add(c);
							c.increaseCounter();
							b = true;
						} else {
							if (parziale.get(parziale.size() - 1).getNome()
									.equals(parziale.get(parziale.size() - 2).getNome())
									&& parziale.get(parziale.size() - 2).getNome()
											.equals(parziale.get(parziale.size() - 3).getNome())) {
								parziale.add(c);
								c.increaseCounter();
								b = true;
								
							}
								
						}

					}

				} else {
					return;
				}
				if (b == true) {
					cerca_ricorsiva(parziale, livello + 1);
					parziale.get(parziale.size() - 1).decreaseCounter();
					parziale.remove(parziale.size() - 1);
				}
			}
			return;
		}

	}

	private double calcolaCosto(List<Citta> parziale) {
		double costoVar = 0;
		int day = 1;
		double costoFisso = 0;
		double costoTot = 0;
		int cont = 0;
		for (int i = 0; i < parziale.size() - 1; i++) {
			for (int j = i + 1; j <= i + 1; j++) {
				if (!parziale.get(i).getNome().equals(parziale.get(j).getNome())) {
					cont++;
				}
			}
		}
		costoFisso = cont * COST;

		for (int i = 0; i < parziale.size(); i++) {
			costoVar += this.dao.getUmiditaLocalitaMeseGiorno(mese, day, parziale.get(i).getNome());
			day++;
		}
		costoTot = costoFisso + costoVar;
		return costoTot;
	}

	public Set<Citta> getCitta() {
		return this.dao.getCitta();

	}

	public Integer getUmiditaLocalitaMeseGiorno(int mese, int giorno, String localita) {
		return this.dao.getUmiditaLocalitaMeseGiorno(mese, giorno, localita);
	}

}
