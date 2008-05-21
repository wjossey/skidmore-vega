package vega.helperClasses;

import interfaces.graph.vertex.Vertex;
import java.util.Iterator;
import java.util.LinkedList;

public class Tour {
	LinkedList<Vertex> tour;
	Iterator<Vertex> iter;
	
	public Tour(Vertex[] vertexList){
		for(int i = 0; i < vertexList.length; i++){
			addVertex(vertexList[i]);
		}
	}
	
	public void addVertex(Vertex c){
		tour.add(c);
	}
		
	public Iterator<Vertex> getIterator(){
		return tour.iterator();
	}
}