/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.dataStructures.stack;

/**
 *  * A stack is a First In Last Out (FILO) data-structure. Items can either be pushed
 * onto the stack, popped off the stack, or examined using peek.  A stack can not be
 * iteratively traversed. 
 * @author w_jossey
 *
 * @param <E> Creates a stack of class E objects.
 */
public interface Stack<E> {
	
	/**
	 * Add an object to the stack.
	 * @param e element to add to the stack.
	 */
    void push(E e);
    
    /**
     * Return the object last added to the stack and remove it from the stack.
     * @return returns the element at the top of the stack.  This operation 
     * removes the element from the stack.
     */
    E pop();
    
    /**
     * Returns the top element on the stack without removing it.
     * @return returns the element at the top of the stack without
     * removing it.
     */
    E peek();
    
}
