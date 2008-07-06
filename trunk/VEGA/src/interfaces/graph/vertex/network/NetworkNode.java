package interfaces.graph.vertex.network;

import interfaces.graph.vertex.Vertex;
import interfaces.graph.edge.network.NetworkEdge;

public interface NetworkNode<N extends NetworkNode<N,E>, E extends NetworkEdge> extends Vertex<N,E>{
	
	public boolean isSource();
	
	public void isSource(boolean isSource);
	
	public boolean isSink();
	
	public void isSink(boolean isSink);

}
