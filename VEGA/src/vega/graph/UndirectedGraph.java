package vega.graph;

import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.Vertex;

public class UndirectedGraph<V extends Vertex<? extends UndirectedEdge>> extends AbstractGraph<V, UndirectedEdge>{

	public UndirectedGraph(int maxVertexSize){
		super(maxVertexSize, false);
	}
	
	public UndirectedGraph(int maxVertexSize, int maxEdgeSize){
		super(maxVertexSize, maxEdgeSize, false);
	}
	
}
