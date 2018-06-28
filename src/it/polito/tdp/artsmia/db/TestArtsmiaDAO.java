package it.polito.tdp.artsmia.db;

import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.ExhibitionIDMap;
import it.polito.tdp.artsmia.model.ObjectIDMap;

public class TestArtsmiaDAO {

	public static void main(String[] args) {
		
		ArtsmiaDAO dao = new ArtsmiaDAO() ;
		ObjectIDMap omap = new ObjectIDMap();
		ExhibitionIDMap emap = new ExhibitionIDMap();
		
		List<ArtObject> objects = dao.listObject(omap) ;
		System.out.println(objects.size());
		System.out.println(dao.getYears().toString());
		//System.out.println(dao.getAllExhibitions(emap, 1950));
		System.out.println(dao.getBiggestExhibition(3, 1950));


	}

}
