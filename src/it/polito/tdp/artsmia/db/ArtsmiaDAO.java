package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.ExhibitionIDMap;
import it.polito.tdp.artsmia.model.ObjectIDMap;

public class ArtsmiaDAO {

	public List<ArtObject> listObject(ObjectIDMap map) {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				ArtObject ao = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				result.add(map.get(ao));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Integer> getYears() {
			
			String sql = "SELECT DISTINCT begin\n" + 
					"FROM exhibitions\n" + 
					"ORDER by begin asc";
	
			List<Integer> result = new ArrayList<>();
	
			Connection conn = DBConnect.getConnection();
	
			try {
				PreparedStatement st = conn.prepareStatement(sql);
	
				ResultSet res = st.executeQuery();
	
				while (res.next()) {
					result.add(res.getInt("begin"));
				}
	
				conn.close();
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	
	
	public List<Exhibition> getAllExhibitions(ExhibitionIDMap map, int year) {
		String sql = "SELECT *\n" + 
				"FROM exhibitions\n" +
				"WHERE begin >= ?";

		List<Exhibition> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Exhibition e = new Exhibition(res.getInt("exhibition_id"), res.getString("exhibition_department"), res.getString("exhibition_title"), 
									res.getInt("begin"), res.getInt("end"));
				result.add(map.get(e));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	public int getBiggestExhibition(int exhibition_id, int year) {
		String sql = "SELECT COUNT(object_id) as cnt\n" + 
				"FROM exhibition_objects\n" + 
				"WHERE exhibition_id=?\n" + 
				"AND exhibition_id IN\n" + 
				"(SELECT exhibition_id\n" + 
				"FROM exhibitions\n" + 
				"WHERE begin >=?)";

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, exhibition_id);
			st.setInt(2, year);
			ResultSet res = st.executeQuery();

			if (res.next()) {
				return res.getInt("cnt");
			}

			conn.close();
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
}
