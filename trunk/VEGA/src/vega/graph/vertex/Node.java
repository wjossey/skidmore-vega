package vega.graph.vertex;

import java.util.ArrayList;

import interfaces.graph.edge.Edge;

/**
 * 
 * @author Weston Jossey
 *
 * @param <D>
 * @param <E>
 */
public class Node<D, E extends Edge> extends AbstractVertex<E> implements interfaces.graph.vertex.Node<D, E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2378871799283592258L;
	
	private D data;
	
	public Node(){
		super();
		data = null;
	}
	
	public Node(D d){
		super();
		data = d;
	}
	
	public Node(int x, int y){
		super(x,y);
		data = null;
	}
	
	public Node(int x, int y, D d){
		super(x,y);
		data = d;
	}
	
	public Node(int x, int y, ArrayList<E> edgeList){
		super(x, y, edgeList);
		data = null;
	}
	
	public Node(int x, int y, ArrayList<E> edgeList, D d){
		super(x, y, edgeList);
		data = d;
	}

	/* (non-Javadoc)
	 * @see vega.graph.vertex.NodeInterface#getData()
	 */
	public D getData(){
		return data;
	}
	
	/* (non-Javadoc)
	 * @see vega.graph.vertex.NodeInterface#setData(D)
	 */
	public void setData(D d){
		this.data = d;
	}
}
