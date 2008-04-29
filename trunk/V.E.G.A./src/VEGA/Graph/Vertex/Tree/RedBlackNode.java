/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VEGA.Graph.Vertex.Tree;

/**
 *
 * @author w_jossey
 */
public class RedBlackNode extends BinaryTreeNode{
    
    public RedBlackNode left;
    public RedBlackNode right;
    public RedBlackNode parent;
    private Comparable data = super.data;

    public boolean color; // for Red Black trees
    
    /**
     * 
     * @param g
     * @param c
     */
    
    /**
     * 
     * @param g
     * @param c
     * @param color
     */
    public RedBlackNode(Comparable c, boolean color){
        /*Sets the data and the graph*/
        super(c);
        
        /*Sets the color of the node*/
        this.color = color;
    }
    
    public RedBlackNode(Comparable c, RedBlackNode left, RedBlackNode right, boolean color){
        super(c);
        this.left = left;
        this.right = right;
        this.color = color;
        
    }
    
    public RedBlackNode getLeftNode(){
        return left;
    }
    
    public RedBlackNode getRightNode(){
        return right;
    }
    
    public RedBlackNode getParent(){
        return parent;
    }
    
    public void setParent(RedBlackNode t){
        this.parent = t;
    }

}
