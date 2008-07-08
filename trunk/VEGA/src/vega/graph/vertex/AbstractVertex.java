package vega.graph.vertex;

import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.edge.Edge;
import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.vertex.GraphvizVertexProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import vega.graph.edge.AbstractEdge;

/**
 * 
 * @author Weston Jossey
 *
 * @param <E>
 */
public class AbstractVertex<E extends Edge> implements Vertex<E>, Serializable, Cloneable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6521996750235441060L;

	/* Variable Declarations Start*/
    
	private GraphvizVertexProperties properties;
	
	private static int pk = 0;
	
	int uid;  //UID of the vertex.  Equal to a primary key in a database.  Must be unique!

	private int x = 0;    //If the vertex needs coordinates, this is the "x" value.

	private int y = 0;    //If the vertex needs coordinates, this is the "y" value.

	private ArrayList<E> edgeList = new ArrayList<E>();  //Keeps track of all the edges for the vertex.
	
	private HashMap<Integer, E> edgeHash = new HashMap<Integer, E>();
 
	private boolean visited = false;  //Boolean as to whether or not the vertex has been visited

	private boolean active = false; //Is the vertex active (VEGA)

	private boolean inUse = false; //Is the vertex in-use (VEGA)

	private String name = ""; //This does not have to be unique, but typically is the UID.
	
	private Vertex<E> previousVertex;

    /* Variable Declarations End*/

	/**
	 * Constructor
	 * @param x X coordinate
	 * @param y Ycoordinate
	 */
	public AbstractVertex(int x, int y){
		this.x = x;
		this.y = y;
		this.uid = pk++;
	}
	
	public AbstractVertex(int x, int y, ArrayList<E> edgeList){
		this.edgeList = edgeList;
		this.x = x;
		this.y = y;
		this.uid = pk++;
	}
	
	public AbstractVertex(){
		x = 0;
		y = 0;
		this.uid = pk++;
	}
	
	public AbstractVertex(String name){
		x = 0;
		y = 0;
		this.uid = pk++;
		this.name = name;
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
     * Marks the vertex as visited so we know not to re-visit.
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Returns all of the edges that are adjacent to the vertex
     * @return
     */
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
        	}else{
        		//Exception
        	}
        }
    }

    /**
     * Get the edge from this vertex to vertex b.
     * @param b Destination or source vertex
     * @return Connecting edge from this vertex to Vertex b.
     */
    public <V extends Vertex<? extends E>> E getEdge(V b) {
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
        
        AbstractEdge.sortEdgesByDistance(edgeArray);
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

	public GraphvizVertexProperties getProperties() {
		
		return properties;
	}
	
	public Vertex<E> getPreviousVertex(){
		return previousVertex;
	}
	
	public Vertex<E> setPreviousVertex(Vertex<E> previousVertex){
		return this.previousVertex = previousVertex;
	}
}

