/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.graph.edge;

/**
 *
 * @author w_jossey
 */
public interface Edge {
    
    double getWeight();
    
    void setInUse(boolean inUseBool);
    
    boolean isInUse();
    
    void setActive(boolean activeBool);
    
    boolean isActive();
    
    void setColor(String color);
    
    String getColor();
    
    void setStyle(String style);
    
    String getStyle();
    
    String toString();
    
}
