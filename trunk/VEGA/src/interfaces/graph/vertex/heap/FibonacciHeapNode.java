package interfaces.graph.vertex.heap;

/**
 * 
 * @author w_jossey
 *
 * @param <D>
 * @param <T>
 */
public interface FibonacciHeapNode<D, T extends FibonacciHeapNode<D,? super T>> {

	/**
	 * Obtain the key for this node.
	 *
	 * @return the key
	 */
	public abstract double getKey();

	/**
	 * 
	 * @param key
	 * @return
	 */
	public abstract double setKey(double key);

	/**
	 * Obtain the data for this node.
	 */
	public abstract D getData();

	/**
	 * Return the node to the left.
	 * @return Node to the left
	 */
	public abstract T getLeft();

	/**
	 * Return the node to the right
	 * @return Node to the right
	 */
	public abstract T getRight();

	/**
	 * Returns the parent node.
	 * @return Parent node
	 */
	public abstract T getParent();

	/**
	 * Returns the child node.
	 * @return Child node
	 */
	public abstract T getChild();

	/**
	 * Sets the left node of the FibHeapNode
	 * @param left New left node.
	 * @return The left node.
	 */
	public abstract T setLeft(T left);

	/**
	 * Sets the right node.
	 * @param right New right node.
	 * @return New right node.
	 */
	public abstract T setRight(T right);

	/**
	 * Sets the parent node.
	 * @param parent New parent node.
	 * @return The parent node
	 */
	public abstract T setParent(T parent);

	/**
	 * Sets the child node.
	 * @param child New child node.
	 * @return The child node
	 */
	public abstract T setChild(T child);

	/**
	 * 
	 * @return
	 */
	public abstract int degree();

	/**
	 * 
	 * @param degree
	 * @return
	 */
	public abstract int degree(int degree);

	/**
	 * Set whether or not the node is marked.
	 * @param mark True if marked, false if not marked.
	 * @return Value of mark
	 */
	public abstract boolean mark(boolean mark);

	/**
	 * Gets the boolean status of whether or not the node is marked.
	 * @return True if marked, false if not marked.
	 */
	public abstract boolean mark();
	
	/**
	 * 
	 * @return
	 */
	public abstract int getPK();

}