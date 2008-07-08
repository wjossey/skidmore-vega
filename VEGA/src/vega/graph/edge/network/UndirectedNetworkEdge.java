package vega.graph.edge.network;

import interfaces.graph.vertex.network.NetworkNode;

/**
 * 
 * @author Weston Jossey
 *
 * @param <N>
 */
public class UndirectedNetworkEdge<N extends NetworkNode<? super interfaces.graph.edge.network.UndirectedNetworkEdge<? super N>>> extends AbstractNetworkEdge implements interfaces.graph.edge.network.UndirectedNetworkEdge<N>{
	N a;
	N b;
	
	public UndirectedNetworkEdge(){
		super();
		a = null;
		b = null;
		
	}
	
	public UndirectedNetworkEdge(N a, N b){
		super();
		this.a = a;
		this.b = b;
	}
	
	public UndirectedNetworkEdge(N a, N b, double capacity){
		super(capacity);
		this.a = a;
		this.b = b;
	}
	
	public UndirectedNetworkEdge(N a, N b, double capacity, double initialFlow){
		super(capacity, initialFlow);
		this.a = a;
		this.b = b;
	}

	public N getVertexA() {
		return a;
	}

	public N getVertexB() {
		return b;
	}
	
	
	
	

}
