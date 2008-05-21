/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.dataStructures;

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
    boolean delete(A arg);

    /**
     * @return Returns the numElements.
     */
    int getNumElements();

    void insert(A arg);

    boolean isEmpty();

    Object search(A arg);

}
