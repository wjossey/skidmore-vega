/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.dataStructures;

import interfaces.graph.vertex.tree.TreeNode;

/**
 *
 * @author w_jossey
 */
public interface BinarySearchTree<A> {

    /**
     * Deletes a value from the tree without having to have the specific value.
     * @param arg  Object to delete
     * @return Returns true if found and deleted, false if it fails.
     */
    public boolean delete(A arg);

    /**
     * @return Returns the numElements.
     */
    public int getNumElements();

    public void insert(A arg);

    public boolean isEmpty();

    public Object search(A arg);
    
    public void insertNode(TreeNode node);
    
    public void deleteNode(TreeNode node);
    

}
