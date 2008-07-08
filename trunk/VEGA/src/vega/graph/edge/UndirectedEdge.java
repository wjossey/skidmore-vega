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
 * @author Weston Jossey
 */
public class UndirectedEdge extends AbstractEdge implements interfaces.graph.edge.UndirectedEdge{
    
    /**
     * See Edge class for documentation.
     * @param a
     * @param b
     */
	
	protected Vertex<UndirectedEdge> vertexA = null;
	protected Vertex<UndirectedEdge> vertexB = null;
	
    public UndirectedEdge(Vertex<UndirectedEdge> a, Vertex<UndirectedEdge> b){
        vertexA = a;
        vertexB = b;
        a.addEdge(this);
        b.addEdge(this);
        
    }
    
    /**
     * See Edge class for documentation.
     * @param a Vertex a
     * @param b Vertex b
     * @param weight Edge weight
     */
    public UndirectedEdge(Vertex<UndirectedEdge> a, Vertex<UndirectedEdge> b, double weight){
        vertexA = a;
        vertexB = b;
        a.addEdge(this);
        b.addEdge(this);
        super.setWeight(weight);
    }

    
	public Vertex<UndirectedEdge> getVertexA() {
		return vertexA;
	}
	
	public Vertex<UndirectedEdge> getVertexB() {
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
