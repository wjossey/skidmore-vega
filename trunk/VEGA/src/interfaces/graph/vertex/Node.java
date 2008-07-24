package interfaces.graph.vertex;

import interfaces.graph.edge.Edge;

/**
 * 
 * @author w_jossey
 *
 * @param <D>
 * @param <E>
 */
public interface Node<D, E extends Edge> {

	public abstract D getData();

	public abstract void setData(D d);

}