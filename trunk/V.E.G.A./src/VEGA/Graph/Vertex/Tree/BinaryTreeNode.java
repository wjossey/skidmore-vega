/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VEGA.Graph.Vertex.Tree;

/**
 *
 * @author w_jossey
 */
public class BinaryTreeNode extends TreeNode{
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private BinaryTreeNode parent;
    
    public BinaryTreeNode(Comparable c){
        super(c);
        this.left = null;
        this.right = null;
        
    }
    
    public BinaryTreeNode(Comparable c, BinaryTreeNode left, BinaryTreeNode right){
        super(c);
        this.left = left;
        this.right = right;
        
    }
    
    public BinaryTreeNode getLeftChild(){
        return left;
    }
    
    public BinaryTreeNode getRightChild(){
        return right;
    }
    
    public void setLeftChild(BinaryTreeNode left){
        this.left = left;
    }
    
    public void setRightChild(BinaryTreeNode right){
        this.right = right;
    }
    
    @Override
    public BinaryTreeNode getParent(){
        return this.parent;
    }
    
    public void setParent(BinaryTreeNode parent){
        this.parent = parent;
    }
}
