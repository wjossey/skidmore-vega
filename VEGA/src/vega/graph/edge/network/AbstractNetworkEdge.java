/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.graph.edge.network;

import vega.graph.edge.AbstractEdge;
/**
 *
 * @author Weston Jossey
 */
public abstract class AbstractNetworkEdge extends AbstractEdge implements interfaces.graph.edge.network.NetworkEdge {

    double capacity;
    double flow;

    public AbstractNetworkEdge(double capacity) {
        super();
        this.capacity = capacity;
    }
    
    public AbstractNetworkEdge(double capacity, double initialFlow){
        super();
        this.capacity = capacity;
        this.flow = initialFlow;
    }
    
    public AbstractNetworkEdge(){
    	super();
    	capacity = 0;
    	flow = 0;
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
