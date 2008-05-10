/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces.Graph.Edge;

import Interfaces.Graph.Vertex.Vertex;

/**
 *
 * @author w_jossey
 */
public interface Edge {
    void Edge(Vertex a, Vertex b, double weight);
    
    void Edge(Vertex a, Vertex b);
    
    double getWeight();
    
    Vertex getVertexA();
    
    Vertex getVertexB();
    
    void setInUse(boolean inUseBool);
    
    boolean isInUse();
    
    void setActive(boolean activeBool);
    
    boolean isActive();
    
    void setColor(String color);
    
    String getColor();
    
    void setStyle(String style);
    
    String getStyle();
    
    @Override
    String toString();
    
}
