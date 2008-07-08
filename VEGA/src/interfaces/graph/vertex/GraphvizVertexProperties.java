package interfaces.graph.vertex;

import interfaces.graph.edge.Edge;

/**
 * 
 * @author w_jossey
 *
 */
public interface GraphvizVertexProperties {
	
	void setParentVertex(Vertex<? super Edge> v);
	
	void setSides(int sides);
	
	int getSides();
	
	String getShape();
	
	String getColor();
	
	String getStyle();
	
	void setColor(String color);
	
	void setShape(String shape);
	
	void setStyle(String style);
	
}
