/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.graph.edge;

import interfaces.graph.vertex.Vertex;

/**
 * A directed edge is an edge that has a source vertex and a destination vertex.
 * This prevents bi-directional movement by an algorithm.  
 * @author w_jossey
 */
public class DirectedEdge<V extends Vertex<? extends interfaces.graph.edge.DirectedEdge<V>>> extends AbstractEdge<V>{
    V from = null; 
    V to = null;
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
     * 
     * @return
     */
    public V getSourceVertex(){
        return from;
    }
    
    /**
     * 
     * @return
     */
    public V getDestinationVertex(){
        return to;
    }
    
    @Override
    public String toString(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
  
}
