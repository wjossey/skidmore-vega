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
 * @param <E>
 */
public interface Stack<E> {
	
	/**
	 * Add an object to the stack.
	 * @param obj
	 */
    void push(E obj);
    
    /**
     * Return the object last added to the stack and remove it from the stack.
     * @return
     */
    E pop();
    
    /**
     * Returns the top element on the stack without removing it.
     * @return 
     */
    E peek();
    
}
