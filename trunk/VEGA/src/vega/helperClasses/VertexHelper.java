package vega.helperClasses;

import java.util.ArrayList;

import interfaces.graph.Graph;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.edge.Edge;
import interfaces.graph.edge.UndirectedEdge;

/**
 * 
 * @author Weston Jossey
 *
 */
public class VertexHelper {
	
	@SuppressWarnings("unchecked")
	public static <V extends Vertex<? extends E>, E extends Edge> ArrayList<V> getKNearestNeighbors(V vertex, int k){
		ArrayList<V> returnArrayList = new ArrayList<V>();
		ArrayList<E> edgeList = (ArrayList<E>) vertex.getEdges();
		
		if (k <= edgeList.size()) {
	        EdgeHelper.sortEdges
	        (edgeList);
	        
	        for (int i = 0; i < edgeList.size() && i < k; i++) {
	        	if(edgeList.get(i) instanceof UndirectedEdge){
	        		UndirectedEdge tempEdge = (UndirectedEdge) edgeList.get(i);
	        		if(tempEdge.getVertexA().getUID() == vertex.getUID()){
	        			returnArrayList.add(i, (V) tempEdge.getVertexB());
	        		}else{
	        			returnArrayList.add(i, (V) tempEdge.getVertexA());
	        		}
	        	}else{
	        		if(edgeList.get(i) instanceof DirectedEdge){
	        			DirectedEdge tempEdge = (DirectedEdge) edgeList.get(i);
	        			returnArrayList.add(i, (V) tempEdge.getDestination());
	        		}
	        	}
	        }
	
	    }
		
		return returnArrayList;
	}

	/**
	 * Returns the entire neighbor array sorted based on edge weight.  
	 * @param <V> Must extend type Vertex
	 * @param <E> Must extend type Edge
	 * @param vertex Vertex to get the nearest neighbor of
	 * @return The nearest neighbor
	 */
	@SuppressWarnings("unchecked")
	public static <V extends Vertex<E>, E extends Edge> ArrayList<V> getNearestNeighbors(V vertex){
		
		ArrayList<V> returnArrayList = new ArrayList<V>();
		ArrayList<E> edgeList = vertex.getEdges();
		
		if (edgeList.size() > 0) {
            EdgeHelper.sortEdges(edgeList);
            
            for (int i = 0; i < edgeList.size(); i++) {
            	if(edgeList.get(i) instanceof UndirectedEdge){
            		UndirectedEdge tempEdge = (UndirectedEdge) edgeList.get(i);
            		if(tempEdge.getVertexA().getUID() == vertex.getUID()){
            			returnArrayList.add(i, (V) tempEdge.getVertexB());
            		}else{
            			returnArrayList.add(i, (V) tempEdge.getVertexA());
            		}
            	}else{
            		if(edgeList.get(i) instanceof DirectedEdge){
            			DirectedEdge tempEdge = (DirectedEdge) edgeList.get(i);
            			returnArrayList.add(i, (V) tempEdge.getDestination());
            		}
            	}
            }

        }
		
		return returnArrayList;
	}
	
	@SuppressWarnings("unchecked")
	public static <V extends Vertex<E>, E extends Edge> V getNearestNeighbor(V vertex){
	
		ArrayList<E> edgeList = vertex.getEdges();
		
		if (edgeList.size() > 0) {
            EdgeHelper.sortEdges(edgeList);
            
            for (int i = 0; i < edgeList.size(); i++) {
            	if(edgeList.get(i) instanceof UndirectedEdge){
            		UndirectedEdge tempEdge = (UndirectedEdge) edgeList.get(i);
            		if(tempEdge.getVertexA().getUID() == vertex.getUID()){
            			if(!tempEdge.getVertexB().inUse()){
            				return (V) tempEdge.getVertexB();
            			}
            		}else{
            			if(!tempEdge.getVertexA().inUse()){
            				return (V) tempEdge.getVertexA();
            			}
            		}
            	}else{
            		if(edgeList.get(i) instanceof DirectedEdge){
            			DirectedEdge tempEdge = (DirectedEdge) edgeList.get(i);
            			if(!tempEdge.getDestination().inUse()){
            				return (V) tempEdge.getDestination();
            			}
            		}
            	}
            }

        }
		
		return null;
	}
	
    /**
     * Static method that takes an array of vertices and returns well formed DOT strings to
     * represent each vertex.
     * @param vertexList
     * @return
     */
    public static String vertexListToString(ArrayList<? extends Vertex<? extends Edge>> vertexList) {
        String returnString = "";
        for (int i = 0; i < vertexList.size(); i++) {
            returnString += vertexList.get(i).toString() + "\n";
        }

        return returnString;
    }
    
    /**
     * 
     * @param tour Array of vertices in the tour.  
     * @return
     */
    public static double computeDistanceOfCycle(Vertex<? extends Edge>[] cycle) {
        double distance = 0;
        if(cycle[0] == cycle[cycle.length - 1]){
            /*
               We have a tour that has the root vertex
               at the start and end of the tour
            */
            for(int i = 0; i < cycle.length - 1; i++){
                distance += cycle[i].getEdge(cycle[i+1]).getWeight();
            }
            
            
        }else{
            for(int i = 0; i < cycle.length - 1; i++){
                distance += cycle[i].getEdge(cycle[i+1]).getWeight();
            }
            
            distance += cycle[cycle.length - 1].getEdge(cycle[0]).getWeight();
        }

        return distance;
    }

}
