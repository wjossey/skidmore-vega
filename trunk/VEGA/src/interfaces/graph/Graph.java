package interfaces.graph;

import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;

public interface Graph {

	public int getSize();

	/**
	 * Returns the vertex array for the graph
	 * @return the vertex array for the graph
	 */
	public Vertex[] getVertexArray();

	/**
	 * 
	 * @return
	 */
	public Edge[] getEdgeArray();

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
	public void addVertex(Vertex v);
	
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
	public boolean removeVertex(Vertex v);
	
	/**
	 * Removes the edge from the graph.
	 * @param e Edge to remove
	 * @return Returns true if the operation succeeds, returns false if it fails.
	 */
	public boolean removeEdge(Edge e);

}