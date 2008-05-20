package VEGA.Algorithms.TSP;

import VEGA.Controller;
import VEGA.PseudoCode.Procedure;
import VEGA.PseudoCode.PseudoCode;
import VEGA.Algorithms.GraphAlgorithm;
import VEGA.Graph.Graph;
import VEGA.Graph.Edge.EdgeImpl;
import VEGA.Graph.Vertex.Vertex;

public class NearestNeighbor implements GraphAlgorithm {

    private PseudoCode pseudoCode;
    private Graph g;
    private Vertex[] vertexArray;
    Controller controller;
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
    private Vertex[] runNearestNeighbor(int startIndex) {

        //Take a snapshot of the graph before the algorithm is run.
        controller.generateGraphInstance(1, nearestNeighborProcedure.getTitle());

        Vertex[] nearestNeighborArray = new Vertex[g.getSize() + 1];
        int counter = 0;
        
        if (startIndex < vertexArray.length) {
            Vertex curr = vertexArray[startIndex];

            nearestNeighborArray[counter++] = NearestNeighborProcedureStepTwo(curr);

            while (curr != null) {
                Vertex next = curr.getNearestNeighbor();
                if (next != null) {
                    EdgeImpl e = curr.getEdge(next);

                    //Step Three
                    nearestNeighborArray[counter++] = NearestNeighborProcedureStepThree(curr, e, next);
                    
                    curr = next;

                } else {
                    Vertex root = vertexArray[startIndex];
                    NearestNeighborProcedureStepFive(curr, root);
                    nearestNeighborArray[counter] = NearestNeighborProcedureStepSix(curr, root);

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
    private Vertex NearestNeighborProcedureStepTwo(Vertex curr) {
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
    private Vertex NearestNeighborProcedureStepThree(Vertex curr, EdgeImpl e, Vertex next) {
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
    private void NearestNeighborProcedureStepFive(Vertex curr, Vertex root) {
        root.setActive(true);
        EdgeImpl e = root.getEdge(curr);
        if (e != null) {
            e.setActive(true);
            e.setInUse(true);
        }


        controller.generateGraphInstance(5, nearestNeighborProcedure.getTitle());
    }

    /**
     * Internal method.
     * @param curr
     * @param root
     * @return
     */
    private Vertex NearestNeighborProcedureStepSix(Vertex curr, Vertex root) {
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
    public Controller getController() {
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
    public void run(Graph g) {
        // TODO Auto-generated method stub
        running = true;
        instanceID = instanceCounter++;
        vertexArray = g.getVertexArray();
        this.g = g;
        controller = new Controller(g, pseudoCode, this);
        runNearestNeighbor(0); //Let's assume we don't assign a starting vertex

        running = false;
    }
    
    /**
     * Executes algorithm but specifies the start vertex.
     * @param g
     * @param startIndex
     */
    public void run(Graph g, int startIndex) {
        running = true;
        vertexArray = g.getVertexArray();
        this.g = g;
        pseudoCode = new PseudoCode("Nearest Neighbor");
        controller = new Controller(g, pseudoCode, this);
        runNearestNeighbor(startIndex);
        running = false;
    }
}
