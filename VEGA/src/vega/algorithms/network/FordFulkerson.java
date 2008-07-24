/*
  To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vega.algorithms.network;

import java.util.ArrayList;

import vega.dataStructures.queue.QueueImpl;
import vega.helperClasses.VertexHelper;
import interfaces.graph.edge.network.DirectedNetworkEdge;
import interfaces.graph.edge.network.NetworkEdge;
import interfaces.graph.vertex.Vertex;
import interfaces.graph.vertex.network.NetworkNode;
//
///**
// *
// * @author w_jossey
// */
public class FordFulkerson<N extends NetworkNode<DirectedNetworkEdge<N>>> {
//    ArrayList<N> networkNodeArray;
//    /*Internal method that reverses a linked list when given a starting and ending
//     * vertex.  This is particularly useful for the 2opt algorithm as we are forced
//     * to reverse the list when a shorter path is found.
//     */
//    /*  FORD-FULKERSON-METHOD (G, s, t)
//     *  1.  Initialize flow f to 0
//     *  2.  While there exists an augmenting path p
//     *  3.  	do augment flow f along p
//     *  4.  return f
//     */
//    public int fordFulkerson(N source, N sink) {
//        int flow = 0;
//        for (int i = 0; i < networkNodeArray.size(); i++) {
//            /*Set flow to 0*/
//        	networkNodeArray.get(i).setIncomingEdge(null);
//        	networkNodeArray.get(i).setOutgoingEdge(null);
//            ArrayList<DirectedNetworkEdge<N>> tempEdgeList = networkNodeArray.get(i).getEdges();
//            for (int j = 0; j < tempEdgeList.size(); j++) {
//                tempEdgeList.get(j).setFlow(0);
//            }
//
//
//            /*While there exists an augmenting path p*/
//            while (findPath(s, t)) {
//                augmentFlow(s, t);
//            }
//            return flow;
//            
//        }
//        return flow;
//    }
//
//    private void augmentFlow(N source, N destination) {
//        ArrayList<N> path = getFoundPath(destination);
//        double residualCapacityOfPath = Double.MAX_VALUE;
//        for (int i = 0; path.get(i) != destination; i++) {
//            NetworkEdge tempEdge = path.get(i).getEdge(path.get(i+1));
//            if (tempEdge.getResidual() < residualCapacityOfPath) {
//                residualCapacityOfPath = tempEdge.getResidual();
//            }
//        }
//
//        for (int i = 0; path.get(i) != destination; i++) {
//            DirectedNetworkEdge<N> tempEdge = path.get(i).getEdge(path.get(i+1));
//            tempEdge.augmentFlow(residualCapacityOfPath);
//            tempEdge = path.get(i+1).getEdge(path.get(i));
//            tempEdge.augmentFlow(-residualCapacityOfPath);
//        }
//    }
//    /*Breadth first search*/
//
//    private boolean findPath(N s, N t) {
//        /*Set all previous vertex values to null*/
//        for (int i = 0; i < networkNodeArray.size() && networkNodeArray.get(i) != null; i++) {
//            networkNodeArray.get(i).setPreviousVertex(null);
//        }
//
//        boolean pathFound = false;
//        QueueImpl queue = new QueueImpl();
//        if (s == t) {
//            return false;
//        } else {
//            ArrayList<N> tempVertexList = VertexHelper.getNearestNeighbors(s);
//            for (int i = 0; i < tempVertexList.size() && tempVertexList.get(i) != null; i++) {
//                queue.enQueue(tempVertexList.get(i));
//                tempVertexList.get(i).setPreviousVertex(s);
//            }
//            /*While there still exists items in the queue and no path has been found*/
//            while (!queue.isEmpty() && pathFound == false) {
//                /*Get the Vertex as the front of the queue*/
//                N tempVertex =  queue.deQueue();
//
//                /*Get all adjacent vertices*/
//                ArrayList<N> adjacencyList = VertexHelper.getNearestNeighbors(tempVertex);
//
//                /*Cycle through the adjacencyList and try to find the sink--- adding any
//                 * non-discovered vertices to the queue
//                 */
//                for (int j = 0; j < adjacencyList.size() && adjacencyList.get(j) != null; j++) {
//                    /*Check to make sure there exists capacity to that vertex that is
//                     * greater than the flow
//                     */
//                    //if(tempVertex.getEdge(adjacencyList[j]).getFlow() < 
//                    //		tempVertex.getEdge(adjacencyList[j]).getCapacity()){
//						/*If the vertex does not already exist in the queue*/
//                    if (queue.enQueue(adjacencyList.get(j))) {
//                        /*If the vertex is the sink*/
//                        if (adjacencyList.get(j) == t) {
//                            /*Set the sinks parent vertex, set the pathFound bool to
//                             * true, and then we'll exit out of the while loop, terminate
//                             * the method, and return "true"
//                             */
//                            adjacencyList.get(j).setPreviousVertex(tempVertex);
//                            pathFound = true;
//                        } else {
//                            adjacencyList.get(j).setPreviousVertex(tempVertex);
//                        //		}
//                        }
//                    }
//                }
//            }
//        }
//        return pathFound;
//    }
//
//    private ArrayList<N> getFoundPath(N t) {
//        ArrayList<N> reverseList = new ArrayList<N>(networkNodeArray.size());
//        N tempVertex = t;
//        int counter = 0;
//        while (tempVertex != null) {
//            reverseList.set(counter, t);
//            tempVertex = tempVertex.getPreviousVertex();
//            counter++;
//        }
//        reverseList.set(counter, tempVertex);
//        ArrayList<N> returnArray = new ArrayList<N>(counter);
//
//        for (int i = 0, j = counter - 1; i < counter; i++, j--) {
//            returnArray.set(i, reverseList.get(j));
//        }
//        return returnArray;
//    }
}
