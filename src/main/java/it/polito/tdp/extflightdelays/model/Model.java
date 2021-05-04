package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;


public class Model {
	
	Graph <Airport, DefaultWeightedEdge> grafo;
	ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO ();
	
	
	public void creaGrafo (double x) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		List<Airport> aeroporti = dao.loadAllAirports();
		Map<Integer, Airport> aeroportiMap = new HashMap <> ();
		for(Airport a : aeroporti) {
			aeroportiMap.put(a.getId(), a);
		}
		
		Graphs.addAllVertices(this.grafo, aeroporti);
		
		List <Connessione> archi = dao.allConnessioni(x);
		
		for (Connessione c : archi) {
			DefaultWeightedEdge e = this.grafo.getEdge(aeroportiMap.get(c.getAirportOrigine().getId()), aeroportiMap.get(c.getAirportDestinazione().getId()));
			
			if( this.grafo.containsEdge(aeroportiMap.get(c.getAirportOrigine().getId()), aeroportiMap.get(c.getAirportDestinazione().getId()))  ){
				 double pesoVecchio = this.grafo.getEdgeWeight(e);
				 double pesoNuovo = (pesoVecchio + c.getPeso())/2;
				 this.grafo.setEdgeWeight(e, pesoNuovo);
			}
			
			else {
				Graphs.addEdgeWithVertices(this.grafo, aeroportiMap.get(c.getAirportOrigine().getId()), aeroportiMap.get(c.getAirportDestinazione().getId()), c.getPeso());
			}
			// aeroportiMap.get(c.getAirportOrigine().getId()), aeroportiMap.get(c.getAirportDestinazione().getId())
			
		}
	}
	
	public List<Airport> loadAllAirports () {
		return dao.loadAllAirports();
	}
	
	public List<Connessione> loadAllConnessioni (double x) {
		return dao.allConnessioni(x);
	}

	public int getNArchi () {
		return this.grafo.edgeSet().size();
	}
	
	public List<Connessione> getConnessioniGiuste (){
		List<Connessione> result = new ArrayList<>();
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			Connessione c = new Connessione (this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e), this.grafo.getEdgeWeight(e));
			result.add(c);
		}
		return result;
	}
	
	
	
}
