/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.graph.edge.tree;

import vega.graph.edge.UndirectedEdgeImpl;
import interfaces.graph.edge.tree.TreeEdge;
import interfaces.graph.vertex.Vertex;

/**
 *
 * @author w_jossey
 */
public class TreeEdgeImpl extends UndirectedEdgeImpl implements TreeEdge{
    
    /**
     * Creates a new edge from a parent to a child.
     * @param parent
     * @param child
     */
    public TreeEdgeImpl (Vertex parent, Vertex child){
        super(parent, child);
    }
    
    
}
