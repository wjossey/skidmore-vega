/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.graph.edge;

import interfaces.graph.vertex.Vertex;

/**
 * The UndirectedEdge class is actually just a repeat of the Edge class.  By default
 * an edge is said to be undirected, so this class is actually just a placeholder so
 * that developers can easily distinguish if an edge is directed or undirected.
 * @author w_jossey
 */
public class UndirectedEdge<V extends Vertex<? extends interfaces.graph.edge.UndirectedEdge<V>>> extends AbstractEdge<V> implements interfaces.graph.edge.UndirectedEdge<V>{
    
    /**
     * See Edge class for documentation.
     * @param a
     * @param b
     */
	
	V vertexA = null;
	V vertexB = null;
	
    public UndirectedEdge(V a, V b){
        vertexA = a;
        vertexB = b;
    }
    
    /**
     * See Edge class for documentation.
     * @param a
     * @param b
     * @param weight
     */
    public UndirectedEdge(V a, V b, double weight){
        vertexA = a;
        vertexB = b;
        setWeight(weight);
    }

    
	@SuppressWarnings("unchecked")
	public V getVertexA() {
		return vertexA;
	}

	@SuppressWarnings("unchecked")
	public V getVertexB() {
		return vertexB;
	}
    
	public String toString() {
        String returnString = "";
        returnString += vertexA.getName(); //a -- b [shape=polygon,sides=5,peripheries=3,color=lightblue,style=filled];

        returnString += " -- ";

        returnString += vertexB.getName() + " ";
        returnString += "[ " + getStyle() + getFormattedWeight() +
                getColor() + "];";


        return returnString;
    }
    
    
}
