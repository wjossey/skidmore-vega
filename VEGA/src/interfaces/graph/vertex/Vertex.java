/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph.vertex;

import java.util.ArrayList;

import interfaces.graph.Graph;
import interfaces.graph.edge.Edge;

/**
 *
 * @author w_jossey
 */
public interface Vertex<V extends Vertex<V,E>, E extends Edge>{
	
	public void setGraph(Graph<V, E> g);
	
	public Graph<V, E> getGraph();
	
	public void setName(String name);
	
	public void setX(int x);
	
	public void setY(int y);
	
	public ArrayList<E> getEdges();
	
	public int getX();
	
	public int getY();
	
	public void addEdge(E toTempVertex);
	
	public E getEdge(V v);
	
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
	
}