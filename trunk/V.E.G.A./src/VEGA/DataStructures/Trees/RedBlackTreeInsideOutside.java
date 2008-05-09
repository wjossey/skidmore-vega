package VEGA.DataStructures.Trees;

import VEGA.DataStructures.Trees.BinarySearchTree;
import VEGA.Graph.Vertex.Tree.BinaryTreeNode;



/**
 * @author oconnell
 * @author Weston Jossey:  Edited on April 21, 2008
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class RedBlackTreeInsideOutside extends BinarySearchTree {
	
	
    RedBlackTreeInsideOutside(){
        super();
        setRoot(null);
    }
    
    @Override
	public void insert(Object x) {
                BinaryTreeNode newNode = new BinaryTreeNode((Comparable) x, RED);
                super.insertNode(newNode);
		fixupInsert(newNode);
	} // end insert();
	
    @Override
	public boolean delete(Object x) {
		
		boolean result = false;
		
		try {
			validateTree();
		} catch (InvalidTreeException e) { 
			System.out.println("This tree is broke");
		} // end try-catch
		
		BinaryTreeNode node = search(getRoot(), (Comparable) x);
		if (node != null) {
			
			BinaryTreeNode nodeToSplice = super.getNodeToDelete(node);
			BinaryTreeNode parent = nodeToSplice.getParentNode(); 
			BinaryTreeNode child = null; 
			
			boolean leftChild = nodeToSplice.isLeftChild();
			
			/*
			 * At least one of the children of the node to splice out 
			 * is null. 
			 * 
			 * If the right is null then the node moved up the tree is
			 * the left (even if it is null). 
			 * 
			 * If the left is null but the right is not null 
			 * then the node moved up was the right child.
			 * 
			 */
			if (nodeToSplice.getRightNode() == null) {
				child = nodeToSplice.getLeftNode();
				
			} else { 
				child = nodeToSplice.getRightNode();
				
			} // end if
			
			//Debug.println("splicing out " + nodeToSplice.getData());
			//Debug.println("nodetosplice parent =  " + nodeToSplice.getParentNode().getData());
			//Debug.println("nodetosplice recorded parent =  " + parent);
			super.spliceOutNode(nodeToSplice);
			//Debug.println("after splicing nodetosplice is ( " 
			//						+ nodeToSplice.getData() + "," + nodeToSplice.getColor() + ")");
			if (isColor(nodeToSplice, BLACK)) {
				//Debug.println("calling fixupdelete with " + parent + " "
				//						+child + " " + leftChild);
				fixupDelete(parent, child, leftChild);
			} // end if 
			numElements--;
			result = true;
			
		} // end if 
		return result;
	} // end delete()
		
	
	private boolean isColor(BinaryTreeNode node, boolean color) {
		boolean result = false; 
		if (node == null) { 
			if (color == BLACK) {
				result = true;
			} // end if 
		} else {   
			result = node.isColor(color);
		} // end if 
		return result; 
	} // end isColor()
	
	private void fixupDelete(BinaryTreeNode parent, BinaryTreeNode child, boolean leftChild) {
	
		while(parent != null && ( child == null || child.isColor(BLACK))) { 
			
			if (leftChild) {
				/*
				 * problem node is a left child 
				 */
				BinaryTreeNode sibling = parent.getRightNode();
				if (isColor(sibling, RED)) { 
					/*
					 * Case 1: child's sibling is red 
					 *    recolor the sibling and the parent. 
					 */
					//Debug.println("left case 1: child is " + child + " sibling is " + sibling);
					sibling.setColor(BLACK);
					parent.setColor(RED);
					rotateWithRightChild(parent);
					parent = child.getParentNode(); 
					sibling = parent.getRightNode();
				} // end if 
				
				if (sibling == null || (isColor(sibling.getLeftNode(), BLACK) && isColor(sibling.getRightNode(), BLACK))) {
					/*
					 * Case 2: sibling's color is black and has two black children 
					 * 
					 */
					//Debug.println("left case 2: child is " + child + " sibling is " + sibling);					
					if (sibling != null) sibling.setColor(RED);
					child = parent; 
				} else { 
					if (isColor(sibling.getRightNode(), BLACK)) {
						/*
						 * Case 3: sibling is black, sibling's left child is red
						 *    and the right one is black 
						 */
						//Debug.println("left case 3: child is " + child + " sibling is " + sibling);						
						sibling.getLeftNode().setColor(BLACK);
						sibling.setColor(RED);
						rotateWithLeftChild(sibling);
						sibling = parent.getRightNode();
					} // endif 
					
					/*
					 * Case 4: sibling is black, sibling's right child is red
					 */
					//Debug.println("left case 4: child is " + child + " sibling is " + sibling);
					sibling.setColor(parent.getColor());
					parent.setColor(BLACK);
					sibling.getRightNode().setColor(BLACK);
					rotateWithRightChild(parent);
					child = getRoot(); 
				} // end if 
				
			} else { 
				/*
				 * Symmetric case for when problem is rightChild
				 */
				/*
				 * problem node is a right child 
				 */
				BinaryTreeNode sibling = parent.getLeftNode();
				//Debug.println("Right child: parent is " + parent + " sibling is" + sibling);
				if (isColor(sibling, RED)) { 
					/*
					 * Case 1: child's sibling is red 
					 *    recolor the sibling and the parent. 
					 */
					//Debug.println("right case 1: child is " + child + " sibling is " + sibling);
					sibling.setColor(BLACK);
					parent.setColor(RED);
					rotateWithLeftChild(parent);
					sibling = parent.getLeftNode();
				} // end if 
				
				if (sibling == null || (isColor(sibling.getLeftNode(), BLACK) && isColor(sibling.getRightNode(), BLACK))) {
				
					/*
					 * Case 2: sibling's color is black and has two black children 
					 * 
					 */
					//Debug.println("right case 2: child is " + child + " sibling is " + sibling);
					/*
					 * we need to set the sibling color to red 
					 */
					if (sibling != null) sibling.setColor(RED);
					
					child = parent; 
				} else { 
					if (isColor(sibling.getLeftNode(), BLACK)) {
						/*
						 * Case 3: sibling is black, sibling's right child is red
						 *    and the left one is black 
						 */
						
						//Debug.println("right case 3: child is " + child + " sibling is " + sibling);
						sibling.getRightNode().setColor(BLACK);
						sibling.setColor(RED);
						rotateWithRightChild(sibling);
						sibling = parent.getLeftNode();
					} // endif 
					
					/*
					 * Case 4: sibling is black, sibling's left child is red
					 */
					//Debug.println("right case 4: child is " + child + " sibling is " + sibling);
					sibling.setColor(parent.getColor());
					parent.setColor(BLACK);
					sibling.getLeftNode().setColor(BLACK);
					rotateWithLeftChild(parent);
					child = getRoot(); 
				} // end if 
			} // end if
			
			/*
			 * Update the parent, child, and leftChild variables for next iteration
			 */
			if (child != null) { 
				parent = child.getParentNode();
			} else { 
				parent = null; 
			} // end if

			leftChild = false; 
			if (child != null) {
				leftChild = child.isLeftChild(); 
			} // end if 
			
		} // end while
		if (child != null) {
			child.setColor(BLACK);
		} // end if
	} // end fixupDelete()
	
	
	private void fixupInsert(BinaryTreeNode z) {
		
		//Debug.println("fixUpinsert(): called with z = (" + z.getData() + "," + z.getColor() +")");
		/*
		 * 
		 */
		while (z.getParentNode() != null && z.getParentNode().isColor(RED)) {
			
			if (z.getParentNode().isLeftChild()) {
				
				BinaryTreeNode uncle = z.getParentNode().getParentNode().getRightNode();   
				if (uncle.isColor(RED)) {
					/*
					 * Case 1: z's uncle is red
					 */
					//Debug.println("fixup insert(): left side case 1");
					z.getParentNode().setColor(BLACK);
					uncle.setColor(BLACK);
					z.getParentNode().getParentNode().setColor(RED); 
					z = z.getParentNode().getParentNode();
					
					System.out.println(this.toGraphViz("fixUpInsertFinishedCase1"));
				} else { 
					if (z.isRightChild()) {
						/*
						 * Case 2: z's uncle has color = black and z is right child 
						 */
						//Debug.println("fixup insert(): left side case 2");
						z = z.getParentNode();
						this.rotate(z,z.getRightNode());
						System.out.println(this.toGraphViz("fixUpInsertFinishedCase2"));
					} // end if
					/*
					 * Case 3: z's uncle has color = black and z is right child 
					 */
					//Debug.println("fixup insert(): left side case 3");
					z.getParentNode().setColor(BLACK);
					z.getParentNode().getParentNode().setColor(RED);
					this.rotate(z.getParentNode().getParentNode(), z.getParentNode());
					System.out.println(this.toGraphViz("fixUpInsertFinishedCase3"));
				} // end if 
			} else { 
				BinaryTreeNode uncle = z.getParentNode().getParentNode().getLeftNode();   
				if (uncle != null && uncle.isColor(RED)) {
					//Debug.println("fixup insert(): right side case 1");
					z.getParentNode().setColor(BLACK);
					uncle.setColor(BLACK);
					z.getParentNode().getParentNode().setColor(RED); 
					z = z.getParentNode().getParentNode();
					System.out.println(this.toGraphViz("fixUpInsertFinishedCase1Else"));					
				} else { 
					if (z.isLeftChild()) {
						//Debug.println("fixup insert(): right side case 2");
						z = z.getParentNode();
						this.rotate(z,z.getLeftNode());
						System.out.println(this.toGraphViz("fixUpInsertFinishedCase2Else"));
					} // end if
					//Debug.println("fixup insert(): right side case 3");
					z.getParentNode().setColor(BLACK);
					z.getParentNode().getParentNode().setColor(RED);
					this.rotate(z.getParentNode().getParentNode(), z.getParentNode());
					System.out.println(this.toGraphViz("fixUpInsertFinishedCase3Else"));
				} // end if 
			} // end if 
		} // end while() 
		getRoot().setColor(BLACK);
	} // end fixupInsert()
	
	

	public static void main(String[] args) throws InvalidTreeException {
		System.out.println("Testing RedBlack tree");
		RedBlackTreeInsideOutside t = new RedBlackTreeInsideOutside();
		t.insert(new Double(1));
		t.validateTree();
		
		t.insert(new Double(2));
		t.validateTree();
		
		t.insert(new Double(3));
		t.validateTree();
		
		t.insert(new Double(4));
		t.validateTree();
		
//		System.out.println("tree before 5 is \n" + t.toGraphViz("Before5"));
		t.insert(new Double(5));
//		System.out.println("tree after 5 is \n" + t.toGraphViz("After5"));
		t.validateTree();
		
		t.insert(new Double(6));
		t.validateTree();
		
		t.insert(new Double(7));
		t.validateTree();
		
		System.out.println("=======================================");
		System.out.println(t.toGraphViz("RedBlackTreeBefore8"));
		t.insert(new Double(8));
		System.out.println(t.toGraphViz("RedBlackTreeAfter8"));
		System.out.println("=======================================");
		
		t.validateTree();
//		t.insert("i");
//		t.insert("j");
//		t.insert("k");
		
		System.out.println(t.toGraphViz("RedBlackTree"));
		
		
		System.out.println("tree is ");
		System.out.println(t);
		Double s;
		
		 s= new Double(4);
		System.out.println("deleting " + s);
		t.delete(s);
		
		System.out.println("new tree is ");
		System.out.flush();
		System.out.println(t.toGraphViz("RedBlackTree"));
		System.out.println(t);
		
//		s= "c";
//		System.out.println("deleting " + s);
//		t.delete(s);
//		
//		System.out.println("new tree is ");
//		System.out.flush();
//		System.out.println(t.toGraphViz("RedBlackTree"));
//		System.out.println(t);
//		
//		s= "e";
//		System.out.println("deleting " + s);
//		t.delete(s);
//		
//		System.out.println("new tree is ");
//		System.out.flush();
//		System.out.println(t.toGraphViz("RedBlackTree"));
//		System.out.println(t);
//		
//		
//		s= "b";
//		System.out.println("deleting " + s);
//		t.delete(s);
//		
//		System.out.println("new tree is ");
//		System.out.flush();
//		System.out.println(t.toGraphViz("RedBlackTree"));
//		System.out.println(t);
	} // end main()
}

