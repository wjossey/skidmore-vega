/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vega.graph.edge.network;

import interfaces.graph.edge.network.DirectedNetworkEdge;
import interfaces.graph.vertex.network.NetworkNode;
import vega.graph.edge.DirectedEdge;
/**
 *
 * @author w_jossey
 */
public abstract class NetworkEdge<N extends NetworkNode<DirectedNetworkEdge<N>>> extends DirectedEdge<N> implements interfaces.graph.edge.network.NetworkEdge {

    double capacity;
    double flow;

    public NetworkEdge(N source, N destination, double capacity) {
        super(source, destination);
        this.capacity = capacity;
    }
    
    public NetworkEdge(N source, N destination, double capacity, double initialFlow){
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
