package interfaces.graph.vertex.tree;

import java.util.ArrayList;

import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.vertex.tree.TreeNode;

public interface TreeNode<C extends Comparable<C>, T extends TreeNode<C,T,E>, E extends Edge> extends Vertex<T,E>{

	/**
	 * 
	 * @return
	 */
	public T getParentNode();

	/**
	 * 
	 * @param parent
	 */
	public void setParentNode(T parent);

	/**
	 * 
	 * @param child
	 */
	public void addChild(T child);

	/**
	 * 
	 * @return
	 */
	public ArrayList<T> getChildren();

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
	public int getID();
	
	/**
	 * 
	 * @param child
	 * @return
	 */
	public E getEdge(T child);
	
	/**
	 * 
	 * @return
	 */

}