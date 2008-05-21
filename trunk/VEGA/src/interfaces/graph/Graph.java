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

}