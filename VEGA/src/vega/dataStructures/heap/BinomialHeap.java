package vega.dataStructures.heap;

/**
 * Implements min binomial heap
 */
public class BinomialHeap {
	
	public BinomialHeapNode minElement;

	public int numberOfNodes = 0;
	public int displayed = 0;

	public BinomialHeap() {
		minElement = null;
	}

	/**
	 * Displays the nodes of min binomial heap using level order traversal
	 */
	public void display() {
		if (numberOfNodes <= 0)
			System.out.println("Min Binomial Heap is empty");

		displayed = 0;
		displayLevel(minElement, 1);

	}

	/**
	 * Displays nodes at level
	 */
	private void displayLevel(BinomialHeapNode n1, int level) {
		if (displayed <= numberOfNodes) {
			BinomialHeapNode temp = n1;
			String s1;

			// To display empty nodes also ---> Set this flag as true
			boolean flag = false;

			s1 = "Level " + level + ": [";

			do {
				if (temp != null) {
					flag = true;
					s1 += temp.data;

					displayed++;
					temp = temp.sibling;

					if (temp != n1) {
						s1 += ", ";

					}
				}

			} while (temp != n1);

			s1 += "]";

			if (flag)
				System.out.println(s1);

			temp = n1;
			do {
				if (temp != null) {
					displayLevel(temp.child, level + 1);
					temp = temp.sibling;
				}
			} while (temp != n1);
		}
	}

	/**
	 * Inserts an integer data into min binomial heap
	 */
	public boolean insert(int data) {
		numberOfNodes++;
		BinomialHeapNode newElement = new BinomialHeapNode(data);

		if (newElement == null) {
			System.out
					.println("Not enough space to create a new node for min binary tree");
			return false;
		} else {
			if (minElement == null) {
				// Heap is empty

				minElement = newElement;
				newElement.sibling = newElement; // Since circular list -
													// sibling points to itself

			} else {

				BinomialHeapNode temp = minElement.sibling;
				minElement.sibling = newElement;
				newElement.sibling = temp;

				// Update the minHeapPointer if necessary
				if (minElement.data > newElement.data) {
					minElement = newElement;
				}

			}
			return true;

		}

	}

	/**
	 * Performs meld operation on min binomial heaps n1 and n2 and returns the
	 * melded heap
	 */
	public BinomialHeapNode meld(BinomialHeapNode n1, BinomialHeapNode n2) {
		BinomialHeapNode temp = n1.sibling;
		n1.sibling = n2.sibling;
		n2.sibling = temp;

		if (n1.data < n2.data) {
			return n1;
		} else {
			return n2;
		}
	}

	/**
	 * Performs the pairwise combine operation
	 */
	public BinomialHeapNode pairWiseCombine(BinomialHeapNode n1) {

		BinomialHeapNode temp = n1;

		int maxDegree = 0;
		do {
			if (maxDegree < temp.degree)
				maxDegree = temp.degree;

			temp = temp.sibling;

		} while (temp != n1);

		// MinBinHeapNode [] table = new MinBinHeapNode[maxDegree + 1];
		BinomialHeapNode[] table = new BinomialHeapNode[50];

		temp = n1;

		boolean done = false;

		// First remove pointer pointing to temp
		while (temp.sibling != n1) {
			temp = temp.sibling;
		}
		temp.sibling = null;

		temp = n1;

		if (temp == null)
			System.out.println("Something wrong");

		BinomialHeapNode nonInsertedNodes = temp;
		// temp points to elements that have not yet inserted in the table
		while (nonInsertedNodes != null) {
			temp = nonInsertedNodes;

			// Make sure that nonInsertedNodes is available in next iteration
			if (nonInsertedNodes.sibling != null) {
				BinomialHeapNode temp4 = nonInsertedNodes;
				nonInsertedNodes = nonInsertedNodes.sibling;
				temp4.sibling = null;
			} else
				nonInsertedNodes = null; // break out of main while -- we r done

			if (table[temp.degree] == null) {
				// No tree of this degree till now
				table[temp.degree] = temp;
			} else {
				// Combine two tree -- Here temp is node we want to insert and
				// temp1 is node in the tree
				while (true) {
					BinomialHeapNode temp1 = table[temp.degree];
					table[temp.degree] = null;

					if (temp1.data < temp.data) {

						if (temp1.child == null) {
							temp1.child = temp;
							temp.sibling = temp;
							temp1.degree = 1;
						} else {
							BinomialHeapNode temp3 = temp1.child.sibling;
							temp1.child.sibling = temp;
							temp.sibling = temp3;
							temp1.degree++;
						}

						temp = temp1; // Here temp is node we want to insert and
										// temp1 is node in the tree

						if (table[temp.degree] == null) {
							// No tree of this degree till now
							table[temp.degree] = temp;
							break;
						} else {
							continue;
						}
					} else {
						if (temp.child == null) {
							temp.child = temp1;
							temp1.sibling = temp1;
							temp.degree = 1;
						} else {
							BinomialHeapNode temp3 = temp.child.sibling;
							temp.child.sibling = temp1;
							temp1.sibling = temp3;
							temp.degree++;
						}

						// Here temp is node we want to insert and temp1 is node
						// in the tree

						if (table[temp.degree] == null) {
							// No tree of this degree till now
							table[temp.degree] = temp;
							break;
						} else {
							continue;
						}
					}
				}

			}
			// Now u have placed temp in the table
			// Remove the pointer pointing to temp.sibling and go ahead

		}// end of while

		// Now create a circular list of the nodes in the table
		temp = null;
		BinomialHeapNode temp1 = null; // Points to the minElement
		BinomialHeapNode temp2 = null; // This doent move
		for (int i = 0; i < 50; i++) {
			if (table[i] != null) {
				if (temp == null) {
					temp = table[i];
					temp1 = table[i];
					temp2 = table[i]; // This doesnt move
					table[i] = null;
				} else {
					if (table[i].data < temp1.data) {
						temp1 = table[i];
					}

					temp.sibling = table[i];
					temp = temp.sibling;
				}

			}
		}

		temp.sibling = temp2;

		return temp1;
	}

	/**
	 * Removes min element from min binomial heap
	 */
	public int removeMin() {
		numberOfNodes--;
		// Remove min tree
		int returnValue = minElement.data;
		BinomialHeapNode temp = minElement.child;

		if (temp != null) {
			// ie minElement is a tree

			BinomialHeapNode temp1 = temp; // Doesnt move
			BinomialHeapNode smallest = temp;

			while (temp.sibling != temp1) {
				if (temp.sibling.data < smallest.data)
					smallest = temp.sibling;
				temp = temp.sibling;
			}

			temp = smallest;

			if (minElement != minElement.sibling) {
				minElement.copyData(minElement.sibling); // Trick

				// display();

				minElement = meld(minElement, temp);

				minElement = pairWiseCombine(minElement);

			} else {
				// only one tree
				minElement = temp;
				minElement = pairWiseCombine(minElement);

			}
		} else {
			// Only top level circular list

			if (minElement.sibling != null) {
				minElement.copyData(minElement.sibling); // Trick
				minElement = pairWiseCombine(minElement);
			} else {
				System.out
						.println("Remove Min not possible bcos tree is empty");
			}

			// display();
		}

		return returnValue;
	}
}