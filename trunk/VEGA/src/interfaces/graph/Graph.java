package interfaces.graph;

import java.util.ArrayList;

import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;

public interface Graph<V extends Vertex<? super E>, E extends Edge>{

	public int getSize();

	/**
	 * Returns the vertex array for the graph
	 * @return the vertex array for the graph
	 */
	public ArrayList<V> getVertexArray();

	/**
	 * Returns the array of edges.
	 * @return Edge array to return
	 */
	public ArrayList<E> getEdgeArray();

	/**
	 * Returns whether we have a directed or undirected graph 
	 * @return return the boolean value of a directed (true) or undirected (false) graph
	 */
	public boolean isDigraph();

	/**
	 * Reset the boolean values for whether or not a city has been visited on a tour
	 **/
	public void resetVerticiesVisited();
	
	/**
	 * Adds the vertex to the graph
	 * @param v Vertex to add
	 */
	public void addVertex(V v);
	
	/**
	 * Returns the total number of vertices in the graph.  Also referred to as the size
	 * of the graph.
	 * @return
	 */
	public int size();
	
	/**
	 * Removes the vertex from the graph.
	 * @param v  Vertex to remove.
	 * @return Returns true if the operation succeeds, returns false if it fails.
	 */
	public boolean removeVertex(V vertex);
	
	/**
	 * Removes the edge from the graph.
	 * @param e Edge to remove
	 * @return Returns true if the operation succeeds, returns false if it fails.
	 */
	public boolean removeEdge(E e);
	
	/**
	 * Adds an edge to the graph.
	 * @param edge Edge to add to the graph
	 */
	public void addEdge(E e);

}