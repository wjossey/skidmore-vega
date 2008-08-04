package vega.graph;

import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.edge.Edge;
import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.Graph;
import java.util.ArrayList;
import java.util.Random;

import vega.exceptions.EdgeBoundException;
import vega.exceptions.InsertOutOfBoundsException;
import vega.exceptions.NullRemovalException;
import vega.helperClasses.EdgeHelper;
import vega.helperClasses.GraphGenerator;
import vega.helperClasses.VertexHelper;

/**
 *The graph class is built off the mathematical structure of the same name. As
 * stated on wikipedia: "In mathematics and computer science, a graph is the
 * basic object of study in graph theory. Informally speaking, a graph is a set
 * of objects called points, nodes, or vertices connected by links called lines
 * or edges. In a proper graph, which is by default undirected, a line from
 * point A to point B is considered to be the same thing as a line from point B
 * to point A. In a digraph, short for directed graph, the two directions are
 * counted as being distinct arcs or directed edges. Typically, a graph is
 * depicted in diagrammatic form as a set of dots (for the points, vertices, or
 * nodes), joined by curves (for the lines or edges)."
 * 
 * @author Weston Jossey
 * @param <V>
 *            Must be a class that implements the Vertex interface.
 * @param <E>
 *            Must be a class that implements the Edge interface.
 */
public class AbstractGraph<V extends Vertex<? extends E>, E extends Edge>
		implements Graph<V, E> {

	protected int maxVertexSize = 0;
	protected int maxEdgeSize = 0;
	protected int positionInArray = 0;
	protected ArrayList<V> vertexList;
	protected ArrayList<E> edgeList;
	protected boolean digraph;

	public AbstractGraph() {
		this.maxVertexSize = -1;
		this.maxEdgeSize = -1;
		vertexList = new ArrayList<V>();
		edgeList = new ArrayList<E>();
	}

	public AbstractGraph(boolean isDigraph) {
		this.maxVertexSize = -1;
		this.maxEdgeSize = -1;
		this.digraph = isDigraph;
		vertexList = new ArrayList<V>();
		edgeList = new ArrayList<E>();
	}

	/**
	 * The constructor method for the Graph class. Takes in a pre-set vertex and
	 * a boolean setting to distinguish if it is a directed-graph or
	 * undirected-graph.
	 * 
	 * @param vertexSize
	 *            the number of vertices in the graph
	 * @param digraph
	 *            a directed graph if true, undirected if false
	 */
	public AbstractGraph(int vertexSize, boolean isDigraph) {
		this.maxVertexSize = vertexSize;
		this.maxEdgeSize = -1;
		this.digraph = isDigraph;
		vertexList = new ArrayList<V>(vertexSize);
		edgeList = new ArrayList<E>();
	}

	public AbstractGraph(V[] vertexArray, E[] edgeArray) {
		vertexList = new ArrayList<V>(vertexArray.length);
		edgeList = new ArrayList<E>(edgeArray.length);

		for (int i = 0; i < vertexArray.length; i++) {
			vertexList.add(vertexArray[i]);
		}
		for (int i = 0; i < edgeArray.length; i++) {
			edgeList.add(edgeArray[i]);
		}
	}

	public AbstractGraph(ArrayList<V> vertexList, int edgeSize,
			boolean isDigraph) {

		if (isDigraph) {
			if (edgeSize > ((vertexList.size() * (vertexList.size() - 1)) / 2) * 2) {
				throw new EdgeBoundException(
						"Edge parameter is greater than the maximum"
								+ " number of edges permitted in a complete graph.");
			}
		} else {
			if (edgeSize > ((vertexList.size() * (vertexList.size() - 1)) / 2)) {
				throw new EdgeBoundException(
						"Edge parameter is greater than the maximum"
								+ " number of edges permitted in a complete graph.");
			}
		}

		this.vertexList = vertexList;
		this.edgeList = new ArrayList<E>(edgeSize);
		this.digraph = isDigraph;
		this.maxVertexSize = -1;
		this.maxEdgeSize = edgeSize;

	}

	public AbstractGraph(ArrayList<V> vertexList, boolean isDigraph) {
		this.vertexList = vertexList;
		this.digraph = isDigraph;
		this.maxVertexSize = vertexList.size();
		this.maxEdgeSize = -1;
		this.edgeList = new ArrayList<E>();

	}

	public AbstractGraph(ArrayList<V> vertexList, ArrayList<E> edgeList,
			boolean isDigraph) {
		this.vertexList = vertexList;
		this.edgeList = edgeList;
		this.maxVertexSize = -1;
		this.maxEdgeSize = -1;
		this.digraph = isDigraph;
	}

	/**
	 * Constructor class when we don't have a complete graph. Has yet to be
	 * implemented yet. The problem generating random graphs is mostly due to
	 * the question of whether or not the graph is strongly connected or not.
	 * 
	 * @param vertexSize
	 *            the number of vertices in the graph
	 * @param edgeSize
	 *            the number of edges in the graph
	 * @param digraph
	 *            a directed graph if true, undirected if false
	 */
	public AbstractGraph(int vertexSize, int edgeSize, boolean digraph) {
		if (digraph) {
			if (edgeSize > ((vertexSize * (vertexSize - 1)) / 2) * 2) {
				throw new EdgeBoundException(
						"Edge parameter is greater than the maximum"
								+ " number of edges permitted in a complete graph.");
			}
		} else {
			if (edgeSize > ((vertexSize * (vertexSize - 1)) / 2)) {
				throw new EdgeBoundException(
						"Edge parameter is greater than the maximum"
								+ " number of edges permitted in a complete graph.");
			}
		}

		this.digraph = digraph;
		this.maxEdgeSize = edgeSize;
		this.maxVertexSize = vertexSize;
		vertexList = new ArrayList<V>(vertexSize);
		edgeList = new ArrayList<E>(edgeSize);

	}

	@SuppressWarnings("unchecked")
	public void generateRandomGraph(boolean triangleInequality) {
		Random generator = new Random();
		int edgeSize, vertexSize;

		if (maxVertexSize == -1) {
			vertexSize = generator.nextInt(100);
		} else {
			vertexSize = maxVertexSize;
		}

		if (maxEdgeSize == -1) {
			// Generate random edge size
			edgeSize = generator.nextInt((vertexSize * (vertexSize - 1)) / 2);
		} else {
			edgeSize = maxEdgeSize;
		}

		if (digraph == true) {

			Graph<Vertex<DirectedEdge>, DirectedEdge> g = (Graph<Vertex<DirectedEdge>, DirectedEdge>) GraphGenerator
					.generateDirectedGraph(vertexSize, edgeSize);
			vertexList = (ArrayList<V>) g.getVertexArray();
			edgeList = (ArrayList<E>) g.getEdgeArray();

		} else {
			if (triangleInequality) {
				Graph<Vertex<UndirectedEdge>, UndirectedEdge> g = (Graph<Vertex<UndirectedEdge>, UndirectedEdge>) GraphGenerator
						.generateUndirectedGraphWithTriangleInequality(
								vertexSize, edgeSize);
				vertexList = (ArrayList<V>) g.getVertexArray();
				edgeList = (ArrayList<E>) g.getEdgeArray();
			} else {
				Graph<Vertex<UndirectedEdge>, UndirectedEdge> g = (Graph<Vertex<UndirectedEdge>, UndirectedEdge>) GraphGenerator
						.generateUndirectedGraph(vertexSize, edgeSize);
				vertexList = (ArrayList<V>) g.getVertexArray();
				edgeList = (ArrayList<E>) g.getEdgeArray();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vega.graph.Graph#getVertexArray()
	 */

	public ArrayList<V> getVertexArray() {
		return vertexList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vega.graph.Graph#getSize()
	 */
	public int getSize() {
		return vertexList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vega.graph.Graph#getEdgeArray()
	 */

	public ArrayList<E> getEdgeArray() {
		return edgeList;
	}

	/**
	 * Returns the size of the graph
	 * 
	 * @return returns the integer value for the number of vertices
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see vega.graph.Graph#i
	 */
	public boolean isDigraph() {
		return digraph;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vega.graph.Graph#resetVerticiesVisited()
	 */
	public void resetVerticiesVisited() {
		for (int i = 0; i < vertexList.size(); i++) {
			vertexList.get(i).inUse(false);
		}
	}

	/**
	 * Converts the graph into a DOT string.
	 * 
	 * @return Returns a well formed DOT string of the graph.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		String returnString = "";

		if (digraph) {
			returnString = "digraph G{\n";
			returnString += "nodesep=.5;\n";
			returnString += VertexHelper.vertexListToString(vertexList) + "\n";
			returnString += EdgeHelper
					.allEdgesDirectedGraph((ArrayList<? extends Vertex<? extends DirectedEdge>>) vertexList)
					+ "\n";
		} else {
			returnString += "graph G{\n;";
			returnString += VertexHelper.vertexListToString(vertexList) + "\n";
			returnString += EdgeHelper.allEdgesWithoutRepeats(vertexList)
					+ "\n";
		}

		returnString += "subgraph clusterLegend {\n";
		returnString += "style=filled;\n";
		returnString += "color=lightgrey;\n";
		returnString += "rank=\"sink\"; // put nodes in this subgraph on a separate, lower rank.\n";
		returnString += "label=\"Legend\";\n";
		returnString += "current [shape=ellipse, width=3, fontsize=20, fillcolor=red, style=filled, label=\"Current\"];";
		returnString += "unfinal [shape=ellipse, width=3, fontsize=20, fillcolor=green, style=filled, label=\"Unfinalized\"];\n";
		returnString += "final [shape=ellipse, width=3, fontsize=20, fillcolor=white, style=filled, label=\"Finalized\"];";

		returnString += "}\n";

		return returnString;
	}

	public int size() {
		return vertexList.size();
	}

	public void addVertices(ArrayList<V> v) {
		if (maxVertexSize != -1 && v.size() + vertexList.size() > maxVertexSize) {
			throw new InsertOutOfBoundsException(
					"Vertex list has reached its capacity");
		} else {
			vertexList.addAll(v);
		}
	}

	/**
	 * Adds a vertex to the vertex array. Internal method only.
	 */
	public void addVertex(V v) {
		if (maxVertexSize == -1 || vertexList.size() < maxVertexSize) {
			vertexList.add(positionInArray++, v);
		} else {
			throw new InsertOutOfBoundsException();
		}
	}

	public boolean removeVertex(V v) {
		if (vertexList.remove(v) == false) {
			throw new NullRemovalException(
					"Vertex cannot be removed because it does not exist");
		} else {
			return true;
		}
	}

	public boolean removeEdge(E e) {
		if (edgeList.remove(e) == false) {
			throw new NullRemovalException(
					"Edge cannot be removed because it does not exist");
		} else {
			return true;
		}
	}

	public void addEdges(ArrayList<E> e) {
		if (maxEdgeSize != -1 && e.size() + vertexList.size() > maxEdgeSize) {
			throw new InsertOutOfBoundsException(
					"Edge list has reached its capacity");
		} else {
			edgeList.addAll(e);
		}
	}

	public void addEdge(E e) {
		if (maxEdgeSize == -1 || maxEdgeSize > edgeList.size()) {
			edgeList.add(e);
		} else {
			throw new InsertOutOfBoundsException(
					"Edge list has reached its capacity");
		}
	}

}
