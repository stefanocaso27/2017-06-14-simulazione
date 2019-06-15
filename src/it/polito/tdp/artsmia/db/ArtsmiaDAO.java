package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Mostra;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> listaAnni() {
		String sql = "SELECT DISTINCT (BEGIN) AS anno " + 
				"FROM exhibitions " + 
				"ORDER BY begin DESC ";
		
		List<Integer> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Integer a = res.getInt("anno");
				result.add(a);
			}
		conn.close();
		return result;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
		}
	}

	public List<Mostra> getMostreAnno(Integer anno) {
		String sql = "SELECT * " + 
				"FROM exhibitions " + 
				"WHERE `begin` >= ? ";
		
		List<Mostra> result = new ArrayList<Mostra>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				Mostra m = new Mostra(res.getInt("exhibition_id"), res.getString("exhibition_department"),
						res.getString("exhibition_title"), res.getInt("begin"), res.getInt("end"));
				
				result.add(m);
			}
		conn.close();
		return result;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
		}		
	}

	public String getNumMaxEsposizioni(Integer anno) {
		String sql = "SELECT o1.department AS dipartimento, COUNT(o1.department) AS numero " + 
				"FROM exhibitions e1, objects o1, exhibition_objects eo1 " + 
				"WHERE eo1.exhibition_id = e1.exhibition_id AND e1.exhibition_department <> 'null' " + 
				"		AND o1.department = e1.exhibition_department AND eo1.object_id = o1.object_id " + 
				"				AND e1.`begin` >= ? " + 
				"GROUP BY o1.department " + 
				"ORDER BY COUNT(o1.department) DESC " + 
				"LIMIT 1 " ;
		String s = "";
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				s = "La mostra con maggior numero di opere e': " + res.getString("dipartimento")
						+ "\nCon numero opere = " + res.getInt("numero");
			}
		conn.close();
		return s;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
		}		
	}
	
}
