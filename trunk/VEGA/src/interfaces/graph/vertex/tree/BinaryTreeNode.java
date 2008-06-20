package interfaces.graph.vertex.tree;

import interfaces.graph.edge.UndirectedEdge;

public interface BinaryTreeNode<C extends Comparable<C>, T extends BinaryTreeNode<C,T>> extends TreeNode<C,T,UndirectedEdge<T>>{

	/**
	 * 
	 * @return
	 */
	public T getLeftNode();

	/**
	 * 
	 * @return
	 */
	public T getRightNode();

	/**
	 * 
	 * @param left
	 */
	public void setLeftNode(T left);

	/**
	 * 
	 * @param right
	 */
	public void setRightNode(T right);

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