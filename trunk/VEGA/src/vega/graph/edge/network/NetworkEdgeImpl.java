/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.graph.edge.network;

import vega.graph.edge.DirectedEdgeImpl;
import interfaces.graph.edge.network.NetworkEdge;
import interfaces.graph.vertex.Vertex;

/**
 *
 * @author w_jossey
 */
public class NetworkEdgeImpl extends DirectedEdgeImpl implements NetworkEdge {

    double capacity;
    double flow;

    public NetworkEdgeImpl(Vertex source, Vertex destination, double capacity) {
        super(source, destination);
        this.capacity = capacity;
    }
    
    public NetworkEdgeImpl(Vertex source, Vertex destination, double capacity, double initialFlow){
        super(source, destination);
        this.capacity = capacity;
        flow = initialFlow;
    }
    
   
    /* (non-Javadoc)
	 * @see vega.graph.edge.network.NetworkEdge#getCapacity()
	 */
    public double getCapacity() {
        return capacity;
    }

    /* (non-Javadoc)
	 * @see vega.graph.edge.network.NetworkEdge#getFlow()
	 */
    public double getFlow() {
        return flow;
    }

    /* (non-Javadoc)
	 * @see vega.graph.edge.network.NetworkEdge#getResidual()
	 */
    public double getResidual() {
        return capacity - flow;
    }

    /* (non-Javadoc)
	 * @see vega.graph.edge.network.NetworkEdge#augmentFlow(double)
	 */
    public void augmentFlow(double augmentValue) {
        flow = augmentValue + flow;
    }

    /* (non-Javadoc)
	 * @see vega.graph.edge.network.NetworkEdge#setFlow(double)
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
