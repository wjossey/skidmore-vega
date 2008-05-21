package vega.dataStructures.trees;

import interfaces.dataStructures.BinarySearchTree;
import interfaces.graph.vertex.tree.BinaryTreeNode;
import java.util.Iterator;

import vega.graph.TreeImpl;
import vega.graph.vertex.tree.BinaryTreeNodeImpl;

/**
 * Binary search tree class.  Contains the InvaliTreeException subclass.
 * @author Tom O'Connell
 * @author Edit by:  Weston Jossey-  April 21, 2008
 *  
 */
public class BinarySearchTreeImpl extends TreeImpl implements BinarySearchTree<Object>{

    public static boolean RED = true;
    public static boolean BLACK = false;

    public class InvalidTreeException extends Exception {

        /**
		 * 
		 */
		private static final long serialVersionUID = -7533735938974561488L;

		public InvalidTreeException(String s) {
            super(s);
        } // end constructor

    } // end class InvalidTreeException 


    private class BSTIterator implements Iterator<Object> {

        private BinaryTreeNode curr;
        private BinaryTreeNode prev;

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
        public Object next() {
            Object result = curr.getData();
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


    public Iterator<?> getIterator() {
        return new BSTIterator();
    } // end getIterator()

    protected BinaryTreeNode root;
    protected int numElements;

    public BinarySearchTreeImpl() {
        root = null;
        numElements = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(Object arg) {
        Comparable<?> c = (Comparable<?>) arg;
        BinaryTreeNodeImpl newNode = new BinaryTreeNodeImpl(c);
        insertNode(newNode);
    } // end insert();


    @SuppressWarnings("unchecked")
	protected void insertNode(BinaryTreeNode newNode) {
        Comparable k = newNode.getData();
        BinaryTreeNode prev = null;
        BinaryTreeNode curr = root;
        while (curr != null) {
            prev = curr;
            if (k.compareTo(curr.getData()) < 0) {
                curr = curr.getLeftNode();
            } else {
                curr = curr.getRightNode();
            } // end if

        } // end while

        newNode.setParentNode(prev);
        if (prev == null) {
            root = newNode;
        } else {
            if (k.compareTo(prev.getData()) < 0) {
                prev.setLeftNode(newNode);
            } else {
                prev.setRightNode(newNode);
            } // end if

        } // end if

        numElements++;
    } // end insertNode();


    public String inOrderWalk(BinaryTreeNode curr) {
        String result = "";
        if (curr != null) {
            result = inOrderWalk(curr.getLeftNode());
            result += " " + curr.getData() + " color = " + curr.getColor();
            result += inOrderWalk(curr.getRightNode());
        }
        return result;
    }

    private BinaryTreeNode minimum(BinaryTreeNode subtree) {
        BinaryTreeNode minimum = null;
        while (subtree != null) {
            minimum = subtree;
            subtree = subtree.getLeftNode();
        }
        return minimum;
    }

    /**
     * Returns the successor node of a subtree
     * @param subtree
     * @return
     */
    private BinaryTreeNode successor(BinaryTreeNode subtree) {
        if (subtree.getRightNode() != null) {
            return minimum(subtree.getRightNode());
        }
        BinaryTreeNode prev = subtree.getParentNode();
        while (prev != null && subtree == prev.getRightNode()) {
            subtree = prev;
            prev = subtree.getParentNode();
        } // end while

        return prev;
    }

    @SuppressWarnings("unchecked")
	protected BinaryTreeNode search(BinaryTreeNode subtree, Comparable<Comparable> key) {
        if (subtree == null || key.compareTo(subtree.getData()) == 0) {
            return subtree;
        }
        if (key.compareTo(subtree.getData()) < 0) {
            return search(subtree.getLeftNode(), key);
        } else {
            return search(subtree.getRightNode(), key);
        } // end if

    } // end search()


    @SuppressWarnings("unchecked")
	public Object search(Object arg) {
        Comparable key = (Comparable) arg;
        BinaryTreeNode node = search(root, key);
        if (node != null) {
            return node.getData();
        } else {
            return null;
        }
    } // end search()


    @SuppressWarnings("unchecked")
	public Comparable searchLoop(Comparable key) {
        BinaryTreeNode subtree = root;
        boolean found = false;
        while (subtree != null && !found) {
            if (key.compareTo(subtree.getData()) == 0) {
                found = true;
            } else if (key.compareTo(subtree.getData()) < 0) {
                subtree = subtree.getLeftNode();
            } else {
                subtree = subtree.getRightNode();
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
    @SuppressWarnings("unchecked")
	public Comparable successor(Comparable key) {
        BinaryTreeNode node = search(root, key);
        if (node != null) {
            BinaryTreeNode succ = successor(node);
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
    @SuppressWarnings("unchecked")
	public boolean delete(Object arg) {
        boolean result = false;
        Comparable key = (Comparable) arg;
        BinaryTreeNode node = search(root, key);
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
    private BinaryTreeNode delete(BinaryTreeNode node) {
        /*
         * 3 cases
         * 
         * 1) node has no children (remove it) 2) node has 1 child (splice it
         * out) 3) node has 2 children (splice out its successor which has only
         * 1 child)
         *  
         */
        BinaryTreeNode nodeToDelete;

        if (node.getLeftNode() == null || node.getRightNode() == null) {
            nodeToDelete = node;
        } else {
            nodeToDelete = successor(node);
            node.setData(nodeToDelete.getData());
        } // end if

        BinaryTreeNode nodeToUpdate;

        if (nodeToDelete.getLeftNode() != null) {
            nodeToUpdate = nodeToDelete.getLeftNode();
        } else {
            nodeToUpdate = nodeToDelete.getRightNode();
        } // end if

        if (nodeToUpdate != null) {
            nodeToUpdate.setParentNode(nodeToDelete.getParentNode());
        } // end if 

        BinaryTreeNode par = null;
        if (nodeToDelete.getParentNode() == null) {
            root = nodeToUpdate;
        } else {

            par = nodeToDelete.getParentNode();
            if (nodeToDelete == par.getLeftNode()) {
                par.setLeftNode(nodeToUpdate);
            } else {
                par.setRightNode(nodeToUpdate);
            }
        } // end if 

        return par;
    } // end delete(TreeNode)


    protected BinaryTreeNode getNodeToDelete(BinaryTreeNode node) {
        /*
         * 3 cases
         * 
         * 1) node has no children (remove it) 2) node has 1 child (splice it
         * out) 3) node has 2 children (splice out its successor which has only
         * 1 child)
         *  
         */
        BinaryTreeNode nodeToDelete;

        //Debug.println("getNodeToDelete(): node passed in is " + node);
        //Debug.println("getNodeToDelete(): node's parent is " + node.getParentNode());
        if (node.getLeftNode() == null || node.getRightNode() == null) {

            nodeToDelete = node;
            //Debug.println("getNodeToDelete(): left and right are null");
        } else {
            nodeToDelete = successor(node);
            //Debug.println("getNodeToDelete(): successor is " + node);
            node.setData(nodeToDelete.getData());
        } // end if

        return nodeToDelete;
    } // end getNodeToDelete();


    protected BinaryTreeNode spliceOutNode(BinaryTreeNode nodeToDelete) {
        BinaryTreeNode nodeToUpdate;

        if (nodeToDelete.getLeftNode() != null) {
            nodeToUpdate = nodeToDelete.getLeftNode();
        } else {
            nodeToUpdate = nodeToDelete.getRightNode();
        } // end if

        if (nodeToUpdate != null) {
            nodeToUpdate.setParentNode(nodeToDelete.getParentNode());
        } // end if 

        BinaryTreeNode par = null;
        if (nodeToDelete.getParentNode() == null) {
            root = nodeToUpdate;
        } else {

            par = nodeToDelete.getParentNode();
            if (nodeToDelete == par.getLeftNode()) {
                par.setLeftNode(nodeToUpdate);
            } else {
                par.setRightNode(nodeToUpdate);
            }
        } // end if 

        return par;
    } // end delete(TreeNode)


    /**
     * Left, root, right
     * @param curr Root of the tree (or sub tree) to walk through.
     * @return Returns graphviz formatted node information.
     */
    public String preOrderGraphViz(BinaryTreeNode curr) {
        String result = "";
        if (curr != null) {
            result += "node[color = " + (curr.isColor(RED) ? "red" : "black") + "]\n";
            result += curr.graphVizName() + "\n";

            //Try to go to the left
            if (curr.getLeftNode() != null) {
                result += curr.graphVizName() + " -> ";
                result += curr.getLeftNode().graphVizName() + "\n";
                result += preOrderGraphViz(curr.getLeftNode());
            } else {
                //No node to the left
                result += "node[color = black]\n";
                result += curr.graphVizName() + " -> \" null" + numNull + "\"\n";
                numNull++;
            } // end if

            //Try to go to the right
            if (curr.getRightNode() != null) {
                // result += "node[color = " + (curr.isColor(RED)? "red":"black") + "]\n";
                result += curr.graphVizName() + " -> ";
                result += curr.getRightNode().graphVizName() + "\n";
                result += preOrderGraphViz(curr.getRightNode());
            } else {
                //No node to the right
                result += "node[color = black]\n";
                result += curr.graphVizName() + " -> \"null" + numNull + "\"\n";
                numNull++;
            } // end if

        } // end if

        return result;
    } // end preOrderGraphViz()


    //Left, right, root
    public String postOrderGraphViz(BinaryTreeNode curr) {
        String result = "";
        String blackString = "black";

        if (curr != null) {

            /*
             * First output the color and name of the current node to make
             * sure the color is set correctly in graphViz.
             */
            result += "node[style=filled, fontcolor=White, color = " + (curr.isColor(RED) ? "red" : blackString) + "]\n";
            result += curr.graphVizName() + "\n";

            /*
             * Now check the left, outputing the whole left subtree before 
             * outputing the edge between the current node and its left child 
             */
            if (curr.getLeftNode() != null) {
                // result += "node[color = " + (curr.isColor(RED)? "red":"black") + "]\n";
                result += postOrderGraphViz(curr.getLeftNode());

                result += curr.graphVizName() + " -> ";
                result += curr.getLeftNode().graphVizName() + "\n";

            } else {
                result += "node[color = " + blackString + ", style=filled]\n";
                result += curr.graphVizName() + " -> \" null" + numNull + "\"\n";
                numNull++;
            } // end if

            /*
             * Now output the right subtree
             */
            if (curr.getRightNode() != null) {
                // result += "node[color = " + (curr.isColor(RED)? "red":"black") + "]\n";
                result += postOrderGraphViz(curr.getRightNode());
                result += curr.graphVizName() + " -> ";
                result += curr.getRightNode().graphVizName() + "\n";

            } else {
                result += "node[color = " + blackString + ", style=filled]\n";
                result += curr.graphVizName() + " -> \"null" + numNull + "\"\n";
                numNull++;
            } // end if

        } // end if

        return result;
    } // end preOrderGraphViz()

    private static int numNull = 0;

    /**
     * create a graphViz() representation of the tree
     * 
     * @return a String representing the graphViz tree.
     */
    public String toGraphViz(String title) {
        String result = "digraph " + title + "  {\n";
        numNull = 0;
        result += postOrderGraphViz(root);
        result += "}";
        return result;
    } // end toGraphViz()


    public String toString() {
        String result = inOrderWalk(root);
        return result;
    }

    /**
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
    
    @Override
    public BinaryTreeNode getRoot(){
        return root;
    }
    
    public void setRoot(BinaryTreeNode root){
        this.root = root;
    }

    /**
     * Generic rotate takes the parent and child
     * @param 
     */
    protected void rotate(BinaryTreeNode parent, BinaryTreeNode child) {
        BinaryTreeNode transferredChild = null;
        BinaryTreeNode grandParent = parent.getParentNode();
        boolean parentWasLeftChild = parent.isLeftChild();

        //Debug.println("rotate(): called with parent = " + parent + " child = " + child);
        if (child.isLeftChild()) {
            //Debug.println("rotate(): child is a left child");
            transferredChild = child.getRightNode();
            //Debug.println("rotate(): transferred child is " + transferredChild);
            parent.setLeftNode(transferredChild);
            child.setRightNode(parent);
        } else {
            //Debug.println("rotate(): child is a right child");
            transferredChild = child.getLeftNode();
            parent.setRightNode(transferredChild);
            //Debug.println("rotate(): transferred child is " + transferredChild);
            child.setLeftNode(parent);
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
                grandParent.setLeftNode(child);
            } else {
                grandParent.setRightNode(child);
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
    protected void rotateWithRightChild(BinaryTreeNode problemNode) {
        BinaryTreeNode oldRight = problemNode.getRightNode();
        BinaryTreeNode newRight = oldRight.getLeftNode();
        BinaryTreeNode parentOfProblem = problemNode.getParentNode();


        oldRight.setLeftNode(problemNode);
        oldRight.setParentNode(problemNode.getParentNode());
        problemNode.setRightNode(newRight);
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
            if (parentOfProblem.getLeftNode() == problemNode) {
                parentOfProblem.setLeftNode(oldRight);
            } else {
                parentOfProblem.setRightNode(oldRight);
            } // end if 

            updateHeight(parentOfProblem);

        } // end if 

    } // end rotateWithRight()


    /**
     * CLRS calls this method "RightRotate". *Note from Tom*
     * @param problemNode
     */
    protected void rotateWithLeftChild(BinaryTreeNode problemNode) {
        BinaryTreeNode oldLeft = problemNode.getLeftNode();
        BinaryTreeNode newLeft = oldLeft.getRightNode();
        BinaryTreeNode parentOfProblem = problemNode.getParentNode();


        oldLeft.setRightNode(problemNode);
        oldLeft.setParentNode(problemNode.getParentNode());
        problemNode.setLeftNode(newLeft);
        problemNode.setParentNode(oldLeft);
        /*
         * update the heights -- must be in this order
         */
        updateHeight(problemNode);
        updateHeight(oldLeft);

        if (parentOfProblem == null) {
            root = oldLeft;
            System.out.println("rotateWithLeft(): root set to " + oldLeft.graphVizName());
        } else {
            if (parentOfProblem.getLeftNode() == problemNode) {
                parentOfProblem.setLeftNode(oldLeft);
            } else {
                parentOfProblem.setRightNode(oldLeft);
            } // end if 

            updateHeight(parentOfProblem);

        } // end if 

    } // end rotateWithLeft()


    /**
     * Updates the height of a node
     * @param parent Node to update height on
     * @return Returns true if balanced
     */
    protected boolean updateHeight(BinaryTreeNode parent) {
        int heightLeft = -1;
        int heightRight = -1;
        boolean balanced = false;

        if (parent.getLeftNode() != null) {
            heightLeft = parent.getLeftNode().getHeight();
        }

        if (parent.getRightNode() != null) {
            heightRight = parent.getRightNode().getHeight();
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
        result = validateTree(root);
        return result;
    } // end validateTree()


    /**
     * Validates to make sure the tree (or sub tree) is a valid binary tree
     * @param sub
     * @return Returns true if a valid binary tree, false if invalid.
     * @throws RedBlackTree.BinarySearchTree.InvalidTreeException
     */
    public boolean validateTree(BinaryTreeNode sub) throws InvalidTreeException {
        boolean valid = true;
        if (sub == null) {
            return true;
        } else {
            if (sub.getLeftNode() != null) {
                valid = validateTree(sub.getLeftNode());

                if (sub.getLeftNode().getParentNode() != sub) {
                    valid = false;
                    //Debug.println("validate(): exception on tree " + this.toGraphViz("BadTree"));
                    throw new InvalidTreeException("bad parent pointer at " + sub.getLeftNode());

                } // end if 

            } // end if 

            if (sub.getRightNode() != null) {
                valid = validateTree(sub.getRightNode());

                if (sub.getRightNode().getParentNode() != sub) {
                    valid = false;
                    //Debug.println("validate(): exception on tree " + this.toGraphViz("BadTree"));
                    throw new InvalidTreeException("bad parent pointer at " + sub.getRightNode());

                } // end if 

            } // end if 

        } // end if 

        return valid;
    } // end validateTree()

}
	
	