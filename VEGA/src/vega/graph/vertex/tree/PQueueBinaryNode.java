package vega.graph.vertex.tree;

/**
 * 
 * @author Weston Jossey
 *
 * @param <C>
 */
public class PQueueBinaryNode<C extends Comparable<? super C>> extends AbstractBinaryTreeNode<C, PQueueBinaryNode<C>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -642587299116323883L;
	
	private int priority = Integer.MAX_VALUE;
	
	public PQueueBinaryNode(C c){
		super(c);
	}
	
	public PQueueBinaryNode(C c, PQueueBinaryNode<C> left, PQueueBinaryNode<C> right){
		super(c, left, right);
	}
	
	public PQueueBinaryNode(){
		super();
	}
	
	public int priority(int priority){
		return this.priority = priority;
	}
	
	public int priority(){
		return this.priority;
	}

}
