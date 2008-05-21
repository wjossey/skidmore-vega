package vega.algorithms;

import vega.algorithms.GraphAlgorithm;
import interfaces.graph.Graph;

public class GraphAlgorithms implements Runnable{
	/**
	 * @param args
	 */
         Graph g;
         GraphAlgorithm a;
    
         public GraphAlgorithms(GraphAlgorithm a, Graph g){
             this.g = g;
             this.a = a;
         }

    public void run() {
        a.run(g);
    }

}
