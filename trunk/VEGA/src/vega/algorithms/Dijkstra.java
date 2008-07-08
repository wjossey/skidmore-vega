package vega.algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import vega.dataStructures.heap.fibHeap.FibonacciHeap;
import interfaces.graph.Graph;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.edge.DirectedEdge;
import vega.graph.vertex.heap.FibonacciHeapNode;

/**
 * 
 * @author w_jossey
 *
 */
public class Dijkstra{
	
	/*Graph*/
	private Graph<Vertex<DirectedEdge>, DirectedEdge> graph;
	/*Source*/ 
	private Vertex<DirectedEdge> source;
	/*Fibonacci Heap*/
	private FibonacciHeap<Vertex<DirectedEdge>> heap = new FibonacciHeap<Vertex<DirectedEdge>>();
	
	private boolean completed = false;
	
	/*Hash Map of FibonacciHeapNodes*/
	private HashMap<Integer,FibonacciHeapNode<Vertex<DirectedEdge>>> nodeHash= new HashMap<Integer, FibonacciHeapNode<Vertex<DirectedEdge>>>();
	
	/**
	 * 
	 * @param graph
	 * @param source
	 */
	@SuppressWarnings("unchecked")
	public Dijkstra(Graph<? extends Vertex<? extends DirectedEdge>, ? extends DirectedEdge> graph, 
			Vertex<? extends DirectedEdge> source){
		
		this.graph = (Graph<Vertex<DirectedEdge>, DirectedEdge>) graph;
		initializeNodes(source, graph.getVertexArray());
		
	}
	
	/**
	 * 
	 * @param g
	 * @param source
	 * @param destination
	 */
	public Dijkstra(Graph<Vertex<DirectedEdge>, DirectedEdge> g, 
			Vertex<DirectedEdge> source, Vertex<DirectedEdge> destination){
		
	}
	
	/**
	 * 
	 * @param source
	 * @param arrayList
	 */
	@SuppressWarnings("unchecked")
	private void initializeNodes(Vertex<? extends DirectedEdge> source, 
			ArrayList<? extends Vertex<? extends DirectedEdge>> arrayList){
		
		for(int i = 0; i < arrayList.size(); i++){
			/*All nodes other than the source get initialized to Double.MAX_VALUE*/
			if(arrayList.get(i) != source){				
				
				/*Get the vertex, create a new FibHeapNode*/
				Vertex<DirectedEdge> tempVertex = (Vertex<DirectedEdge>) arrayList.get(i);
				
				FibonacciHeapNode<Vertex<DirectedEdge>> node = new 
								FibonacciHeapNode<Vertex<DirectedEdge>>(tempVertex, Double.MAX_VALUE);
				
				/*Put the node in the hash and the fibHeap*/
				nodeHash.put(node.getData().getUID(), node);
				heap.insert(node, node.getKey());
				
				/*For debugging*/
				System.out.println(heap.toString());
					
			}else{
				/*Create new FibHeapNode and insert it in the hash and the FibHeap*/
				FibonacciHeapNode<Vertex<DirectedEdge>> node = new 
								FibonacciHeapNode<Vertex<DirectedEdge>>((Vertex<DirectedEdge>) source, 0);
				
				nodeHash.put(node.getData().getUID(), node);
				heap.insert(node, node.getKey());
			}
		
		}
	}
	
	/*Run the algorithm*/
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void run(){
		while(heap.size() > 0){
			/*Get the minimum node from the FibHeap*/
			FibonacciHeapNode<Vertex<DirectedEdge>> minNode = heap.removeMin();
			
			/*For debugging*/
			System.out.println(heap.toString());
			
			/*Get the vertex and the edgeList from the minNode*/
			Vertex<DirectedEdge> minVertex = minNode.getData();
			ArrayList<DirectedEdge> minVertexEdgeList = minVertex.getEdges();
			
			/*Go through the adjacency list of the minVertex*/
			for(int i = 0; i < minVertexEdgeList.size(); i++){
				
				/*Edge between the minVertex and some adjacent vertex*/
				DirectedEdge tempEdge = minVertexEdgeList.get(i);
				
				/*Get the destination vertex*/
				Vertex<DirectedEdge> tempVertex = (Vertex<DirectedEdge>) minVertexEdgeList.get(i).getDestination();
				
				/*Get the heapNode of the tempVertex*/
				FibonacciHeapNode<Vertex<DirectedEdge>> tempNode = nodeHash.get(tempVertex.getUID());
				
				/*Get the current key of the tempHeapNode*/
				Double tempKey = tempNode.getKey();
				
				/*If the current key is GREATER THAN the the minimumNode's key summed with the edge weight, we want to  *
				 * decrease the key																						*/
				if(tempKey > (minNode.getKey() + tempEdge.getWeight())){
					double newWeight = minNode.getKey() + tempEdge.getWeight();
					
					/*Decrease the key*/
					heap.decreaseKey(tempNode, newWeight);
					
					/*Assign the vertex's previous vertex in the shortest path*/
					tempVertex.setPreviousVertex(minVertex);				
				}
			}
		}		
	}
	
	public String printShortestPaths(){
		String returnString = "";
		if(completed){
			/*Get the vertexList from the graph, and index of the source vertex in the list*/
			ArrayList<Vertex<DirectedEdge>> vertexList = (ArrayList<Vertex<DirectedEdge>>) graph.getVertexArray();
			int indexOfSource = vertexList.indexOf(source);
			
			/*For each node that is not the source, print the path from the source to it*/
			for(int i = 0; i < vertexList.size() && i != indexOfSource; i++){
				if(vertexList.get(i).getPreviousVertex() != null){
					returnString += printPath((Vertex<DirectedEdge>) vertexList.get(i));
				}
			}
			
			return returnString;
		}else{
			return null;
		}
		
	}
	
	
	private static int counter = 0;
	
	/**
	 * 
	 * @param v
	 * @return
	 */
	private String printPath(Vertex<DirectedEdge> v){
		String returnString = "";
		Vertex<DirectedEdge> prev = v.getPreviousVertex();
		if(prev == null){
			return v.getName() + " -> ";
		}else{
			counter++;
			returnString += printPath(prev);
			counter--;
			if(counter != 0){
				returnString += v.getName() + " -> ";
			}else{
				returnString += v.getName() + " ::: ";
			}
			return returnString;
		}
	}
	
}
