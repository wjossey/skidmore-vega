package interfaces.graph.edge.network;

import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.network.NetworkNode;

public interface UndirectedNetworkEdge<N extends NetworkNode<N,UndirectedNetworkEdge<N>>> extends UndirectedEdge<N>, NetworkEdge{

}
