/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph.edge;

/**
 *
 * @author w_jossey
 */
public interface Edge{
    
	/**
	 * Returns the edge weight.
	 * @return Edge weight as a double
	 */
    double getWeight();
    
    /**
     * Sets the edge weight.
     * @param d New edge weight as a double.
     */
    void setWeight(double d);
    
    /**
     * Sets a boolean that marks whether the edge is in use.  Used by VEGA.
     * @param inUseBool True if in use, false if not in use.
     */
    void inUse(boolean inUseBool);
    
    /**
     * Returns the value of the inUse boolean.
     * @return Returns true if in use, false if not in use.
     */
    boolean inUse();
    
    /**
     * Sets the boolean value of whether or not the edge is active.  Used by VEGA.
     * @param activeBool Set to true if the edge is active, else false.
     */
    void isActive(boolean activeBool);
    
    /**
     * Gets the boolean value of whether or not the edge is active.
     * @return Returns true if the edge is active, else false.
     */
    boolean isActive();
    
    /**
     * Sets the color of the edge.
     * @param color Color to color the edge.
     */
    void setColor(String color);
    
    /**
     * Returns the color of the edge.
     * @return Color of the edge.
     */
    String getColor();
    
    /**
     * Sets the style of the edge for Graphviz.
     * @param style Style to style the edge with.
     */
    void setStyle(String style);
    
    /**
     * Returns the style of the edge.
     * @return Style of the edge as a String.
     */
    String getStyle();
    
    /**
     * Represents the edge as a String.
     * @return Returns the edge as a string output.
     */
    String toString();
    
}
