package vega.helperClasses;

import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;

import java.util.ArrayList;


/**
 * 
 * @author Weston Jossey
 *
 */
public class EdgeHelper {
	
	public static <E extends Edge> ArrayList<E> sortEdges(ArrayList<E> edgeArray){
			
		return edgeArray;
		
	}

	/**
	 * 
	 * @param <V>
	 * @param <E>
	 * @param edgeList
	 * @param index
	 * @return
	 */
	public static <V extends Vertex<E>, E extends Edge> String edgeListToString(ArrayList<E> edgeList, int index) {
	    String returnString = "";
	    for (int i = index; i < edgeList.size(); i++) {
	        returnString += edgeList.get(i).toString() + "\n";
	    }
	
	    return returnString;
	}

	/**
	 *
	 * @param edgeList
	 */
	public static <E extends Edge> void sortEdgesByDistance(E[] edgeList) {
	    SortingAlgorithms.quicksort(edgeList, 0, edgeList.length - 1);
	}

	/**
	 * 
	 * @param edgeList
	 * @return
	 */
	public static <E extends Edge> String edgeListToString(ArrayList<E> edgeList) {
	    String returnString = "";
	    for (int i = 0; i < edgeList.size(); i++) {
	        returnString += edgeList.get(i).toString() + "\n";
	    }
	
	    return returnString;
	}

	/**
	 * 
	 * @param <V> A class that extends Vertex
	 * @param <E> A class that extends Edge
	 * @param vertexList An ArrayList of vertices of type @param<V>
	 * @return a string representation of the edges
	 */
	public static <V extends Vertex<E>, E extends Edge> String allEdgesToString(ArrayList<V> vertexList) {
	    String returnString = "";
	    for (int i = 0; i < vertexList.size(); i++) {
	        ArrayList<E> edgeList = vertexList.get(i).getEdges();
	        returnString += edgeListToString(edgeList);
	    }
	
	    return returnString;
	}

	/**
	 * A static method that generates the edges for an array of vertices without
	 * repeating edges.  Keeps the DOT file smaller.
	 * @param vertexList a vertex array
	 * @return returns the generated String.
	 */
	
	public static String allEdgesWithoutRepeats(ArrayList<? extends Vertex<? extends Edge>> vertexList) {
	    String returnString = "";
	    for (int i = 0; i < vertexList.size(); i++) {
	        for (int j = i + 1; j < vertexList.size(); j++) {
	        	Vertex<?> a = vertexList.get(i);
	        	Vertex<?> b = vertexList.get(j);
	        	a.getEdge(b);
	            Edge e = vertexList.get(i).getEdge(vertexList.get(j));
	            if (e != null) {
	                returnString += e.toString() + "\n";
	            }
	        }
	    }
	
	    return returnString;
	}
	
	public static String allEdgesDirectedGraph(ArrayList<? extends Vertex<? extends DirectedEdge>> vertexList){
		String returnString = "";
		for(int i = 0; i < vertexList.size(); i++){
			Vertex<? extends DirectedEdge> tempVertex = vertexList.get(i);
			ArrayList<? extends DirectedEdge> tempEdgeList = tempVertex.getEdges();
			for(int j = 0; j < tempEdgeList.size(); j++){
				DirectedEdge e = tempEdgeList.get(j);
				if(e != null){
					returnString += e.toString() + "\n";
				}
			}
		}
		
		return returnString;
		
	}
	
    /**
     * Same as the method above except it gives you the edges instead of the vertices
     **/
    public static double computeDistanceOfTour(Edge[] edges) {
        double distance = 0;
        for (int i = 0; i < edges.length; i++) {
            distance += edges[i].getWeight();
        }

        return distance;
    }

}
