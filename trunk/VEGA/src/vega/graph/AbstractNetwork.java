/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.graph;

import interfaces.graph.Network;
import interfaces.graph.edge.network.NetworkEdge;
import interfaces.graph.vertex.network.NetworkNode;

import java.util.ArrayList;

import vega.helperClasses.EdgeHelper;
import vega.helperClasses.VertexHelper;

/**
 * 
 * @author w_jossey
 *
 * @param <N>
 * @param <E>
 */
public class AbstractNetwork<N extends NetworkNode<E>, E extends NetworkEdge> extends AbstractGraph<N,E> implements Network<N>{

    /**
     * The constructor method for the Graph class.  Takes in a pre-set vertex
     * and a boolean setting to distinguish if it is a directed-graph or undirected-graph.
     * 
     * @param vertexSize the number of vertices in the graph
     * @param digraph a directed graph if true, undirected if false
     * 
     */
	
	private ArrayList<N> source;
	private ArrayList<N> sink;
	
    public AbstractNetwork(int vertexSize, boolean isDigraph) {
        super(vertexSize, isDigraph);
    }
    
    public AbstractNetwork(N[] vertexArray, E[] edgeArray){
        super(vertexArray, edgeArray);
    }
    
    public AbstractNetwork(ArrayList<N> vertexList, ArrayList<E> edgeList, boolean isDigraph){
        super(vertexList, edgeList, isDigraph);
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
    public AbstractNetwork(int vertexSize, int edgeSize, boolean digraph) {
    }
    
    /**
     * Empty Constructor
     */
    public AbstractNetwork(){
        
    }

	public ArrayList<N> getSinks() {
		return sink;
	}

	public ArrayList<N> getSources() {
		return source;
	}

	public void setSinks(ArrayList<N> v) {
		sink = v;
	}

	public void setSources(ArrayList<N> v) {
		source = v;
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
        returnString += EdgeHelper.allEdgesWithoutRepeats(vertexList) + "\n";

        returnString += "}\n";

        return returnString;
    }
}
