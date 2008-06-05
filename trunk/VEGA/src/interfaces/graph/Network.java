/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph;

import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.vertex.Vertex;

/**
 *
 * @author w_jossey
 */
public interface Network extends Graph{
	
	public void setSource(Vertex v);
	
	public void setSource(Vertex[] a);
	
	public void setSink(Vertex v);
	
	public void setSink(Vertex[] a);
	
	public void addVertex(Vertex v);
	
	public void addEdge(DirectedEdge e);
	
	public boolean removeVertex(Vertex v);
	
	public boolean removeEdge(DirectedEdge e);

}
