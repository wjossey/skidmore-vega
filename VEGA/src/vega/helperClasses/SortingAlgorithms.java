package vega.helperClasses;

import interfaces.graph.edge.Edge;

public class SortingAlgorithms {

	/**
	 *
	 * @param edgeList
	 * @param left
	 * @param right
	 */
	public static <E extends Edge> void quicksort(E[] edgeList, int left, int right) {
	    if (right <= left) {
	        return;
	    //Else
	    }
	    int i = partition(edgeList, left, right);
	    quicksort(edgeList, left, i - 1);
	    quicksort(edgeList, i + 1, right);
	
	}

	/**
	 * Private internal method used for quicksort.
	 * @param <E>
	 * @param edgeList
	 * @param left
	 * @param right
	 * @return
	 */
	private static <E extends Edge> int partition(E[] edgeList, int left, int right) {
	    int i = left - 1;
	    int j = right;
	
	    while (true) {
	        while (less(edgeList[++i].getWeight(), edgeList[right].getWeight()));
	        while (less(edgeList[right].getWeight(), edgeList[--j].getWeight())) {
	            if (j == left) {
	                break;
	            }
	        }
	        if (i >= j) {
	            break;
	        }
	        swap(edgeList, i, j);
	    }
	    swap(edgeList, i, right);
	
	    return i;
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	private static boolean less(double x, double y) {
	    return (x < y);
	}

	/**
	 * 
	 * @param edgeList
	 * @param i
	 * @param j
	 */
	private static <E extends Edge> void swap(E[] edgeList, int i, int j) {
	    //swaps++;  //We can keep track of our swaps for proof purposes.
	    E temp = edgeList[i];
	    edgeList[i] = edgeList[j];
	    edgeList[j] = temp;
	}

}
