package vega.graph.vertex.tree;

/**
 * 
 * @author Weston Jossey
 *
 * @param <C>
 */
public class RedBlackTreeNode<C extends Comparable<C>> extends AbstractBinaryTreeNode<C, interfaces.graph.vertex.tree.RedBlackTreeNode<C>> implements interfaces.graph.vertex.tree.RedBlackTreeNode<C>{
	private static final long serialVersionUID = -379091460216646444L;
	boolean color;
	
	/**
	 * 
	 * @param c
	 */
	public RedBlackTreeNode(C c) {
		super(c);
	}
	
	public RedBlackTreeNode(C c, boolean color){
		super(c);
		this.color = color;
	}
	
	public RedBlackTreeNode(C c, RedBlackTreeNode<C> leftNode, RedBlackTreeNode<C> rightNode){
		super(c, leftNode, rightNode);
	}
	
	public RedBlackTreeNode(C c, RedBlackTreeNode<C> leftNode, RedBlackTreeNode<C> rightNode, boolean color){
		super(c, leftNode, rightNode);
		this.color = color;
	}
	
	
	public boolean getColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}
	
	public boolean isColor(boolean color){
		if(this.color == color){
			return true;
		}else{
			return false;
		}
	}
	
}
