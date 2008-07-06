package vega.helperClasses;

import java.util.ArrayList;

import interfaces.graph.Graph;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.edge.Edge;
import interfaces.graph.edge.UndirectedEdge;

public class VertexHelper {
	
	/**
	 * 
	 * @param <V>
	 * @param <E>
	 * @param g
	 * @return
	 */
	public static <V extends Vertex<? super E>, E extends Edge> Graph<V,E> generateVertices(Graph<V, E> g){
		Graph<V, E> graph = g;
		graph.addVertex(null);
	
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <V extends Vertex<E>, E extends Edge> ArrayList<V> getKNearestNeighbors(V vertex, int k){
		ArrayList<V> returnArrayList = new ArrayList<V>();
		ArrayList<E> edgeList = vertex.getEdges();
		
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
    public static <V extends Vertex<? super E>, E extends Edge>String vertexListToString(ArrayList<V> vertexList) {
        String returnString = "";
        for (int i = 0; i < vertexList.size(); i++) {
            returnString += vertexList.get(i).toString() + "\n";
        }

        return returnString;
    }

}
