package vega;

import java.util.Random;

import vega.algorithms.Dijkstra;
import vega.dataStructures.heap.fibHeap.FibonacciHeap;
import vega.dataStructures.trees.RedBlackTree;
import vega.graph.DiGraph;
import vega.graph.edge.DirectedEdge;
import vega.graph.vertex.Vertex;
import vega.graph.vertex.heap.FibonacciHeapNode;

public class Test {
	
	public static DiGraph<Vertex<DirectedEdge>> demoGraph(){
		DiGraph<Vertex<DirectedEdge>> g = new DiGraph<Vertex<DirectedEdge>>(6);
		
		Vertex<DirectedEdge> columbus = new Vertex<DirectedEdge>("Columbus");
		Vertex<DirectedEdge> chicago = new Vertex<DirectedEdge>("Chicago");
		Vertex<DirectedEdge> detroit = new Vertex<DirectedEdge>("Detroit");
		Vertex<DirectedEdge> newYork = new Vertex<DirectedEdge>("New York");
		Vertex<DirectedEdge> denver = new Vertex<DirectedEdge>("Denver");
		Vertex<DirectedEdge> tampa = new Vertex<DirectedEdge>("Tampa");
		
		
		
		DirectedEdge e1 = new DirectedEdge(columbus, detroit, 105);
		DirectedEdge e2 = new DirectedEdge(columbus, chicago, 59);
		DirectedEdge e3 = new DirectedEdge(chicago, detroit, 87);
		DirectedEdge e4 = new DirectedEdge(chicago, newYork, 210);
		DirectedEdge e5 = new DirectedEdge(detroit, chicago, 71);
		DirectedEdge e6 = new DirectedEdge(detroit, newYork, 49);
		DirectedEdge e7 = new DirectedEdge(detroit, denver, 107);
		DirectedEdge e8 = new DirectedEdge(newYork, chicago, 105);
		DirectedEdge e9 = new DirectedEdge(newYork, denver, 158);
		DirectedEdge e10 = new DirectedEdge(newYork, tampa, 89);
		DirectedEdge e11 = new DirectedEdge(denver, detroit, 150);
		DirectedEdge e12 = new DirectedEdge(denver, tampa, 150);
		DirectedEdge e13 = new DirectedEdge(tampa, newYork, 59);
		
		g.addEdge(e1);
		g.addEdge(e2);
		g.addEdge(e3);
		g.addEdge(e4);
		g.addEdge(e5);
		g.addEdge(e6);
		g.addEdge(e7);
		g.addEdge(e8);
		g.addEdge(e9);
		g.addEdge(e10);
		g.addEdge(e11);
		g.addEdge(e12);
		g.addEdge(e13);
		
		g.addVertex(columbus);
		g.addVertex(chicago);
		g.addVertex(detroit);
		g.addVertex(newYork);
		g.addVertex(denver);
		g.addVertex(tampa);
		
		
		return g;
	}

	public static  void main(String[] args){
		DiGraph<Vertex<DirectedEdge>> g = new DiGraph<Vertex<DirectedEdge>>();
		
		Vertex<DirectedEdge> s = new Vertex<DirectedEdge>("S");
		Vertex<DirectedEdge> t = new Vertex<DirectedEdge>("T");
		Vertex<DirectedEdge> y = new Vertex<DirectedEdge>("Y");
		Vertex<DirectedEdge> x = new Vertex<DirectedEdge>("X");
		Vertex<DirectedEdge> z = new Vertex<DirectedEdge>("Z");
		
		
		
		DirectedEdge e1 = new DirectedEdge(s, t, 10);
		DirectedEdge e2 = new DirectedEdge(s, y, 5);
		DirectedEdge e3 = new DirectedEdge(t, x, 1);
		DirectedEdge e4 = new DirectedEdge(t, y, 2);
		DirectedEdge e5 = new DirectedEdge(y, z, 2);
		DirectedEdge e6 = new DirectedEdge(y, x, 9);
		DirectedEdge e7 = new DirectedEdge(y, t, 3);
		DirectedEdge e8 = new DirectedEdge(x, z, 4);
		DirectedEdge e9 = new DirectedEdge(z, s, 7);
		DirectedEdge e10 = new DirectedEdge(z, x, 6);
		
		g.addEdge(e1);
		g.addEdge(e2);
		g.addEdge(e3);
		g.addEdge(e4);
		g.addEdge(e5);
		g.addEdge(e6);
		g.addEdge(e7);
		g.addEdge(e8);
		g.addEdge(e9);
		g.addEdge(e10);
		
		g.addVertex(s);
		g.addVertex(t);
		g.addVertex(x);
		g.addVertex(y);
		g.addVertex(z);
		

		Dijkstra dijkstraObj = new Dijkstra(g, s);
		dijkstraObj.run();
		System.out.println(dijkstraObj.printShortestPaths());
		
		//BinarySearchTree<String> tree = new BinarySearchTree<String>();
		
		RedBlackTree<String> redBlackTree = new RedBlackTree<String>();
//		redBlackTree.insert("Weston Jossey");
//		redBlackTree.insert("Debra Pilarski");
//		redBlackTree.insert("Raymond Pilarski");
//		redBlackTree.insert("Russell Jossey");
//		redBlackTree.insert("Sheree Jossey");
//		redBlackTree.insert("Breana Jossey");
//		redBlackTree.insert("Evan Patrick Wells");
//		redBlackTree.insert("Fred Wells");
//		redBlackTree.insert("Alice Dean");
//		redBlackTree.insert("Megan McDermott");
//		redBlackTree.insert("Tom O'Connell");
//		System.out.println(redBlackTree.toGraphviz("G"));
		Random rand = new Random();
		
		for(int i = 0; i< 50; i++){
			int first = 100 + rand.nextInt(899);
			int second = 10 + rand.nextInt(89);
			int third = 1000 + rand.nextInt(8999);
			String socialSecurity = Integer.toString(first) + "-" + 
			Integer.toString(second) + "-" + Integer.toString(third);
			redBlackTree.insert(socialSecurity);
		}
		
	//	System.out.println(redBlackTree.toGraphviz("G"));
		
		FibonacciHeap<Integer> heap = new FibonacciHeap<Integer>();
		Random fibRand = new Random();
		
		for(int i = 0; i< 300; i++){
			int first = 100 + fibRand.nextInt(899);
			heap.insert(new FibonacciHeapNode<Integer>(new Integer(first), first), first);
		}
		
		//System.out.println(heap.toGraphViz());
		
		heap.removeMin();
		
		System.out.println(heap.toGraphViz());
		
	}
}
