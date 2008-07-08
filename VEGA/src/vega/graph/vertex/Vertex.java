package vega.graph.vertex;

import java.util.ArrayList;

import interfaces.graph.edge.Edge;

/**
 * 
 * @author Weston Jossey
 *
 * @param <E>
 */
public class Vertex<E extends Edge> extends AbstractVertex<E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Vertex(String name){
		super(name);
	}
	
	public Vertex(){
		super();
	}
	
	public Vertex(int x, int y){
		super(x, y);
	}
	
	public Vertex(int x, int y, ArrayList<E> edgeList){
		super(x, y, edgeList);
	}

}
