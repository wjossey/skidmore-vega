/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.graph.vertex.tree;

import interfaces.graph.vertex.tree.BinaryTreeNode;

/**
 *
 * @author w_jossey
 */
public class BinaryTreeNodeImpl extends TreeNodeImpl implements BinaryTreeNode{

    private BinaryTreeNode leftNode;
    private BinaryTreeNode rightNode;
    private BinaryTreeNode parentNode;

    /**
     * 
     * @param c
     */
    public BinaryTreeNodeImpl(Comparable c) {
        super(c);
        leftNode = null;
        rightNode = null;

    }
    
    public BinaryTreeNodeImpl(Comparable c, boolean color){
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
    public BinaryTreeNodeImpl(Comparable c, BinaryTreeNode left, BinaryTreeNode right) {
        super(c);
        leftNode = left;
        rightNode = right;

    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#getLeftNode()
	 */
    public BinaryTreeNode getLeftNode() {
        return leftNode;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#getRightNode()
	 */
    public BinaryTreeNode getRightNode() {
        return rightNode;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#setLeftNode(interfaces.graph.vertex.tree.BinaryTreeNode)
	 */
    public void setLeftNode(BinaryTreeNode left) {
       leftNode = left;
    }
    
    public BinaryTreeNode getParentNode(){
    	return (BinaryTreeNode) super.getParentNode();
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#setRightNode(interfaces.graph.vertex.tree.BinaryTreeNode)
	 */
    public void setRightNode(BinaryTreeNode right) {
        rightNode = right;
    }

    /**
     * 
     * @return
     */
    

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#setParentNode(interfaces.graph.vertex.tree.BinaryTreeNode)
	 */
    public void setParentNode(BinaryTreeNode parent) {
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
