package vega.algorithms.tsp;

import vega.Controller;
import vega.pseudoCode.Procedure;
import vega.pseudoCode.PseudoCode;
import vega.helperClasses.*;
import interfaces.algorithms.GraphAlgorithm;
import interfaces.graph.Tree;
import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

public class TwoOpt<C extends Comparable<C>, T extends TreeNode<C,T,UndirectedEdge<T>>> implements GraphAlgorithm<Tree<C,T,UndirectedEdge<T>>,T, UndirectedEdge<T>> {

    private Tree<C,T,UndirectedEdge<T>> g;
    private PseudoCode pseudoCode;
    Controller<T, UndirectedEdge<T>> controller;
    int imageCounter = 0;
    private String FILENAME = "twoOpt";
    private static int instanceCounter = 0;
    private int instanceID = 0;
    private boolean running = false;
    private Procedure twoOptMove;

    public void run(Tree<C,T,UndirectedEdge<T>> g) {
        // TODO Auto-generated method stub
        running = true;
        instanceID = instanceCounter++;
        this.g = g;
        pseudoCode = new PseudoCode("2-OPT");
        twoOptMove = new Procedure("2OPT(V, E):");
        twoOptMove.appendLine("Code To Be Inserted)");
        pseudoCode.addProcedure(twoOptMove);
        controller = new Controller<T, UndirectedEdge<T>>(this.g, pseudoCode, this);
        executeTwoOpt(this.g.getVertexArray()); //Let's assume we don't assign a starting vertex

        running = false;
    }

    public String getFileName() {
        return FILENAME;
    }

    public boolean isRunning() {
        return running;
    }

    /**
     * The 2OPT algorithm.  
     * @return returns the tour in the form of a Vertex array
     */
    public ArrayList<T> executeTwoOpt(ArrayList<T> tour) {
        //Transform tour to a linked list

        /*Start of the twoOpt algorithm.  First, let's connect our tour*/
        for (int i = 0; i < tour.size(); i++) {
            tour.get(i).setInUse(true);

            if (i == tour.size() - 1) {
                tour.get(i).getEdge(tour.get(0)).setInUse(true);
            } else {
                tour.get(i).getEdge(tour.get(i + 1)).setInUse(true);
            }
        }

        /* */
        //controller.generateGraphInstance();

        boolean moveMade = true;
        LinkedList<T> tourLinked = new LinkedList<T>();
        for (int i = 0; i < tour.size(); i++) {
            tourLinked.add(tour.get(i));
        }
        while (moveMade) {
            moveMade = false;
            for (int i = 0; i < tour.size(); i++) {
                T v1 = tour.get(i);
                T v2 = null;
                T v3 = null;
                T v4 = null;

                int indexV1 = tourLinked.indexOf(v1);
                int indexV2;

                /*If we have the last index as our v1, then we have a wrap
                 * around situation.
                 */
                if (indexV1 == tour.size() - 1) {
                    v2 = tourLinked.get(0);  //v1 is the last element in the tour

                    indexV2 = 0;
                } else {
                    v2 = tourLinked.get(indexV1 + 1); //v1 is not the last element of the tour

                    indexV2 = indexV1 + 1;
                }

                Double v1Tov2Distance = v1.getEdge(v2).getWeight();

                //Fixed constant of 5 to keep our running time reasonable
                int constantK = 0;
                if (g.getSize() > 5) {
                    constantK = 5;
                } else {
                    constantK = g.getSize() - 1;
                }
                
                
                ArrayList<T> nearestNeighborArray = VertexHelper.getKNearestNeighbors(tour.get(i), constantK);

                Double twoOptDistance = Double.POSITIVE_INFINITY;
                for (int k = 0; k < nearestNeighborArray.size(); k++) {
                    T tempV3 = null;
                    T tempV4 = null;
                    if (nearestNeighborArray.get(k).getUID() != v2.getUID()) {
                        tempV3 = nearestNeighborArray.get(k);
                        tempV4 = nextVertex(tourLinked, tourLinked.indexOf(tempV3));

                        Double tempV3toTempV4Distance = tempV3.getEdge(tempV4).getWeight();
                        Double v1ToTempV3Distance = v1.getEdge(tempV3).getWeight();
                        Double v2ToTempV4Distance = v2.getEdge(tempV4).getWeight();

                        if ((v1Tov2Distance + tempV3toTempV4Distance) >
                                (v1ToTempV3Distance + v2ToTempV4Distance)) {
                            //We have a possible optimal tour
                            if (v3 == null && v4 == null) {
                                v3 = tempV3;
                                v4 = tempV4;
                                twoOptDistance = v1ToTempV3Distance + v2ToTempV4Distance;
                            } else {
                                if ((v1ToTempV3Distance + v2ToTempV4Distance) < twoOptDistance) {
                                    twoOptDistance = v1ToTempV3Distance + v2ToTempV4Distance;
                                    v3 = tempV3;
                                    v4 = tempV4;
                                }
                            }
                        }

                    }
                }

                if (v3 != null) {
                    moveMade = true;
                    int indexV3 = tourLinked.indexOf(v3);

                    v1.setActive(true);
                    v2.setActive(true);
                    v3.setActive(true);
                    v4.setActive(true);

                    controller.generateGraphInstance(1, twoOptMove.getTitle());

                    v1.getEdge(v2).setActive(true);
                    v3.getEdge(v4).setActive(true);

                    controller.generateGraphInstance(1, twoOptMove.getTitle());

                    v1.getEdge(v2).setActive(false);
                    v3.getEdge(v4).setActive(false);
                    v1.getEdge(v2).setInUse(false);
                    v3.getEdge(v4).setInUse(false);

                    v2.getEdge(v4).setActive(true);
                    v2.getEdge(v4).setInUse(true);
                    v1.getEdge(v3).setActive(true);
                    v1.getEdge(v3).setInUse(true);

                    controller.generateGraphInstance(1, twoOptMove.getTitle());

                    v1.getEdge(v3).setActive(false);
                    v2.getEdge(v4).setActive(false);
                    v1.setActive(false);
                    v2.setActive(false);
                    v3.setActive(false);
                    v4.setActive(false);

                    reverse(tourLinked, indexV2, indexV3);
                //System.out.println("Iteration: " + i + "\n" + "Distance= " + 
                //		computeDistanceOfTour(tourLinked) + "\n" + 
                //		produceCompleteGraphVizForTourAray(linkedListToArray(tourLinked)));
                }
            }
        }

        controller.generateGraphInstance(1, twoOptMove.getTitle());

        return linkedListToArray(tourLinked);
    }

    /*A simple conversion method that takes in a LinkedList that is 
     * a LL of vertices and converts them to an array.  
     */
    private ArrayList<T> linkedListToArray(LinkedList<T> linkedList) {
        ArrayList<T> returnArray = new ArrayList<T>(linkedList.size());
        for (int i = 0; i < linkedList.size(); i++) {
            returnArray.set(i, linkedList.get(i));
        }

        return returnArray;
    }

    /* Internal method to give you the next vertex in a linked list of vertices.
     * The method returns the first vertex in the list if it reaches the end of the list 
     */
    private T nextVertex(LinkedList<T> tour, int curr) {
        T returnVertex;
        if (curr == tour.size() - 1 || curr >= tour.size()) {
            returnVertex = tour.get(0);
        } else {
            returnVertex = tour.get(curr + 1);
        }

        return returnVertex;
    }

    /*Internal method that reverses a linked list when given a starting and ending
     * vertex.  This is particularly useful for the 2opt algorithm as we are forced
     * to reverse the list when a shorter path is found.
     */
    private void reverse(LinkedList<T> tour, int start, int end) {
        int length = end - start;

        if (length < 0) {
            length += tour.size();
        }

        length = length / 2 + 1; //Only need to compute it in half

        for (int k = 0; k < length; k++) {
            T tempVertex = tour.get(start);
            tour.set(start, tour.get(end)); //Swap the two cities

            tour.set(end, tempVertex);
            if (++start >= tour.size()) {
                start = 0;
            }
            if (--end < 0) {
                end = tour.size() - 1;
            }
        }

    }

    public Controller<T,UndirectedEdge<T>> getController() {
        return controller;
    }

    public int getInstanceID() {
        return instanceID;
    }

    public String getPseudoCodeText(int line, String procedure) {
        return pseudoCode.getPseudoCodeWithLineMarker(line, procedure);
    }

}
