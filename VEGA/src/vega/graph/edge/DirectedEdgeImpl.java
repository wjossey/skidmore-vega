/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.graph.edge;

import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.vertex.Vertex;

/**
 * A directed edge is an edge that has a source vertex and a destination vertex.
 * This prevents bi-directional movement by an algorithm.  
 * @author w_jossey
 */
public class DirectedEdgeImpl extends EdgeImpl{
    Vertex<? extends DirectedEdge> from = null; 
    Vertex<? extends DirectedEdge> to = null;
    double weight;
    
    /**
     * Creates a directed edge with no edge-weight.
     * @param source Source vertex
     * @param destination Destination vertex
     */
    
	public <E extends DirectedEdge> DirectedEdgeImpl(Vertex<E> source, Vertex<E> destination){
        super(source, destination);
        this.from = source;
        this.to = destination;
        weight = Double.NaN;
    }
    
    /**
     * Creates a directed edge with an edge weight.
     * @param source Source vertex
     * @param destination Destinatino vertex
     * @param weight The weight of the edge
     */
    public <E extends DirectedEdge> DirectedEdgeImpl(Vertex<E> source, 
    		Vertex<E> destination, double weight){
        super(source, destination, weight);
        this.from = source;
        this.to = destination;
    }
    
    /**
     * 
     * @return
     */
    public Vertex<? extends DirectedEdge> getSourceVertex(){
        return from;
    }
    
    /**
     * 
     * @return
     */
    public Vertex<? extends DirectedEdge> getDestinationVertex(){
        return to;
    }
    
    @Override
    public String toString(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
