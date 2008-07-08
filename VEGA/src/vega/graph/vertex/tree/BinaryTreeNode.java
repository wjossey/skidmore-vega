package vega.graph.vertex.tree;

public class BinaryTreeNode<C extends Comparable<? super C>> extends AbstractBinaryTreeNode<C, BinaryTreeNode<C>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409729029534322212L;
	
	public BinaryTreeNode(C c){
		super(c);
	}
	
	public BinaryTreeNode(C c, BinaryTreeNode<C> left, BinaryTreeNode<C> right){
		super(c, left, right);
	}
	
	public BinaryTreeNode(){
		super();
	}

}
