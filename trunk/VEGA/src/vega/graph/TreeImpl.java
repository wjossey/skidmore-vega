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
public class TreeImpl<C extends Comparable<C>, E extends Edge> extends GraphImpl<TreeNode<C,E>, E> implements Tree<C, TreeNode<C,E>, E>{
    TreeNode<C,E> root;
    ArrayList<TreeNode<C,E>> treeArray;
    ArrayList<E> edgeArray;
    
    /**
     * 
     * @param t
     * @param e
     */
    public TreeImpl(ArrayList<TreeNode<C,E>> t, ArrayList<E> e){
        treeArray = t;
        edgeArray = e;
    }
    
    /**
     * 
     * @param treeNodes
     */
    public TreeImpl(ArrayList<TreeNode<C,E>> treeNodes){
        treeArray = treeNodes;
    }
    
    /**
     * 
     * @param root
     */
    public TreeImpl(TreeNode<C,E> root){
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
    public TreeNode<C,E> getRoot(){
        return root;
    }
    
    public String toString(){
        return toString(root);
    }
    
    private String toString(TreeNode<C,E> t){
        String returnString = "";
        returnString += t.toString();
        ArrayList<TreeNode<C,E>> childArray = t.getChildren();
        
        for(int i = 0; i < childArray.size(); i++){
            returnString += childArray.get(i).toString();
            returnString += toString(childArray.get(i));
        }
        
        return returnString;
    }

	public void setRoot(TreeNode<C,E> v) {
		root = v;	
	}
    
}
