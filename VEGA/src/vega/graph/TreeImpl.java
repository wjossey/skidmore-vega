/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.graph;

import interfaces.graph.Tree;
import interfaces.graph.edge.tree.TreeEdge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.vertex.tree.TreeNode;

/**
 *
 * @author w_jossey
 */
public class TreeImpl extends GraphImpl implements Tree{
    TreeNode root;
    TreeNode[] treeArray;
    TreeEdge[] edgeArray;
    
    /**
     * 
     * @param t
     * @param e
     */
    public TreeImpl(TreeNode[] t, TreeEdge[] e){
        treeArray = t;
        edgeArray = e;
    }
    
    /**
     * 
     * @param treeNodes
     */
    public TreeImpl(TreeNode[] treeNodes){
        treeArray = treeNodes;
    }
    
    /**
     * 
     * @param root
     */
    public TreeImpl(TreeNode root){
        this.root = root;
    }
    
    /**
     * Empty Constructor.  Sets the root to null;
     */
    public TreeImpl(){
        root = null;
    }
    
    /**
     * 
     * @return
     */
    public TreeNode getRoot(){
        return root;
    }
    
    public String toString(){
        return toString(root);
    }
    
    private String toString(TreeNode t){
        String returnString = "";
        returnString += t.toString();
        TreeNode[] childArray = t.getChildren();
        
        for(int i = 0; i < childArray.length; i++){
            returnString += childArray[i].toString();
            returnString += toString(childArray[i]);
        }
        
        return returnString;
    }

	public void setRoot(TreeNode v) {
		root = v;	
	}
    
}
