package vega.helperClasses;

import interfaces.graph.vertex.Vertex;
import interfaces.graph.vertex.tree.BinaryTreeNode;
import interfaces.graph.edge.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import vega.dataStructures.trees.BinarySearchTreeImpl;

public class Tour<V extends Vertex<V,E>, E extends Edge<V,E>> {
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
	
	public static <R extends BinaryTreeNode<String,R,E>, E extends UndirectedEdge<R,E>> void main(String[] args){
		BinarySearchTreeImpl<String, R, E> t = new BinarySearchTreeImpl<String, R, E>();
	}
}