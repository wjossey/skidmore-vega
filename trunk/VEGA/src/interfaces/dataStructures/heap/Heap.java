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
	 * 
	 * @return
	 */
	public boolean removeMax();
	
	/**
	 * 
	 * @param arg
	 */
	public void insert(C arg);
		
}
