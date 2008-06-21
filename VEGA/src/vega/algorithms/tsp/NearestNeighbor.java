package vega.algorithms.tsp;

import java.util.ArrayList;

import vega.Controller;
import vega.helperClasses.VertexHelper;
import vega.pseudoCode.Procedure;
import vega.pseudoCode.PseudoCode;
import interfaces.algorithms.GraphAlgorithm;
import interfaces.graph.Graph;
import interfaces.graph.edge.Edge;
import interfaces.graph.vertex.Vertex;

public class NearestNeighbor<V extends Vertex<V,E>, E extends Edge> implements GraphAlgorithm<Graph<V,E>,V,E> {

    private PseudoCode pseudoCode;
    private Graph<V,E> g;
    private ArrayList<V> vertexArray;
    Controller<V,E> controller;
    private String FILENAME = "nearestNeighbor";
    private static int instanceCounter = 0;
    private int instanceID = 0;
    private boolean running = false;
    Procedure nearestNeighborProcedure;

    /**
     * Contructor instantiates the pseudo code for the algorithm.
     */
    public NearestNeighbor() {
        pseudoCode = new PseudoCode("Nearest Neighbor Algorithm");

        nearestNeighborProcedure = new Procedure("Nearest Neighbor\n");
        nearestNeighborProcedure.appendLine("NearestNeighbor(V, E):");
        nearestNeighborProcedure.appendLine("\tSelect Root as Active Vertex");
        nearestNeighborProcedure.appendLine("\t\tSelect Nearest Neighbor Not In Use");
        nearestNeighborProcedure.appendLine("\t\tRepeat Until No Unused Verticies Remain");
        nearestNeighborProcedure.appendLine("\tAdd Root As Final Vertex In the Cycle");
        nearestNeighborProcedure.appendLine("Return Tour");

        pseudoCode.addProcedure(nearestNeighborProcedure);
    }

    

    /**
     * Execute the algorithm from the start vertex.
     * @param startIndex Root vertex
     * @return Returns vertex array sorted based on the Hamiltonian Cycle found.
     */
    private ArrayList<V> runNearestNeighbor(int startIndex) {

        //Take a snapshot of the graph before the algorithm is run.
        controller.generateGraphInstance(1, nearestNeighborProcedure.getTitle());

        ArrayList<V> nearestNeighborArray = new ArrayList<V>(g.getSize() + 1);
        int counter = 0;
        
        if (startIndex < vertexArray.size()) {
            V curr = vertexArray.get(startIndex);

            nearestNeighborArray.set(counter++, NearestNeighborProcedureStepTwo(curr));

            while (curr != null) {
            	V next = VertexHelper.getNearestNeighbor(curr);
                if (next != null) {
                    E e = curr.getEdge(next);

                    //Step Three
                    nearestNeighborArray.set(counter++, NearestNeighborProcedureStepThree(curr, e, next));
                    
                    curr = next;

                } else {
                    V root = vertexArray.get(startIndex);
                    NearestNeighborProcedureStepFive(curr, root);
                    nearestNeighborArray.set(counter, NearestNeighborProcedureStepSix(curr, root));
                    curr = null;
                }
            }

            return nearestNeighborArray;

        } else {
            //Start index exceeds number of verticies
            return null;
        }
    }

    /**
     * Internal method.
     * @param curr
     * @return
     */
    private V NearestNeighborProcedureStepTwo(V curr) {
        curr.setActive(true);
        curr.setInUse(true);
        controller.generateGraphInstance(2, nearestNeighborProcedure.getTitle());
        return curr;
    }

    /**
     * Internal method.
     * @param curr
     * @param e
     * @param next
     * @return
     */
    private V NearestNeighborProcedureStepThree(V curr, E e, V next) {
        curr.setInUse(true);
        curr.setActive(true);
        e.setInUse(true);
        e.setActive(true);
        next.setActive(true);
        next.setInUse(true);

        controller.generateGraphInstance(3, nearestNeighborProcedure.getTitle());

        e.setActive(false);
        curr.setActive(false);

        return next;
    }

    /**
     * Internal method.
     * @param curr
     * @param root
     */
    private void NearestNeighborProcedureStepFive(V curr, V root) {
        root.setActive(true);
        E edge = root.getEdge(curr);
        if (edge != null) {
            edge.setActive(true);
            edge.setInUse(true);
        }


        controller.generateGraphInstance(5, nearestNeighborProcedure.getTitle());
    }

    /**
     * Internal method.
     * @param curr
     * @param root
     * @return
     */
    private V NearestNeighborProcedureStepSix(V curr, V root) {
        curr.setActive(false);
        curr.getEdge(root).setActive(false);
        root.setActive(false);

        controller.generateGraphInstance(6, nearestNeighborProcedure.getTitle());

        return root;
    }

    /**
     * Gets the instance of the algorithm.  Used for file naming.
     * @return
     */
    public int getInstanceID() {
        return instanceID;
    }
   

    /**
     * Returns true if the algorithm is running, false if it is not
     * @return
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Gets the controller for the algorithm.  Used to communicate with VEGA.
     * @return
     */
    public Controller<V,E> getController() {
        return controller;
    }

    /**
     * Returns the filename.
     * @return
     */
    public String getFileName() {
        return FILENAME;
    }
    
    /**
     * Run method executes the algorithm on the graph.
     * @param g Graph to execute algorithm on.
     */
    public void run(Graph<V,E> g) {
        // TODO Auto-generated method stub
        running = true;
        instanceID = instanceCounter++;
        vertexArray = g.getVertexArray();
        this.g = g;
        controller = new Controller<V,E>(g, pseudoCode, this);
        runNearestNeighbor(0); //Let's assume we don't assign a starting vertex

        running = false;
    }
    
    /**
     * Executes algorithm but specifies the start vertex.
     * @param g
     * @param startIndex
     */
    public void run(Graph<V,E> g, int startIndex) {
        running = true;
        vertexArray = g.getVertexArray();
        this.g = g;
        pseudoCode = new PseudoCode("Nearest Neighbor");
        controller = new Controller<V,E>(g, pseudoCode, this);
        runNearestNeighbor(startIndex);
        running = false;
    }
}
