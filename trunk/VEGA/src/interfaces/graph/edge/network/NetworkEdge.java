package interfaces.graph.edge.network;

public interface NetworkEdge {

	/**
	 * Get capacity of the network edge
	 * @return
	 */
	public double getCapacity();

	/**
	 * Gets the flow on the edge.  Set to 0 initially.
	 * @return
	 */
	public double getFlow();

	/**
	 * Return the residual capacity of the edge.
	 * @return
	 */
	public double getResidual();

	/**
	 * Augments the flow for the edge in a network.
	 * @param value
	 */
	public void augmentFlow(double augmentValue);

	/**
	 * Sets the flow for the edge.
	 * @param flow
	 */
	public void setFlow(double flow);

}