/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.graph.vertex.tree;

import interfaces.graph.edge.*;
import vega.graph.vertex.AbstractVertex;

import java.util.ArrayList;

/**
 *
 * @author w_jossey
 */

public class AbstractTreeNode<C extends Comparable<C>, T extends interfaces.graph.vertex.tree.TreeNode<C,T,E>, E extends Edge> extends AbstractVertex<T,E> implements interfaces.graph.vertex.tree.TreeNode<C,T,E>{

    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static int idCounter = 0;
    private int id; // unique identifier for the node for graphViz data
    private T parent;
    private C data;
    private int height; // for AVL trees
    private ArrayList<T> childNodes = new ArrayList<T>();

    /**
     * Creates a tree node with the value of @param k
     * @param k
     */
    public AbstractTreeNode(C k, E e) {
    	super();
    	data = k;
        parent = null;
        id = idCounter;
        idCounter++;
    }
    
    public AbstractTreeNode(C k){
    	super();
    	data = k;
    	parent = null;
    	id = idCounter++;
    }

    /**
     * 
     */
    public AbstractTreeNode() {
        super(); 
    	data = null;
        parent = null;
        height = -1;
        id = idCounter;
        idCounter++;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#getParentNode()
	 */
	public T getParentNode(){
        return parent;
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#setParentNode(interfaces.graph.vertex.tree.TreeNode)
	 */
    public void setParentNode(T parent){
        this.parent = parent;
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#addChild(interfaces.graph.vertex.tree.TreeNode)
	 */
    public void addChild(T child){
        childNodes.add(child);
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#getChildren()
	 */
    public ArrayList<T> getChildren(){
        return childNodes;
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#getData()
	 */
    public C getData(){
        return data;
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#setData(java.lang.Comparable)
	 */
    public void setData(C c){
        data = c;
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#getHeight()
	 */
    public int getHeight(){
        return height;
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#setHeight(int)
	 */
    public void setHeight(int height){
        this.height = height;
    }
    
    
    public int getID(){
    	return id;
    }

    @Override
    public String toString() {
        String result = "(" + data + ")";
        return result;
    } // end toString()

	public E getEdge(T child) {
		// TODO Auto-generated method stub
		return null;
	}

} // end class TreeNode
