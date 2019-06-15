package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	private ArtsmiaDAO dao;
	private List<Integer> anni;
	private SimpleDirectedGraph<Mostra, DefaultEdge> grafo;
	private List<Mostra> mostre;
	
	public Model() {
		this.dao = new ArtsmiaDAO();
		this.anni = new ArrayList<Integer>();
		this.mostre = new LinkedList<Mostra>();
	}
	
	public List<Integer> inserisciAnni() {
		this.anni = dao.listaAnni();
		
		return anni;
	}
	
	public List<Mostra> getMostreFromAnno(Integer anno) {
		this.mostre = dao.getMostreAnno(anno);
		
		return mostre;
	}
	
	public void creaGrafo(Integer anno) {
		this.grafo = new SimpleDirectedGraph<Mostra, DefaultEdge>(DefaultEdge.class);
		this.getMostreFromAnno(anno);
		
		Graphs.addAllVertices(grafo, mostre);
		
		for(Mostra m1 : grafo.vertexSet()) {
			for(Mostra m2 : grafo.vertexSet()) {
				if(!m1.equals(m2)) {
					if(m2.getBeginYear() > m1.getBeginYear() && m1.getEndYear() > m2.getBeginYear()) {
						grafo.addEdge(m1, m2);
					}
				}
			}
		}
		System.out.println("# vertici: " + grafo.vertexSet().size());
		System.out.println("# archi: " + grafo.edgeSet().size());
	}
	
	public String getMaxNumeroEsposizione(Integer anno) {
		String s = "";
		s = dao.getNumMaxEsposizioni(anno);
		
		System.out.println(s);
		return s;
	}
	
	public boolean isStronglyConnected() {
		KosarajuStrongConnectivityInspector<Mostra, DefaultEdge> ksci =
				new KosarajuStrongConnectivityInspector<Mostra, DefaultEdge>(grafo);
		
		return ksci.isStronglyConnected();
	}

}
