package vega.algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import vega.Controller;
import vega.dataStructures.heap.fibHeap.FibonacciHeap;
import interfaces.algorithms.GraphAlgorithm;
import interfaces.graph.Graph;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.edge.DirectedEdge;
import vega.graph.vertex.heap.FibonacciHeapNode;
import vega.pseudoCode.Procedure;
import vega.pseudoCode.PseudoCode;

/**
 * 
 * @author w_jossey
 * 
 */
public class Dijkstra<C extends Comparable<? super C>>
		implements
		GraphAlgorithm<Graph<Vertex<DirectedEdge>, DirectedEdge>, Vertex<DirectedEdge>, DirectedEdge> {

	private Graph<Vertex<DirectedEdge>, DirectedEdge> graph;
	private Vertex<DirectedEdge> source;
	private FibonacciHeap<Vertex<DirectedEdge>> heap = new FibonacciHeap<Vertex<DirectedEdge>>();
	private PseudoCode pseudoCode;
	private Controller<Vertex<DirectedEdge>, DirectedEdge> controller;
	private String FILENAME = "Dijkstra";
	private static int instanceCounter = 0;
	private boolean running = false;
	private Procedure dijkstraMove;
	private int instanceID = 0;

	private boolean completed = false;

	/* Hash Map of FibonacciHeapNodes */
	private HashMap<Integer, FibonacciHeapNode<Vertex<DirectedEdge>>> nodeHash = new HashMap<Integer, FibonacciHeapNode<Vertex<DirectedEdge>>>();

	/**
	 * 
	 * @param graph
	 * @param source
	 */
	public Dijkstra(Graph<Vertex<DirectedEdge>, DirectedEdge> graph,
			Vertex<DirectedEdge> source) {

		this.graph = graph;
		initializeNodes(source, graph.getVertexArray());

	}

	/**
	 * 
	 * @param g
	 * @param source
	 * @param destination
	 */
	public Dijkstra(Graph<Vertex<DirectedEdge>, DirectedEdge> g,
			Vertex<DirectedEdge> source, Vertex<DirectedEdge> destination) {

	}

	public Dijkstra() {

	}

	public void run(Graph<Vertex<DirectedEdge>, DirectedEdge> g) {
		running = true;
		instanceID = instanceCounter++;
		graph = g;
		source = g.getVertexArray().get(0);
		pseudoCode = new PseudoCode("Dijkstra's");
		dijkstraMove = new Procedure("Dijkstra(V, E):");
		dijkstraMove.appendLine(dijkstraMove.getTitle()); // 0
		dijkstraMove
				.appendLine("  While there are still cities left to be finalized"); // 1
		dijkstraMove
				.appendLine("      Find the unfinalized city with the lowest best cost");// 2
		dijkstraMove.appendLine("      Call that city x");// 3
		dijkstraMove
				.appendLine("      For each unfinalized city y with a direct flight from x");// 4
		dijkstraMove.appendLine("          Let z be the cost to fly from x to y");// 5
		dijkstraMove
				.appendLine("          If best cost to x + z < current best to y");// 6
		dijkstraMove.appendLine("            Then update y's best cost so far");// 7
		dijkstraMove.appendLine("END");
		pseudoCode.addProcedure(dijkstraMove);
		controller = new Controller<Vertex<DirectedEdge>, DirectedEdge>(graph,
				pseudoCode, this);
		run(); // Let's assume we don't assign a starting vertex

		running = false;
	}

	/* Run the algorithm */
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void run() {
		
		initializeNodes(source, graph.getVertexArray());
		Vertex<DirectedEdge> last = null;
		for (int i = 0; i < graph.getVertexArray().size(); i++) {
			Vertex<DirectedEdge> temp = graph.getVertexArray().get(i);
			temp.setLabel(temp.getName() + " / ?");
		}

		controller.generateGraphInstance(1, dijkstraMove.getTitle());

		while (heap.size() > 0) {
			/* Get the minimum node from the FibHeap */

			FibonacciHeapNode<Vertex<DirectedEdge>> minNode = heap.removeMin();

			/* Get the vertex and the edgeList from the minNode */
			Vertex<DirectedEdge> minVertex = minNode.getData();
			minVertex.currentVertex(true);
			minVertex.setLabel(minVertex.getName() + " / " + minNode.getKey());
			controller.generateGraphInstance(3, dijkstraMove.getTitle());

			ArrayList<DirectedEdge> minVertexEdgeList = minVertex.getEdges();
			for (int i = 0; i < minVertexEdgeList.size(); i++) {
				minVertexEdgeList.get(i).active(true);
			}
			
			controller.generateGraphInstance(5, dijkstraMove.getTitle());

			/* Go through the adjacency list of the minVertex */
			for (int i = 0; i < minVertexEdgeList.size(); i++) {

				/* Edge between the minVertex and some adjacent vertex */
				DirectedEdge tempEdge = minVertexEdgeList.get(i);

				/* Get the destination vertex */
				Vertex<DirectedEdge> tempVertex = (Vertex<DirectedEdge>) minVertexEdgeList
						.get(i).getDestination();
				if(tempVertex.finalized()){
					continue;
				}

				tempVertex.inUse(true);
				controller.generateGraphInstance(7, dijkstraMove.getTitle());

				/* Get the heapNode of the tempVertex */
				FibonacciHeapNode<Vertex<DirectedEdge>> tempNode = nodeHash
						.get(tempVertex.getUID());

				/* Get the current key of the tempHeapNode */
				Double tempKey = tempNode.getKey();

				/*
				 * If the current key is GREATER THAN the the minimumNode's key
				 * summed with the edge weight, we want to decrease the key
				 */
				if (tempKey > (minNode.getKey() + tempEdge.getWeight())) {
					double newWeight = minNode.getKey() + tempEdge.getWeight();

					/* Decrease the key */
					heap.decreaseKey(tempNode, newWeight);
					tempNode.getData().setLabel(
							tempNode.getData().getName() + " / "
									+ Double.toString(newWeight));
					controller
							.generateGraphInstance(8, dijkstraMove.getTitle());

					/* Assign the vertex's previous vertex in the shortest path */
					if(tempVertex.getPreviousVertex() != null){
						tempVertex.getPreviousVertex().getEdge(tempVertex).onPath(false);
					}
					tempVertex.setPreviousVertex(minVertex);
					tempVertex.getPreviousVertex().getEdge(tempVertex).onPath(true);

				}

				tempVertex.inUse(false);
			}
			for (int i = 0; i < minVertexEdgeList.size(); i++) {
				minVertexEdgeList.get(i).active(false);
			}

			minVertex.currentVertex(false);
			minVertex.active(false);
			minVertex.finalized(true);

		}
		
		controller.generateGraphInstance(1, dijkstraMove.getTitle());
		completed = true;
	}

	public void highlightAllShortestPaths() {
		ArrayList<Vertex<DirectedEdge>> vertexList = graph.getVertexArray();
		for (int i = 0; i < vertexList.size(); i++) {
			if (vertexList.get(i) != source) {
				highlightPath(vertexList.get(i));
			}
		}
		
		controller.generateGraphInstance(9, dijkstraMove.getTitle());
	}

	public String printShortestPaths() {
		String returnString = "";
		if (completed) {
			/*
			 * Get the vertexList from the graph, and index of the source vertex
			 * in the list
			 */
			ArrayList<Vertex<DirectedEdge>> vertexList = graph.getVertexArray();
			int indexOfSource = vertexList.indexOf(source);

			/*
			 * For each node that is not the source, print the path from the
			 * source to it
			 */
			for (int i = 0; i < vertexList.size(); i++) {
				if (i != indexOfSource) {
					if (vertexList.get(i).getPreviousVertex() != null) {
						returnString += printPath((Vertex<DirectedEdge>) vertexList
								.get(i));
					}
				}
			}

			return returnString;
		} else {
			return null;
		}

	}

	private static int counter = 0;

	public Controller<Vertex<DirectedEdge>, DirectedEdge> getController() {
		return controller;
	}

	public String getFileName() {
		return FILENAME;
	}

	public int getInstanceID() {
		return instanceID;
	}

	public boolean isRunning() {
		return running;
	}

	private void highlightPath(Vertex<DirectedEdge> destination) {
		Vertex<DirectedEdge> curr = destination;
		if (completed) {
			while (curr != source) {
				curr.getPreviousVertex().getEdge(curr).onPath(true);
				curr = curr.getPreviousVertex();
			}

//			controller.generateGraphInstance(1, dijkstraMove.getTitle());
//
//			curr = destination;
//			while (curr != source) {
//				curr.getPreviousVertex().getEdge(curr).onPath(false);
//				curr = curr.getPreviousVertex();
//			}
		}
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	private String printPath(Vertex<DirectedEdge> v) {
		String returnString = "";
		Vertex<DirectedEdge> prev = v.getPreviousVertex();
		if (prev == null) {
			return v.getName() + " -> ";
		} else {
			counter++;
			returnString += printPath(prev);
			counter--;
			if (counter != 0) {
				returnString += v.getName() + " -> ";
			} else {
				returnString += v.getName() + " ::: ";
			}
			return returnString;
		}
	}

	/**
	 * 
	 * @param source
	 * @param arrayList
	 */
	private void initializeNodes(Vertex<DirectedEdge> source,
			ArrayList<Vertex<DirectedEdge>> arrayList) {

		for (int i = 0; i < arrayList.size(); i++) {
			/*
			 * All nodes other than the source get initialized to
			 * Double.MAX_VALUE
			 */
			if (arrayList.get(i) != source) {

				/* Get the vertex, create a new FibHeapNode */
				Vertex<DirectedEdge> tempVertex = (Vertex<DirectedEdge>) arrayList
						.get(i);

				FibonacciHeapNode<Vertex<DirectedEdge>> node = new FibonacciHeapNode<Vertex<DirectedEdge>>(
						tempVertex, Double.MAX_VALUE);

				/* Put the node in the hash and the fibHeap */
				nodeHash.put(node.getData().getUID(), node);
				heap.insert(node, node.getKey());

			} else {
				/*
				 * Create new FibHeapNode and insert it in the hash and the
				 * FibHeap
				 */
				FibonacciHeapNode<Vertex<DirectedEdge>> node = new FibonacciHeapNode<Vertex<DirectedEdge>>(
						(Vertex<DirectedEdge>) source, 0);

				nodeHash.put(node.getData().getUID(), node);
				heap.insert(node, node.getKey());
			}

		}
	}

}
