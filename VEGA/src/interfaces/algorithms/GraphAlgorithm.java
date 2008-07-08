package interfaces.algorithms;

import vega.Controller;
import interfaces.graph.Graph;
import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;

/**
 * 
 * @author Weston Jossey
 *
 * @param <G> Class implements Graph
 * @param <V> Class implements Vertex
 * @param <E> Class implements Edge
 */
public interface GraphAlgorithm<G extends Graph<V,E>, V extends Vertex<E>, E extends Edge> {
	
	/**
	 * Method executes the algorithm that implements the interface.
	 * @param g Graph to execute the algorithm on.
	 */
    public void run(G g);
       
    /**
     * Gets the filename to store the images.
     * @return Returns the filename as a string.
     */
    public String getFileName();
     
    /**
     * Gets the controller for the Algorithm.
     * @return Returns the controller.
     */
    public Controller<V, E> getController();
     
    /**
     * Returns the instanceID of the algorithm, so multiple instances can co-execute.
     * @return The instance ID
     */
    public int getInstanceID();
     
    /**
     * Returns true if the algorithm is still running, false if the algorithm is not executing.
     * @return Returns true if running, false if not executing.
     */
    public boolean isRunning();
	
}
