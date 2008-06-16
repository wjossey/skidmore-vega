/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.graph.edge;

import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.Vertex;

/**
 * The UndirectedEdge class is actually just a repeat of the Edge class.  By default
 * an edge is said to be undirected, so this class is actually just a placeholder so
 * that developers can easily distinguish if an edge is directed or undirected.
 * @author w_jossey
 */
public class UndirectedEdgeImpl extends EdgeImpl implements UndirectedEdge{
    
    /**
     * See Edge class for documentation.
     * @param a
     * @param b
     */
	
	Vertex<? extends UndirectedEdge> vertexA = null;
	Vertex<? extends UndirectedEdge> vertexB = null;
	
    public <E extends UndirectedEdge> UndirectedEdgeImpl(Vertex<E> a, Vertex<E> b){
        super(a, b);
    }
    
    /**
     * See Edge class for documentation.
     * @param a
     * @param b
     * @param weight
     */
    public <E extends UndirectedEdge> UndirectedEdgeImpl(Vertex<E> a, Vertex<E> b, double weight){
        super(a, b, weight);
    }

    
	@SuppressWarnings("unchecked")
	public Vertex<? extends UndirectedEdge> getVertexA() {
		return vertexA;
	}

	@SuppressWarnings("unchecked")
	public Vertex<? extends UndirectedEdge> getVertexB() {
		return vertexB;
	}
    
    
    
}
