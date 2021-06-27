package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	
	private Graph<Actor, DefaultWeightedEdge> grafo;
	private ImdbDAO dao;
	private Map<Integer, Actor> verticiIdMap ;
	
	public String creaGrafo(String g) {
		
		dao = new ImdbDAO();
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.verticiIdMap = new HashMap<>() ;
		
		for(Actor a : this.dao.listAllActorsGenere(g)) {
			this.verticiIdMap.put(a.id, a);
		}
		
		Graphs.addAllVertices(this.grafo, this.dao.listAllActorsGenere(g));
		
		List<Adiacenze> archi = dao.listAdiacenze(g);
		for(Adiacenze arco : archi) {
			Graphs.addEdge(this.grafo,
					this.verticiIdMap.get(arco.getA1()),
					this.verticiIdMap.get(arco.getA2()), 
					(double)arco.getP());
		}
		
		
		return String.format("Grafo creato!\n#vertici: %d\n#archi: %d\n\n" , this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
		
	}

	public List<String> getAllGenere() {
		dao = new ImdbDAO();
		return this.dao.listAllGenere();
	}

	public List<Actor> getVicini(Actor scelto) {
		if(!grafo.vertexSet().contains(scelto))
			throw new RuntimeException("L'attore scelto non fa parte del grafo");
		List <Actor> vicini = new ArrayList <Actor>();
		
		//BreadthFirstIterator
		GraphIterator<Actor,DefaultWeightedEdge> bfi = new BreadthFirstIterator <Actor,DefaultWeightedEdge> (grafo ,scelto);
		while(bfi.hasNext()) 
				vicini.add(bfi.next());
		
		return vicini;
	}

	public List<Actor> getActorGenere(String g) {
		return dao.listAllActorsGenere(g);
	}
}
