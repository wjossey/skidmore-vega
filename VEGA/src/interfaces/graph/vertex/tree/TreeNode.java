package interfaces.graph.vertex.tree;

import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.vertex.tree.TreeNode;

public interface TreeNode<E extends Edge> extends Vertex<E>{

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
	public TreeNode<E> getParentNode();

	/**
	 * 
	 * @param parent
	 */
	public void setParentNode(TreeNode<E> parent);

	/**
	 * 
	 * @param child
	 */
	public void addChild(TreeNode<E> child);

	/**
	 * 
	 * @return
	 */
	public TreeNode<E>[] getChildren();

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
	public E getEdge(TreeNode<E> child);

}