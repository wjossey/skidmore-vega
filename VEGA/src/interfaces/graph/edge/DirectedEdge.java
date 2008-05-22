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
	
	Vertex getSource();
	
	Vertex getDestination();

}
