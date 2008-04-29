/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VEGA.Graph.Vertex.Tree;

import VEGA.Graph.Tree.Tree;
import VEGA.Graph.Vertex.Vertex;
import java.util.ArrayList;

/**
 *
 * @author w_jossey
 */
public class TreeNode extends Vertex {

    ArrayList<TreeNode> childList = new ArrayList<TreeNode>();
    private TreeNode parent;
    Comparable data;

    public TreeNode(Comparable c) {
        super();
        data = c;

    }

    public TreeNode(Comparable c, TreeNode[] children) {
        super();
        data = c;


    }

    public void addChild(TreeNode child) {
        childList.add(child);
    }

    public void addChildren(TreeNode[] childArray) {
        for (int i = 0; i < childArray.length; i++) {
            if (childArray[i] != null) {
                addChild(childArray[i]);
            }

        }
    }
    
    public TreeNode[] getChildren(){
        TreeNode[] returnArray = null;
        returnArray = childList.toArray(returnArray);
        return returnArray;
    }
    
    public Comparable getData(){
        return data;
    }
    
    public void setData(Comparable c){
        data = c;
    }
    
    public void setParent(TreeNode t){
        parent = t;
    }
    
    public TreeNode getParent(){
        return parent;
    }
    
    public String toString(){
        String returnString = "";
        
        return returnString;
    }
    
    
}
