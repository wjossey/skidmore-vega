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
public interface UndirectedEdge<V extends Vertex<V,UndirectedEdge<V>>> extends Edge{
	
	public V getVertexA();
	
	public V getVertexB();

}
