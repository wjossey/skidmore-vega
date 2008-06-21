package vega.graph.vertex.tree;

public class BinaryTreeNode<C extends Comparable<C>> extends AbstractBinaryTreeNode<C, BinaryTreeNode<C>> implements interfaces.graph.vertex.tree.BinaryTreeNode<C, BinaryTreeNode<C>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409729029534322212L;
	
	public BinaryTreeNode(C data){
		super(data);
	}
	
	public BinaryTreeNode(C data, BinaryTreeNode<C> left, BinaryTreeNode<C> right){
		super(data,left,right);
	}
	
}
