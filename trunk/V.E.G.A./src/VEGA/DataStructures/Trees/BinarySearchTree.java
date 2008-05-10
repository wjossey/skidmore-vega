/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces.DataStructures;

/**
 *
 * @author w_jossey
 */
public interface BinarySearchTree {

    /**
     * Deletes a value from the tree without having to have the specific value.
     * @param arg  Object to delete
     * @return Returns true if found and deleted, false if it fails.
     */
    boolean delete(Object arg);

    /**
     * @return Returns the numElements.
     */
    int getNumElements();

    void insert(Object arg);

    boolean isEmpty();

    Object search(Object arg);

}
