package interfaces.graph.edge.network;

import interfaces.graph.vertex.network.NetworkNode;

/**
 * 
 * @author w_jossey
 *
 * @param <N>
 */
public interface UndirectedNetworkEdge<N extends NetworkNode<? extends UndirectedNetworkEdge<? extends N>>> extends NetworkEdge{

	public N getVertexA();
	
	public N getVertexB();
	
}
