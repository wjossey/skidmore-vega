/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph.edge;

import interfaces.graph.vertex.Vertex;

/**
 *
 * @author w_jossey
 */
public interface DirectedEdge extends Edge{
	
	/**
	 * Gets the source vertex of the edge.
	 * @return Source vertex of the edge.
	 */
	public Vertex<? extends DirectedEdge> getSource();
	
	/**
	 * Gets the destination vertex of the edge.
	 * @return Destination vertex of the edge.
	 */
	public Vertex<? extends DirectedEdge> getDestination();

}
