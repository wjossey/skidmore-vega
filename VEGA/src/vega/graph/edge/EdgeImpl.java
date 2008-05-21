package vega.graph.edge;

import interfaces.graph.vertex.Vertex;
import interfaces.graph.edge.Edge;

public class EdgeImpl implements Edge{

    /*Variable declaration*/
    private Vertex a;
    private Vertex b;
    private boolean directed = false;
    private double weight;
    private boolean active = false;
    private boolean inUse = false;
    private String color = "DEFAULT";
    private String style = "DEFAULT";
    /*End variable declaration*/

    /**
     * Constructor method for the Edge class.  Takes in two vertices and their edge weight.
     * @param a Vertex a
     * @param b Vertex b
     * @param weight Edge weight
     */
    public EdgeImpl(Vertex a, Vertex b, double weight) {

        //Arrange cities relative to their UID so we always know which city to expect first
        //The city with the greater UID will always be City A.
        
    	if (a.getUID() > b.getUID()) {
            this.a = a;
            this.b = b;
        } else {
            this.a = b;
            this.b = a;
        }
        this.weight = weight;
    }

    /**
     * Edge constructor method without edge weight.
     * @param a Vertex a
     * @param b Vertex b
     */
    public EdgeImpl(Vertex a, Vertex b) {
        //Arrange cities relative to their UID so we always know which city to expect first
        //The city with the greater UID will always be City A.
        if (a.getUID() > b.getUID()) {
            this.a = a;
            this.b = b;
        } else {
            this.a = b;
            this.b = a;
        }
    }

    /**
     * Returns the edge weight
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the edge weight in a formatted string.  Useful for DOT
     * @return
     */
    public String getFormattedWeight() {
        if (weight != Double.NaN) {
            String returnString = "label=";

            returnString += String.format("%.2f", weight);
            returnString += ", ";
            return returnString;
        } else {
            return null;
        }
    }

    /**
     * Get Vertex a.
     * @return
     */
    public Vertex getVertexA() {
        return a;
    }

    /**
     * Get Vertex b.
     * @return
     */
    public Vertex getVertexB() {
        return b;
    }

    /**
     *
     * @param inUseBool
     */
    public void setInUse(boolean inUseBool) {
        inUse = inUseBool;
    }

    /**
     *
     * @return
     */
    public boolean isInUse() {
        return inUse;
    }

    /**
     *
     * @param activeBool
     */
    public void setActive(boolean activeBool) {
        this.active = activeBool;
    }

    /**
     *
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     *
     * @return
     */
    public String getColor() {
        String returnString = "";
        if (color.equalsIgnoreCase("DEFAULT")) {
            returnString = "color=black";
        } else {
            returnString = "color=" + color;
        }
        return returnString;
    }

    /**
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return
     */
    public String getStyle() {
        String returnString = "";

        if (active) {
            returnString = "style=bold, ";
        } else {
            if (inUse) {
                returnString = "style=filled, ";
            } else {
                if (style.equalsIgnoreCase("DEFAULT")) {
                    returnString = "style=dotted, ";
                } else {
                    returnString = style;
                }
            }
        }

        return returnString;
    }

    /**
     *
     * @param style
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     *
     * @param edgeList
     */
    public static void sortEdgesByDistance(Edge[] edgeList) {
        quicksort(edgeList, 0, edgeList.length - 1);
    }

    /**
     *
     * @param edgeList
     * @param left
     * @param right
     */
    private static void quicksort(Edge[] edgeList, int left, int right) {
        if (right <= left) {
            return;
        //Else
        }
        int i = partition(edgeList, left, right);
        quicksort(edgeList, left, i - 1);
        quicksort(edgeList, i + 1, right);

    }

    @SuppressWarnings("empty-statement")
    private static int partition(Edge[] edgeList, int left, int right) {
        int i = left - 1;
        int j = right;

        while (true) {
            while (less(edgeList[++i].getWeight(), edgeList[right].getWeight()));
            while (less(edgeList[right].getWeight(), edgeList[--j].getWeight())) {
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(edgeList, i, j);
        }
        swap(edgeList, i, right);

        return i;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean less(double x, double y) {
        return (x < y);
    }

    /**
     * 
     * @param edgeList
     * @param i
     * @param j
     */
    private static void swap(Edge[] edgeList, int i, int j) {
        //swaps++;  //We can keep track of our swaps for proof purposes.
        Edge temp = edgeList[i];
        edgeList[i] = edgeList[j];
        edgeList[j] = temp;
    }

    /**
     * 
     * @param edgeList
     * @return
     */
    private static String edgeListToString(Edge[] edgeList) {
        String returnString = "";
        for (int i = 0; i < edgeList.length; i++) {
            returnString += edgeList[i].toString() + "\n";
        }

        return returnString;
    }

    /**
     * 
     * @param edgeList
     * @param index
     * @return
     */
    public static String edgeListToString(Edge[] edgeList, int index) {
        String returnString = "";
        for (int i = index; i < edgeList.length; i++) {
            returnString += edgeList[i].toString() + "\n";
        }

        return returnString;
    }

    /**
     * 
     * @param vertexList
     * @return
     */
    public static String allEdgesToString(Vertex[] vertexList) {
        String returnString = "";
        for (int i = 0; i < vertexList.length; i++) {
            Edge[] edgeList = vertexList[i].getEdges();
            returnString += edgeListToString(edgeList);
        }

        return returnString;
    }

    /**
     * A static method that generates the edges for an array of vertices without
     * repeating edges.  Keeps the DOT file smaller.
     * @param vertexList a vertex array
     * @return returns the generated String.
     */
    public static String allEdgesWithoutRepeats(Vertex[] vertexList) {
        String returnString = "";
        for (int i = 0; i < vertexList.length; i++) {
            for (int j = i + 1; j < vertexList.length; j++) {
                Edge e = vertexList[i].getEdge(vertexList[j]);
                if (e != null) {
                    returnString += e.toString() + "\n";
                }
            }
        }

        return returnString;
    }

    /**
     * Generates the DOT string for the edge.  The format is:
     * a -- b [shape=polygon,sides=5,peripheries=3,color=lightblue,style=filled];
     * @return the DOT formatted string.
     */
    @Override
    public String toString() {
        String returnString = "";
        returnString += a.getName(); //a -- b [shape=polygon,sides=5,peripheries=3,color=lightblue,style=filled];

        if (directed) {
            returnString += " -> ";
        } else {
            returnString += " -- ";
        }

        returnString += b.getName() + " ";
        returnString += "[ " + getStyle() + getFormattedWeight() +
                getColor() + "];";


        return returnString;
    }

}
