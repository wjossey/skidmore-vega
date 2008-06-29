package vega.dataStructures.trees;

import vega.graph.vertex.tree.BinaryTreeNode;

/**
 * Non-Abstract Binary Search Tree class that extends the abstract base BinarySearchTree
 * class.  
 * @author w_jossey
 *
 * @param <C>
 */
public class BinarySearchTree<C extends Comparable<C>> extends AbstractBinarySearchTree<C, BinaryTreeNode<C>> implements interfaces.dataStructures.tree.BinarySearchTree<C, BinaryTreeNode<C>>{

	/**
	 * Inserts the comparable object into the tree that holds the comparables.
	 * @param arg Comparable to insert into the BinarySearchTree
	 */
	public void insert(C arg) {
		BinaryTreeNode<C> newNode = new BinaryTreeNode<C>(arg);
		super.insertNode(newNode);
	}


}
