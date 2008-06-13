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
public interface Vertex<E extends Edge>{
	
	public void setGraph(Graph<? extends Vertex<E>, E> g);
	
	public Graph<? extends Vertex<E>, E> getGraph();
	
	public void setName(String name);
	
	public void setX(int x);
	
	public void setY(int y);
	
	public void setIncomingEdge(E e);
	
	public <V extends Vertex<E>> void setPreviousVertex(V v);
	
	public void setOutgoingEdge(E e);
	
	public E[] getEdges();
	
	public int getX();
	
	public int getY();
	
	public Edge getOutgoingEdge();
	
	public Edge getIncomingEdge();
	
	public void addEdge(E toTempVertex);
	
	public <V extends Vertex<E>> E getEdge(V v);
	
	public int getUID();
	
	public boolean visited();
	
	public String getName();
	
	public void sortEdges();
	
	public <V extends Vertex<E>> V getNearestNeighbor();
	
	public <V extends Vertex<E>> V[] getKNearestNeighbors(int k);
	
	public <V extends Vertex<E>> V[] getNeighbors();
	
	public <V extends Vertex<E>> V getPreviousVertex();
	
	public GraphvizVertexProperties getProperties();
	
	public void setVisited(boolean visited);
	
	public void setInUse(boolean inUse);
	
	public boolean inUse();
	
	public boolean isActive();
	
	public void setActive(boolean active);
	
}
