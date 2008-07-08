package vega.graph;

import java.util.ArrayList;

import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.vertex.Vertex;

/**
 * 
 * @author Weston Jossey
 *
 * @param <V>
 * @param <E>
 */
public class DiGraph<V extends Vertex<? extends DirectedEdge>> extends AbstractGraph<V,DirectedEdge>{

	public DiGraph(){
		
	}
	
	public DiGraph(int size){
		super(size, true);
	}
	
	public DiGraph(V[] vertexArray, DirectedEdge[] edgeArray){
		super(vertexArray, edgeArray);
	}
	
	public DiGraph(ArrayList<V> vertexList, ArrayList<DirectedEdge> edgeList){
		super(vertexList, edgeList);
	}
	
	public DiGraph(int vertexSize, int edgeSize){
		//Auto generate vertices and edges.
		super(vertexSize, edgeSize, true);
	}	
	
}
