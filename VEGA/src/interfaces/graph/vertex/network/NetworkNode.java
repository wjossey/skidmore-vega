package interfaces.graph.vertex.network;

import interfaces.graph.vertex.Vertex;
import interfaces.graph.edge.network.NetworkEdge;

/**
 * 
 * @author w_jossey
 *
 * @param <E>
 */
public interface NetworkNode<E extends NetworkEdge> extends Vertex<E>{
	
	public boolean isSource();
	
	public void isSource(boolean isSource);
	
	public boolean isSink();
	
	public void isSink(boolean isSink);

}
