/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.graph.vertex.tree;

import interfaces.graph.vertex.tree.TreeNode;
import interfaces.graph.edge.*;
import vega.graph.vertex.VertexImpl;

import java.util.ArrayList;

/**
 *
 * @author w_jossey
 */
public class TreeNodeImpl<E extends Edge> extends VertexImpl<E> implements TreeNode<E>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idCounter = 0;
    public static boolean RED = true;
    public static boolean BLACK = false;

    /**
     * Creates a tree node with the value of @param k
     * @param k
     */
    public TreeNodeImpl(Comparable<?> k) {
    	super();
    	data = k;
        parent = null;
        id = idCounter;
        idCounter++;
        color = RED;
    }

    /**
     * 
     * @param k
     * @param c
     */
    public TreeNodeImpl(Comparable<?> k, boolean c) {
        super();
    	data = k;
        parent = null;
        id = idCounter;
        idCounter++;
        color = c;
    }

    /**
     * 
     */
    public TreeNodeImpl() {
        super(); 
    	data = null;
        parent = null;
        height = -1;
        id = idCounter;
        idCounter++;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#isColor(boolean)
	 */
    public boolean isColor(boolean c) {
        return color == c;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#graphVizName()
	 */
    public String graphVizName() {
//			String result = "\"" + id + "\\n " + data + "\\n " + " height is "
//									+ height + "\"";
        String result = "\"" + data + "\\n color is " + (color ? "red" : "black") + "\"";
        return result;
    } // end graphVizName()

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#getParentNode()
	 */
    public TreeNode<E> getParentNode(){
        return parent;
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#setParentNode(interfaces.graph.vertex.tree.TreeNode)
	 */
    public void setParentNode(TreeNode<E>parent){
        this.parent = parent;
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#addChild(interfaces.graph.vertex.tree.TreeNode)
	 */
    public void addChild(TreeNode<E> child){
        childNodes.add(child);
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#getChildren()
	 */
    public ArrayList<TreeNode<E>> getChildren(){
        return childNodes;
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#getData()
	 */
    public Comparable<?> getData(){
        return data;
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#setData(java.lang.Comparable)
	 */
    public void setData(Comparable<?> c){
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
    
    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#getColor()
	 */
    public boolean getColor(){
        return color;
    }
    
    private int id; // unique identifier for the node for graphViz data
    private TreeNode<E> parent;
    private Comparable<?> data;
    private int height; // for AVL trees
    private ArrayList<TreeNode<E>> childNodes = new ArrayList<TreeNode<E>>();

    private boolean color; // for Red Black trees

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.TreeNode#setColor(boolean)
	 */
    public void setColor(boolean color) {
        this.color = color;
    }
    
    public int getID(){
    	return id;
    }

    @Override
    public String toString() {
        String result = "(" + data + ")";
        return result;
    } // end toString()

	public E getEdge(TreeNode<E> child) {
		// TODO Auto-generated method stub
		return null;
	}

} // end class TreeNode
