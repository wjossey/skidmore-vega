/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.graph.vertex.tree;

import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.tree.BinaryTreeNode;

/**
 *
 * @author w_jossey
 */
public class BinaryTreeNodeImpl<C extends Comparable<C>, E extends Edge> extends TreeNodeImpl<C,E> implements BinaryTreeNode<C,E>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1808529175569579047L;
	
	private BinaryTreeNode<C,E> leftNode;
    private BinaryTreeNode<C,E> rightNode;
    private BinaryTreeNode<C,E> parentNode;

    /**
     * 
     * @param c
     */
    public BinaryTreeNodeImpl(C c) {
        super(c);
        leftNode = null;
        rightNode = null;

    }
    
    public BinaryTreeNodeImpl(C c, boolean color){
        super(c);
        leftNode = null;
        rightNode = null;
        setColor(color);
    }

    /**
     * 
     * @param c
     * @param left
     * @param right
     */
    public BinaryTreeNodeImpl(C c, BinaryTreeNode<C,E> left, BinaryTreeNode<C,E> right) {
        super(c);
        leftNode = left;
        rightNode = right;

    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#getLeftNode()
	 */
    public BinaryTreeNode<C,E> getLeftNode() {
        return leftNode;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#getRightNode()
	 */
    public BinaryTreeNode<C,E> getRightNode() {
        return rightNode;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#setLeftNode(interfaces.graph.vertex.tree.BinaryTreeNode)
	 */
    public void setLeftNode(BinaryTreeNode<C,E> left) {
       leftNode = left;
    }
    
    public BinaryTreeNode<C,E> getParentNode(){
    	return (BinaryTreeNode<C,E>) super.getParentNode();
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#setRightNode(interfaces.graph.vertex.tree.BinaryTreeNode)
	 */
    public void setRightNode(BinaryTreeNode<C,E> right) {
        rightNode = right;
    }

    /**
     * 
     * @return
     */
    

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#setParentNode(interfaces.graph.vertex.tree.BinaryTreeNode)
	 */
    public void setParentNode(BinaryTreeNode<C,E> parent) {
        parentNode = parent;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#isRightChild()
	 */
    public boolean isRightChild() {
        if (parentNode != null && parentNode.getRightNode() == this) {
            return true;
        } else {
            return false;
        } // end if

    } // end isRightChild()


    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#isLeftChild()
	 */
    public boolean isLeftChild() {
        if (parentNode != null && parentNode.getLeftNode() == this) {
            return true;
        } else {
            return false;
        } // end if 

    } // end isLeftChild()
    
    

}
