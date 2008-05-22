/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph.vertex;

import interfaces.graph.Graph;
import interfaces.graph.edge.Edge;

/**
 *
 * @author w_jossey
 */
public interface Vertex {
	
	void setGraph(Graph g);
	
	Graph getGraph();
	
	void setName(String name);
	
	void setX(int x);
	
	void setY(int y);
	
	void setIncomingEdge(Edge e);
	
	void setPreviousVertex(Vertex v);
	
	void setOutgoingEdge(Edge e);
	
	Edge[] getEdges();
	
	int getX();
	
	int getY();
	
	Edge getOutgoingEdge();
	
	Edge getIncomingEdge();
	
	void addEdge(Edge e);
	
	Edge getEdge(Vertex b);
	
	int getUID();
	
	boolean visited();
	
	String getName();
	
	void sortEdges();
	
	Vertex getNearestNeighbor();
	
	Vertex[] getKNearestNeighbors(int k);
	
	Vertex[] getNeighbors();
	
	Vertex getPreviousVertex();
	
	GraphvizVertexProperties getProperties();
	
	void setVisited(boolean visited);
	
	void setInUse(boolean inUse);
	
	boolean inUse();
	
	boolean isActive();
	
	void setActive(boolean active);
	
}
