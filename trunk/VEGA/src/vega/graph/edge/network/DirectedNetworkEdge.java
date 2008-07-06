package vega.graph.edge.network;

import interfaces.graph.vertex.network.NetworkNode;

public class DirectedNetworkEdge<N extends NetworkNode<N, interfaces.graph.edge.network.DirectedNetworkEdge<N>>> extends AbstractNetworkEdge implements interfaces.graph.edge.network.DirectedNetworkEdge<N>{

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
