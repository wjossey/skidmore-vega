 package vega.dataStructures.heap;

import java.util.ArrayList;
import java.util.Random;

// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// boolean isFull( )      --> Return true if full; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws Overflow if capacity exceeded

/**
 * @param<C>
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinaryHeap<C extends Comparable<? super C>> {
	private static final int DEFAULT_CAPACITY = Integer.MAX_VALUE;

	private int currentSize; // Number of elements in heap
	private ArrayList<C> array; // The heap array
	private int capacity = 0;

	/**
	 * Construct the binary heap.
	 */
	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Construct the binary heap.
	 * 
	 * @param capacity
	 *            the capacity of the binary heap.
	 */
	public BinaryHeap(int capacity) {
		currentSize = 0;
		this.capacity = capacity;
		array = new ArrayList<C>(capacity + 1);
		array.add(0, null); // Place holder at 0. We use 1
		// as the root b/c it matches the math
		// easier.
	}

	/**
	 * Insert into the priority queue, maintaining heap order. Duplicates are
	 * allowed.
	 * 
	 * @param x
	 *            the item to insert.
	 * @exception Overflow
	 *                if container is full.
	 */
	public void insert(C x) throws Exception {
		if (isFull())
			throw new Exception();

		// Percolate up
		int hole = ++currentSize;
		for (; hole > 1 && x.compareTo(array.get(hole / 2)) < 0; hole /= 2)
			array.add(hole, array.get(hole / 2));
		array.add(hole, x);
	}

	/**
	 * Find the smallest item in the priority queue.
	 * 
	 * @return the smallest item, or null, if empty.
	 */
	public C findMin() {
		if (isEmpty())
			return null;
		return array.get(1);
	}

	/**
	 * Remove the smallest item from the priority queue.
	 * 
	 * @return the smallest item, or null, if empty.
	 */
	public C deleteMin() {
		if (isEmpty())
			return null;

		C minItem = findMin();
		array.add(1, array.get(currentSize--));
		percolateDown(1);

		return minItem;
	}

	/**
	 * Establish heap order property from an arbitrary arrangement of items.
	 * Runs in linear time.
	 */
	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--)
			percolateDown(i);
	}

	/**
	 * Test if the priority queue is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return currentSize == 0;
	}

	/**
	 * Test if the priority queue is logically full.
	 * 
	 * @return true if full, false otherwise.
	 */
	public boolean isFull() {
		return currentSize == capacity;
	}

	/**
	 * Make the priority queue logically empty.
	 */
	public void makeEmpty() {
		currentSize = 0;
	}

	/**
	 * Internal method to percolate down in the heap.
	 * 
	 * @param hole
	 *            the index at which the percolate begins.
	 */
	private void percolateDown(int hole) {
		/* 1 */int child;
		/* 2 */C tmp = array.get(hole);

		/* 3 */for (; hole * 2 <= currentSize; hole = child) {
			/* 4 */child = hole * 2;
			/* 5 */if (child != currentSize &&
			/* 6 */array.get(child + 1).compareTo(array.get(child)) < 0)
				/* 7 */child++;
			/* 8 */if (array.get(child).compareTo(tmp) < 0)
				/* 9 */array.add(hole, array.get(child));
			else
				/* 10 */break;
		}
		/* 11 */array.add(hole, tmp);
	}

	public String toString() {

		String returnString = "";

		for (int i = 1; i < array.size(); i++) {
			returnString += array.get(i).toString() + ", ";
		}

		return returnString;
	}

	public String toGraphviz() {
		System.out.println(array.size());
		String returnString = "";
		for (int i = 1; i < array.size(); i++) {
			returnString += printNode(i);
		}

		for (int i = 1; i < array.size() / 2; i++) {
			returnString += i + " -- " + (2 * i) + ";\n";
			if (2 * i + 1 <= array.size()) {
				returnString += i + " -- " + (2 * i + 1) + ";\n";
			}
		}

		return returnString;
	}

	public String printNode(int index) {
		String returnString = "";
		returnString += index + " [";
		returnString += "label=\"" + array.get(index).toString() + "\"];\n";
		return returnString;
	}

	// Test program
	public static void main(String[] args) throws Exception {
		int numItems = 10000;
		Random ran = new Random();
		ran.nextInt();
		BinaryHeap<Integer> h = new BinaryHeap<Integer>(numItems);
		for (int i = 0; i < 5; i++) {
			h.insert(new Integer(ran.nextInt(numItems)));
		}
		System.out.println(h.toGraphviz());

	}
}