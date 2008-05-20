package vega.DataStructures.Trees;

import vega.DataStructures.Trees.BinarySearchTreeImpl;
import vega.Graph.Vertex.Tree.BinaryTreeNode;


public class RBT extends BinarySearchTreeImpl {

	
	public void insert(Comparable key) { 
		BinaryTreeNode newNode = new BinaryTreeNode(key, RED);
		super.insertNode(newNode);
		fixUpInsert(newNode);
	} // end insert()
	
	private void fixUpInsert(BinaryTreeNode problemChild) {
		while(problemChild.getParentNode() != null && problemChild.getParentNode().isColor(RED)) {
			if (problemChild.getParentNode().isLeftChild()) { 
				BinaryTreeNode uncle = problemChild.getParentNode().getParentNode().getRightNode();
				
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
				BinaryTreeNode uncle = problemChild.getParentNode().getParentNode().getLeftNode();
				
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










