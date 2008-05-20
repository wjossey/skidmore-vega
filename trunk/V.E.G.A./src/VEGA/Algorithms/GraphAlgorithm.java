package vega.Algorithms;

import vega.Controller;
import vega.Graph.Graph;

public interface GraphAlgorithm {
	
    void run(Graph g);
        
    String getFileName();
        
    Controller getController();
        
    int getInstanceID();
        
    boolean isRunning();
	
}
