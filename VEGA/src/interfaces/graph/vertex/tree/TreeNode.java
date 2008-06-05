package interfaces.graph.vertex.tree;

import interfaces.graph.edge.tree.TreeEdge;
import interfaces.graph.vertex.tree.TreeNode;

public interface TreeNode {

	/**
	 * @param c
	 * @return
	 */
	public boolean isColor(boolean c);

	/**
	 * 
	 * @return
	 */
	public String graphVizName(); // end graphVizName()

	/**
	 * 
	 * @return
	 */
	public TreeNode getParentNode();

	/**
	 * 
	 * @param parent
	 */
	public void setParentNode(TreeNode parent);

	/**
	 * 
	 * @param child
	 */
	public void addChild(TreeNode child);

	/**
	 * 
	 * @return
	 */
	public TreeNode[] getChildren();

	/**
	 * 
	 * @return
	 */
	public Comparable<?> getData();

	/**
	 * 
	 * @param c
	 */
	public void setData(Comparable<?> c);

	/**
	 * 
	 * @return
	 */
	public int getHeight();

	/**
	 * 
	 * @param height
	 */
	public void setHeight(int height);

	/**
	 * 
	 * @return
	 */
	public boolean getColor();

	/**
	 * @param color The color to set.
	 */
	public void setColor(boolean color);
	
	/**
	 * 
	 * @return
	 */
	public int getID();
	
	/**
	 * 
	 * @param child
	 * @return
	 */
	public TreeEdge getEdge(TreeNode child);

}