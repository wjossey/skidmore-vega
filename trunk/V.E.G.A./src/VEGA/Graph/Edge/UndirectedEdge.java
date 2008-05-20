/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.Graph.Edge;

import vega.Graph.Vertex.Vertex;

/**
 * The UndirectedEdge class is actually just a repeat of the Edge class.  By default
 * an edge is said to be undirected, so this class is actually just a placeholder so
 * that developers can easily distinguish if an edge is directed or undirected.
 * @author w_jossey
 */
public class UndirectedEdge extends EdgeImpl{
    
    /**
     * See Edge class for documentation.
     * @param a
     * @param b
     */
    public UndirectedEdge(Vertex a, Vertex b){
        super(a, b);
    }
    
    /**
     * See Edge class for documentation.
     * @param a
     * @param b
     * @param weight
     */
    public UndirectedEdge(Vertex a, Vertex b, double weight){
        super(a, b, weight);
    }
    
}
