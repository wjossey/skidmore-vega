package vega.dataStructures.heap;

public class BinomialHeapNode{

	public int degree = 0;
	public BinomialHeapNode child = null;
	public BinomialHeapNode sibling = null;
	public int data = 0;

	public BinomialHeapNode(int data) {
		this.data = data;
	}

	public void copyData(BinomialHeapNode n1) {
		this.degree = n1.degree;

		this.data = n1.data;
		this.child = n1.child;
		this.sibling = n1.sibling;
	}
	
}
