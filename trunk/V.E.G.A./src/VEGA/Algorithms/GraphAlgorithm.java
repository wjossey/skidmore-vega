package VEGA.Algorithms;

import VEGA.Controller;
import VEGA.Graph.Graph;

public interface GraphAlgorithm {
	
    void run(Graph g);
        
    String getFileName();
        
    Controller getController();
        
    int getInstanceID();
        
    boolean isRunning();
	
}
