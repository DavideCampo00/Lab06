package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.CittaUmiditaMedia;
import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione WHERE Localita=? AND MONTH(Data)=?  ";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,localita);
			st.setInt(2, mese);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public Integer getUmiditaLocalitaMeseGiorno(int mese,int giorno,String localita) {

		final String sql = "SELECT Umidita FROM situazione WHERE Localita=? AND MONTH(Data)=? AND DAY(Data)=? ";
		int result=0;

		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,localita);
			st.setInt(2, mese);
			st.setInt(3, giorno);

			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				result= rs.getInt("Umidita");
			}

			

			conn.close();
			return result;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
		
		
		
	
	
	public List<CittaUmiditaMedia> getUmiditaMedia(int mese){
		final String sql = "SELECT Localita, AVG(Umidita) AS mediaUmidita "
				+ "FROM situazione "
				+ "WHERE MONTH(Data)=? "
				+ "GROUP BY Localita ";

		List<CittaUmiditaMedia> rilevamentiMedi = new ArrayList<CittaUmiditaMedia>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, mese);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				CittaUmiditaMedia c=new CittaUmiditaMedia(new Citta(rs.getString("Localita")), rs.getDouble("mediaUmidita"));
				rilevamentiMedi.add(c);
			}

			conn.close();
			return rilevamentiMedi;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public Set<Citta> getCitta() {

		final String sql = "SELECT DISTINCT Localita FROM situazione  ";

		Set<Citta> setCitta = new HashSet<Citta>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Citta citta=new Citta(rs.getString("Localita"));
				setCitta.add(citta);
			}

			conn.close();
			return setCitta;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	


}
