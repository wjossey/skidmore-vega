/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces.DataStructures;

import VEGA.Graph.Vertex.Tree.BinaryTreeNode;

/**
 *
 * @author w_jossey
 */
public interface RedBlackTreeV1 {

    boolean delete(Object x);

    void insert(Object x);
    
    BinaryTreeNode search(BinaryTreeNode subTree, Comparable c);

}
