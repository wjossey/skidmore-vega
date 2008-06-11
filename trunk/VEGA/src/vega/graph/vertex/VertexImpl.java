package vega.graph.vertex;

import interfaces.graph.Graph;
import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.edge.Edge;
import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.vertex.GraphvizVertexProperties;

import java.io.Serializable;
import java.util.HashMap;

import vega.graph.edge.EdgeImpl;

//
//  Vertex.java
//  Traveling Salesman Java
//
//  Created by Weston Jossey on 7/10/07.
//  Copyright 2007 __MyCompanyName__. All rights reserved.
//
public class VertexImpl implements Vertex, Serializable, Cloneable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6521996750235441060L;

	/* Variable Declarations Start*/
	private static int vertexCounter = 0;  //Used to generate the UID of the vertex.
    
	private GraphvizVertexProperties properties;

	private int uid;  //UID of the vertex.  Equal to a primary key in a database.  Must be unique!

	private int x = 0;    //If the vertex needs coordinates, this is the "x" value.

	private int y = 0;    //If the vertex needs coordinates, this is the "y" value.

	private Edge[] edgeList;  //Keeps track of all the edges for the vertex.
   
	private HashMap edgeHash;  //A hash of edges

	private int edgeCounter = 0;  //Keeps track of how many edges this vertex has

	private boolean visited = false;  //Boolean as to whether or not the vertex has been visited

	private DirectedEdge incomingEdge;  //We can use this to track a path if we so desire (Try to remove)

	private DirectedEdge outgoingEdge;  //This is the outgoing edge in a path (Try to remove)

	private Graph g;  //This is the parent graph we're working with

	private Vertex previousVertex;

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
    public VertexImpl(int x, int y, Graph g) {
        this.g = g;
        uid = vertexCounter++;
        setX(x);
        setY(y);
        if(g.isDigraph()){
        	/* We have a directed graph*/
        	edgeList = new DirectedEdge[this.g.getSize() - 1];
        }else{
        	/*We have an undirected graph*/
        	edgeList = new UndirectedEdge[this.g.getSize() - 1];
        }
        edgeHash = new HashMap<Integer, Edge>();
        properties = new VertexPropertiesImpl(this);
    }

    /**
     * 
     * @param g Parent graph
     */
    public VertexImpl(Graph g) {
        this.g = g;
        setX(0);
        setY(0);
        if(g.isDigraph()){
        	/*We have a directed graph*/
        	edgeList = new DirectedEdge[this.g.getSize() - 1];
        }else{
        	/*We have an undirected graph*/
        	edgeList = new UndirectedEdge[this.g.getSize() - 1];
        }
        edgeHash = new HashMap<Integer, Edge>();
        properties = new VertexPropertiesImpl(this);

    }

    /**
     * Empty Constructor.
     */
    public VertexImpl() {
    	properties = new VertexPropertiesImpl(this);
    }

    /**
     * Set the parent graph.
     * @param g Parent graph
     */
    public void setGraph(Graph g) {
        this.g = g;
    }

    /**
     * 
     * @return Returns parent graph
     */
    public Graph getGraph() {
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
    public void setIncomingEdge(DirectedEdge e) {
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
    public void setPreviousVertex(Vertex v) {
        previousVertex = v;
    }

    /**
     * Set the outgoing edge from the vertex.
     * @param e
     */
    public void setOutgoingEdge(DirectedEdge e) {
        outgoingEdge = e;
    }

    /**
     * Returns all of the edges that are adjacent to the vertex
     * @return
     */
    public Edge[] getEdges() {
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
    public DirectedEdge getOutgoingEdge() {
        return outgoingEdge;
    }

    /**
     * Returns the incoming edge for the vertex.
     * @return
     */
    public DirectedEdge getIncomingEdge() {
        return incomingEdge;
    }

    /**
     * Add an edge to the vertex's adjacency list.
     * @param e Edge to add to the vertex's adjacency list
     */
    public void addEdge(Edge e) {
        /*Put it in an array for sortability (quicksorted)*/
        edgeList[edgeCounter++] = e;
  
        /*Put it in a hash for rapid access*/
        if(g.isDigraph()){
        	DirectedEdge tempEdge = (DirectedEdge) e;
        	edgeHash.put(tempEdge.getDestination().getUID(), tempEdge);
        }
        if (e.getVertexA().getUID() == uid) {
            edgeHash.put(e.getVertexB().getUID(), e);
        } else {
            edgeHash.put(e.getVertexA().getUID(), e);
        }
    }

    /**
     * Get the edge from this vertex to vertex b.
     * @param b Destination or source vertex
     * @return Connecting edge from this vertex to Vertex b.
     */
    public Edge getEdge(Vertex b) {
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
        EdgeImpl.sortEdgesByDistance(edgeList);
    }

    /**
     * Gets the previous vertex.
     * @return
     */
    public Vertex getPreviousVertex() {
        return previousVertex;
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
     * Returns the nearest neighbor to the vertex.
     * @return
     */
    public Vertex getNearestNeighbor() {
        boolean vertexFound = false;
        Vertex returnVertex = null;
        EdgeImpl.sortEdgesByDistance(edgeList);
        for (int i = 0; i < edgeList.length && !vertexFound; i++) {
        	edgeList[i].getVertexA();
            Vertex a = edgeList[i].getVertexA();
            Vertex b = edgeList[i].getVertexB();
            if (a.getUID() == this.uid) {
                if (!b.inUse()) {
                    returnVertex = b;
                    vertexFound = true;
                }
            } else {
                if (!a.inUse()) {
                    returnVertex = a;
                    vertexFound = true;
                }
            }
        }

        return returnVertex;
    }

    /**
     * Gets the k nearest neighbors, where k is some natural number that is less than the 
     * total number of neighbors for this vertex.
     * @param k 
     * @return
     */
    public Vertex[] getKNearestNeighbors(int k) {
        if (k <= edgeList.length) {
            EdgeImpl.sortEdgesByDistance(edgeList);
            Vertex[] returnArray = new Vertex[k];
            for (int i = 0; i < returnArray.length; i++) {
                UndirectedEdge tempEdge = edgeList[i];
                if (tempEdge.getVertexA().getUID() == uid) {
                    returnArray[i] = tempEdge.getVertexB();
                } else {
                    returnArray[i] = tempEdge.getVertexA();
                }
            }

            return returnArray;
        } else {
            return null;
        }
    }

    /**
     * Returns all adjacent vertices based on the edge list.
     * @return
     */
    public Vertex[] getNeighbors() {
        Vertex[] neighborArray = new VertexImpl[edgeList.length];
        int arrayCounter = 0;
        for (int i = 0; i < edgeList.length && edgeList[i] != null; i++) {
            if (edgeList[i].getVertexA() != this) {
                neighborArray[arrayCounter++] = edgeList[i].getVertexA();
            } else {
                if (edgeList[i].getVertexB() != this) {
                    neighborArray[arrayCounter++] = edgeList[i].getVertexB();
                }
            }
        }

        return neighborArray;
    }

    /**
     * Static method that takes an array of vertices and returns well formed DOT strings to
     * represent each vertex.
     * @param vertexList
     * @return
     */
    public static String vertexListToString(Vertex[] vertexList) {
        String returnString = "";
        for (int i = 0; i < vertexList.length; i++) {
            returnString += vertexList[i].toString() + "\n";
        }

        return returnString;
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

	public GraphvizVertexProperties getProperties() {
		return properties;
	}

}

