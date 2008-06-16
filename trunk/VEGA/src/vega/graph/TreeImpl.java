/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.graph;

import java.util.ArrayList;

import interfaces.graph.Tree;
import interfaces.graph.edge.*;
import interfaces.graph.vertex.tree.TreeNode;

/**
 *
 * @author w_jossey
 */
public class TreeImpl<E extends Edge> extends GraphImpl<TreeNode<E>, E> implements Tree<TreeNode<E>, E>{
    TreeNode<E> root;
    TreeNode<E>[] treeArray;
    E[] edgeArray;
    
    /**
     * 
     * @param t
     * @param e
     */
    public TreeImpl(TreeNode<E>[] t, E[] e){
        treeArray = t;
        edgeArray = e;
    }
    
    /**
     * 
     * @param treeNodes
     */
    public TreeImpl(TreeNode<E>[] treeNodes){
        treeArray = treeNodes;
    }
    
    /**
     * 
     * @param root
     */
    public TreeImpl(TreeNode<E> root){
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
    public TreeNode<E> getRoot(){
        return root;
    }
    
    public String toString(){
        return toString(root);
    }
    
    private String toString(TreeNode<E> t){
        String returnString = "";
        returnString += t.toString();
        ArrayList<TreeNode<E>> childArray = t.getChildren();
        
        for(int i = 0; i < childArray.size(); i++){
            returnString += childArray.get(i).toString();
            returnString += toString(childArray.get(i));
        }
        
        return returnString;
    }

	public void setRoot(TreeNode<E> v) {
		root = v;	
	}
    
}
