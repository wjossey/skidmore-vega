package vega.helperClasses;

import interfaces.graph.vertex.Vertex;
import interfaces.graph.edge.Edge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Tour<V extends Vertex<E>, E extends Edge> {
	LinkedList<V> tour;
	Iterator<V> iter;
	
	public Tour(ArrayList<V> vertexList){
		for(int i = 0; i < vertexList.size(); i++){
			addVertex(vertexList.get(i));
		}
	}
	
	public void addVertex(V c){
		tour.add(c);
	}
		
	public Iterator<V> getIterator(){
		return tour.iterator();
	}
}