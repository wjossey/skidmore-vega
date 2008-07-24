/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph.vertex;

import java.util.ArrayList;

import interfaces.graph.edge.Edge;

/**
 * 
 * @author w_jossey
 *
 * @param <E>
 */
public interface Vertex<E extends Edge>{
	
	public void setName(String name);
	
	public void setX(int x);
	
	public void setY(int y);
	
	public ArrayList<E> getEdges();
	
	public int getX();
	
	public int getY();
	
	public void addEdge(E toTempVertex);
	
	public E getEdge(Vertex<?> v);
	
	public int getUID();
	
	public boolean visited();
	
	public String getName();
	
	public void sortEdges();
	
	public GraphvizVertexProperties getProperties();
	
	public void setVisited(boolean visited);
	
	public void setInUse(boolean inUse);
	
	public boolean inUse();
	
	public boolean isActive();
	
	public void setActive(boolean active);
	
	public Vertex<E> getPreviousVertex();
	
	public Vertex<E> setPreviousVertex(Vertex<E> previousVertex);
	
}
