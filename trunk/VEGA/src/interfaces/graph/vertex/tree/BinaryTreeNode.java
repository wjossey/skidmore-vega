package interfaces.graph.vertex.tree;

import interfaces.graph.edge.Edge;

public interface BinaryTreeNode<E extends Edge> extends TreeNode<E>{

	/**
	 * 
	 * @return
	 */
	public BinaryTreeNode<E> getLeftNode();

	/**
	 * 
	 * @return
	 */
	public BinaryTreeNode<E> getRightNode();

	/**
	 * 
	 * @param left
	 */
	public void setLeftNode(BinaryTreeNode<E> left);

	/**
	 * 
	 * @param right
	 */
	public void setRightNode(BinaryTreeNode<E> right);

	/**
	 * 
	 * @param parent
	 */
	public void setParentNode(BinaryTreeNode<E> parent);
	
	/**
	 * @return Returns the parent node
	 */
	public BinaryTreeNode<E> getParentNode();

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