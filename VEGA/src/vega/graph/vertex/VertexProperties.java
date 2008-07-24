package vega.graph.vertex;

import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.vertex.GraphvizVertexProperties;

/**
 * 
 * @author Weston Jossey
 *
 */
public class VertexProperties implements GraphvizVertexProperties {
	
	Vertex<?> vertex;
	
	private String color = "DEFAULT"; //Color of the vertex

    private String style = "DEFAULT"; //Style of the vertex (fill?)

    private String shape = "DEFAULT"; //SHAPE
    
    private int sides = 0; //Used by Graphviz to draw polygons
	
	VertexProperties(Vertex<?> v){
		vertex = v;
	}
	
	 /**
     * Gets the color of the background for the vertex.
     * @return
     */
    public String getColor() {
        String returnString = "";

        if (vertex.isActive()) {
            returnString = "red";
        } else {
            if (vertex.inUse()) {
                returnString = "gray";
            } else {
                if (color.equalsIgnoreCase("DEFAULT")) {
                    returnString = "green";
                } else {
                    returnString = color;
                }
            }
        }
        return returnString;
    }

	/**
     * Returns the shape of the vertex.  By default, it is an Ellipse.
     * @return
     */
    public String getShape() {
        String returnString = "";
        if (shape.equalsIgnoreCase("DEFAULT")) {
            returnString += "ellipse";
        } else {
            returnString += shape;
        }
        return returnString;
    }

	public int getSides() {
		return sides;
	}

	/**
     * Returns the style of the vertex.
     * @return
     */
    public String getStyle() {
        if (vertex.isActive() || vertex.inUse()) {
            return "filled";
        } else {
            if (style.equalsIgnoreCase("DEFAULT")) {
                return "filled";
            } else {
                return style;
            }
        }
    }

	public void setColor(String color) {
		this.color = color;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setParentVertex(Vertex<? super Edge> v) {
		vertex = v;
	}

}
