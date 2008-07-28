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
	public double getWeight();
    
    /**
     * Sets the edge weight.
     * @param d New edge weight as a double.
     */
    public void setWeight(double d);
    
    /**
     * Sets a boolean that marks whether the edge is in use.  Used by VEGA.
     * @param inUseBool True if in use, false if not in use.
     */
    public boolean inUse(boolean inUseBool);
    
    /**
     * Returns the value of the inUse boolean.
     * @return Returns true if in use, false if not in use.
     */
    public boolean inUse();
    
    /**
     * Sets the boolean value of whether or not the edge is active.  Used by VEGA.
     * @param activeBool Set to true if the edge is active, else false.
     */
    public boolean active(boolean active);
    
    /**
     * Gets the boolean value of whether or not the edge is active.
     * @return Returns true if the edge is active, else false.
     */
    public boolean active();
    
    /**
     * Sets the color of the edge.
     * @param color Color to color the edge.
     */
    public void setColor(String color);
    
    /**
     * Returns the color of the edge.
     * @return Color of the edge.
     */
    public String getColor();
    
    /**
     * Sets the style of the edge for Graphviz.
     * @param style Style to style the edge with.
     */
    public void setStyle(String style);
    
    /**
     * Returns the style of the edge.
     * @return Style of the edge as a String.
     */
    public String getStyle();
    
    /**
     * Represents the edge as a String.
     * @return Returns the edge as a string output.
     */
    public String toString();
    
    /**
     * 
     * @return
     */
    public boolean onPath();
    
    /**
     * 
     * @param isOnPath
     * @return
     */
    public boolean onPath(boolean isOnPath);
    
    
}
