/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VEGA.Graph.Edge.Tree;

import VEGA.Graph.Edge.UndirectedEdge;
import VEGA.Graph.Vertex.Vertex;

/**
 *
 * @author w_jossey
 */
public class TreeEdge extends UndirectedEdge{
    
    /**
     * Creates a new edge from a parent to a child.
     * @param parent
     * @param child
     */
    public TreeEdge(Vertex parent, Vertex child){
        super(parent, child);
    }
    
    
}
