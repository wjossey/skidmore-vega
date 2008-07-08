package interfaces.dataStructures.heap;

import interfaces.graph.vertex.heap.FibonacciHeapNode;

/**
 * 
 * @author Weston Jossey
 *
 * @param <C> Class that implements comparable
 * @param <T> Class that implements Tree Node
 */
public interface FibonacciHeap<C, T extends FibonacciHeapNode<? super C, ? extends T>>{

	/**
	 * Tests if the Fibonacci heap is empty or not. Returns true if the heap is
	 * empty, false otherwise.
	 *
	 * <p>Running time: O(1) actual</p>
	 *
	 * @return true if the heap is empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Removes all elements from this heap.
	 */
	public void clear();

	/**
	 * Decreases the key value for a heap node, given the new value to take on.
	 * The structure of the heap may be changed and will not be consolidated.
	 *
	 * <p>Running time: O(1) amortized</p>
	 *
	 * @param x node to decrease the key of
	 * @param k new key value for node x
	 *
	 * @exception IllegalArgumentException Thrown if k is larger than x.key
	 * value.
	 */
	public void decreaseKey(T x, double k);

	/**
	 * Deletes a node from the heap given the reference to the node. The trees
	 * in the heap will be consolidated, if necessary. This operation may fail
	 * to remove the correct element if there are nodes with key value
	 * -Infinity.
	 *
	 * <p>Running time: O(log n) amortized</p>
	 *
	 * @param x node to remove from heap
	 */
	public void delete(T x);

	/**
	 * Inserts a new data element into the heap. No heap consolidation is
	 * performed at this time, the new node is simply inserted into the root
	 * list of this heap.
	 *
	 * <p>Running time: O(1) actual</p>
	 *
	 * @param node new node to insert into heap
	 * @param key key value associated with data object
	 */
	public void insert(T node, double key);

	/**
	 * Returns the smallest element in the heap. This smallest element is the
	 * one with the minimum key value.
	 *
	 * <p>Running time: O(1) actual</p>
	 *
	 * @return heap node with the smallest key
	 */
	public T min();

	/**
	 * Removes the smallest element from the heap. This will cause the trees in
	 * the heap to be consolidated, if necessary.
	 *
	 * <p>Running time: O(log n) amortized</p>
	 *
	 * @return node with the smallest key
	 */
	public T removeMin();

	/**
	 * Returns the size of the heap which is measured in the number of elements
	 * contained in the heap.
	 *
	 * <p>Running time: O(1) actual</p>
	 *
	 * @return number of elements in the heap
	 */
	public int size();

}