/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.graph;

import interfaces.graph.Network;
import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;

import java.util.ArrayList;

import vega.graph.edge.EdgeImpl;
import vega.helperClasses.VertexHelper;

/**
 *
 * @author w_jossey
 */
public class NetworkImpl<V extends Vertex<E>, E extends Edge> extends GraphImpl<V, E> implements Network<V, E>{

    /**
     * The constructor method for the Graph class.  Takes in a pre-set vertex
     * and a boolean setting to distinguish if it is a directed-graph or undirected-graph.
     * 
     * @param vertexSize the number of vertices in the graph
     * @param digraph a directed graph if true, undirected if false
     * 
     */
	
	private V[] source;
	private V[] sink;
	
    public NetworkImpl(int vertexSize, boolean isDigraph) {
        super(vertexSize, isDigraph);
    }
    
    public NetworkImpl(V[] vertexArray, E[] edgeArray){
        super(vertexArray, edgeArray);
    }
    
    public NetworkImpl(ArrayList<V> vertexList, ArrayList<E> edgeList){
        super(vertexList, edgeList);
    }

    /**
     * Constructor class when we don't have a complete graph.  Has yet to be implemented yet.
     * The problem generating random graphs is mostly due to the question of whether or not the
     * graph is strongly connected or not.  
     * 
     * @param vertexSize the number of vertices in the graph
     * @param edgeSize the number of edges in the graph
     * @param digraph a directed graph if true, undirected if false
     */
    public NetworkImpl(int vertexSize, int edgeSize, boolean digraph) {
    }
    
    /**
     * Empty Constructor
     */
    public NetworkImpl(){
        
    }

	public V[] getSinks() {
		return sink;
	}

	public V[] getSources() {
		return source;
	}

	@SuppressWarnings("unchecked")
	public void setSinks(Vertex[] v) {
		sink = (V[]) v;
	}

	@SuppressWarnings("unchecked")
	public void setSources(Vertex[] v) {
		source = (V[]) v;
	}
    

    /**
     * Converts the graph into a DOT string.  
     * @return Returns a well formed DOT string of the graph.
     */
    @Override
    public String toString() {
        String returnString = "";

        if (digraph) {
            returnString = "digraph G{\n";
        } else {
            returnString += "graph G{\n;";
        //returnString += "graph G{\nsize=\"8.5,11\";";
        }

        returnString += VertexHelper.vertexListToString(vertexList) + "\n";
        returnString += EdgeImpl.allEdgesWithoutRepeats(vertexList) + "\n";

        returnString += "}\n";

        return returnString;
    }

}
