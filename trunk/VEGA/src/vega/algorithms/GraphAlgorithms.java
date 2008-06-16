package vega.algorithms;

import interfaces.algorithms.GraphAlgorithm;
import interfaces.graph.Graph;
import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;

public class GraphAlgorithms<V extends Vertex<E>, E extends Edge> implements Runnable{
	/**
	 * @param args
	 */
         Graph<V, E> g;
         GraphAlgorithm<V, E> a;
    
         public GraphAlgorithms(GraphAlgorithm<V, E> a, Graph<V, E> g){
             this.g = g;
             this.a = a;
         }

    public void run() {
        a.run(g);
    }

}
