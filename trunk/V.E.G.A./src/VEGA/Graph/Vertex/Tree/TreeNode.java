/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.Graph.Vertex.Tree;

import java.util.ArrayList;

/**
 *
 * @author w_jossey
 */
public class TreeNode {

    private static int idCounter = 0;
    public static boolean RED = true;
    public static boolean BLACK = false;

    /**
     * Creates a tree node with the value of @param k
     * @param k
     */
    public TreeNode(Comparable k) {
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
    public TreeNode(Comparable k, boolean c) {
        data = k;
        parent = null;
        id = idCounter;
        idCounter++;
        color = c;
    }

    /**
     * 
     */
    public TreeNode() {
        data = null;
        parent = null;
        height = -1;
        id = idCounter;
        idCounter++;
    }

    /**
     * @param c
     * @return
     */
    public boolean isColor(boolean c) {
        return color == c;
    }

    /**
     * 
     * @return
     */
    public String graphVizName() {
//			String result = "\"" + id + "\\n " + data + "\\n " + " height is "
//									+ height + "\"";
        String result = "\"" + data + "\\n color is " + (color ? "red" : "black") + "\"";
        return result;
    } // end graphVizName()

    /**
     * 
     * @return
     */
    public TreeNode getParentNode(){
        return parent;
    }
    
    /**
     * 
     * @param parent
     */
    public void setParentNode(TreeNode parent){
        this.parent = parent;
    }
    
    public void addChild(TreeNode child){
        childNodes.add(child);
    }
    
    public TreeNode[] getChildren(){
        return (TreeNode[]) childNodes.toArray();
    }
    
    /**
     * 
     * @return
     */
    public Comparable getData(){
        return data;
    }
    
    /**
     * 
     * @param c
     */
    public void setData(Comparable c){
        data = c;
    }
    
    /**
     * 
     * @return
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * 
     * @param height
     */
    public void setHeight(int height){
        this.height = height;
    }
    
    /**
     * 
     * @return
     */
    public boolean getColor(){
        return color;
    }
    
    private int id; // unique identifier for the node for graphViz data
    private TreeNode parent;
    private Comparable data;
    private int height; // for AVL trees
    private ArrayList<TreeNode> childNodes = new ArrayList<TreeNode>();

    private boolean color; // for Red Black trees

    /**
     * @param color The color to set.
     */
    public void setColor(boolean color) {
        this.color = color;
    }

    @Override
    public String toString() {
        String result = "(" + data + ")";
        return result;
    } // end toString()

} // end class TreeNode
