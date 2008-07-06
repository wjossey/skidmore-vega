package interfaces.graph.edge.network;

import interfaces.graph.vertex.network.NetworkNode;
import interfaces.graph.edge.DirectedEdge;

public interface DirectedNetworkEdge<N extends NetworkNode<N, DirectedNetworkEdge<N>>> extends DirectedEdge<N>, NetworkEdge{

}
