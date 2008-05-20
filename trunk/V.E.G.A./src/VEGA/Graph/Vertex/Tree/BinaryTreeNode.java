/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.Graph.Vertex.Tree;

/**
 *
 * @author w_jossey
 */
public class BinaryTreeNode extends TreeNode {

    private BinaryTreeNode leftNode;
    private BinaryTreeNode rightNode;
    private BinaryTreeNode parentNode;

    /**
     * 
     * @param c
     */
    public BinaryTreeNode(Comparable c) {
        super(c);
        leftNode = null;
        rightNode = null;

    }
    
    public BinaryTreeNode(Comparable c, boolean color){
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
    public BinaryTreeNode(Comparable c, BinaryTreeNode left, BinaryTreeNode right) {
        super(c);
        leftNode = left;
        rightNode = right;

    }

    /**
     * 
     * @return
     */
    public BinaryTreeNode getLeftNode() {
        return leftNode;
    }

    /**
     * 
     * @return
     */
    public BinaryTreeNode getRightNode() {
        return rightNode;
    }

    /**
     * 
     * @param left
     */
    public void setLeftNode(BinaryTreeNode left) {
       leftNode = left;
    }

    /**
     * 
     * @param right
     */
    public void setRightNode(BinaryTreeNode right) {
        rightNode = right;
    }

    /**
     * 
     * @return
     */
    @Override
    public BinaryTreeNode getParentNode() {
        return parentNode;
    }

    /**
     * 
     * @param parent
     */
    public void setParentNode(BinaryTreeNode parent) {
        parentNode = parent;
    }

    /**
     * 
     * @return
     */
    public boolean isRightChild() {
        if (parentNode != null && parentNode.getRightNode() == this) {
            return true;
        } else {
            return false;
        } // end if

    } // end isRightChild()


    /**
     * 
     * @return
     */
    public boolean isLeftChild() {
        if (parentNode != null && parentNode.getLeftNode() == this) {
            return true;
        } else {
            return false;
        } // end if 

    } // end isLeftChild()
    
    

}
