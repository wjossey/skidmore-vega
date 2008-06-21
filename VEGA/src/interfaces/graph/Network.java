/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph;

import java.util.ArrayList;

import interfaces.graph.vertex.network.NetworkNode;
import interfaces.graph.edge.network.NetworkEdge;

/**
 *
 * @author w_jossey
 */
public interface Network<N extends NetworkNode<N,E>, E extends NetworkEdge<N,E>>{
	
	/**
	 * 
	 * @param v
	 */
	public void setSources(ArrayList<N> v);
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<N> getSources();
	
	/**
	 * 
	 * @param v
	 */
	public void setSinks(ArrayList<N> v);
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<N> getSinks();

}
