/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph.edge.tree;

import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.tree.TreeNode;

/**
 *
 * @author w_jossey
 */
public interface TreeEdge extends UndirectedEdge{
	
	public TreeNode getParent();
	
	public TreeNode getChild();

}
