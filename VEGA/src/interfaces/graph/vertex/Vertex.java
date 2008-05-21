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
	
	void setInUse(boolean inUse);
	
	void setStyle(String style);
	
	void setIncomingEdge(Edge e);
	
	void setVisited(boolean visited);
	
	void setPreviousVertex(Vertex v);
	
	void setColor(String color);
	
	void setShape(String shape);
	
	void setOutgoingEdge(Edge e);
	
	Edge[] getEdges();
	
	String getStyle();
	
	boolean inUse();
	
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
	
	Vertex getPreviousVertex();
	
	void setSides(int sides);
	
	int getSides();
	
	String getShape();
	
	String getColor();
	
	boolean getActive();
	
	void setActive(boolean active);
	
	Vertex getNearestNeighbor();
	
	Vertex[] getKNearestNeighbors(int k);
	
	Vertex[] getNeighbors();
	
}
