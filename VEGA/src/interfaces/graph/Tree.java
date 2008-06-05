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
	
	public void setRoot(TreeNode v);
	
	public TreeNode getRoot();

}
