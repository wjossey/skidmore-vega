package VEGA.Algorithms.Trees.RedBlack;

// RedBlackTree class

import VEGA.Controller;
import VEGA.Exceptions.DuplicateItemException;
import VEGA.PseudoCode.Procedure;
import VEGA.PseudoCode.PseudoCode;
import VEGA.Algorithms.GraphAlgorithm;
import VEGA.Graph.Graph;
import VEGA.Graph.Vertex.Vertex;

//
// CONSTRUCTION: with a negative infinity sentinel
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print all items
// ******************ERRORS********************************
// Exceptions are thrown by insert if warranted and remove.
/**
 * Implements a red-black tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class RedBlackTree implements GraphAlgorithm {

    /**
     * Construct the tree.
     */
    private Controller controller;
    private String FILENAME = "nearestNeighbor";
    private PseudoCode pseudoCode;
    private Procedure insert;
    private static int instanceCounter = 0;
    private int instanceID = 0;
    private boolean running = false;

    public RedBlackTree() {
        pseudoCode = new PseudoCode("Red Black Tree");

        insert = new Procedure("Insert(Vertex n):");
        insert.appendLine("");

        header = new RedBlackNode(null);
        header.left = header.right = nullNode;
    }

    /**
     * Compare item and t.element, using compareTo, with
     * caveat that if t is header, then item is always larger.
     * This routine is called if is possible that t is header.
     * If it is not possible for t to be header, use compareTo directly.
     */
    private final int compare(Comparable item, RedBlackNode t) {
        if (t == header) {
            return 1;
        } else {
            return item.compareTo(t.element);
        }
    }

    /**
     * Insert into the tree.
     * @param item the item to insert.
     * @throws DuplicateItemException if item is already present.
     */
    public void insert(Comparable item) {
        current = parent = grand = header;
        nullNode.element = item;

        while (compare(item, current) != 0) {
            great = grand;
            grand = parent;
            parent = current;
            current = compare(item, current) < 0 ? current.left : current.right;

            // Check if two red children; fix if so
            if (current.left.color == RED && current.right.color == RED) {
                handleReorient(item);
            }
        }

        // Insertion fails if already present
        if (current != nullNode) {
            throw new DuplicateItemException(item.toString());
        }
        current = new RedBlackNode(item, nullNode, nullNode);

        // Attach to parent
        if (compare(item, parent) < 0) {
            parent.left = current;
        } else {
            parent.right = current;
        }
        handleReorient(item);
    }

    /**
     * Remove from the tree.
     * @param x the item to remove.
     * @throws UnsupportedOperationException if called.
     */
    public void remove(Comparable x) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find the smallest item  the tree.
     * @return the smallest item or null if empty.
     */
    public Comparable findMin() {
        if (isEmpty()) {
            return null;
        }
        RedBlackNode itr = header.right;

        while (itr.left != nullNode) {
            itr = itr.left;
        }
        return itr.element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item or null if empty.
     */
    public Comparable findMax() {
        if (isEmpty()) {
            return null;
        }
        RedBlackNode itr = header.right;

        while (itr.right != nullNode) {
            itr = itr.right;
        }
        return itr.element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public Comparable find(Comparable x) {
        nullNode.element = x;
        current = header.right;

        for (;;) {
            if (x.compareTo(current.element) < 0) {
                current = current.left;
            } else if (x.compareTo(current.element) > 0) {
                current = current.right;
            } else if (current != nullNode) {
                return current.element;
            } else {
                return null;
            }
        }
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        header.right = nullNode;
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return header.right == nullNode;
    }

    /**
     * Internal routine that is called during an insertion
     * if a node has two red children. Performs flip and rotations.
     * @param item the item being inserted.
     */
    private void handleReorient(Comparable item) {
        // Do the color flip
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED) // Have to rotate
        {
            grand.color = RED;
            if ((compare(item, grand) < 0) !=
                    (compare(item, parent) < 0)) {
                parent = rotate(item, grand);  // Start dbl rotate

            }
            current = rotate(item, great);
            current.color = BLACK;
        }
        header.right.color = BLACK; // Make root black

    }

    /**
     * Internal routine that performs a single or double rotation.
     * Because the result is attached to the parent, there are four cases.
     * Called by handleReorient.
     * @param item the item in handleReorient.
     * @param parent the parent of the root of the rotated subtree.
     * @return the root of the rotated subtree.
     */
    private RedBlackNode rotate(Comparable item, RedBlackNode parent) {
        if (compare(item, parent) < 0) {
            return parent.left = compare(item, parent.left) < 0 ? rotateWithLeftChild(parent.left) : // LL
                    rotateWithRightChild(parent.left);  // LR

        } else {
            return parent.right = compare(item, parent.right) < 0 ? rotateWithLeftChild(parent.right) : // RL
                    rotateWithRightChild(parent.right);  // RR

        }
    }

    /**
     * Rotate binary tree node with left child.
     */
    private static RedBlackNode rotateWithLeftChild(RedBlackNode k2) {
        RedBlackNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     */
    private static RedBlackNode rotateWithRightChild(RedBlackNode k1) {
        RedBlackNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    @Override
    public String toString() {
        String returnString = "";
        returnString += "Graph g{\n";

        if (header != nullNode) {
            //returnString += printTreeNodes(header.right);
            //returnString += printTreeEdges(header.right);
            returnString += printTree();
        }

        returnString += "}\n";
        return returnString;
    }

    private String printTreeNodes(RedBlackNode t) {
        String returnString = "";
        if (t != nullNode) {
            returnString += t.toString() + "\n";
            returnString += printTreeNodes(t.left);
            returnString += printTreeNodes(t.right);
        }

        return returnString;

    }

    private String printEdge(RedBlackNode parent, RedBlackNode child) {
        if (child != null) {
            String returnString = "";
            returnString += parent.getName(); //a -- b [shape=polygon,sides=5,peripheries=3,color=lightblue,style=filled]; 

            returnString += " -- ";

            returnString += child.getName() + ";\n";

            return returnString;
        } else {
            return null;
        }

    }

    private String printTreeEdges(RedBlackNode t) {
        String returnString = "";
        if (t != nullNode) {
            if (t.left != null) {
                returnString += printEdge(t, t.left);
                returnString += printTreeEdges(t.left);
            }

            if (t.right != nullNode) {
                returnString += printEdge(t, t.right);
                returnString += printTreeEdges(t.right);
            }

        }


        return returnString;

    }

    public String printTree() {
        return printTree(header.right);
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private String printTree(RedBlackNode t) {
        /*if (t != nullNode) {
        printTree(t.left);
        System.out.println(t.element);
        printTree(t.right);
        }*/

        String returnString = "";
        if (t != nullNode) {
            returnString += printTree(t.left);
            returnString += t.toString() + "\n";
            returnString += printTree(t.right);

            if (t.left != nullNode) {
                returnString += printEdge(t, t.left);
            } else {
                returnString += printLeftNull(t)  + "\n";
            }
            if (t.right != nullNode) {
                returnString += printEdge(t, t.right);
            } else {
                returnString += printRightNull(t) + "\n";
            }
        }

        return returnString;

    }

    private String printLeftNull(RedBlackNode t) {
        String returnString = "";
        returnString += "NilLeft" + t.getName();
        returnString += " [fillcolor=Black, style=filled, fontcolor=White, shape=box, label=\"Nil\"];\n";
        returnString += t.getName() + " -- " + "NilLeft" + t.getName() +";";

        return returnString;
    }
    
    private String printRightNull(RedBlackNode t) {
        String returnString = "";
        returnString += "NilRight" + t.getName();
        returnString += " [fillcolor=Black, style=filled, fontcolor=White, shape=box, label=\"Nil\"];\n";
        returnString += t.getName() + " -- " + "NilRight" + t.getName() +";";

        return returnString;
    }

    private static class RedBlackNode extends Vertex {
        // Constructors

        RedBlackNode(Comparable theElement) {
            this(theElement, null, null);
        }

        RedBlackNode(Comparable theElement, RedBlackNode lt, RedBlackNode rt) {
            super();
            element = theElement;
            left = lt;
            right = rt;
            color = RedBlackTree.BLACK;
        }

        @Override
        public String getColor() {
            String returnString = "";

            if (color == BLACK) {
                returnString = "Black";
            } else {
                returnString = "Red";
            }
            return returnString;

        }

        public String getFontColor() {
            String returnString = "";
            returnString = "White";
            
            return returnString;
        }

        @Override
        public String toString() {
            String returnString = "";
            if (element != null) {
                returnString += element.toString();
                returnString += " [fillcolor=" + getColor() + ", style=" + getStyle() +
                        ", shape=" + getShape() + ", fontcolor=" + getFontColor() + ", label=\"" + 
                        element.toString() + "\"];";
            }

            return returnString;

        }

        @Override
        public String getName() {
            if (element != null) {
                return element.toString();
            } else {
                return "Error";
            }

        }
        Comparable element;    // The data in the node

        RedBlackNode left;       // Left child

        RedBlackNode right;      // Right child

        int color;      // Color

    }
    private RedBlackNode header;
    private static RedBlackNode nullNode;
    

    static // Static initializer for nullNode
    {
        nullNode = new RedBlackNode(null);
        nullNode.left = nullNode.right = nullNode;
    }
    private static final int BLACK = 1;    // Black must be 1

    private static final int RED = 0;
    // Used in insert routine and its helpers
    private static RedBlackNode current;
    private static RedBlackNode parent;
    private static RedBlackNode grand;
    private static RedBlackNode great;

    // Test program
    public static void main(String[] args) {
        RedBlackTree t = new RedBlackTree();

        System.out.println("Checking... (no more output means success)");

        //for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {

        t.insert(new Integer(13));
        t.insert(new Integer(8));
        t.insert(new Integer(17));
        t.insert(new Integer(1));
        t.insert(new Integer(11));
        t.insert(new Integer(15));
        t.insert(new Integer(25));
        t.insert(new Integer(6));
        t.insert(new Integer(22));
        t.insert(new Integer(27));
        


        //}
        System.out.println(t.toString());
    //if (((Integer) (t.findMin())).intValue() != 1 ||
    //        ((Integer) (t.findMax())).intValue() != NUMS - 1) {
    //    System.out.println("FindMin or FindMax error!");
    //}
    //for (int i = 1; i < NUMS; i++) {
    //    if (((Integer) (t.find(new Integer(i)))).intValue() != i) {
    //        System.out.println("Find error1!");
    //    }
    //} 
    }

    public void run(Graph g) {
        running = true;
        controller = new Controller(g, pseudoCode, this);
        RedBlackTree t = new RedBlackTree();
        final int NUMS = 400000;
        final int GAP = 35461;

        System.out.println("Checking... (no more output means success)");

        for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
            t.insert(new Integer(i));
        }
        if (((Integer) (t.findMin())).intValue() != 1 ||
                ((Integer) (t.findMax())).intValue() != NUMS - 1) {
            System.out.println("FindMin or FindMax error!");
        }
        for (int i = 1; i < NUMS; i++) {
            if (((Integer) (t.find(new Integer(i)))).intValue() != i) {
                System.out.println("Find error1!");
            }
        }
        running = false;
    }

    public String getFileName() {
        return FILENAME;
    }

    public Controller getController() {
        return controller;
    }

    public int getInstanceID() {
        return instanceID;
    }

    public boolean isRunning() {
        return running;
    }
}


