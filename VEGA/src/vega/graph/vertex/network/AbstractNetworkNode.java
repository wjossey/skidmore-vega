package vega.graph.vertex.network;

import interfaces.graph.vertex.network.NetworkNode;
import interfaces.graph.edge.network.NetworkEdge;
import vega.graph.vertex.AbstractVertex;

/**
 * 
 * @author Weston Jossey
 *
 * @param <N>
 * @param <E>
 */
public class AbstractNetworkNode<N extends NetworkNode<E>, E extends NetworkEdge> extends AbstractVertex<E> implements NetworkNode<E>{

	/**
	 * 
	 */
	
	boolean isSink = false;
	boolean isSource = false;
	
	private static final long serialVersionUID = 3980335298056324399L;

	public boolean isSink() {
		return isSink;
	}
	
	public void isSink(boolean isSink){
		this.isSink = isSink;
	}

	public boolean isSource() {		
		return isSource;
	}
	
	public void isSource(boolean isSource){
		this.isSource = isSource;
	}

}
