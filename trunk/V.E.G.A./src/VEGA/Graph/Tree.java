/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.Graph;

import vega.Graph.Edge.Tree.TreeEdge;
import vega.Graph.Vertex.Tree.TreeNode;

/**
 *
 * @author w_jossey
 */
public class Tree extends Graph{
    TreeNode root;
    TreeNode[] treeArray;
    TreeEdge[] edgeArray;
    
    /**
     * 
     * @param t
     * @param e
     */
    public Tree(TreeNode[] t, TreeEdge[] e){
        treeArray = t;
        edgeArray = e;
    }
    
    /**
     * 
     * @param treeNodes
     */
    public Tree(TreeNode[] treeNodes){
        treeArray = treeNodes;
    }
    
    /**
     * 
     * @param root
     */
    public Tree(TreeNode root){
        this.root = root;
    }
    
    /**
     * Empty Constructor.  Sets the root to null;
     */
    public Tree(){
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
    
}
