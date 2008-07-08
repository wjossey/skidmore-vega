package interfaces.graph.edge.network;

import interfaces.graph.vertex.network.NetworkNode;

/**
 * 
 * @author w_jossey
 *
 * @param <N>
 */
public interface DirectedNetworkEdge<N extends NetworkNode<? super DirectedNetworkEdge<? super N>>> extends NetworkEdge{

	/**
	 * Gets the source vertex of the edge.
	 * @return Source vertex of the edge.
	 */
	public N getSource();
	
	/**
	 * Gets the destination vertex of the edge.
	 * @return Destination vertex of the edge.
	 */
	public N getDestination();
	
}
