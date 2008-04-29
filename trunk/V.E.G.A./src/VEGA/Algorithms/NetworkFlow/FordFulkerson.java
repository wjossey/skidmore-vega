/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VEGA.Algorithms.NetworkFlow;

import VEGA.HelperClasses.DataStructures.Queue;
import VEGA.Graph.Edge.Network.NetworkEdge;
import VEGA.Graph.Vertex.Vertex;

/**
 *
 * @author w_jossey
 */
public class FordFulkerson {
    Vertex[] vertexArray;
    /*Internal method that reverses a linked list when given a starting and ending
     * vertex.  This is particularly useful for the 2opt algorithm as we are forced
     * to reverse the list when a shorter path is found.
     */
    /*  FORD-FULKERSON-METHOD (G, s, t)
     *  1.  Initialize flow f to 0
     *  2.  While there exists an augmenting path p
     *  3.  	do augment flow f along p
     *  4.  return f
     */
    public int fordFulkerson(Vertex s, Vertex t) {
        int flow = 0;
        for (int i = 0; i < vertexArray.length; i++) {
            /*Set flow to 0*/
            vertexArray[i].setIncomingEdge(null);
            vertexArray[i].setOutgoingEdge(null);
            NetworkEdge[] tempEdgeList = (NetworkEdge[]) vertexArray[i].getEdges();
            for (int j = 0; j < tempEdgeList.length; j++) {
                tempEdgeList[j].setFlow(0);
            }


            /*While there exists an augmenting path p*/
            while (findPath(s, t)) {
                augmentFlow(s, t);
            }
            return flow;
            
        }
        return flow;
    }

    private void augmentFlow(Vertex s, Vertex t) {
        Vertex[] path = getFoundPath(t);
        double residualCapacityOfPath = Double.MAX_VALUE;
        for (int i = 0; path[i] != t; i++) {
            NetworkEdge tempEdge = (NetworkEdge) path[i].getEdge(path[i + 1]);
            if (tempEdge.getResidual() < residualCapacityOfPath) {
                residualCapacityOfPath = tempEdge.getResidual();
            }
        }

        for (int i = 0; path[i] != t; i++) {
            NetworkEdge tempEdge = (NetworkEdge) path[i].getEdge(path[i + 1]);
            tempEdge.augmentFlow(residualCapacityOfPath);
            tempEdge = (NetworkEdge) path[i + 1].getEdge(path[i]);
            tempEdge.augmentFlow(-residualCapacityOfPath);
        }
    }
    /*Breadth first search*/

    private boolean findPath(Vertex s, Vertex t) {
        /*Set all previous vertex values to null*/
        for (int i = 0; i < vertexArray.length && vertexArray[i] != null; i++) {
            vertexArray[i].setPreviousVertex(null);
        }

        boolean pathFound = false;
        Queue queue = new Queue();
        if (s == t) {
            return false;
        } else {
            Vertex[] tempVertexList = s.getNeighbors();
            for (int i = 0; i < tempVertexList.length && tempVertexList[i] != null; i++) {
                queue.enQueue(tempVertexList[i]);
                tempVertexList[i].setPreviousVertex(s);
            }
            /*While there still exists items in the queue and no path has been found*/
            while (!queue.isEmpty() && pathFound == false) {
                /*Get the Vertex as the front of the queue*/
                Vertex tempVertex = (Vertex) queue.deQueue();

                /*Get all adjacent vertices*/
                Vertex[] adjacencyList = tempVertex.getNeighbors();

                /*Cycle through the adjacencyList and try to find the sink--- adding any
                 * non-discovered vertices to the queue
                 */
                for (int j = 0; j < adjacencyList.length && adjacencyList[j] != null; j++) {
                    /*Check to make sure there exists capacity to that vertex that is
                     * greater than the flow
                     */
                    //if(tempVertex.getEdge(adjacencyList[j]).getFlow() < 
                    //		tempVertex.getEdge(adjacencyList[j]).getCapacity()){
						/*If the vertex does not already exist in the queue*/
                    if (queue.enQueue(adjacencyList[j])) {
                        /*If the vertex is the sink*/
                        if (adjacencyList[j] == t) {
                            /*Set the sinks parent vertex, set the pathFound bool to
                             * true, and then we'll exit out of the while loop, terminate
                             * the method, and return "true"
                             */
                            adjacencyList[j].setPreviousVertex(tempVertex);
                            pathFound = true;
                        } else {
                            adjacencyList[j].setPreviousVertex(tempVertex);
                        //		}
                        }
                    }
                }
            }
        }
        return pathFound;
    }

    private Vertex[] getFoundPath(Vertex t) {
        Vertex[] reverseList = new Vertex[vertexArray.length];
        Vertex tempVertex = t;
        int counter = 0;
        while (tempVertex != null) {
            reverseList[counter] = t;
            tempVertex = tempVertex.getPreviousVertex();
            counter++;
        }
        reverseList[counter] = tempVertex;
        Vertex[] returnArray = new Vertex[counter];

        for (int i = 0, j = counter - 1; i < counter; i++, j--) {
            returnArray[i] = reverseList[j];
        }
        return returnArray;
    }
}
