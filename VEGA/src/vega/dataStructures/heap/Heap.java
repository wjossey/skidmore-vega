package vega.dataStructures.heap;

import java.util.ArrayList;

import interfaces.graph.edge.UndirectedEdge;

import interfaces.graph.vertex.heap.HeapNode;

import vega.graph.AbstractTree;

public class Heap<C extends Comparable<C>> extends AbstractTree<C, HeapNode<C>, UndirectedEdge<HeapNode<C>>> implements interfaces.dataStructures.heap.Heap<C>{
	private ArrayList<HeapNode<C>> a;
    private int arrayLength;

    /**
     * 
     * @param array
     */
    public Heap(C[] array){
    	
    	sort(array);
    }
    
    /**
     * 
     * @param array
     */
    public Heap(ArrayList<HeapNode<C>> array){
    	a = array;
    	arrayLength = array.size();
    }
    
    /**
     * 
     */
    public Heap(){
    	arrayLength = 0;
    	a = new ArrayList<HeapNode<C>>();
    }
    
    /**
     * 
     * @param array
     */
    private void sort(C[] array)
    {
    	a = new ArrayList<HeapNode<C>>(array.length);
    	
    	System.out.println("Array Length: " + array.length);
        for(int i = 0; i < array.length; i++){
        	System.out.println("i: " + i);
        	a.add(new vega.graph.vertex.heap.BinaryHeapNode<C>(array[i]));
        }
        arrayLength = a.size();
        heapSort();
    }
    
    /**
     * 
     */
    private void heapSort()
    {
        buildHeap();
        while (arrayLength > 1)
        {
            arrayLength--;
            exchange (0, arrayLength);
            maxHeapify (0);
        } 
    }

    /**
     * 
     */
    public void buildHeap()
    {
        for (int v = ((arrayLength/2) - 1); v>=0; v--)
            maxHeapify (v);
    }

    /**
     * 
     * @param v
     */
    private void maxHeapify(int v)
    {
        int w=2*v+1;    // first descendant of v
        while (w < arrayLength)
        {
            if (w+1 < arrayLength){
            	// is there a second descendant?
            	if (a.get(w+1).getData().compareTo(a.get(w).getData()) > 0){
            		w++;// w is the descendant of v with maximum label
            	}
            }
                
            if (w+1 < arrayLength && a.get(v).getData().compareTo(a.get(w).getData()) >= 0){
            	return;  // v has heap property
            }
            // otherwise
            exchange(v, w);  // exchange labels of v and w
            v=w;        // continue
            w=2*v+1;
        }
    }

    /**
     * 
     * @param i
     * @param j
     */
    private void exchange(int i, int j)
    {
        HeapNode<C> t =a.get(i);
        a.set(i, a.get(j));
        a.set(j, t);
    }

    /**
     * 
     */
	public C findMax() {
		return a.get(0).getData();
	}

	/**
	 * Insert the comparable item into the heap.
	 * @param arg Comparable item to insert
	 */
	public void insert(C arg) {
		
		
	}

	/**
	 * Remove the largest item from the heap.
	 */
	public C removeMax() {
		//TODO
		return null;
	}
	
	/**
	 * @return Returns the array in string form.
	 */
	public String toString(){
		String returnString = "";
		
		for(int i = 0; i < a.size() - 1; i++){
			returnString += a.get(i).getData().toString() + ", ";
		}
		
		returnString += a.get(a.size() - 1).getData().toString();
		
		return returnString;
		
	}
	
	
	public static void main(String args[]){
		String[] stringArray = {"Apple", "Hit", "Dog", "Hippo", "Zebra", "Mandible", "Popper"};
		Heap<String> heap = new Heap<String>(stringArray);
		System.out.println(heap.toString());
	}

}