package vega.graph.edge;

import java.util.ArrayList;

import interfaces.graph.vertex.Vertex;
import interfaces.graph.edge.Edge;

public abstract class AbstractEdge<V extends Vertex<? extends Edge>> implements Edge{

    /*Variable declaration*/
	
    private double weight;
    private boolean active = false;
    private boolean inUse = false;
    private String color = "DEFAULT";
    private String style = "DEFAULT";
    /*End variable declaration*/

    /**
     * Returns the edge weight
     * @return
     */
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double d){
    	weight = d;
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
    public static <V extends Vertex<E>, E extends Edge> void sortEdgesByDistance(E[] edgeList) {
        quicksort(edgeList, 0, edgeList.length - 1);
    }

    /**
     *
     * @param edgeList
     * @param left
     * @param right
     */
    private static <V extends Vertex<E>, E extends Edge> void quicksort(E[] edgeList, int left, int right) {
        if (right <= left) {
            return;
        //Else
        }
        int i = partition(edgeList, left, right);
        quicksort(edgeList, left, i - 1);
        quicksort(edgeList, i + 1, right);

    }

    private static <V extends Vertex<E>, E extends Edge> int partition(E[] edgeList, int left, int right) {
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
    private static <V extends Vertex<E>, E extends Edge> void swap(E[] edgeList, int i, int j) {
        //swaps++;  //We can keep track of our swaps for proof purposes.
        E temp = edgeList[i];
        edgeList[i] = edgeList[j];
        edgeList[j] = temp;
    }

    /**
     * 
     * @param edgeList
     * @return
     */
    private static <V extends Vertex<E>, E extends Edge> String edgeListToString(ArrayList<E> edgeList) {
        String returnString = "";
        for (int i = 0; i < edgeList.size(); i++) {
            returnString += edgeList.get(i).toString() + "\n";
        }

        return returnString;
    }

    /**
     * 
     * @param edgeList
     * @param index
     * @return
     */
    public static <V extends Vertex<E>, E extends Edge> String edgeListToString(ArrayList<E> edgeList, int index) {
        String returnString = "";
        for (int i = index; i < edgeList.size(); i++) {
            returnString += edgeList.get(i).toString() + "\n";
        }

        return returnString;
    }

    /**
     * 
     * @param vertexList
     * @return
     */
    public static <V extends Vertex<E>, E extends Edge> String allEdgesToString(ArrayList<V> vertexList) {
        String returnString = "";
        for (int i = 0; i < vertexList.size(); i++) {
            ArrayList<E> edgeList = vertexList.get(i).getEdges();
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
    public static <V extends Vertex<E>, E extends Edge> String allEdgesWithoutRepeats(ArrayList<V> vertexList) {
        String returnString = "";
        for (int i = 0; i < vertexList.size(); i++) {
            for (int j = i + 1; j < vertexList.size(); j++) {
                E e = vertexList.get(i).getEdge(vertexList.get(j));
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

}
