/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.dataStructures.trees;

import vega.graph.vertex.tree.AbstractBinaryTreeNode;
import interfaces.graph.vertex.tree.BinaryTreeNode;

/**
 *
 * @author w_jossey
 */
public class RedBlackTreeV2Impl extends AbstractBinarySearchTree{

    public RedBlackTreeV2Impl(){
        super();
    }
    
    public void insert(Comparable<?> c){
        AbstractBinaryTreeNode node = new AbstractBinaryTreeNode(c);
        insertNode(node);
        insert_case1(node);
    }
    
    /**
     * 
     * @param node
     * @return
     */
    private BinaryTreeNode getGrandParentNode(BinaryTreeNode node){
        /*Make sure the null and its parent aren't null*/
        if((node != null) && (node.getParentNode() != null)){
            return node.getParentNode().getParentNode();
        }else{
            /*Else return null*/
            return null;
        }
    }
    
    /**
     * 
     * @param node
     * @return
     */
    private BinaryTreeNode getUncleNode(BinaryTreeNode node){
        BinaryTreeNode grandParent = getGrandParentNode(node);
        if(grandParent == null){
            return null;
        }else{
            if(node.getParentNode() == grandParent.getLeftNode()){
                return grandParent.getRightNode();
            }else{
                return grandParent.getLeftNode();
            }
        }
    }
    
    private BinaryTreeNode getSibling(BinaryTreeNode node){  
        if(node == node.getParentNode().getLeftNode()){
            return node.getParentNode().getRightNode();
        }else{
            return node.getParentNode().getLeftNode();
        }
    }
    
    private boolean isLeaf(BinaryTreeNode node){
        if(node.getLeftNode() == null && node.getRightNode() == null){
            return true;
        }else{
            return false;
        }
    }
    
    private void insert_case1(BinaryTreeNode node){
        if(node.getParentNode() == null){
            node.setColor(BLACK);
        }else{
            insert_case2(node);
        }
    }
    
    private void insert_case2(BinaryTreeNode node){
        if(node.getParentNode().getColor() == BLACK){
            return;
        }else{
            insert_case3(node);
        }
    }
    
    private void insert_case3(BinaryTreeNode node){
        BinaryTreeNode uncle = getUncleNode(node);
        
        if((uncle != null) && (uncle.getColor() == RED)){
            node.getParentNode().setColor(BLACK);
            uncle.setColor(BLACK);
            BinaryTreeNode grandparent = getGrandParentNode(node);
            grandparent.setColor(RED);
            insert_case1(grandparent);
        }else{
            insert_case4(node);
        }
    }
    
    private void insert_case4(BinaryTreeNode node){
        BinaryTreeNode grandparent = getGrandParentNode(node);
        
        if((node == node.getParentNode().getRightNode()) && 
                (node.getParentNode() == grandparent.getLeftNode())){
            rotateWithRightChild(node.getParentNode());
            node = node.getLeftNode();            
        }else{
            if((node == node.getParentNode().getLeftNode()) && 
                    (node.getParentNode() == node.getRightNode())){   
                rotateWithLeftChild(node.getParentNode());
                node = node.getRightNode();
            }
        }
        
        insert_case5(node);
    }
    private void insert_case5(BinaryTreeNode node){
        BinaryTreeNode grandparent = getGrandParentNode(node);
        node.getParentNode().setColor(BLACK);
        grandparent.setColor(RED);
        if((node == node.getParentNode().getLeftNode()) && 
                (node.getParentNode() == grandparent.getLeftNode())){
            rotateWithRightChild(grandparent);
        }else{
            rotateWithLeftChild(grandparent);
        }
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public boolean delete(Object arg) {
        boolean result = false;
        Comparable key = (Comparable) arg;
        BinaryTreeNode node = search(getRoot(), key);
        if (node != null) {
            deleteNode(node);
            numElements--;
            result = true;
        }
        return result;
    }
   
    private BinaryTreeNode deleteNode(BinaryTreeNode node){
        BinaryTreeNode child;
        if(isLeaf(node.getLeftNode())){
            child = node.getLeftNode();
        }else{
            child = node.getRightNode();
        }
        
        node.setData(child.getData());
        if(node.getColor() == BLACK){
            if(child.getColor() == RED){
                child.setColor(BLACK);
            }else{
                delete_case1(child);
            }
        }
        
        return node;
        
    }
    
    private void delete_case1(BinaryTreeNode node){
        if(node.getParentNode() != null){
            delete_case2(node);
        }
    }
    
    private void delete_case2(BinaryTreeNode node){
        BinaryTreeNode sibling = getSibling(node);
        
        if(sibling.getColor() == RED){
            node.getParentNode().setColor(RED);
            sibling.setColor(BLACK);
            if(node == node.getParentNode().getLeftNode()){
                rotateWithLeftChild(node.getParentNode());
            }else{
                rotateWithRightChild(node.getParentNode());
            }
        }
        
        delete_case3(node);
    }
    
    private void delete_case3(BinaryTreeNode node){
        BinaryTreeNode sibling = getSibling(node);
        
        if((node.getParentNode().getColor() == BLACK) && 
                (sibling.getColor() == BLACK) &&
                (sibling.getLeftNode().getColor() == BLACK) &&
                (sibling.getRightNode().getColor() == RED)){
            sibling.setColor(RED);
            delete_case1(node.getParentNode());
        }else{
            delete_case4(node);
        }
                
    }
    
    private void delete_case4(BinaryTreeNode node){
        BinaryTreeNode sibling = getSibling(node);
        
        if((node.getParentNode().getColor() == RED) &&
                (sibling.getColor() == BLACK) && 
                (sibling.getLeftNode().getColor() == BLACK) &&
                (sibling.getRightNode().getColor() == BLACK)){
            sibling.setColor(RED);
            node.getParentNode().setColor(BLACK);
        }else{
            delete_case5(node);
        }
    }
    
    private void delete_case5(BinaryTreeNode node){
        BinaryTreeNode sibling = getSibling(node);
        
        if((node == node.getParentNode().getLeftNode()) &&
                (sibling.getColor() == BLACK) && 
                (sibling.getLeftNode().getColor() == RED) &&
                (sibling.getRightNode().getColor() == BLACK)){
            sibling.setColor(RED);
            sibling.getLeftNode().setColor(BLACK);
            rotateWithRightChild(sibling);
        }else{
            if((node == node.getParentNode().getRightNode()) &&
                    (sibling.getColor() == BLACK) && 
                    (sibling.getRightNode().getColor() == RED) &&
                    (sibling.getLeftNode().getColor() == BLACK)){
                sibling.setColor(RED);
                sibling.getRightNode().setColor(BLACK);
                rotateWithLeftChild(sibling);
            }
        }
        
        delete_case6(node);
    }
    
    private void delete_case6(BinaryTreeNode node){
        BinaryTreeNode sibling = getSibling(node);
        
        sibling.setColor(node.getParentNode().getColor());
        node.getParentNode().setColor(BLACK);
        if(node == node.getParentNode().getLeftNode()){
            sibling.getRightNode().setColor(BLACK);
            rotateWithLeftChild(node.getParentNode());
        }else{
            sibling.getLeftNode().setColor(BLACK);
            rotateWithRightChild(node.getParentNode());
        }
    }
    
    public static void main(String[] args){
        RedBlackTreeV2Impl rbt = new RedBlackTreeV2Impl();
        rbt.insert(new Double(5));
        System.out.println(rbt.toGraphViz("After insert 5"));
        rbt.insert(new Double(8));
        System.out.println(rbt.toGraphViz("After insert 8"));
        rbt.insert(new Double(1));
        System.out.println(rbt.toGraphViz("After insert 1"));
        rbt.insert(new Double(83));
        System.out.println(rbt.toGraphViz("After insert 83"));
        rbt.insert(new Double(12));
        System.out.println(rbt.toGraphViz("After insert 12"));
        rbt.insert(new Double(33));
        System.out.println(rbt.toGraphViz("After insert 33"));
        rbt.insert(new Double(22));
        System.out.println(rbt.toGraphViz("After insert 22"));
        
    }
    
}
