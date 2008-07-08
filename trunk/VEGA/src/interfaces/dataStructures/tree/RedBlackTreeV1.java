/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.dataStructures.tree;

/**
 * 
 * @author w_jossey
 *
 * @param <C>
 */
public interface RedBlackTreeV1<C extends Comparable<C>>{

	/**
	 * 
	 * @param x
	 * @return
	 */
    boolean delete(C x);

    /**
     * 
     * @param x
     */
    void insert(C x);
   
}
