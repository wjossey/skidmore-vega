package vega.graph.vertex;

import interfaces.graph.Graph;
import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.edge.Edge;
import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.vertex.GraphvizVertexProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import vega.graph.edge.EdgeImpl;

//
//  Vertex.java
//  Traveling Salesman Java
//
//  Created by Weston Jossey on 7/10/07.
//  Copyright 2007 __MyCompanyName__. All rights reserved.
//
public class VertexImpl<E extends Edge> implements Vertex<E>, Serializable, Cloneable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6521996750235441060L;

	/* Variable Declarations Start*/
	private static int vertexCounter = 0;  //Used to generate the UID of the vertex.
    
	private GraphvizVertexProperties<E> properties;

	private int uid;  //UID of the vertex.  Equal to a primary key in a database.  Must be unique!

	private int x = 0;    //If the vertex needs coordinates, this is the "x" value.

	private int y = 0;    //If the vertex needs coordinates, this is the "y" value.

	private ArrayList<E> edgeList;  //Keeps track of all the edges for the vertex.
	
	private HashMap<Integer, E> edgeHash;
 
	private boolean visited = false;  //Boolean as to whether or not the vertex has been visited

	private E incomingEdge;  //We can use this to track a path if we so desire (Try to remove)

	private E outgoingEdge;  //This is the outgoing edge in a path (Try to remove)

	private Graph<? extends Vertex<E>, E> g;  //This is the parent graph we're working with

	private Vertex<E> previousVertex;

	private boolean active = false; //Is the vertex active (VEGA)

	private boolean inUse = false; //Is the vertex in-use (VEGA)

	private String name = ""; //This does not have to be unique, but typically is the UID.

    /* Variable Declarations End*/


    /**
     * Takes in an x coordinate and a y coordinate and a parent graph.
     * @param x X Coordinate
     * @param y Y Coordinate
     * @param g Parent graph
     */
    public VertexImpl(int x, int y, Graph<? extends Vertex<E>, E> g) {
    	this.g = g;
        uid = vertexCounter++;
        setX(x);
        setY(y);
        edgeHash = new HashMap<Integer, E>();
        properties = new VertexPropertiesImpl<E>(this);
    }

    /**
     * 
     * @param g Parent graph
     */
    public VertexImpl(Graph<? extends Vertex<E>, E> g) {
        this.g = g;
        setX(0);
        setY(0);
        if(g.isDigraph()){
        	/*We have a directed graph*/
        }else{
        	/*We have an undirected graph*/
        }
        edgeHash = new HashMap<Integer, E>();
        properties = new VertexPropertiesImpl<E>(this);

    }

    /**
     * Empty Constructor.
     */
    public VertexImpl() {
    	properties = new VertexPropertiesImpl<E>(this);
    }

    /**
     * Set the parent graph.
     * @param g Parent graph
     */
    public void setGraph(Graph<? extends Vertex<E>, E> g) {
        this.g = g;
    }

    /**
     * 
     * @return Returns parent graph
     */
    public Graph<? extends Vertex<E>, E> getGraph() {
        return g;
    }

    /**
     * Set the name of the vertex
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the X coordinate of the vertex.  This method is currently unsafe (4/22/08) because
     * we are able to change the x-coordinate which may ruin the triangle inequality.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the Y coordinate of the vertex.  This method is currently unsafe (4/22/08) because 
     * we are able to change the y-coordinate which may ruin the triangle inequality.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Primarily for VEGA use.  This sets the vertex to "in-use" which causes VEGA to color the vertex
     * to the "in-use" color.
     * @param inUse
     */
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    /**
     * Assign an incoming edge to the vertex.
     * @param e
     */
    public void setIncomingEdge(E e) {
        incomingEdge = e;
    }

    /**
     * Marks the vertex as visited so we know not to re-visit.
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Assign a previous vertex.  Could be useful for tour or cycle building.
     * @param v
     */
    public <V extends Vertex<E>> void setPreviousVertex(V v) {
        previousVertex = v;
    }

    /**
     * Set the outgoing edge from the vertex.
     * @param e
     */
    public void setOutgoingEdge(E e) {
        outgoingEdge = e;
    }

    /**
     * Returns all of the edges that are adjacent to the vertex
     * @return
     */
    @SuppressWarnings("unchecked")
	public ArrayList<E> getEdges() {
        return edgeList;
    }

    

    /**
     * Returns the boolean status of whether or not the vertex is in use.
     * @return
     */
    public boolean inUse() {
        return inUse;
    }

    /**
     * Returns the x-coordinate value for the vertex.
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate value for the vertex.
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the outgoing edge for the vertex.
     * @return
     */
    public E getOutgoingEdge() {
        return outgoingEdge;
    }

    /**
     * Returns the incoming edge for the vertex.
     * @return
     */
    public E getIncomingEdge() {
        return incomingEdge;
    }

    /**
     * Add an edge to the vertex's adjacency list.
     * @param e Edge to add to the vertex's adjacency list
     */
    public void addEdge(E e) {
        /*Put it in an array for sortability (quicksorted)*/
        edgeList.add(e);
        if(e instanceof DirectedEdge){
        	DirectedEdge tempEdge = (DirectedEdge) e;
        	edgeHash.put(tempEdge.getDestination().getUID(), e);
        }else{
        	if(e instanceof UndirectedEdge){
        		UndirectedEdge tempEdge = (UndirectedEdge) e;
        		if(tempEdge.getVertexA().getUID() == this.getUID()){
        			edgeHash.put(tempEdge.getVertexB().getUID(), e);
        		}else{
        			edgeHash.put(tempEdge.getVertexA().getUID(), e);
        		}
        	}
        }
    }

    /**
     * Get the edge from this vertex to vertex b.
     * @param b Destination or source vertex
     * @return Connecting edge from this vertex to Vertex b.
     */
    public <V extends Vertex<E>> E getEdge(V b) {
        return edgeHash.get(b.getUID());
    }

    /**
     * Returns the Unique Identifier for the vertex.
     * @return
     */
    public int getUID() {
        return uid;
    }

    /**
     * Returns whether or not the vertex has been visited.
     * @return
     */
    public boolean visited() {
        return visited;
    }

    /**
     * Returns the UID if no name has been assigned, else it 
     * returns the name assigned to the vertex.
     * @return
     */
    public String getName() {
        if (name.equalsIgnoreCase("")) {
            name += uid;
        }
        return name;
    }

   /**
    * Sorts the edges using quicksort.
    */
    public void sortEdges() {
        Edge[] edgeArray = (Edge[]) edgeList.toArray();
        
        EdgeImpl.sortEdgesByDistance(edgeArray);
    }

    /**
     * Gets the previous vertex.
     * @return
     */
    @SuppressWarnings("unchecked")
	public <V extends Vertex<E>> V getPreviousVertex() {
    	return (V) previousVertex;
    }


  

   

    /**
     * Returns whether or not the vertex is currently active.
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the vertex to an active state.
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }




    /**
     * 
     * @return Vertex description in valid DOT format.  
     */
    @Override
    public String toString() {
        String returnString = "";

        returnString += getName();
        
        returnString += " [fillcolor=" + properties.getColor() + ", style=" + 
        		properties.getStyle() + ", shape=" + properties.getShape() + 
        		", sides=" + Integer.toString(properties.getSides()) + "];";
        
        return returnString;

    }

	public GraphvizVertexProperties<E> getProperties() {
		
		return properties;
	}

}

