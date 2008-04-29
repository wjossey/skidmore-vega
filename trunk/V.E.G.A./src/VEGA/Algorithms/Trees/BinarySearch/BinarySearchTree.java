package VEGA.Algorithms.Trees.BinarySearch;

import VEGA.Algorithms.GraphAlgorithm;
import VEGA.Controller;
import VEGA.Graph.Graph;
import VEGA.Graph.Tree.Tree;
import VEGA.Graph.Vertex.Tree.*;

/*
 * Created on Feb 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import VEGA.PseudoCode.PseudoCode;
/**
 * @author oconnell
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BinarySearchTree extends Tree implements GraphAlgorithm {

    public int bstSize = 0;
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    BinaryTreeNode root;
    public static int time = 0;
    private static int counter = 0;
    private Controller c;
    private PseudoCode pseudoCode;

    /**
     * 
     * @param treeNodes
     */
    public BinarySearchTree(BinaryTreeNode[] treeNodes) {
        super(treeNodes);
        root = null;
    }

    /**
     * 
     */
    public BinarySearchTree() {
        super();
        root = null;
    }

    /**
     * 
     * @return
     */
    @Override
    public BinaryTreeNode getRoot() {
        return (BinaryTreeNode) super.getRoot();
    }

    /**
     * 
     * @param key
     * @param t
     */
    public void add(Comparable key, BinaryTreeNode t) {

        BinaryTreeNode newNode = new BinaryTreeNode(key);
        if (t == null) {
            root = newNode;
        } else {
            if (newNode.getData().compareTo(t.getData()) > 0) {
                if (t.getRightChild() == null) {
                    t.setRightChild(newNode);
                    newNode.setParent(t);
                } else {
                    add(key, t.getRightChild());
                }
            } else if (t.getLeftChild() == null) {
                t.setLeftChild(newNode);
                newNode.setParent(t);
            } else {
                add(key, t.getLeftChild());
            }
        }
    }

    /**
     * 
     * @param key
     */
    public void add(Comparable key) {
        add(key, root);
        bstSize++;

    }

    /**
     * 
     * @param key
     * @return
     */
    public Comparable search(Comparable key) {
        if (!searchBoolean(key, root)) {
            return null;
        }
        return (search(key, root)).getData();

    }

    /**
     * 
     * @param key
     * @param t
     * @return
     */
    private boolean searchBoolean(Comparable key, BinaryTreeNode t) {
        boolean found = false;

        if (t == null) {
            found = false;
        } else if (key.compareTo(t.getData()) == 0) {
            found = true;
        } else if (key.compareTo(t.getData()) > 0) {
            found = searchBoolean(key, t.getRightChild());
        } else {
            found = searchBoolean(key, t.getLeftChild());
        }
        return found;
    }

    /**
     * 
     * @param key
     * @param t
     * @return
     */
    private BinaryTreeNode search(Comparable key, BinaryTreeNode t) {

        BinaryTreeNode returnNode = null;

        if (root == null) {
            return null;
        }
        if (key.compareTo(t.getData()) == 0) {
            returnNode = t;
        } else if (key.compareTo(t.getData()) > 0) {
            returnNode = search(key, t.getRightChild());
        } else {
            returnNode = search(key, t.getLeftChild());
        }
        return returnNode;

    }

    /**
     * 
     * @return
     */
    public Comparable min() {
        Comparable result = min(root);
        return result;
    }

    /**
     * 
     * @param t
     * @return
     */
    private Comparable min(BinaryTreeNode t) {
        Comparable result = null;
        if (t != null) {
            if (t.getLeftChild() != null) {
                result = min(t.getLeftChild());
            }
        }
        return result;
    }

    /**
     * 
     * @param t
     * @return
     */
    private BinaryTreeNode minNode(BinaryTreeNode t) {
        BinaryTreeNode returnNode = null;
        if (t != null) {
            if (t.getLeftChild() != null) {
                returnNode = (BinaryTreeNode) minNode(t.getLeftChild());
            }
        }
        return returnNode;
    }

    /**
     * 
     * @param key
     */
    public void delete(Comparable key) {
        bstSize--;
        BinaryTreeNode deletedNode, successor;
        deletedNode = search(key, root);
        if (deletedNode.getLeftChild() != null && deletedNode.getRightChild() != null) {
            successor = successor(deletedNode);
            if (successor.getRightChild() == null) {
                if (successor.getParent().getRightChild() == successor) {
                    successor.getParent().setRightChild(null);
                } else {
                    successor.getParent().setLeftChild(null);
                }
                deletedNode.setData(successor.getData());

            } else {

                if (successor.getParent().getRightChild() == successor) {
                    successor.getParent().setRightChild(successor.getRightChild());
                } else {
                    successor.getParent().setLeftChild(null);
                }
                deletedNode.setData(successor.getData());

            }


        } else if (deletedNode.getLeftChild() != null || deletedNode.getRightChild() != null) {
            if (deletedNode.getParent().getLeftChild() == deletedNode) {
                if (deletedNode.getLeftChild() != null) {
                    deletedNode.getParent().setLeftChild(deletedNode.getLeftChild());
                    deletedNode.getLeftChild().setParent(deletedNode.getParent());
                } else {
                    deletedNode.getParent().setLeftChild(deletedNode.getRightChild());
                    deletedNode.getRightChild().setParent(deletedNode.getParent());
                }

            } else {
                if (deletedNode.getLeftChild() != null) {
                    deletedNode.getParent().setRightChild(deletedNode.getLeftChild());
                    deletedNode.getLeftChild().setParent(deletedNode.getParent());
                } else {
                    deletedNode.getParent().setRightChild(deletedNode.getRightChild());
                    deletedNode.getRightChild().setParent(deletedNode.getParent());
                }

            }

        } else {
            if (deletedNode.getParent().getRightChild() == deletedNode) {
                deletedNode.getParent().setRightChild(null);
            } else {
                deletedNode.getParent().setLeftChild(null);
            }
        }
    }

    /**
     * 
     * @param t
     * @return
     */
    private BinaryTreeNode successor(BinaryTreeNode t) {
        return minNode(t.getRightChild());
    }

    /**
     * 
     * @return
     */
    public Object[] treeToHeap() {
        counter = 0;
        Object[] heap = new Object[bstSize];
        inOrderWalkVertex(root, heap);
        return heap;
    }

    /**
     * 
     * @param t
     * @param v
     */
    private void inOrderWalkVertex(BinaryTreeNode t, Object[] v) {
        if (t != null) {
            inOrderWalkVertex(t.getLeftChild(), v);
            v[counter] = t.getData();
            counter++;
            inOrderWalkVertex(t.getRightChild(), v);

        }
    }

    /**
     * 
     * @param t
     * @return
     */
    private String inOrderWalk(BinaryTreeNode t) {
        String result = "";
        if (t != null) {
            result = inOrderWalk(t.getLeftChild());
            result += t.getData() + " ";
            result += inOrderWalk(t.getRightChild());
        }
        return result;
    } // end inOrderWalk()


    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        String result = inOrderWalk(root);
        return result;
    }

    public void run(BinarySearchTree t) {
        c = new Controller(t, pseudoCode, t);
    }

    public String getFileName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Controller getController() {
        return c;
    }

    public int getInstanceID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isRunning() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void run(Graph g) {
        run((BinarySearchTree) g);
    }
}
