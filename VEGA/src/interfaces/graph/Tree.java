/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph;

import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.tree.TreeNode;

/**
 *
 * @author w_jossey
 */
public interface Tree<C extends Comparable<C>, T extends TreeNode<C,T,E>, E extends Edge> extends Graph<T,E>{
	
	/**
	 * Sets the root node of the graph.
	 * @param v Node to set as root
	 */
	public void setRoot(T v);
	
	/**
	 * Returns the root vertex of the Tree.
	 * @return The root node.
	 */
	public T getRoot();

}