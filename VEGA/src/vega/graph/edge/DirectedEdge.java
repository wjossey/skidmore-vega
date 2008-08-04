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
 * @author Weston Jossey
 */
public class DirectedEdge extends AbstractEdge implements interfaces.graph.edge.DirectedEdge{
    protected Vertex<DirectedEdge> from = null; 
    protected Vertex<DirectedEdge> to = null;
    
    /**
     * Creates a directed edge with no edge-weight.
     * @param source Source vertex
     * @param destination Destination vertex
     */
	public DirectedEdge(Vertex<DirectedEdge> source, Vertex<DirectedEdge> destination){
        if(source != null && destination != null){
        	this.from = source;
            this.to = destination;
            from.addEdge(this);
            super.setWeight(Double.NaN);
        }
    }
    
    /**
     * Creates a directed edge with an edge weight.
     * @param source Source vertex
     * @param destination Destinatino vertex
     * @param weight The weight of the edge
     */
    public DirectedEdge(Vertex<DirectedEdge> source, Vertex<DirectedEdge> destination, double weight){
    	super.setWeight(weight);
    	this.from = source;
        this.to = destination;
        from.addEdge(this);
    }
    
    /**
     * Get the source vertex of the edge.
     * @return Source vertex
     */
    public Vertex<DirectedEdge> getSource(){
        return from;
    }
    
    /**
     * Get the destination vertex of the edge.
     * @return 
     * @return Destination vertex
     */
    public Vertex<DirectedEdge> getDestination(){
        return to;
    }
   
    public String toString() {
        String returnString = "";
        returnString += from.getUID(); //a -- b [shape=polygon,sides=5,peripheries=3,color=lightblue,style=filled];

        returnString += " -> ";

        returnString += to.getUID() + " ";
        returnString += "[ " + getStyle() + getFormattedWeight() +
                getColor() + ", fontsize=18];";


        return returnString;
    }
 
}
