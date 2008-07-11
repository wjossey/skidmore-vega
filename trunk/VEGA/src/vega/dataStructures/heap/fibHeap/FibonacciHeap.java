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
 * FibonnaciHeap.java
 * --------------------------
 * (C) Copyright 1999-2003, by Nathan Fiedler and Contributors.
 *
 * Original Author:  Nathan Fiedler
 * Contributor(s):   John V. Sichi
 *
 * $Id: FibonacciHeap.java 568 2007-09-30 00:12:18Z perfecthash $
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
 *
 */
package vega.dataStructures.heap.fibHeap;

import vega.graph.vertex.heap.FibonacciHeapNode;

import java.util.*;


/**
 *  * This class implements a Fibonacci heap data structure. Much of the code in
 * this class is based on the algorithms in the "Introduction to Algorithms"by
 * Cormen, Leiserson, and Rivest in Chapter 21. The amortized running time of
 * most of these methods is O(1), making it a very fast data structure. Several
 * have an actual running time of O(1). removeMin() and delete() have O(log n)
 * amortized running times because they do the heap consolidation. If you
 * attempt to store nodes in this heap with key values of -Infinity
 * (Double.NEGATIVE_INFINITY) the <code>delete()</code> operation may fail to
 * remove the correct element.
 *
 * <p><b>Note that this implementation is not synchronized.</b> If multiple
 * threads access a set concurrently, and at least one of the threads modifies
 * the set, it <i>must</i> be synchronized externally. This is typically
 * accomplished by synchronizing on some object that naturally encapsulates the
 * set.</p>
 *
 * <p>This class was originally developed by Nathan Fiedler for the GraphMaker
 * project. It was imported to JGraphT with permission, courtesy of Nathan
 * Fiedler.</p>
 * 
 * <p>This class has been re-developed for VEGA by Weston Jossey.  The class was 
 * acquired through the JGraphT open source project.  The code has been modified to
 * allow for integration with VEGA, as well as a removal of all direct access of
 * non-internal variables.<p>
 *
 * @author Nathan Fiedler
 * @author w_jossey
 *
 * @param <C>
 */
public class FibonacciHeap<C> implements interfaces.dataStructures.heap.FibonacciHeap<C,FibonacciHeapNode<C>>
{
    //~ Instance fields --------------------------------------------------------

    /**
     * Points to the minimum node in the heap.
     */
    private FibonacciHeapNode<C> minNode;

    /**
     * Number of nodes in the heap.
     */
    private int nNodes;

    //~ Constructors -----------------------------------------------------------

    /**
     * Constructs a FibonacciHeap object that contains no elements.
     */
    public FibonacciHeap()
    {
    } // FibonacciHeap

    //~ Methods ----------------------------------------------------------------

    /* (non-Javadoc)
	 * @see vega.dataStructures.heap.fibHeap.FibonacciHea#isEmpty()
	 */
    public boolean isEmpty()
    {
        return minNode == null;
    }

    // isEmpty

    /**
     * 
     */
    public void clear()
    {
        minNode = null;
        nNodes = 0;
    }

    /**
     * 
     */
    public void decreaseKey(FibonacciHeapNode<C> x, double k)
    {
        if (k > x.getKey()) {
            throw new IllegalArgumentException(
                "decreaseKey() got larger key value");
        }

        x.setKey(k);

        FibonacciHeapNode<C> parent = x.getParent();

        if ((parent != null) && (x.getKey() < parent.getKey())) {
            cut(x, parent);
            cascadingCut(parent);
        }

        if (x.getKey() < minNode.getKey()) {
            minNode = x;
        }
    }

    // decreaseKey

    /**
     * 
     */
    public void delete(FibonacciHeapNode<C> x)
    {
        // make x as small as possible
        decreaseKey(x, Double.NEGATIVE_INFINITY);

        // remove the smallest, which decreases n also
        removeMin();
    }

    /**
     * Insert the node into the heap with a key value.
     * @param node Node to insert.
     * @param key Reference key to node
     */
    public void insert(FibonacciHeapNode<C> node, double key)
    {
        node.setKey(key);

        // concatenate node into min list
        if (minNode != null) {
            node.setLeft(minNode);
            node.setRight(minNode.getRight());
            minNode.setRight(node);
            node.getRight().setLeft(node);

            if (key < minNode.getKey()) {
                minNode = node;
            }
        } else {
            minNode = node;
        }

        nNodes++;
    }


    /**
     * @return Returns the minimimum node of the heap.
     */
    public FibonacciHeapNode<C> min()
    {
        return minNode;
    }

    /**
     * Removes the minimum node from the heap.
     * @return Minimum node of the heap.
     */
    public FibonacciHeapNode<C> removeMin()
    {
        FibonacciHeapNode<C> z = minNode;

        if (z != null) {
            int numKids = z.degree();
            FibonacciHeapNode<C> x = z.getChild();
            FibonacciHeapNode<C> tempRight;

            // for each child of z do...
            while (numKids > 0) {
                tempRight = x.getRight();

                // remove x from child list
                x.getLeft().setRight(x.getRight());
                x.getRight().setLeft(x.getLeft());

                // add x to root list of heap
                x.setLeft(minNode);
                x.setRight(minNode.getRight());
                minNode.setRight(x);
                x.getRight().setLeft(x);

                // set parent[x] to null
                x.setParent(null);
                x = tempRight;
                numKids--;
            }

            // remove z from root list of heap
            z.getLeft().setRight(z.getRight());
            z.getRight().setLeft(z.getLeft());

            if (z == z.getRight()) {
                minNode = null;
            } else {
                minNode = z.getRight();
                consolidate();
            }

            // decrement size of heap
            nNodes--;
        }

        return z;
    }


    /**
     * Returns the size of the heap.
     * @return Size of the heap.
     */
    public int size()
    {
        return nNodes;
    }

    // size

    /**
     * Joins two Fibonacci heaps into a new one. No heap consolidation is
     * performed at this time. The two root lists are simply joined together.
     *
     * <p>Running time: O(1) actual</p>
     *
     * @param h1 first heap
     * @param h2 second heap
     *
     * @return new heap containing h1 and h2
     */
    public static <C extends Comparable<C>> FibonacciHeap<C> union(
        FibonacciHeap<C> h1,
        FibonacciHeap<C> h2)
    {
        FibonacciHeap<C> h = new FibonacciHeap<C>();

        if ((h1 != null) && (h2 != null)) {
            h.minNode = h1.minNode;

            if (h.minNode != null) {
                if (h2.minNode != null) {
                    h.minNode.getRight().setLeft(h2.minNode.getLeft());
                    h2.minNode.getLeft().setRight(h.minNode.getRight());
                    h.minNode.setRight(h2.minNode);
                    h2.minNode.setLeft(h.minNode);

                    if (h2.minNode.getKey() < h1.minNode.getKey()) {
                        h.minNode = h2.minNode;
                    }
                }
            } else {
                h.minNode = h2.minNode;
            }

            h.nNodes = h1.nNodes + h2.nNodes;
        }

        return h;
    }

    /**
     * Creates a String representation of this Fibonacci heap.
     *
     * @return Fibonacci heap as a string.
     */
    public String toString()
    {
        if (minNode == null) {
            return "FibonacciHeap=[]";
        }

        // create a new stack and put root on it
        Stack<FibonacciHeapNode<C>> stack = new Stack<FibonacciHeapNode<C>>();
        stack.push(minNode);

        StringBuffer buf = new StringBuffer(512);
        buf.append("FibonacciHeap=[");

        // do a simple breadth-first traversal on the tree
        while (!stack.empty()) {
            FibonacciHeapNode<C> curr = stack.pop();
            if(curr.getKey() == Double.MAX_VALUE){
        		buf.append("+Inf");
        	}else{
        		buf.append(curr);
        	}
            buf.append(", ");

            if (curr.getChild() != null) {
                stack.push(curr.getChild());
            }

            FibonacciHeapNode<C> start = curr;
            curr = curr.getRight();

            while (curr != start) {
            	if(curr.getKey() == Double.MAX_VALUE){
            		buf.append("+Inf");
            	}else{
            		buf.append(curr);
            	}
                buf.append(", ");

                if (curr.getChild() != null) {
                    stack.push(curr.getChild());
                }

                curr = curr.getRight();
            }
        }

        buf.append(']');

        return buf.toString();
    }

    // toString

    /**
     * Performs a cascading cut operation. This cuts y from its parent and then
     * does the same for its parent, and so on up the tree.
     *
     * <p>Running time: O(log n); O(1) excluding the recursion</p>
     *
     * @param y node to perform cascading cut on
     */
    protected void cascadingCut(FibonacciHeapNode<C> y)
    {
        FibonacciHeapNode<C> z = y.getParent();

        // if there's a parent...
        if (z != null) {
            // if y is unmarked, set it marked
            if (!y.mark()) {
                y.mark(true);
            } else {
                // it's marked, cut it from parent
                cut(y, z);

                // cut its parent as well
                cascadingCut(z);
            }
        }
    }

    // cascadingCut

    /**
     * Consolidates the trees in the heap by joining trees of equal degree until
     * there are no more trees of equal degree in the root list.
     *
     * <p>Running time: O(log n) amortized</p>
     */
    protected void consolidate()
    {
        int arraySize = nNodes + 1;
        List<FibonacciHeapNode<C>> array =
            new ArrayList<FibonacciHeapNode<C>>(arraySize);

        // Initialize degree array
        for (int i = 0; i < arraySize; i++) {
            array.add(null);
        }

        // Find the number of root nodes.
        int numRoots = 0;
        FibonacciHeapNode<C> x = minNode;

        if (x != null) {
            numRoots++;
            x = x.getRight();

            while (x != minNode) {
                numRoots++;
                x = x.getRight();
            }
        }

        // For each node in root list do...
        while (numRoots > 0) {
            // Access this node's degree..
            int d = x.degree();
            FibonacciHeapNode<C> next = x.getRight();

            // ..and see if there's another of the same degree.
            while (array.get(d) != null) {
                // There is, make one of the nodes a child of the other.
                FibonacciHeapNode<C> y = array.get(d);

                // Do this based on the key value.
                if (x.getKey() > y.getKey()) {
                    FibonacciHeapNode<C> temp = y;
                    y = x;
                    x = temp;
                }

                // FibonacciHeapNode<C> y disappears from root list.
                link(y, x);

                // We've handled this degree, go to next one.
                array.set(d, null);
                d++;
            }

            // Save this node for later when we might encounter another
            // of the same degree.
            array.set(d, x);

            // Move forward through list.
            x = next;
            numRoots--;
        }

        // Set min to null (effectively losing the root list) and
        // reconstruct the root list from the array entries in array[].
        minNode = null;

        for (int i = 0; i < arraySize; i++) {
            if (array.get(i) != null) {
                // We've got a live one, add it to root list.
                if (minNode != null) {
                    // First remove node from root list.
                    array.get(i).getLeft().setRight(array.get(i).getRight());
                    array.get(i).getRight().setLeft(array.get(i).getLeft());

                    // Now add to root list, again.
                    array.get(i).setLeft(minNode);
                    array.get(i).setRight(minNode.getRight());
                    minNode.setRight(array.get(i));
                    array.get(i).getRight().setLeft(array.get(i));

                    // Check if this is a new min.
                    if (array.get(i).getKey() < minNode.getKey()) {
                        minNode = array.get(i);
                    }
                } else {
                    minNode = array.get(i);
                }
            }
        }
    }

    // consolidate

    /**
     * The reverse of the link operation: removes x from the child list of y.
     * This method assumes that min is non-null.
     *
     * <p>Running time: O(1)</p>
     *
     * @param x child of y to be removed from y's child list
     * @param y parent of x about to lose a child
     */
    protected void cut(FibonacciHeapNode<C> x, FibonacciHeapNode<C> y)
    {
        // remove x from childlist of y and decrement degree[y]
        x.getLeft().setRight(x.getRight());
        x.getRight().setLeft(x.getLeft());
        y.degree(y.degree() - 1);

        // reset y.child if necessary
        if (y.getChild() == x) {
            y.setChild(x.getRight());
        }

        if (y.degree() == 0) {
            y.setChild(null);
        }

        // add x to root list of heap
        x.setLeft(minNode);
        x.setRight(minNode.getRight());
        minNode.setRight(x);
        x.getRight().setLeft(x);

        // set parent[x] to nil
        x.setParent(null);

        // set mark[x] to false
        x.mark(false);
    }

    // cut

    /**
     * Make node y a child of node x.
     *
     * <p>Running time: O(1) actual</p>
     *
     * @param y node to become child
     * @param x node to become parent
     */
    protected void link(FibonacciHeapNode<C> y, FibonacciHeapNode<C> x)
    {
        // remove y from root list of heap
        y.getLeft().setRight(y.getRight());
        y.getRight().setLeft(y.getLeft());

        // make y a child of x
        y.setParent(x);

        if (x.getChild() == null) {
            x.setChild(y);
            y.setRight(y);
            y.setLeft(y);
        } else {
            y.setLeft(x.getChild());
            y.setRight(x.getChild().getRight());
            x.getChild().setRight(y);
            y.getRight().setLeft(y);
        }

        // increase degree[x]
        x.degree(x.degree() + 1);

        // set mark[y] false
        y.mark(false);
    }

    // link
    
    public static void main(String args[]){
    	FibonacciHeap<Integer> heap = new FibonacciHeap<Integer>();
    	
    	Random generator = new Random(12823423);
    	for(int i = 0; i < 1000; i++){
    		int temp = generator.nextInt(50000);
    		heap.insert(new FibonacciHeapNode<Integer>(new Integer(temp), temp), temp);
    		//System.out.println(heap.toGraphViz());
    	}
    	
    		heap.consolidate();
    		System.out.println(heap.toGraphViz());
    		ArrayList<String> dot = new ArrayList<String>();
    		while(!heap.isEmpty()){
    			heap.removeMin();
    			dot.add(heap.toGraphViz());
    		}
    	
    	
    	//System.out.println(heap.toGraphViz());
    	
    }
    
    /**
     * Returns the heap as a String that can be passed to GraphViz.
     * @return Generated string representation of the heap.
     */
    public String toGraphViz(){
    	if (minNode == null) {
            return "FibonacciHeap=[]";
        }

        // create a new stack and put root on it
        Stack<FibonacciHeapNode<C>> stack = new Stack<FibonacciHeapNode<C>>();
        stack.push(minNode);
        ArrayList<FibonacciHeapNode<C>> list = new ArrayList<FibonacciHeapNode<C>>();
        ArrayList<FibonacciHeapNode<C>> rank = new ArrayList<FibonacciHeapNode<C>>();
        
        StringBuffer buf = new StringBuffer(512);
        buf.append("digraph G{\n");
        buf.append("ranksep=1.5;\n");

        // do a simple breadth-first traversal on the tree
        while (!stack.empty()) {
            FibonacciHeapNode<C> curr = stack.pop();
            if(!list.contains(curr)){
            	list.add(curr);
            	
            	FibonacciHeapNode<C> tempRankCurr = curr;
            	buf.append("{ rank = same; ");
            	while(!rank.contains(tempRankCurr)){
            		rank.add(tempRankCurr);
            		buf.append(tempRankCurr.getPK() + "; ");
            		tempRankCurr = tempRankCurr.getRight();
            		
            	}
            	buf.append("}\n");
            	buf.append(curr.toGraphViz() + "\n");
                
            	if(curr.getParent() != null && curr.getParent() != curr && !list.contains(curr.getParent())){
                	buf.append(curr.getPK() + " -> " + curr.getParent().getPK() + ";\n");
                	buf.append(curr.getParent().getPK() + " -> " + curr.getPK() + ";\n");
                }
                if(curr.getRight() != null && curr.getRight() != curr && !list.contains(curr.getRight())){
                	buf.append(curr.getPK() + " -> " + curr.getRight().getPK() + ";\n");
                	buf.append(curr.getRight().getPK() + " -> " + curr.getPK() + ";\n");
                }
                if(curr.getLeft() != null && curr.getLeft() != curr && curr.getLeft() != curr.getRight() && !list.contains(curr.getLeft())){
                	buf.append(curr.getPK() + " -> " + curr.getLeft().getPK() + ";\n");
                	buf.append(curr.getLeft().getPK() + " -> " + curr.getPK() + ";\n");
                }
                if(curr.getChild() != null && curr.getChild() != curr && !list.contains(curr.getChild())){
                	buf.append(curr.getPK() + " -> " + curr.getChild().getPK() + ";\n");
                	buf.append(curr.getChild().getPK() + " -> " + curr.getPK() + ";\n");
                }
            }
            
            if (curr.getChild() != null) {
                stack.push(curr.getChild());
            }

            FibonacciHeapNode<C> start = curr;
            curr = curr.getRight();

            while (curr != start) {
            	if(!list.contains(curr)){
            		list.add(curr);
                	buf.append(curr.toGraphViz() + "\n");
                    
                    if(curr.getParent() != null && curr.getParent() != curr && !list.contains(curr.getParent())){
                    	buf.append(curr.getPK() + " -> " + curr.getParent().getPK() + ";\n");
                    	buf.append(curr.getParent().getPK() + " -> " + curr.getPK() + ";\n");
                    }
                    if(curr.getRight() != null && curr.getRight() != curr && !list.contains(curr.getRight())){
                    	buf.append(curr.getPK() + " -> " + curr.getRight().getPK() + ";\n");
                    	buf.append(curr.getRight().getPK() + " -> " + curr.getPK() + ";\n");
                    }
                    if(curr.getLeft() != null && curr.getLeft() != curr && curr.getLeft() != curr.getRight() && !list.contains(curr.getLeft())){
                    	buf.append(curr.getPK() + " -> " + curr.getLeft().getPK() + ";\n");
                    	buf.append(curr.getLeft().getPK() + " -> " + curr.getPK() + ";\n");
                    }
                    if(curr.getChild() != null && curr.getChild() != curr && !list.contains(curr.getChild())){
                    	buf.append(curr.getPK() + " -> " + curr.getChild().getPK() + ";\n");
                    	buf.append(curr.getChild().getPK() + " -> " + curr.getPK() + ";\n");
                    }
            	}

                if (curr.getChild() != null) {
                    stack.push(curr.getChild());
                }

                curr = curr.getRight();
            }
        }

        buf.append('}');

        return buf.toString();
    }
}

// FibonacciHeap
