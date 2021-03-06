package vega.dataStructures.trees;

import interfaces.graph.edge.UndirectedEdge;

import java.util.Iterator;

import interfaces.graph.vertex.tree.BinaryTreeNode;
import interfaces.graph.vertex.tree.RedBlackTreeNode;
import vega.exceptions.InvalidTreeException;
import vega.graph.AbstractTree;

/**
 *  * Binary search tree class.  Moved the InvaliTreeException subclass to it's own class in the
 * exception package.
 * @author Tom O'Connell
 * @author Weston Jossey-  April 21, 2008
 *
 * @param <C>
 * @param <T>
 */
public abstract class AbstractBinarySearchTree<C extends Comparable<C>, T extends BinaryTreeNode<C,T>> extends AbstractTree<C,T, UndirectedEdge> implements interfaces.dataStructures.tree.BinarySearchTree<C>{

    private class BSTIterator implements Iterator<C> {

        private T curr;
        private T prev;

        public BSTIterator() {
            curr = minimum(root);
            prev = null;
        }


        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return curr != null;
        }


        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#next()
         */
        public C next() {
            C result = curr.getData();
            prev = curr;
            curr = successor(curr);
            return result;
        }


        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#remove()
         */
        public void remove() {
            delete(prev);
        }
    } // end inner class BSTIterator


    /**
     * Returns an iterator for the comparables of the binary search tree.
     * @return
     */
    public Iterator<C> getIterator() {
        return new BSTIterator();
    } // end getIterator()

    protected T root;
    protected int numElements;

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Method to implement.  Must specify a type of treeNode to instantiate when given
     * a generic comparable.  
     */
	public abstract void insert(C arg);
	
	/**
	 * Insert the new node into the tree.
	 * @param newNode Node to insert.
	 */
	protected void insertNode(T newNode) {
        C k = newNode.getData();
        T prev = null;
        T curr = root;
        while (curr != null) {
            prev = curr;
            if (k.compareTo(curr.getData()) < 0) {
                curr = curr.getLeftChild();
            } else {
                curr = curr.getRightChild();
            } // end if

        } // end while

        newNode.setParentNode(prev);
        if (prev == null) {
            root = newNode;
        } else {
            if (k.compareTo(prev.getData()) < 0) {
                prev.setLeftChild(newNode);
            } else {
                prev.setRightChild(newNode);
            } // end if

        } // end if

        numElements++;
    } // end insertNode();


	/**
	 * Returns a string that represents an in order walk of the tree.
	 * @param curr Root sub-tree node.
	 * @return String representation of the tree.
	 */
    public String inOrderWalk(T curr) {
        String result = "";
        if (curr != null) {
        	T leftNode = curr.getLeftChild();
        	T rightNode = curr.getRightChild();
            result = inOrderWalk(leftNode);
            result += " " + curr.getData();
            result += inOrderWalk(rightNode);
        }
        return result;
    }

    /**
     * Return the minimum node of the binary search tree.
     * @param subtree Root vertex of the subtree.
     * @return Minimum node of the subtree.
     */
	private T minimum(T subtree) {
		T minimum = null;
        while (subtree != null) {
            minimum = subtree;
            subtree = subtree.getLeftChild();
        }
        return minimum;
    }

    /**
     * Returns the successor node of a subtree
     * @param subtree Subtree to get the successor from.
     * @return Successor of the subtree.
     */
	private T successor(T subtree) {
        if (subtree.getRightChild() != null) {
        	T rightNode = subtree.getRightChild();
            return minimum(rightNode);
        }
        T prev = subtree.getParentNode();
        while (prev != null && subtree == prev.getRightChild()) {
            subtree = prev;
            prev = subtree.getParentNode();
        } // end while

        return prev;
    }

	/**
	 * Search the tree for node T.
	 * @param subtree Root of subtree to search.
	 * @param key Key to find.
	 * @return Tree node of found item or null.
	 */
	protected T search(T subtree, C key) {
        if (subtree == null || key.compareTo(subtree.getData()) == 0) {
            return subtree;
        }
        if (key.compareTo(subtree.getData()) < 0) {
        	T leftNode = subtree.getLeftChild();
            return search(leftNode, key);
        } else {
        	T rightNode = subtree.getRightChild();
            return search(rightNode, key);
        } // end if

    } // end search()

	/**
	 * Search the whole tree for some comparable element.
	 * @return Returns the comparable or else null.
	 */
	public C search(C arg) {
        C key = arg;
        T node = search(root, key);
        if (node != null) {
            return node.getData();
        } else {
            return null;
        }
    } // end search()


	public C searchLoop(C key) {
		T subtree = root;
        boolean found = false;
        while (subtree != null && !found) {
            if (key.compareTo(subtree.getData()) == 0) {
                found = true;
            } else if (key.compareTo(subtree.getData()) < 0) {
                subtree = subtree.getLeftChild();
            } else {
                subtree = subtree.getRightChild();
            }
        } // end while

        if (subtree != null) {
            return subtree.getData();
        } else {
            return null;
        }
    } // end search()


    /**
     * 
     * @param key
     * @return
     */
	public C successor(C key) {
		T node = search(root, key);
        if (node != null) {
        	T succ = successor(node);
            if (succ != null) {
                return succ.getData();
            } else {
                return null;
            }
        } else {
            return null;
        }
    } // end search()


    /**
     * Deletes a value from the tree without having to have the specific value.
     * @param arg  Object to delete
     * @return Returns true if found and deleted, false if it fails.
     */
	public boolean delete(C arg) {
        boolean result = false;
        C key = arg;
        T node = search(root, key);
        if (node != null) {
            delete(node);
            numElements--;
            result = true;
        }
        return result;
    }

    /**
     * Deletes the data associated with the node specified
     * <p>We have three cases:
     * <ul>1. Node has no children (remove it)</ul>
     * <ul>2. Node has 1 child (splice it out)</ul>
     * <ul>3. Node has two childre (splice out its successor which has only 1 child)</ul>
     * </p>
     * @param node the node whose data should be deleted from the tree
     * @return returns the parent of the actual node that was 
     * spliced out of the tree. 
     */
	private T delete(T node) {
        /*
         * 3 cases
         * 
         * 1) node has no children (remove it) 2) node has 1 child (splice it
         * out) 3) node has 2 children (splice out its successor which has only
         * 1 child)
         *  
         */
		T nodeToDelete;

        if (node.getLeftChild() == null || node.getRightChild() == null) {
            nodeToDelete = node;
        } else {
            nodeToDelete = successor(node);
            node.setData(nodeToDelete.getData());
        } // end if

        T nodeToUpdate;

        if (nodeToDelete.getLeftChild() != null) {
            nodeToUpdate = nodeToDelete.getLeftChild();
        } else {
            nodeToUpdate = nodeToDelete.getRightChild();
        } // end if

        if (nodeToUpdate != null) {
            nodeToUpdate.setParentNode(nodeToDelete.getParentNode());
        } // end if 

        T par = null;
        if (nodeToDelete.getParentNode() == null) {
            root = nodeToUpdate;
        } else {

            par = nodeToDelete.getParentNode();
            if (nodeToDelete == par.getLeftChild()) {
                par.setLeftChild(nodeToUpdate);
            } else {
                par.setRightChild(nodeToUpdate);
            }
        } // end if 

        return par;
    } // end delete(TreeNode)


	protected T getNodeToDelete(T node) {
        /*
         * 3 cases
         * 
         * 1) node has no children (remove it) 2) node has 1 child (splice it
         * out) 3) node has 2 children (splice out its successor which has only
         * 1 child)
         *  
         */
		T nodeToDelete;

        //Debug.println("getNodeToDelete(): node passed in is " + node);
        //Debug.println("getNodeToDelete(): node's parent is " + node.getParentNode());
        if (node.getLeftChild() == null || node.getRightChild() == null) {

            nodeToDelete = node;
            //Debug.println("getNodeToDelete(): left and right are null");
        } else {
            nodeToDelete = successor(node);
            //Debug.println("getNodeToDelete(): successor is " + node);
            node.setData(nodeToDelete.getData());
        } // end if

        return nodeToDelete;
    } // end getNodeToDelete();


	protected T spliceOutNode(T nodeToDelete) {
		T nodeToUpdate;

        if (nodeToDelete.getLeftChild() != null) {
            nodeToUpdate = nodeToDelete.getLeftChild();
        } else {
            nodeToUpdate = nodeToDelete.getRightChild();
        } // end if

        if (nodeToUpdate != null) {
            nodeToUpdate.setParentNode(nodeToDelete.getParentNode());
        } // end if 

        T par = null;
        if (nodeToDelete.getParentNode() == null) {
            root = nodeToUpdate;
        } else {

            par = nodeToDelete.getParentNode();
            if (nodeToDelete == par.getLeftChild()) {
                par.setLeftChild(nodeToUpdate);
            } else {
                par.setRightChild(nodeToUpdate);
            }
        } // end if 

        return par;
    } // end delete(TreeNode)


    /**
     * Left, root, right
     * @param curr Root of the tree (or sub tree) to walk through.
     * @return Returns graphviz formatted node information.
     */
    public String preOrderGraphViz(T curr) {
        String result = "";
        if (curr != null) {

            //Try to go to the left
            if (curr.getLeftChild() != null) {
            	T leftNode = curr.getLeftChild();
                result += preOrderGraphViz(leftNode);
            } else {
                //No node to the left

            } // end if

            //Try to go to the right
            if (curr.getRightChild() != null) {
            	T rightNode = curr.getRightChild();
                result += preOrderGraphViz(rightNode);
            } else {
                //No node to the right
            	
            } // end if

        } // end if

        return result;
    } // end preOrderGraphViz()


    /**
     * Prints a post order graphViz representation of the tree.
     * @param curr Subtree root node.
     * @return String representation of the tree.
     */
    public String postOrderGraphViz(T curr) {
        String result = "";

        if (curr != null) {

            /*
             * First output the color and name of the current node to make
             * sure the color is set correctly in graphViz.
             */

            /*
             * Now check the left, output the whole left subtree before 
             * outputting the edge between the current node and its left child 
             */
            if (curr.getLeftChild() != null) {
            	T leftNode = curr.getLeftChild();
                result += postOrderGraphViz(leftNode);

            } else {
                
            } // end if

            /*
             * Now output the right subtree
             */
            if (curr.getRightChild() != null) {
            	T rightNode = curr.getRightChild();
                result += postOrderGraphViz(rightNode);

            } else {
            	
            } // end if

        } // end if

        return result;
    } // end preOrderGraphViz()

    /**
     * create a graphViz() representation of the tree
     * 
     * @return a String representing the graphViz tree.
     */
    public String toGraphviz(String title) {
        String result = "graph " + title + "  {\n";
        BinaryTreeNode<C,T> root = this.getRoot();
        result += printNodes(root);
        result += "}";
        return result;
    } // end toGraphViz()
    
    private static int NILcounter = 0;
    private static String NIL = "NIL";
    @SuppressWarnings("unchecked")
	private String printNodes(BinaryTreeNode<C,T> root){
    	String returnString = "";
    	
    	if(root.getLeftChild() != null){
    		returnString += printNodes(root.getLeftChild());
    		returnString += root.getUID() + " -- " + root.getLeftChild().getUID() + ";\n";
    	}else{
    		returnString += root.getUID() + " -- " + NIL + NILcounter + ";\n";
    		returnString += NIL + NILcounter++ + "[label=\"NIL\"";
    		if(root instanceof RedBlackTreeNode){
    			returnString += ", color=black, style=filled, fontcolor=white";
    		}
    		returnString += "];\n";
    	}
    	
    	if(root.getRightChild() != null){
    		returnString += printNodes(root.getRightChild());
    		returnString += root.getUID() + " -- " + root.getRightChild().getUID() + ";\n";
    	}else{
    		returnString += root.getUID() + " -- " + NIL + NILcounter + ";\n";
    		returnString += NIL + NILcounter++ + "[label=\"NIL\"";
    		if(root instanceof RedBlackTreeNode){
    			returnString += ", color=black, style=filled, fontcolor=white";
    		}
    		returnString += "];\n";
    	}
    	
    	returnString += root.getUID() + " [label=\"" + root.getData().toString() + "\"";
    	
    	if(root instanceof RedBlackTreeNode){
            RedBlackTreeNode<C> tempRoot;
            tempRoot = (RedBlackTreeNode<C>) root;
            if(tempRoot.getColor() == RedBlackTreeNode.BLACK){
    			//BLACK
    			returnString += ", color=black, style=filled, fontcolor=white";
    		}else{
    			//RED
    			returnString += ", color=red, style=filled, fontcolor=white";
    		}
    	}
    	returnString += "];\n";
    	
    	return returnString;
    }


    public String toString() {
        String result = inOrderWalk(root);
        return result;
    }

    /**
     * Returns the number of elements in the tree.
     * @return Returns the numElements.
     */
    public int getNumElements() {
        return numElements;
    }


    /*
     * (non-Javadoc)
     * 
     * @see HashTables.Dictionary#getNumEntries()
     */
    public int getNumEntries() {
        // TODO Auto-generated method stub
        return numElements;
    }

    /**
     * Return the root vertex of the tree.
     * @return Root node.
     */
	public T getRoot(){
        return root;
    }
    
	/**
	 * Set the root vertex of the tree.
	 * @return Root node.
	 */
    public void setRoot(T root){
        this.root = root;
    }

    /**
     * Generic rotate takes the parent and child and rotates.
     * @param parent Parent node to rotate.
     * @param child Child node to rotate.
     */
    protected void rotate(T parent, T child) {
    	T transferredChild = null;
    	T grandParent = parent.getParentNode();
        boolean parentWasLeftChild = parent.isLeftChild();

        //Debug.println("rotate(): called with parent = " + parent + " child = " + child);
        if (child.isLeftChild()) {
            //Debug.println("rotate(): child is a left child");
            transferredChild = child.getRightChild();
            //Debug.println("rotate(): transferred child is " + transferredChild);
            parent.setLeftChild(transferredChild);
            child.setRightChild(parent);
        } else {
            //Debug.println("rotate(): child is a right child");
            transferredChild = child.getLeftChild();
            parent.setRightChild(transferredChild);
            //Debug.println("rotate(): transferred child is " + transferredChild);
            child.setLeftChild(parent);
        } // end if 

        /*
         * Update the parent pointers and root if necessary
         */
        child.setParentNode(parent.getParentNode());
        parent.setParentNode(child);
        if (transferredChild != null) {
            transferredChild.setParentNode(parent);
            //Debug.println("rotate(): transferredchild " + transferredChild + "'s new parent is " + transferredChild.getParentNode());
        } // end if 

        //Debug.println("rotate(): child " + child + "'s new parent is " + child.getParentNode());
        //Debug.println("rotate(): parent " + parent + "'s new parent is " + parent.getParentNode());

        if (grandParent == null) {
            root = child;
        } else {
            if (parentWasLeftChild) {
                grandParent.setLeftChild(child);
            } else {
                grandParent.setRightChild(child);
            } // end if 

        } // end if 


    /*
     * update the heights -- must be in this order
     */
//		updateHeight(transferredChild);
//		updateHeight(child);
//		updateHeight(parent);


    } // end rotate()


    /*
     * CLRS calls this Left-Rotate
     */
    protected void rotateWithRightChild(T problemNode) {
    	T oldRight = problemNode.getRightChild();
    	T newRight = oldRight.getLeftChild();
    	T parentOfProblem = problemNode.getParentNode();


        oldRight.setLeftChild(problemNode);
        oldRight.setParentNode(problemNode.getParentNode());
        problemNode.setRightChild(newRight);
        problemNode.setParentNode(oldRight);
        /*
         * update the heights -- must be in this order
         */
        updateHeight(problemNode);
        updateHeight(oldRight);

        if (parentOfProblem == null) {
            root = oldRight;
            //Debug.println("rotateWithRight(): root set to " + oldRight.graphVizName());
        } else {
            if (parentOfProblem.getLeftChild() == problemNode) {
                parentOfProblem.setLeftChild(oldRight);
            } else {
                parentOfProblem.setRightChild(oldRight);
            } // end if 

            updateHeight(parentOfProblem);

        } // end if 

    } // end rotateWithRight()


    /**
     * CLRS calls this method "RightRotate". *Note from Tom*
     * @param problemNode
     */
    protected void rotateWithLeftChild(T problemNode) {
    	T oldLeft = problemNode.getLeftChild();
    	T newLeft = oldLeft.getRightChild();
    	T parentOfProblem = problemNode.getParentNode();


        oldLeft.setRightChild(problemNode);
        oldLeft.setParentNode(problemNode.getParentNode());
        problemNode.setLeftChild(newLeft);
        problemNode.setParentNode(oldLeft);
        /*
         * update the heights -- must be in this order
         */
        updateHeight(problemNode);
        updateHeight(oldLeft);

        if (parentOfProblem == null) {
            root = oldLeft;
        } else {
            if (parentOfProblem.getLeftChild() == problemNode) {
                parentOfProblem.setLeftChild(oldLeft);
            } else {
                parentOfProblem.setRightChild(oldLeft);
            } // end if 

            updateHeight(parentOfProblem);

        } // end if 

    } // end rotateWithLeft()


    /**
     * Updates the height of a node
     * @param parent Node to update height on
     * @return Returns true if balanced
     */
    protected boolean updateHeight(T parent) {
        int heightLeft = -1;
        int heightRight = -1;
        boolean balanced = false;

        if (parent.getLeftChild() != null) {
            heightLeft = parent.getLeftChild().getHeight();
        }

        if (parent.getRightChild() != null) {
            heightRight = parent.getRightChild().getHeight();
        } // end if

        /* Update the height of the node*/
        parent.setHeight(Math.max(heightLeft, heightRight) + 1);


        /* Determine if this node is balanced.*/
        if (Math.abs(heightLeft - heightRight) <= 1) {
            balanced = true;
        } // end if

        return balanced;
    } // end updateHeight()


    /**
     * Validates tree from the root nood as to whether or not it is a valid binary search
     * tree.
     * @return
     * @throws RedBlackTree.BinarySearchTree.InvalidTreeException
     */
    public boolean validateTree() throws InvalidTreeException {
        boolean result = true;
        T tempRoot = root;
        result = validateTree(tempRoot);
        return result;
    } // end validateTree()


    /**
     * Validates to make sure the tree (or sub tree) is a valid binary tree
     * @param sub
     * @return Returns true if a valid binary tree, false if invalid.
     * @throws RedBlackTree.BinarySearchTree.InvalidTreeException
     */
    public boolean validateTree(T sub) throws InvalidTreeException {
        boolean valid = true;
        if (sub == null) {
            return true;
        } else {
            if (sub.getLeftChild() != null) {
            	T leftNode = sub.getLeftChild();
                valid = validateTree(leftNode);

                if (sub.getLeftChild().getParentNode() != sub) {
                    valid = false;
                    //Debug.println("validate(): exception on tree " + this.toGraphViz("BadTree"));
                    throw new InvalidTreeException("bad parent pointer at " + sub.getLeftChild());

                } // end if 

            } // end if 

            if (sub.getRightChild() != null) {
            	T rightNode = sub.getRightChild();
                valid = validateTree(rightNode);

                if (sub.getRightChild().getParentNode() != sub) {
                    valid = false;
                    //Debug.println("validate(): exception on tree " + this.toGraphViz("BadTree"));
                    throw new InvalidTreeException("bad parent pointer at " + sub.getRightChild());

                } // end if 

            } // end if 

        } // end if 

        return valid;
    } // end validateTree()
    
    
}
	
	