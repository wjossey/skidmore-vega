/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VEGA.Graph.Edge.Network;

import VEGA.Graph.Edge.DirectedEdge;
import VEGA.Graph.Vertex.Vertex;

/**
 *
 * @author w_jossey
 */
public class NetworkEdge extends DirectedEdge {

    double capacity;
    double flow;

    NetworkEdge(Vertex source, Vertex destination, double capacity) {
        super(source, destination);
        this.capacity = capacity;
    }
    
    NetworkEdge(Vertex source, Vertex destination, double capacity, double initialFlow){
        super(source, destination);
        this.capacity = capacity;
        flow = initialFlow;
    }
    
   
    /**
     * Get capacity of the network edge
     * @return
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Gets the flow on the edge.  Set to 0 initially.
     * @return
     */
    public double getFlow() {
        return flow;
    }

    /**
     * Return the residual capacity of the edge.
     * @return
     */
    public double getResidual() {
        return capacity - flow;
    }

    /**
     * Augments the flow for the edge in a network.
     * @param value
     */
    public void augmentFlow(double augmentValue) {
        flow = augmentValue + flow;
    }

    /**
     * Sets the flow for the edge.
     * @param flow
     */
    public void setFlow(double flow) {
        this.flow = flow;
    }
    
    @Override
    public String toString(){
        String returnString = "";
        
        return returnString;
    }
    
}
