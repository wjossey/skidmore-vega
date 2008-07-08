/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph;

import java.util.ArrayList;

import interfaces.graph.vertex.network.NetworkNode;
import interfaces.graph.edge.Edge;

/**
 * 
 * @author w_jossey
 *
 * @param <N>
 * @param <E>
 */
public interface Network<N extends NetworkNode<? extends Edge>>{
	
	/**
	 * Sets the sources of the network.
	 * @param v ArrayList of the sources
	 */
	public void setSources(ArrayList<N> v);
	
	/**
	 * Returns the sources of the network.
	 * @return ArrayList of the sources.
	 */
	public ArrayList<N> getSources();
	
	/**
	 * Sets the sinks of the network.
	 * @param v ArrayList of the sinks.
	 */
	public void setSinks(ArrayList<N> v);
	
	/**
	 * Gets the sinks of the network.
	 * @return ArrayList of the sinks.
	 */
	public ArrayList<N> getSinks();

}
