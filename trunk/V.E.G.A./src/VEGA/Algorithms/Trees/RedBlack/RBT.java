package VEGA.Algorithms.Trees.RedBlack;

import VEGA.Algorithms.Trees.BinarySearch.BinarySearchTree;
import VEGA.Graph.Vertex.Tree.BinaryTreeNode;
import VEGA.Graph.Vertex.Tree.RedBlackNode;


public class RBT extends BinarySearchTree {

	
	public void insert(Comparable key) { 
		RedBlackNode newNode = new RedBlackNode(key, RED);
		super.insertNode(newNode);
		fixUpInsert(newNode);
	} // end insert()
	
	private void fixUpInsert(RedBlackNode problemChild) {
		while(problemChild.getParentNode() != null && problemChild.getParentNode().isColor(RED)) {
			if (problemChild.getParentNode().isLeftChild()) { 
				RedBlackNode uncle = (RedBlackNode) problemChild.getParentNode().getParentNode().getRightNode();
				
				if (uncle != null && uncle.isColor(RED)) {
					/*
					 * Case 1:
					 */
					problemChild.getParentNode().setColor(BLACK);
					uncle.setColor(BLACK);
					problemChild.getParentNode().getParentNode().setColor(RED);
					problemChild = (RedBlackNode) problemChild.getParentNode().getParentNode();
					
				} else {
					
					if (problemChild.isRightChild()) {
						/*
						 * Case 2
						 */
						problemChild = (RedBlackNode) problemChild.getParentNode();
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
				RedBlackNode uncle = (RedBlackNode) problemChild.getParentNode().getParentNode().getLeftNode();
				
				if (uncle != null && uncle.isColor(RED)) {
					/*
					 * Case 1:
					 */
					problemChild.getParentNode().setColor(BLACK);
					uncle.setColor(BLACK);
					problemChild.getParentNode().getParentNode().setColor(RED);
					problemChild = (RedBlackNode) problemChild.getParentNode().getParentNode();
					
				} else {
					
					if (problemChild.isLeftChild()) {
						/*
						 * Case 2
						 */
						problemChild = (RedBlackNode) problemChild.getParentNode();
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










