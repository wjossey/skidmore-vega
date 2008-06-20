package interfaces.graph.vertex.tree;

public interface RedBlackTreeNode<C extends Comparable<C>> extends BinaryTreeNode<C, RedBlackTreeNode<C>>{
	
	public static final boolean RED = true;
	public static final boolean BLACK = false;
	
	/**
	 * 
	 * @param color
	 */
	public void setColor(boolean color);
	
	/**
	 * 
	 * @return
	 */
	public boolean getColor();
	
	/**
	 * 
	 * @param color
	 * @return
	 */
	public boolean isColor(boolean color);
	
	
}
