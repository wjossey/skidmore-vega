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
 *Base class for all graphs that have the property that they are trees.  An abstract 
 *tree has a data objects that must be comparable.  It is comprised of 
 *Tree Nodes (Vertices) and Edges.  The class overwrites the toString() method and
 *implements an overloaded toString(T v) where T is an object that extends TreeNode.
 *The tree also has a root node that acts as a pointer for the array of nodes and
 *edges.  
 * @author w_jossey
 */
public abstract class AbstractTree<C extends Comparable<C>, T extends TreeNode<C,T,E>, E extends Edge> extends AbstractGraph<T,E> implements Tree<C, T, E>{
    protected T root;
    protected ArrayList<T> treeArray;
    protected ArrayList<E> edgeArray;
    
    /**
     * Get the root vertex of the tree.
     * @return Root vertex.
     */
    public T getRoot(){
        return root;
    }
    
    /**
     * Unordered printing of the tree nodes.
     * @return Return the tree as a string.
     */
    public String toString(){
        return toString(root);
    }
    
    /**
     * Private method to print out all of the TreeNodes unordered.
     * @param t Root sub-tree Node.
     * @return
     */
    private String toString(T t){
        String returnString = "";
        returnString += t.toString();
        ArrayList<T> childArray = t.getChildren();
        
        for(int i = 0; i < childArray.size(); i++){
            returnString += childArray.get(i).toString();
            returnString += toString(childArray.get(i));
        }
        
        return returnString;
    }

    /**
     * Set the root of the tree.
     * @param v Root TreeNode
     */
	public void setRoot(T v) {
		root = v;	
	}
    
}
