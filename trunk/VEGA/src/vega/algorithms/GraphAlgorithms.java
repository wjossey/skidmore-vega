package vega.algorithms;

import interfaces.algorithms.GraphAlgorithm;
import interfaces.graph.Graph;
import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;

public class GraphAlgorithms<V extends Vertex<V,E>, E extends Edge> implements Runnable{
	/**
	 * @param args
	 */
     Graph<V, E> g;
     GraphAlgorithm<Graph<V,E>, V, E> a;
    
    public GraphAlgorithms(GraphAlgorithm<Graph<V,E>, V, E> a, Graph<V, E> g){
    	this.g = g;
        this.a = a;
    }

    public void run() {
        a.run(g);
    }

}
