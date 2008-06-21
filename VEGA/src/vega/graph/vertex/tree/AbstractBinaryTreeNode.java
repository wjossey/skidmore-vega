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
public class AbstractBinaryTreeNode<C extends Comparable<C>, T extends BinaryTreeNode<C,T>> extends AbstractTreeNode<C,T,UndirectedEdge<T>> implements BinaryTreeNode<C,T>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1808529175569579047L;
	
	private T leftNode;
    private T rightNode;

    /**
     * 
     * @param c
     */
    public AbstractBinaryTreeNode(C c) {
        super(c);
        leftNode = null;
        rightNode = null;

    }

    /**
     * 
     * @param c
     * @param left
     * @param right
     */
    public AbstractBinaryTreeNode(C c, T left, T right) {
        super(c);
        leftNode = left;
        rightNode = right;

    }
    
    public AbstractBinaryTreeNode(){
    	super();
    	leftNode = null;
    	rightNode = null;
    	
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#getLeftNode()
	 */
	public T getLeftNode() {
		return leftNode;
       
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#getRightNode()
	 */

	@SuppressWarnings("unchecked")
	public T getRightNode() {
        return rightNode;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#setLeftNode(interfaces.graph.vertex.tree.BinaryTreeNode)
	 */
    public void setLeftNode(T left) {
       leftNode = left;
    }

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#setRightNode(interfaces.graph.vertex.tree.BinaryTreeNode)
	 */
    public void setRightNode(T right) {
        rightNode = right;
    }

    /**
     * 
     * @return
     */

    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#isRightChild()
	 */
    public boolean isRightChild() {
        if (getParentNode() != null && getParentNode().getRightNode() == this) {
            return true;
        } else {
            return false;
        } // end if

    } // end isRightChild()


    /* (non-Javadoc)
	 * @see vega.graph.vertex.tree.BinaryTreeNode#isLeftChild()
	 */
    public boolean isLeftChild() {
        if (getParentNode() != null && getParentNode().getLeftNode() == this) {
            return true;
        } else {
            return false;
        } // end if 

    } // end isLeftChild()
    
    

}
