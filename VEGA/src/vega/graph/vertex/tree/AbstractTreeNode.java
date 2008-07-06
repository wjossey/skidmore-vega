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

public abstract class AbstractTreeNode<C extends Comparable<? super C>, T extends AbstractTreeNode<? super C,? super T,? super E>, E extends Edge> extends AbstractVertex<E> implements interfaces.graph.vertex.tree.TreeNode<C,T,E>{

    /**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	private static int idCounter = 0;
    private int uid; // unique identifier for the node for graphViz data
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
        uid = idCounter;
        idCounter++;
    }
    
    public AbstractTreeNode(C k){
    	super();
    	data = k;
    	parent = null;
    	uid = idCounter++;
    }

    /**
     * 
     */
    public AbstractTreeNode() {
        super(); 
    	data = null;
        parent = null;
        height = -1;
        uid = idCounter;
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
    	return uid;
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
	
	public String getGraphvizString(){
		String returnString = "";
    	returnString += uid;
    	returnString += " [";
    	returnString += "label = \"" + uid + "\"";
    	returnString += "];";
    	
    	return returnString;
	}

} // end class TreeNode
