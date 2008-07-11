package vega.graph;

import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.Graph;
import java.util.ArrayList;

/**
 *The graph class is built off the mathematical structure of the same name.  As 
 * stated on wikipedia:  "In mathematics and computer science, a graph 
 * is the basic object of study in graph theory. Informally speaking, 
 * a graph is a set of objects called points, nodes, or vertices connected 
 * by links called lines or edges. In a proper graph, which is by default 
 * undirected, a line from point A to point B is considered to be the same 
 * thing as a line from point B to point A. In a digraph, short for directed 
 * graph, the two directions are counted as being distinct arcs or directed 
 * edges. Typically, a graph is depicted in diagrammatic form as a set of 
 * dots (for the points, vertices, or nodes), joined by curves (for the lines 
 * or edges)."
 * @author Weston Jossey
 * @param <V> Must be a class that implements the Vertex interface.
 * @param <E> Must be a class that implements the Edge interface.
 */
public class AbstractGraph<V extends Vertex<? extends E>, E extends Edge> implements Graph<V, E>{

    protected int size = 0;
    protected int positionInArray = 0;
    protected ArrayList<V> vertexList = new ArrayList<V>();
    protected ArrayList<E> edgeList = new ArrayList<E>();
    protected boolean digraph;

    /**
     * The constructor method for the Graph class.  Takes in a pre-set vertex
     * and a boolean setting to distinguish if it is a directed-graph or undirected-graph.
     * 
     * @param vertexSize the number of vertices in the graph
     * @param digraph a directed graph if true, undirected if false
     */
    public AbstractGraph(int vertexSize, boolean isDigraph) {
        this.size = vertexSize;
        this.digraph = isDigraph;
        vertexList = new ArrayList<V>(vertexSize);
        //generateVertices();
        if (!digraph) {
            /*We don't have a digraph, so we go ahead and compute the edge weights between
             * each vertex.  This is going to create a complete graph of size K.  
             */

            //TODO We probably want to have some option in here to create a graph that is
            //strongly connected but not complete.  
            /*(computeDistances();*/ //Commented out so the class compiles.  Randomly generating
        							 //distances should be handled outside of the class.
        } else {
            generateEdges();
        }
    }
    
    public AbstractGraph(V[] vertexArray, E[] edgeArray){
        vertexList = new ArrayList<V>(vertexArray.length);
        edgeList = new ArrayList<E>(edgeArray.length);
        
        for(int i = 0; i < vertexArray.length; i++){
            vertexList.add(vertexArray[i]);
        }
        for(int i = 0; i < edgeArray.length; i++){
            edgeList.add(edgeArray[i]);
        }
    }
    
    public AbstractGraph(ArrayList<V> vertexList, ArrayList<E> edgeList){
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }
    
    /**
     * Constructor class when we don't have a complete graph.  Has yet to be implemented yet.
     * The problem generating random graphs is mostly due to the question of whether or not the
     * graph is strongly connected or not.  
     * 
     * @param vertexSize the number of vertices in the graph
     * @param edgeSize the number of edges in the graph
     * @param digraph a directed graph if true, undirected if false
     */
    public AbstractGraph(int vertexSize, int edgeSize, boolean digraph) {
    
    }
    
    /**
     * Empty Constructor
     */
    public AbstractGraph(){
        
    }

    /* (non-Javadoc)
	 * @see vega.graph.Graph#getVertexArray()
	 */
    
	public ArrayList<V> getVertexArray() {
        return vertexList;
    }
	
	   /* (non-Javadoc)
	 * @see vega.graph.Graph#getSize()
	 */
    public int getSize(){
        return vertexList.size();
    }
    
    /* (non-Javadoc)
	 * @see vega.graph.Graph#getEdgeArray()
	 */ 
    
	public ArrayList<E> getEdgeArray(){
        return edgeList;
    }

    /**
     * Returns the size of the graph
     * @return returns the integer value for the number of vertices
     */

    /* (non-Javadoc)
	 * @see vega.graph.Graph#i 
	 */
    public boolean isDigraph() {
        return digraph;
    }

    /**
     * Random vertex generator.  Creates vertices and gives them X and Y coordinates.  These 
     * coordinates are then used to compute the distances between the vertices and create
     * edges that satisfy the triangle inequality
     */
	/*private void generateVertices() {
        for (int i = 0; i < size; i++) {
            int randX = (int) (Math.random() * (size * 10));
            int randY = (int) (Math.random() * (size * 10));
            VertexImpl<E> tempVertex = new VertexImpl<E>(randX, randY, this);
            addVertex((V)tempVertex);
        }
    }*/

    /**
     * This method merely loops through the vertexArray array that has been already created.
     * It takes the current vertex, saves it as tempVertex, and adds edges to and from this
     * vertex.  By guaranteeing that there is always a flow in and out of the vertex, we
     * are guaranteed a strongly connected graph.
     */
 
	private void generateEdges() {
        /*for (int i = 0; i < vertexList.size(); i++) {
            V tempVertex = vertexList.get(i);;
            Random generator = new Random();
            int fromVertex, toVertex;
            int capacity1, capacity2;
            
            do {
                fromVertex = generator.nextInt(size);
                capacity1 = generator.nextInt(1000) + 1;

                toVertex = generator.nextInt(size);
                capacity2 = generator.nextInt(1000) + 1;
            } while (fromVertex != i && toVertex != i);


            
            UndirectedEdge toTempVertex = new UndirectedEdge(vertexList.get(fromVertex), tempVertex, capacity1);
            UndirectedEdge fromTempVertex = new UndirectedEdge(tempVertex, vertexList.get(toVertex), capacity2);

            tempVertex.addEdge((E) toTempVertex);
            tempVertex.addEdge((E) fromTempVertex);

            edgeList.add((E) toTempVertex);
            vertexList.get(fromVertex).addEdge((E) toTempVertex);
            vertexList.get(toVertex).addEdge((E) fromTempVertex);
            
        }*/
    }

    /**
     * Adds a vertex to the vertex array.  Internal method only.
     */
    public void addVertex(V v) {
        vertexList.add(positionInArray++, v);
    }

    /**
     * This algorithm goes through and produces all of the edge weights between the vertices if we're 
     * working with a complete graph. This is particularly useful in situations such as TSP where we 
     * are dealing with some complete Graph and want it to auto-generate the edges for us
     */
    @SuppressWarnings("unchecked")
//	private void computeDistances() {
//    	
//        /*For all of the vertices in the array...*/
//        for (int i = 0; i < vertexList.size(); i++) {
//            /*Get the x1 and y1 values*/
//            int x1 = vertexList.get(i).getX();
//            int y1 = vertexList.get(i).getY();
//
//            /*For all other vertices that have not already had their distances computed...*/
//            for (int j = i + 1; j < vertexList.size(); j++) {
//
//                /*Get x2 and y2*/
//                int x2 = vertexList.get(j).getX();
//                int y2 = vertexList.get(j).getY();
//
//                double xSquared;
//                double ySquared;
//
//                if (x1 > x2) {
//                    xSquared = Math.pow((x1 - x2), 2);
//                } else {
//                    xSquared = Math.pow((x2 - x1), 2);
//                }
//                if (y1 > y2) {
//                    ySquared = Math.pow((y1 - y2), 2);
//                } else {
//                    ySquared = Math.pow((y2 - y1), 2);
//                }
//
//                /*Distance formula!*/
//                double distanceSquared = (xSquared + ySquared);
//                double dist = Math.sqrt(distanceSquared);
//
//                /*Create the edge and add it to both vertices*/
//                UndirectedEdge e = new UndirectedEdge(vertexList.get(i), vertexList.get(j), dist);
//                vertexList.get(i).addEdge((E) e);
//                vertexList.get(j).addEdge((E) e);
//                edgeList.add((E) e);
//            }
//        }
//
//    }

    /**
     * 
     * @param tour Array of vertices in the tour.  
     * @return
     */
    public double computeDistanceOfCycle(V[] cycle) {
        double distance = 0;
        if(cycle[0] == cycle[cycle.length - 1]){
            /*
               We have a tour that has the root vertex
               at the start and end of the tour
            */
            for(int i = 0; i < cycle.length - 1; i++){
                distance += cycle[i].getEdge(cycle[i+1]).getWeight();
            }
            
            
        }else{
            for(int i = 0; i < cycle.length - 1; i++){
                distance += cycle[i].getEdge(cycle[i+1]).getWeight();
            }
            
            distance += cycle[cycle.length - 1].getEdge(cycle[0]).getWeight();
        }

        return distance;
    }

    /**
     * Same as the method above except it gives you the edges instead of the vertices
     **/
    public double computeDistanceOfTour(E[] edges) {
        double distance = 0;
        for (int i = 0; i < edges.length; i++) {
            distance += edges[i].getWeight();
        }

        return distance;
    }


    /* (non-Javadoc)
	 * @see vega.graph.Graph#resetVerticiesVisited()
	 */
    public void resetVerticiesVisited() {
        for (int i = 0; i < vertexList.size(); i++) {
            vertexList.get(i).setInUse(false);
        }
    }

    

    /**
     * Converts the graph into a DOT string.  
     * @return Returns a well formed DOT string of the graph.
     */
    @Override
    public String toString() {
        String returnString = "";

        if (digraph) {
            returnString = "digraph G{\n";
        } else {
            returnString += "graph G{\n;";
        //returnString += "graph G{\nsize=\"8.5,11\";";
        }
        
        
        //returnString += VertexHelper.vertexListToString(vertexList) + "\n";
        //returnString += AbstractEdge.allEdgesWithoutRepeats(vertexList) + "\n";

        returnString += "}\n";

        return returnString;
    }

	public boolean removeVertex(V v) {
		return vertexList.remove(v);
	}

	public int size() {
		return vertexList.size();
	}

	public boolean removeEdge(E e) {
		edgeList.remove(e);
		return true;

	}
	
	public void addEdge(E e) {
		edgeList.add(e);
	}

	public void addEdges(ArrayList<E> e) {
		edgeList.addAll(e);
	}

	public void addVertices(ArrayList<V> v) {
		vertexList.addAll(v);
	}
}
