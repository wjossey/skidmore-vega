/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.graph.vertex.tree;

import interfaces.graph.vertex.tree.BinaryTreeNode;
import interfaces.graph.edge.UndirectedEdge;

/**
 *
 * @author w_jossey
 */
public class BinaryTreeNode<C extends Comparable<? super C>> extends AbstractTreeNode<C, BinaryTreeNode<? super C>, UndirectedEdge<T>> implements BinaryTreeNode<C,T>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1808529175569579047L;
	
	private static int nodeCounter = 0;
	
	
	
	private T leftNode;
    private T rightNode;
    
    private int uid;

    /**
     * 
     * @param c
     */
    public BinaryTreeNode(C c) {
        super(c);
        leftNode = null;
        rightNode = null;
        uid = nodeCounter++;

    }

    /**
     * 
     * @param c
     * @param left
     * @param right
     */
    public BinaryTreeNode(C c, T left, T right) {
        super(c);
        leftNode = left;
        rightNode = right;
        uid = nodeCounter++;

    }
    
    /**
     * 
     */
    public BinaryTreeNode(){
    	super();
    	leftNode = null;
    	rightNode = null;
    	uid = nodeCounter++;
    	
    }

    /**
     * Return the left child of the node.
     */
	public T getLeftChild() {
		return leftNode;
       
    }

	/**
	 * 
	 */
	public T getRightChild() {
        return rightNode;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#setLeftNode(interfaces.graph.vertex.tree.BinaryTreeNode)
	 */
    public void setLeftChild(T left) {
       leftNode = left;
    }

    /**
     * Sets the right node.
     * @param right Right child.
     */
    public void setRightChild(T right) {
        rightNode = right;
    }

    /**
     * Check to see if the node is a right node.
     * @return Returns true if the node is a right child.  Otherwise returns false.
     */
    public boolean isRightChild() {
        if (getParentNode() != null && getParentNode().getRightChild() == this) {
            return true;
        } else {
            return false;
        } // end if

    } // end isRightChild()


    /**
     * Check to see if the node is a left node.
     * @return Returns true if the node is a left child.  Otherwise returns false.
     */
    public boolean isLeftChild() {
        if (getParentNode() != null && getParentNode().getLeftChild() == this) {
            return true;
        } else {
            return false;
        } // end if 

    } // end isLeftChild()
    
    
    public String toString(){
    	String returnString = "";
    	
    	
    	return returnString;
    }
    
    public String getGraphvizString(){
    	String returnString = "";
    	returnString += uid;
    	returnString += " [";
    	returnString += "label = \"" + uid + "\"";
    	returnString += "];";
    	
    	return returnString;
    }
    

}
