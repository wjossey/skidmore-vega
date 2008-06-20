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
public class AbstractTree<C extends Comparable<C>, T extends TreeNode<C,T,E>, E extends Edge> extends AbstractGraph<T,E> implements Tree<C, T, E>{
    protected T root;
    protected ArrayList<T> treeArray;
    protected ArrayList<E> edgeArray;
    
    /**
     * 
     * @return
     */
    public T getRoot(){
        return root;
    }
    
    public String toString(){
        return toString(root);
    }
    
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

	public void setRoot(T v) {
		root = v;	
	}
    
}
