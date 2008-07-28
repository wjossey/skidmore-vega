package vega.graph.vertex;

import interfaces.graph.edge.DirectedEdge;
import interfaces.graph.edge.Edge;
import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.Vertex;
import vega.graph.vertex.VertexProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import vega.helperClasses.EdgeHelper;

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
    
	private VertexProperties properties;
	
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

	private boolean isCurrentVertex = false;

	private boolean isFinalized = false;

	private String label;

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
		properties = new VertexProperties(this);
	}
	
	public AbstractVertex(int x, int y, ArrayList<E> edgeList){
		this.edgeList = edgeList;
		this.x = x;
		this.y = y;
		this.uid = pk++;
		properties = new VertexProperties(this);
	}
	
	public AbstractVertex(){
		x = 0;
		y = 0;
		this.uid = pk++;
		properties = new VertexProperties(this);
	}
	
	public AbstractVertex(String name){
		x = 0;
		y = 0;
		this.uid = pk++;
		this.name = name;
		properties = new VertexProperties(this);
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
     * Returns all of the edges that are adjacent to the vertex
     * @return
     */
	public ArrayList<E> getEdges() {
        return edgeList;
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
    public E getEdge(Vertex<?> b) {
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
        
        EdgeHelper.sortEdgesByDistance(edgeArray);
    }
    /**
     * 
     * @return Vertex description in valid DOT format.  
     */
    @Override
    public String toString() {
        String returnString = "";

        returnString += getUID();
        
        returnString += " [fillcolor=" + properties.getColor() + ", style=" + 
        		properties.getStyle() + ", shape=" + properties.getShape() + 
        		", sides=" + Integer.toString(properties.getSides()) + ", label=\"" + getLabel() + "\"];";
        
        return returnString;

    }

	public VertexProperties getProperties() {
		
		return properties;
	}
	
	public Vertex<E> getPreviousVertex(){
		return previousVertex;
	}
	
	public Vertex<E> setPreviousVertex(Vertex<E> previousVertex){
		return this.previousVertex = previousVertex;
	}

	public boolean currentVertex(boolean isCurrentVertex) {
		return this.isCurrentVertex = isCurrentVertex;
	}

	public boolean currentVertex() {
		return isCurrentVertex;
	}

	public boolean finalized(boolean isFinalized) {
		return this.isFinalized = isFinalized;
	}

	public boolean finalized() {
		return isFinalized;
	}

	public boolean active() {
		return active;
	}

	public boolean active(boolean active) {
		return this.active = active;
	}

	public boolean inUse(boolean inUse) {
		return this.inUse = inUse;
	}

	/**
	 * Returns the boolean status of whether or not the vertex is in use.
	 * @return
	 */
	public boolean inUse() {
	    return inUse;
	}

	public boolean visited(boolean visited) {
		return this.visited = visited;
	}

	/**
	 * Returns whether or not the vertex has been visited.
	 * @return
	 */
	public boolean visited() {
	    return visited;
	}

	public String setLabel(String label){
		return this.label = label;
	}
	
	public String getLabel(){
		if(label == null){
			return name;
		}else{
			return label;
		}
	}
}

