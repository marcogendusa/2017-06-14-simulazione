package it.polito.tdp.artsmia.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private ArtsmiaDAO dao;
	private Graph<Exhibition, DefaultEdge> grafo;
	private List<ArtObject> opere;
	private ObjectIDMap omap;
	private List<Exhibition> mostre;
	private ExhibitionIDMap emap;


	public Model() {
		this.dao = new ArtsmiaDAO();
		this.omap = new ObjectIDMap();
		this.opere = dao.listObject(omap);
		this.emap = new ExhibitionIDMap();
	}
	
	public List<Integer> getYears() {
		return dao.getYears();
	}
	
	public void creaGrafo(int year) {
		this.grafo = new SimpleDirectedGraph<Exhibition, DefaultEdge>(DefaultEdge.class);
		this.mostre = dao.getAllExhibitions(emap, year);
		Graphs.addAllVertices(this.grafo, this.mostre);
		
		for(Exhibition e1: this.mostre) {
			for(Exhibition e2: this.mostre) {
				if(!e1.equals(e2)) {
					if(e1.getBeginYear()<e2.getBeginYear() && e1.getEndYear()>e2.getEndYear())
						grafo.addEdge(e1, e2);
				}
			}
		}
	}
	
	
	public Exhibition getBiggestExhibition(int year) {
		int max = 0;
		Exhibition best = null;
		for(Exhibition e: grafo.vertexSet()) {
			if(dao.getBiggestExhibition(e.getId(), year) > max) {
				max = dao.getBiggestExhibition(e.getId(), year);
				best = e;
			}
		}
		return best;
	}
	
	
	

}
