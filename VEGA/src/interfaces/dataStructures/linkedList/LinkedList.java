/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.dataStructures.linkedList;

/**
 * 
 * @author Weston Jossey
 *
 * @param <K> Creates a linked list of class K objects.
 */
public interface LinkedList<K>{
    
	/**
	 * Inserts element into the beginning of the list.
	 * @param k the element to be added to the start of the list.
	 */
	public void addFirst(K k);
	
	/**
	 * Inserts the element into the end of the list
	 * @param k the element to be added to the end of the list
	 */
	public void addLast(K k);
	
	/**
	 * Inserts the element at the index specified.  The list starts
	 * index 0.
	 * @param index index at which to insert the object
	 * @param k element to insert.
	 */
	public void add(int index, K k);
	
	/**
	 * Appends the item to the end of the list
	 * @param k element to insert at the end of the list.
	 */
	public void add(K k);
	
	/**
	 * Gets the item at the specified index.
	 * @param index index of element to return
	 * @return element at specified index.
	 */
	public K get(int index);
	
	/**
	 * Gets the item at the head of the list (index 0).
	 * @return element at the head of the list.
	 */
	public K getFirst();
	
	/**
	 * Gets the item at the tail of the list.
	 * @return element at the tail of the list.
	 */
	public K getLast();
	
	/**
	 * Returns the index of a specified element if it exists
	 * in the list.  Returns -1 if the element does not exist.
	 * @param k element to search for
	 * @return index of element in the list.
	 */
	public int indexOf(K k);
	
	/**
	 * Removes the specified element from the list if it exists. 
	 * @param k element to remove.
	 * @return Returns true if element is found and deleted, else it
	 * returns false.
	 */
	public boolean remove(K k);
	
	/**
	 * Removes the element at the head of the list.
	 * @return element removed from the head of the list.
	 */
	public K removeFirst();
	
	/**
	 * Removes the element at the tail of the list.
	 * @return element removed from the tail of the list.
	 */
	public K removeLast();
	
	/**
	 * Returns the size of the list.
	 * @return size of the list.
	 */
	public int size();
	
}
