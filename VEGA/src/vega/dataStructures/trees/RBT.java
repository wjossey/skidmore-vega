package vega.dataStructures.trees;

import vega.dataStructures.trees.AbstractBinarySearchTree;
import interfaces.graph.vertex.tree.RedBlackTreeNode;


public class RBT<C extends Comparable<C>> extends AbstractBinarySearchTree<C, RedBlackTreeNode<C>>{
	
	private final boolean RED = RedBlackTreeNode.RED;
	private final boolean BLACK = RedBlackTreeNode.BLACK;
	
	@SuppressWarnings("unchecked")
	public void insert(C key) { 
		vega.graph.vertex.tree.RedBlackTreeNode<C> newNode = new vega.graph.vertex.tree.RedBlackTreeNode<C>(key, RedBlackTreeNode.RED);
		insertNode(newNode);
		fixUpInsert(newNode);
	} // end insert()
	
	private void fixUpInsert(RedBlackTreeNode<C> problemChild) {
		RedBlackTreeNode<C> problemNode = problemChild;
		while(problemNode.getParentNode() != null && problemNode.getParentNode().isColor(RED)) {
			if (problemChild.getParentNode().isLeftChild()) {
				RedBlackTreeNode<C> uncle = (RedBlackTreeNode<C>) problemChild.getParentNode().getParentNode().getRightChild();
				
				if (uncle != null && uncle.isColor(RED)) {
					/*
					 * Case 1:
					 */
					problemChild.getParentNode().setColor(BLACK);
					uncle.setColor(BLACK);
					problemChild.getParentNode().getParentNode().setColor(RED);
					problemChild = problemChild.getParentNode().getParentNode();
					
				} else {
					
					if (problemChild.isRightChild()) {
						/*
						 * Case 2
						 */
						problemChild = problemChild.getParentNode();
						rotateWithRightChild(problemChild);
					} // end if 
					
					/*
					 * Case 3
					 */
					problemChild.getParentNode().setColor(BLACK);
					problemChild.getParentNode().getParentNode().setColor(RED);
					rotateWithLeftChild(problemChild.getParentNode().getParentNode());
				} // end if 
				
			} else {
				/*
				 * Symmetric cases for 1, 2 and 3 above
				 */
				System.out.println(this.toGraphViz("NullPointer"));
				System.out.flush();
				RedBlackTreeNode<C> uncle = problemChild.getParentNode().getParentNode().getLeftChild();
				
				if (uncle != null && uncle.isColor(RED)) {
					/*
					 * Case 1:
					 */
					problemChild.getParentNode().setColor(BLACK);
					uncle.setColor(BLACK);
					problemChild.getParentNode().getParentNode().setColor(RED);
					problemChild = problemChild.getParentNode().getParentNode();
					
				} else {
					
					if (problemChild.isLeftChild()) {
						/*
						 * Case 2
						 */
						problemChild = problemChild.getParentNode();
						rotateWithLeftChild(problemChild);
					} // end if 
					
					/*
					 * Case 3
					 */
					problemChild.getParentNode().setColor(BLACK);
					problemChild.getParentNode().getParentNode().setColor(RED);
					rotateWithRightChild(problemChild.getParentNode().getParentNode());
				} // end if 
								
			} // end if 
		} // end while 
		root.setColor(BLACK);
	} // end fixUpInsert()
	
	
	public static void main(String[] args) {
		System.out.println("Testing RedBlack tree");
		RBT<String> t = new RBT<String>();
		t.insert("a");
		System.out.println(t.toGraphViz("RedBlackTree"));
		t.insert("b");
		System.out.println(t.toGraphViz("RedBlackTree"));
		t.insert("c");
		System.out.println(t.toGraphViz("RedBlackTree"));
		t.insert("d");
		System.out.println(t.toGraphViz("RedBlackTree"));
		t.insert("e");
		System.out.println(t.toGraphViz("RedBlackTree"));
		System.out.println("tree is ");
		System.out.println(t);	
	}
}










