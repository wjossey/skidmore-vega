/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.dataStructures.heap;

import interfaces.graph.Tree;
import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.heap.HeapNode;

/**
 *
 * @author w_jossey
 */
public interface Heap<C extends Comparable<C>> extends Tree<C, HeapNode<C>, UndirectedEdge<HeapNode<C>>>{
	
	/**
	 * Instantiates an empty heap.
	 */
	public void buildHeap();
	
	/**
	 * Returns the minimum value in the heap.
	 * @return The minimum value on the heap.
	 */
	public C findMax();
	
	/**
	 * Deletes the maximum item from the heap.
	 * @return
	 */
	public C removeMax();
	
	/**
	 * Inserts a new item into the heap.
	 * @param arg  Comparable item to insert.
	 */
	public void insert(C arg);
		
}
