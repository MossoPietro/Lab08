package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private Graph<Airport, DefaultWeightedEdge> grafo;
	private ExtFlightDelaysDAO dao;
	private Map<Integer, Airport> idMap;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
		idMap = new HashMap<Integer, Airport>();
		idMap = dao.loadAllAirports();
	}
	
	public void creaGrafo (int distanzaMedia) {
		this.grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, idMap.values());
		
		List<Rotta> rotte = dao.getRotte(idMap);
		for (Rotta r : rotte) {
			DefaultWeightedEdge edge = grafo.getEdge(r.getPartenza(), r.getArrivo());
			
			if (edge == null) {
				if (r.getDistanza() > distanzaMedia) {
					Graphs.addEdge(grafo, r.getPartenza(), r.getArrivo(), r.getDistanza());
				}
			} else {
				double pesoAggiornato = (r.getDistanza() + grafo.getEdgeWeight(edge)) / 2;
				if (pesoAggiornato > distanzaMedia) {
					grafo.setEdgeWeight(edge, pesoAggiornato);
				}
			}
			
		}
		
		
	}
	
	public int getNumeroVerticiGrafo() {
		return grafo.vertexSet().size();
	}
	
	public int getNumeroArchiGrafo() {
		return grafo.edgeSet().size();
	}
	
	public List<Rotta> getArchi() {
		List<Rotta> risultato = new ArrayList<Rotta>();
		for (DefaultWeightedEdge e : grafo.edgeSet()) {
			risultato.add(new Rotta(grafo.getEdgeSource(e), grafo.getEdgeTarget(e), grafo.getEdgeWeight(e)));
		}
		return risultato;
	}

}
