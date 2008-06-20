package interfaces.graph.vertex;

public interface GraphvizVertexProperties {
	
	void setParentVertex(Vertex<?,?> v);
	
	void setSides(int sides);
	
	int getSides();
	
	String getShape();
	
	String getColor();
	
	String getStyle();
	
	void setColor(String color);
	
	void setShape(String shape);
	
	void setStyle(String style);
	
}
