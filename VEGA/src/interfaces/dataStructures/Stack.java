/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.dataStructures;

/**
 *
 * @author w_jossey
 */
public interface Stack<A> {
	
    void push(Comparable a);
    
    Comparable pop();
    
    Comparable peek();
    
}
