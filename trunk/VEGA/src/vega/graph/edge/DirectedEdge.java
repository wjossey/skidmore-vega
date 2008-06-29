/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.graph.edge;

import interfaces.graph.vertex.Vertex;

/**
 * A directed edge is an edge that has a source vertex and a destination vertex.
 * This prevents bi-directional movement by an algorithm on the edge.  The visibility
 * of a vertex from one to another is limited by whether it has the opposite vertex
 * as a destination of one of its edges.  
 * @author w_jossey
 */
public class DirectedEdge<V extends Vertex<V,interfaces.graph.edge.DirectedEdge<V>>> extends AbstractEdge implements interfaces.graph.edge.DirectedEdge<V>{
    protected V from = null; 
    protected V to = null;
    double weight;
    
    /**
     * Creates a directed edge with no edge-weight.
     * @param source Source vertex
     * @param destination Destination vertex
     */
	public DirectedEdge(V source, V destination){
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
    public DirectedEdge(V source, V destination, double weight){
        this.from = source;
        this.to = destination;
    }
    
    /**
     * Get the source vertex of the edge.
     * @return Source vertex
     */
    public V getSource(){
        return from;
    }
    
    /**
     * Get the destination vertex of the edge.
     * @return Destination vertex
     */
    public V getDestination(){
        return to;
    }
   
    public String toString(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
