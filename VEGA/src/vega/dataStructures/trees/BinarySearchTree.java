package vega.dataStructures.trees;

import interfaces.algorithms.GraphAlgorithm;
import interfaces.graph.Graph;
import interfaces.graph.edge.UndirectedEdge;
import vega.Controller;
import vega.graph.vertex.tree.BinaryTreeNode;

/**
 * Non-Abstract Binary Search Tree class that extends the abstract base BinarySearchTree
 * class.  
 * @author w_jossey
 *
 * @param <C>
 */
public class BinarySearchTree<C extends Comparable<C>> extends AbstractBinarySearchTree<C, BinaryTreeNode<C>> implements GraphAlgorithm<Graph<BinaryTreeNode<C>, UndirectedEdge>,BinaryTreeNode<C>,UndirectedEdge>{

	/**
	 * Inserts the comparable object into the tree that holds the comparables.
	 * @param arg Comparable to insert into the BinarySearchTree
	 */
	public void insert(C arg) {
		BinaryTreeNode<C> newNode = new BinaryTreeNode<C>(arg);
		super.insertNode(newNode);
	}

	public Controller<BinaryTreeNode<C>, UndirectedEdge> getController() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getInstanceID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	public void run(Graph<BinaryTreeNode<C>, UndirectedEdge> g) {
		// TODO Auto-generated method stub
		
	}


}
