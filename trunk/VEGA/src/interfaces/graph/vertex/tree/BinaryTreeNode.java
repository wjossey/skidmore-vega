package interfaces.graph.vertex.tree;

public interface BinaryTreeNode extends TreeNode{

	/**
	 * 
	 * @return
	 */
	public BinaryTreeNode getLeftNode();

	/**
	 * 
	 * @return
	 */
	public BinaryTreeNode getRightNode();

	/**
	 * 
	 * @param left
	 */
	public void setLeftNode(BinaryTreeNode left);

	/**
	 * 
	 * @param right
	 */
	public void setRightNode(BinaryTreeNode right);

	/**
	 * 
	 * @param parent
	 */
	public void setParentNode(BinaryTreeNode parent);
	
	/**
	 * @return Returns the parent node
	 */
	public BinaryTreeNode getParentNode();

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