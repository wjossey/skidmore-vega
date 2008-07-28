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
	
	public String getName();
	
	public void sortEdges();
	
	public GraphvizVertexProperties getProperties();
	
	public Vertex<E> getPreviousVertex();
	
	public Vertex<E> setPreviousVertex(Vertex<E> previousVertex);
	
	public String setLabel(String label);
	
	public String getLabel();
	
	public boolean visited(boolean visited);
	
	public boolean visited();
	
	public boolean inUse(boolean inUse);
	
	public boolean inUse();
	
	public boolean active();
	
	public boolean active(boolean active);
	
	public boolean currentVertex(boolean isCurrentVertex);
	
	public boolean currentVertex();
	
	public boolean finalized(boolean isFinalized);
	
	public boolean finalized();
	
}
