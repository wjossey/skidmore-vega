package vega.helperClasses;

import java.util.ArrayList;
import java.util.Random;

import interfaces.graph.Graph;
import vega.graph.edge.DirectedEdge;
import vega.graph.AbstractGraph;
import vega.graph.vertex.Vertex;
import vega.graph.edge.UndirectedEdge;

public class GraphGenerator {

	/**
	 * 
	 * @param numVertices
	 * @return
	 */
	public static Graph<? extends Vertex<? extends UndirectedEdge>, ? extends  UndirectedEdge> generateUndirectedGraphWithTriangleInequality(
			int numVertices, int numEdges) {

		Graph<Vertex<UndirectedEdge>, UndirectedEdge> graph = new AbstractGraph<Vertex<UndirectedEdge>, UndirectedEdge>(
				numVertices, false);
		generateUndirectedVertices(graph, numVertices);
		if(numEdges == ((numVertices * (numVertices - 1)) / 2)){
			//Complete graph
			generateCompleteUndirectedEdgesWithTriangleInequality(graph);
		}else{
			generateUndirectedEdgesWithTriangleInequality(graph, numEdges);
		}
		return graph;
	}

	public static Graph<? extends Vertex<? extends UndirectedEdge>, ? extends UndirectedEdge> generateUndirectedGraph(
			int numVertices, int numEdges) {

		Graph<Vertex<UndirectedEdge>, UndirectedEdge> graph = new AbstractGraph<Vertex<UndirectedEdge>, UndirectedEdge>(
				numVertices, numEdges, false);
		generateUndirectedVertices(graph, numVertices);
		generateRandomUndirectedEdges(graph, numEdges);

		return graph;

	}

	/**
	 * 
	 * @param numVertex
	 * @param numEdges
	 * @return
	 */
	public static Graph<? extends Vertex<? extends DirectedEdge>, ? extends DirectedEdge> generateDirectedGraph(
			int numVertices, int numEdges) {

		Graph<Vertex<DirectedEdge>, DirectedEdge> graph = new AbstractGraph<Vertex<DirectedEdge>, DirectedEdge>(
				numVertices, numEdges, true);

		generateDirectedVertices(graph, numVertices);
		generateRandomDirectedEdges(graph, numEdges);

		return graph;
	}

	/**
	 * Random vertex generator. Creates vertices and gives them X and Y
	 * coordinates. These coordinates are then used to compute the distances
	 * between the vertices and create edges that satisfy the triangle
	 * inequality
	 */
	private static void generateUndirectedVertices(
			Graph<Vertex<UndirectedEdge>, UndirectedEdge> graph, int numVertices) {

		for (int i = 0; i < numVertices; i++) {
			int randX = (int) (Math.random() * (numVertices * 10));
			int randY = (int) (Math.random() * (numVertices * 10));
			Vertex<UndirectedEdge> tempVertex = new Vertex<UndirectedEdge>(
					randX, randY);
			graph.addVertex(tempVertex);
		}

	}

	private static void generateDirectedVertices(
			Graph<Vertex<DirectedEdge>, DirectedEdge> graph, int numVertices) {

		for (int i = 0; i < numVertices; i++) {
			int randX = (int) (Math.random() * (numVertices * 10));
			int randY = (int) (Math.random() * (numVertices * 10));
			Vertex<DirectedEdge> tempVertex = new Vertex<DirectedEdge>(randX,
					randY);
			graph.addVertex(tempVertex);
		}

	}

	/**
	 * This algorithm goes through and produces all of the edge weights between
	 * the vertices if we're working with a complete graph. This is particularly
	 * useful in situations such as TSP where we are dealing with some complete
	 * Graph and want it to auto-generate the edges for us
	 */
	private static void generateCompleteUndirectedEdgesWithTriangleInequality(
			Graph<Vertex<UndirectedEdge>, UndirectedEdge> graph) {

		ArrayList<Vertex<UndirectedEdge>> vertexList = graph.getVertexArray();

		/* For all of the vertices in the array... */
		for (int i = 0; i < vertexList.size(); i++) {
			/* Get the x1 and y1 values */
			int x1 = vertexList.get(i).getX();
			int y1 = vertexList.get(i).getY();

			/*
			 * For all other vertices that have not already had their distances
			 * computed...
			 */
			for (int j = i + 1; j < vertexList.size(); j++) {

				/* Get x2 and y2 */
				int x2 = vertexList.get(j).getX();
				int y2 = vertexList.get(j).getY();

				double xSquared;
				double ySquared;

				if (x1 > x2) {
					xSquared = Math.pow((x1 - x2), 2);
				} else {
					xSquared = Math.pow((x2 - x1), 2);
				}
				if (y1 > y2) {
					ySquared = Math.pow((y1 - y2), 2);
				} else {
					ySquared = Math.pow((y2 - y1), 2);
				}

				/* Distance formula! */
				double distanceSquared = (xSquared + ySquared);
				double dist = Math.sqrt(distanceSquared);

				/* Create the edge and add it to both vertices */
				UndirectedEdge e = new UndirectedEdge(vertexList.get(i),
						vertexList.get(j), dist);
				vertexList.get(i).addEdge(e);
				vertexList.get(j).addEdge(e);
				graph.addEdge(e);
			}
		}

	}
	
	private static void generateUndirectedEdgesWithTriangleInequality(
			Graph<Vertex<UndirectedEdge>, UndirectedEdge> graph, int numEdges) {
		
		Random generator = new Random();
		ArrayList<Vertex<UndirectedEdge>> vertexList = graph.getVertexArray();

		/* For all of the vertices in the array... */
		for (int i = 0; i < numEdges; i++) {
			/* Get the x1 and y1 values */
			int vertexIndex = generator.nextInt(vertexList.size());
			int x1 = vertexList.get(vertexIndex).getX();
			int y1 = vertexList.get(vertexIndex).getY();

			int destinationIndex;
			do{
				destinationIndex = generator.nextInt(vertexList.size());
			}while(vertexList.get(vertexIndex).getEdge(vertexList.get(destinationIndex)) == null);
			
			int x2 = vertexList.get(destinationIndex).getX();
			int y2 = vertexList.get(destinationIndex).getY();
			
			double xSquared;
			double ySquared;

			if (x1 > x2) {
				xSquared = Math.pow((x1 - x2), 2);
			} else {
				xSquared = Math.pow((x2 - x1), 2);
			}
			if (y1 > y2) {
				ySquared = Math.pow((y1 - y2), 2);
			} else {
				ySquared = Math.pow((y2 - y1), 2);
			}

			/* Distance formula! */
			double distanceSquared = (xSquared + ySquared);
			double dist = Math.sqrt(distanceSquared);

			/* Create the edge and add it to both vertices */
			UndirectedEdge e = new UndirectedEdge(vertexList.get(vertexIndex),
					vertexList.get(destinationIndex), dist);
			
			graph.addEdge(e);
		}

	}

	/**
	 * Generates random directed edges. The algorithm gives no guarantee for
	 * connectivity. It doesn't allow for self loops or multiple edges from the
	 * same source to the same destination.
	 * 
	 * @param graph
	 * @param numEdges
	 */
	private static void generateRandomDirectedEdges(
			Graph<Vertex<DirectedEdge>, DirectedEdge> graph, int numEdges) {

		ArrayList<Vertex<DirectedEdge>> vertexList = graph.getVertexArray();
		for (int i = 0; i < numEdges; i++) {

			Random generator = new Random();
			int fromVertex, toVertex;
			int randomWeight;

			do {
				fromVertex = generator.nextInt(vertexList.size());
				toVertex = generator.nextInt(vertexList.size());
				randomWeight = generator.nextInt(vertexList.size() * 10) + 1;
			} while (fromVertex != i
					&& toVertex != i
					&& vertexList.get(fromVertex).getEdge(
							vertexList.get(toVertex)) == null);

			DirectedEdge tempDirectedEdge = new DirectedEdge(vertexList
					.get(fromVertex), vertexList.get(toVertex), randomWeight);

			graph.addEdge(tempDirectedEdge);
		}
	}

	/**
	 * 
	 * @param graph
	 * @param numEdges
	 */
	private static void generateRandomUndirectedEdges(
			Graph<Vertex<UndirectedEdge>, UndirectedEdge> graph, int numEdges) {
		ArrayList<Vertex<UndirectedEdge>> vertexList = graph.getVertexArray();
		for (int i = 0; i < numEdges; i++) {

			Random generator = new Random();
			int fromVertex, toVertex;
			int randomWeight;

			do {
				fromVertex = generator.nextInt(vertexList.size());
				toVertex = generator.nextInt(vertexList.size());
				randomWeight = generator.nextInt(vertexList.size() * 10) + 1;
			} while (fromVertex != i
					&& toVertex != i
					&& vertexList.get(fromVertex).getEdge(
							vertexList.get(toVertex)) == null);

			UndirectedEdge tempDirectedEdge = new UndirectedEdge(vertexList
					.get(fromVertex), vertexList.get(toVertex), randomWeight);

			graph.addEdge(tempDirectedEdge);
		}
	}

}
