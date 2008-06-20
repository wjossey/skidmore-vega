package vega.dataStructures.trees;

import vega.dataStructures.trees.BinarySearchTreeImpl;
import vega.graph.vertex.tree.RedBlackTreeNode;
import interfaces.graph.edge.Edge;
import interfaces.graph.edge.UndirectedEdge;
import interfaces.graph.vertex.tree.RedBlackTreeNode;


public class RBT<C extends Comparable<C>, V extends RedBlackTreeNode<C,V,E>, E extends Edge> extends BinarySearchTreeImpl<C,V,E>{
	
	private final boolean RED = RedBlackTreeNode.RED;
	private final boolean BLACK = RedBlackTreeNode.BLACK;
	
	@SuppressWarnings("unchecked")
	public void insert(C key) { 
		RedBlackTreeNode<C,V,E> newNode = new RedBlackTreeNode<C,V,E>(key, RedBlackTreeNode.RED);
		super.insertNode((V) newNode);
		fixUpInsert((V) newNode);
	} // end insert()
	
	private void fixUpInsert(V problemChild) {
		RedBlackTreeNode<C,V,E> problemNode = problemChild;
		while(problemNode.getParentNode() != null && problemNode.getParentNode().isColor(RED)) {
			if (problemChild.getParentNode().isLeftChild()) { 
				RedBlackTreeNode<C,V,E> uncle = problemChild.getParentNode().getParentNode().getRightNode();
				
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
				RedBlackTreeNode<C,V,E> uncle = problemChild.getParentNode().getParentNode().getLeftNode();
				
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
		RBT t = new RBT();
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










