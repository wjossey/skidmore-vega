package vega.graph.vertex.heap;

import vega.graph.vertex.tree.AbstractBinaryTreeNode;

/**
 * 
 * @author Weston Jossey
 *
 * @param <C>
 */
public class BinaryHeapNode<C extends Comparable<C>> extends AbstractBinaryTreeNode<C, interfaces.graph.vertex.heap.HeapNode<C>> implements interfaces.graph.vertex.heap.HeapNode<C>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3263352603505283758L;
	
	public BinaryHeapNode(){
		super();
	}
	
	public BinaryHeapNode(C c){
		super(c);
	}
	
	public BinaryHeapNode(C c, BinaryHeapNode<C> left, BinaryHeapNode<C> right){
		super(c, left, right);
	}
	
	public String toString(){
		return "";
	}

}
