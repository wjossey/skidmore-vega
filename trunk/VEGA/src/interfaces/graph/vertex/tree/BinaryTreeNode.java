package interfaces.graph.vertex.tree;

import interfaces.graph.edge.UndirectedEdge;

public interface BinaryTreeNode<C extends Comparable<? super C>, T extends BinaryTreeNode<? super C, ? super T>> extends TreeNode<C,T,UndirectedEdge>{

	/**
	 * 
	 * @return
	 */
	public T getLeftChild();

	/**
	 * 
	 * @return
	 */
	public T getRightChild();

	/**
	 * 
	 * @param left
	 */
	public void setLeftChild(T left);

	/**
	 * 
	 * @param right
	 */
	public void setRightChild(T right);

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