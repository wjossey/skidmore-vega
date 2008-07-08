/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.dataStructures.tree;

/**
 * 
 * @author w_jossey
 *
 * @param <C>
 */
public interface BinarySearchTree<C extends Comparable<? super C>>{

    /**
     * Deletes a value from the tree without having to have the specific value.
     * @param arg  Object to delete
     * @return Returns true if found and deleted, false if it fails.
     */
    public boolean delete(C arg);

    /**
     * Returns the number of elements in the tree.
     * @return Returns the numElements.
     */
    public int getNumElements();

    /**
     * Inserts an object of the type parameter into the tree. Usually we will only allow
     * for items that implement comparable.  
     * @param arg
     */
    public void insert(C arg);

    /**
     * Returns true if the binary search tree has no nodes, false if it has nodes.
     * @return True if empty, false if it contains nodes.
     */
    public boolean isEmpty();

    /**
     * Searches for the parameterized object and returns the instance from the Tree if it exists.
     * @param arg Argument to search for.
     * @return Returns the object if found, null if not.
     */
    public C search(C arg);
    

}
