package vega.algorithms;

import interfaces.algorithms.GraphAlgorithm;
import interfaces.graph.Graph;
import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;

/**
 * 
 * @author w_jossey
 *
 * @param <V>
 * @param <E>
 */
public class GraphAlgorithms<V extends Vertex<E>, E extends Edge> implements Runnable{
	/**
	 * @param args
	 */
     Graph<V, E> g;
     GraphAlgorithm<Graph<V,E>, V, E> a;
    
    /**
     * 
     * @param a
     * @param g
     */
    public GraphAlgorithms(GraphAlgorithm<Graph<V,E>, V, E> a, Graph<V, E> g){
    	this.g = g;
        this.a = a;
    }

    /**
     * 
     */
    public void run() {
        a.run(g);
    }

}
