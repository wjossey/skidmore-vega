package vega.graph.vertex.heap;

import vega.graph.vertex.tree.AbstractBinaryTreeNode;

public class HeapNode<C extends Comparable<C>> extends AbstractBinaryTreeNode<C, interfaces.graph.vertex.heap.HeapNode<C>> implements interfaces.graph.vertex.heap.HeapNode<C>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3263352603505283758L;
	
	public HeapNode(){
		super();
	}
	
	public HeapNode(C c){
		super(c);
	}
	
	public HeapNode(C c, HeapNode<C> left, HeapNode<C> right){
		super(c, left, right);
	}

}
