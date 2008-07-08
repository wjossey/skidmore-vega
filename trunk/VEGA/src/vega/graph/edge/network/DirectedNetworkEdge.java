package vega.graph.edge.network;

import interfaces.graph.vertex.network.NetworkNode;

/**
 * 
 * @author Weston Jossey
 *
 * @param <N>
 */
public class DirectedNetworkEdge<N extends NetworkNode<? extends interfaces.graph.edge.network.DirectedNetworkEdge<? extends N>>> extends AbstractNetworkEdge implements interfaces.graph.edge.network.DirectedNetworkEdge<N>{

	N source;
	N destination;
	
	public DirectedNetworkEdge(){
		super();
		source = null;
		destination = null;
	}
	
	public DirectedNetworkEdge(N source, N destination){
		super();
		this.source = source;
		this.destination = destination;
	}
	
	public DirectedNetworkEdge(N source, N destination, double capacity){
		super(capacity);
		this.source = source;
		this.destination = destination;
	}
	
	public DirectedNetworkEdge(N source, N destination, double capacity, double initialFlow){
		super(capacity, initialFlow);
		this.source = source;
		this.destination = destination;
	}
	
	public N getDestination() {
		
		return destination;
	}

	public N getSource() {
		
		return source;
	}

}
