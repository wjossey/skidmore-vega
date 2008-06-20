/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.dataStructures.tree;

/**
 *
 * @author w_jossey
 */
public interface RedBlackTreeV1<C extends Comparable<C>>{

    boolean delete(C x);

    void insert(C x);
   
}
