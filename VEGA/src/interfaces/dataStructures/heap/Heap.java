/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.dataStructures.heap;

/**
 *
 * @author w_jossey
 */
public interface Heap<C extends Comparable<C>> {
	
	/**
	 * Instantiates an empty heap.
	 */
	public void createHeap();
	
	/**
	 * Returns the minimum value in the heap.
	 * @return The minimum value on the heap.
	 */
	public C findMin();
	
	public boolean deleteMin();
	
	public void insert(C arg);
	
	public boolean decreaseKey(double value);
	
	public boolean merge(Heap<C> h);
	
	

}
