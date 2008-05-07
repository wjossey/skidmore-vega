/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VEGA.Graph.Vertex.Tree;

/**
 *
 * @author w_jossey
 */
public class BinaryTreeNode extends TreeNode {

    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private BinaryTreeNode parent;

    /**
     * 
     * @param c
     */
    public BinaryTreeNode(Comparable c) {
        super(c);
        this.left = null;
        this.right = null;

    }

    /**
     * 
     * @param c
     * @param left
     * @param right
     */
    public BinaryTreeNode(Comparable c, BinaryTreeNode left, BinaryTreeNode right) {
        super(c);
        this.left = left;
        this.right = right;

    }

    /**
     * 
     * @return
     */
    public BinaryTreeNode getLeftNode() {
        return left;
    }

    /**
     * 
     * @return
     */
    public BinaryTreeNode getRightNode() {
        return right;
    }

    /**
     * 
     * @param left
     */
    public void setLeftNode(BinaryTreeNode left) {
        this.left = left;
    }

    /**
     * 
     * @param right
     */
    public void setRightNode(BinaryTreeNode right) {
        this.right = right;
    }

    /**
     * 
     * @return
     */
    @Override
    public BinaryTreeNode getParentNode() {
        return this.parent;
    }

    /**
     * 
     * @param parent
     */
    public void setParentNode(BinaryTreeNode parent) {
        this.parent = parent;
    }

    /**
     * 
     * @return
     */
    public boolean isRightChild() {
        if (parent != null && parent.right == this) {
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
        if (parent != null && parent.left == this) {
            return true;
        } else {
            return false;
        } // end if 

    } // end isLeftChild()
    
    

}
