package vega.algorithms;

import vega.Controller;
import vega.graph.Graph;

public interface GraphAlgorithm {
	
    void run(Graph g);
        
    String getFileName();
        
    Controller getController();
        
    int getInstanceID();
        
    boolean isRunning();
	
}
