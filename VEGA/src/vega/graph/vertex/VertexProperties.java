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

	Vertex<? extends Edge> vertex;

	private String color = "grey"; // Color of the vertex
	private boolean colorOverride = false;

	private String style = "DEFAULT"; // Style of the vertex (fill?)
	private boolean styleOverride = false;
	
	private String shape = "DEFAULT"; // SHAPE
	private boolean shapeOverride = false;
	
	private int sides = 0; // Used by Graphviz to draw polygons
	private boolean sideOverride = false;
	
	VertexProperties(Vertex<?> v) {
		vertex = v;
	}

	/**
	 * Gets the color of the background for the vertex.
	 * 
	 * @return
	 */
	public String getColor() {
		String returnString = "";
		
		if(colorOverride){
			return color;
		}
		
		if(vertex.currentVertex() == true){
			returnString = "red";
		}else{
			if (vertex.active() == true || vertex.finalized() == true){
				returnString = "white";
			} else {
				if (vertex.inUse() == true) {
					returnString = "yellow";
				} else {
					returnString = "green";
				}
			}
		}
		
		return returnString;
	}

	/**
	 * Returns the shape of the vertex. By default, it is an Ellipse.
	 * 
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
	 * 
	 * @return
	 */
	public String getStyle() {
		if (vertex.active() || vertex.inUse()) {
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
		colorOverride = true;
		this.color = color;
	}

	public void setShape(String shape) {
		shapeOverride = true;
		this.shape = shape;
	}

	public void setSides(int sides) {
		sideOverride = true;
		this.sides = sides;
	}

	public void setStyle(String style) {
		styleOverride = true;
		this.style = style;
	}

	public void setParentVertex(Vertex<? super Edge> v) {
		vertex = v;
	}

}
