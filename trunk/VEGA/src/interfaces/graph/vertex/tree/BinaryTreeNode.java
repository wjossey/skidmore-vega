package interfaces.graph.vertex.tree;

import interfaces.graph.edge.Edge;

public interface BinaryTreeNode<C extends Comparable<C>, E extends Edge> extends TreeNode<C,E>{

	/**
	 * 
	 * @return
	 */
	public BinaryTreeNode<C,E> getLeftNode();

	/**
	 * 
	 * @return
	 */
	public BinaryTreeNode<C,E> getRightNode();

	/**
	 * 
	 * @param left
	 */
	public void setLeftNode(BinaryTreeNode<C,E> left);

	/**
	 * 
	 * @param right
	 */
	public void setRightNode(BinaryTreeNode<C,E> right);

	/**
	 * 
	 * @param parent
	 */
	public void setParentNode(BinaryTreeNode<C,E> parent);
	
	/**
	 * @return Returns the parent node
	 */
	public BinaryTreeNode<C,E> getParentNode();

	/**
	 * 
	 * @return
	 */
	public boolean isRightChild(); // end isRightChild()

	/**
	 * 
	 * @return
	 */
	public boolean isLeftChild(); // end isLeftChild()

}