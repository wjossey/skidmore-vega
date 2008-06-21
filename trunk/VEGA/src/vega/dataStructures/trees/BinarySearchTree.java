package vega.dataStructures.trees;

import vega.graph.vertex.tree.BinaryTreeNode;

public class BinarySearchTree<C extends Comparable<C>> extends AbstractBinarySearchTree<C, BinaryTreeNode<C>> implements interfaces.dataStructures.tree.BinarySearchTree<C, BinaryTreeNode<C>>{

	@Override
	public void insert(C arg) {
		// TODO Auto-generated method stub
		BinaryTreeNode<C> newNode = new BinaryTreeNode<C>(arg);
		super.insertNode(newNode);
	}


}
