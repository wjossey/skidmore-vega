/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph;

import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;

/**
 *
 * @author w_jossey
 */
public interface Network<V extends Vertex<? extends Edge>>{
	
	public void setSources(V[] v);
	
	public V[] getSources();
	
	public void setSinks(V[] v);
	
	public V[] getSinks();

}
