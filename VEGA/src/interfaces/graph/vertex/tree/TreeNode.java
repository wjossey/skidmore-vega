package interfaces.graph.vertex.tree;

import java.util.ArrayList;

import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.vertex.tree.TreeNode;

public interface TreeNode<C extends Comparable<C>, E extends Edge> extends Vertex<E>{
	public static final boolean RED = true;
    public static final boolean BLACK = false;
	
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
	public TreeNode<C,E> getParentNode();

	/**
	 * 
	 * @param parent
	 */
	public void setParentNode(TreeNode<C,E> parent);

	/**
	 * 
	 * @param child
	 */
	public void addChild(TreeNode<C,E> child);

	/**
	 * 
	 * @return
	 */
	public ArrayList<TreeNode<C,E>> getChildren();

	/**
	 * 
	 * @return
	 */
	public C getData();

	/**
	 * 
	 * @param c
	 */
	public void setData(C c);

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
	public E getEdge(TreeNode<C,E> child);

}