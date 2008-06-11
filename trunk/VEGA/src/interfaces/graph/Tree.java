/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph;

import interfaces.graph.vertex.tree.TreeNode;

/**
 *
 * @author w_jossey
 */
public interface Tree extends Graph{
	
	/**
	 * Sets the root node of the graph.
	 * @param v Node to set as root
	 */
	public void setRoot(TreeNode v);
	
	/**
	 * Returns the root vertex of the Tree.
	 * @return The root node.
	 */
	public TreeNode getRoot();

}
