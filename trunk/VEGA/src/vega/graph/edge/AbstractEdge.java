package vega.graph.edge;



import interfaces.graph.edge.Edge;

/**
 * 
 * @author Weston Jossey
 *
 */
public abstract class AbstractEdge implements Edge{

    /*Variable declaration*/
	
    private double weight;
    private boolean active = false;
    private boolean inUse = false;
    private boolean onPath = false;
    private String color = "DEFAULT";
    private String style = "DEFAULT";
    /*End variable declaration*/

    /**
     * Empty constructor.  Instantiates the edge.
     */
    public AbstractEdge(){
    	active = false;
    	inUse = false;
    	weight = 0;
    }
    
    /**
     * Returns the edge weight
     * @return Edge weight
     */
    public double getWeight() {
        return weight;
    }
    
    /**
     * Sets the edge weight
     * @param d Edge Weight
     */
    public void setWeight(double d){
    	weight = d;
    }

    /**
     * Returns the edge weight in a formatted string.  Useful for DOT
     * @return String formatted weight
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
     * Sets the edge to in use.
     * @param inUseBool
     */
    public void inUse(boolean inUseBool) {
        inUse = inUseBool;
    }

    /**
     *
     * @return
     */
    public boolean inUse() {
        return inUse;
    }

    /**
     *
     * @param activeBool
     */
    public void isActive(boolean activeBool) {
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
    public boolean onPath(){
    	return onPath;
    }
    
    /**
     * 
     * @param isOnPath
     * @return
     */
    public boolean onPath(boolean isOnPath){
    	return onPath = isOnPath;
    }

    /**
     * Gets the color of the edge
     * @return Returns the color of the edge.
     */
    public String getColor() {
        String returnString = "";
        if (color.equalsIgnoreCase("DEFAULT")) {
            returnString = "color=black";
        } else {
            returnString = "color=" + color;
        }
        
        if(onPath){
        	returnString = "color=red";
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
     * Gets the style of the edge
     * @return style of the edge
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
        
        if(onPath){
        	returnString = "style=bold, ";
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
     * Generates the DOT string for the edge.  The format is:
     * a -- b [shape=polygon,sides=5,peripheries=3,color=lightblue,style=filled];
     * @return the DOT formatted string.
     */

}
