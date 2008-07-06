/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (barak_naveh@users.sourceforge.net)
 *
 * (C) Copyright 2003-2007, by Barak Naveh and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
/* --------------------------
 * FibonnaciHeapNode.java
 * --------------------------
 * (C) Copyright 1999-2007, by Nathan Fiedler and Contributors.
 *
 * Original Author:  Nathan Fiedler
 * Contributor(s):   John V. Sichi
 *
 * $Id: FibonacciHeapNode.java 568 2007-09-30 00:12:18Z perfecthash $
 *
 * Changes
 * -------
 * 03-Sept-2003 : Adapted from Nathan Fiedler (JVS);
 *
 *      Name    Date            Description
 *      ----    ----            -----------
 *      nf      08/31/97        Initial version
 *      nf      09/07/97        Removed FibHeapData interface
 *      nf      01/20/01        Added synchronization
 *      nf      01/21/01        Made Node an inner class
 *      nf      01/05/02        Added clear(), renamed empty() to
 *                              isEmpty(), and renamed printHeap()
 *                              to toString()
 *      nf      01/06/02        Removed all synchronization
 *      JVS     06/24/06        Generics
 *
 */
package vega.graph.vertex.heap;

/**
 * Implements a node of the Fibonacci heap. It holds the information necessary
 * for maintaining the structure of the heap. It also holds the reference to the
 * key value (which is used to determine the heap structure).
 *
 * @author Nathan Fiedler
 */
public class FibonacciHeapNode<T>
{
	private static int uid = 0;
	private int pk;
	
    //~ Instance fields --------------------------------------------------------

    /**
     * Node data.
     */
    private T data;

    /**
     * first child node
     */
    private FibonacciHeapNode<T> child;

    /**
     * left sibling node
     */
    private FibonacciHeapNode<T> left;

    /**
     * parent node
     */
    private FibonacciHeapNode<T> parent;

    /**
     * right sibling node
     */
    private FibonacciHeapNode<T> right;

    /**
     * true if this node has had a child removed since this node was added to
     * its parent
     */
    private boolean mark;

    /**
     * key value for this node
     */
    private double key;

    /**
     * number of children of this node (does not count grandchildren)
     */
    private int degree;

    //~ Constructors -----------------------------------------------------------

    /**
     * Default constructor. Initializes the right and left pointers, making this
     * a circular doubly-linked list.
     *
     * @param data data for this node
     * @param key initial key for node
     */
    public FibonacciHeapNode(T data, double key)
    {
    	pk = uid++;
        right = this;
        left = this;
        this.data = data;
        this.key = key;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * Obtain the key for this node.
     *
     * @return the key
     */
    public final double getKey()
    {
        return key;
    }
    
    /**
     * 
     * @param key
     * @return
     */
    public double setKey(double key){
    	return this.key = key;
    }

    /**
     * Obtain the data for this node.
     */
    public final T getData()
    {
        return data;
    }
    
    /**
     * Return the node to the left.
     * @return Node to the left
     */
    public FibonacciHeapNode<T> getLeft(){
    	return left;
    }
    
    /**
     * Return the node to the right
     * @return Node to the right
     */
    public FibonacciHeapNode<T> getRight(){
    	return right;
    }
    
    /**
     * Returns the parent node.
     * @return Parent node
     */
    public FibonacciHeapNode<T> getParent(){
    	return parent;
    }
    
    /**
     * Returns the child node.
     * @return Child node
     */
    public FibonacciHeapNode<T> getChild(){
    	return child;
    }
    
    /**
     * Sets the left node of the FibHeapNode
     * @param left New left node.
     * @return The left node.
     */
    public FibonacciHeapNode<T> setLeft(FibonacciHeapNode<T> left){
    	return this.left = left;
    }
    
    /**
     * Sets the right node.
     * @param right New right node.
     * @return New right node.
     */
    public FibonacciHeapNode<T> setRight(FibonacciHeapNode<T> right){
    	return this.right = right;
    }
    
    /**
     * Sets the parent node.
     * @param parent New parent node.
     * @return The parent node
     */
    public FibonacciHeapNode<T> setParent(FibonacciHeapNode<T> parent){
    	return this.parent = parent;
    }
    
    /**
     * Sets the child node.
     * @param child New child node.
     * @return The child node
     */
    public FibonacciHeapNode<T> setChild(FibonacciHeapNode<T> child){
    	return this.child = child;
    }
    
    /**
     * 
     * @return
     */
    public int degree(){
    	return degree;
    }
    
    /**
     * 
     * @param degree
     * @return
     */
    public int degree(int degree){
    	return this.degree = degree;
    }
    
    /**
     * Set whether or not the node is marked.
     * @param mark True if marked, false if not marked.
     * @return Value of mark
     */
    public boolean mark(boolean mark){
    	return this.mark = mark;
    }
    
    /**
     * Gets the boolean status of whether or not the node is marked.
     * @return True if marked, false if not marked.
     */
    public boolean mark(){
    	return mark;
    }

    /**
     * Returns the primary key value of the node.  
     * @return Primary key value of the node.
     */
    public int getPk(){
    	return pk;
    }

    /**
     * Return the string representation of this object.
     *
     * @return string representing this object
     */
    public String toString()
    {
        if (true) {
            return Double.toString(key);
        } else {
            StringBuffer buf = new StringBuffer();
            buf.append("Node=[ ");

            if (parent != null) {
                buf.append(Double.toString(parent.key));
            } else {
                buf.append("---");
            }

            buf.append(", key = ");
            buf.append(Double.toString(key));
            buf.append(", degree = ");
            buf.append(Integer.toString(degree));
            buf.append(", right = ");

            if (right != null) {
                buf.append(Double.toString(right.key));
            } else {
                buf.append("---");
            }

            buf.append(", left = ");

            if (left != null) {
                buf.append(Double.toString(left.key));
            } else {
                buf.append("---");
            }

            buf.append(", child = ");

            if (child != null) {
                buf.append(Double.toString(child.key));
            } else {
                buf.append("---");
            }

            buf.append(']');

            return buf.toString();
        }
    }
    
    /**
     * Returns the FibHeapNode as a graphviz node.
     * @return String representation of the FibHeapNode for graphviz.
     */
    public String toGraphViz(){
    	String returnString = "";
    	returnString += pk + " [";
    	
    	returnString += "label= \"" + key + "\"];";
    	
    	
    	return returnString;
    }

    // toString
}

// End FibonacciHeapNode.java
