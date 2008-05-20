package vega.DataStructures.Queue;

import java.util.LinkedList;

public class Queue {
	LinkedList list;
	
	public Queue(){
		list = new LinkedList();
	}
	
	/*Returns true if the object did not already exist on the queue.  Returns false
	 * if the object did exist already on the queue
	 */
	public boolean enQueue(Object o){
		if(!list.contains(o)){
			list.addLast(o);
			return true;
		}else{
			return false;
		}
	}
	
	public Object deQueue(){
		return list.removeFirst();
	}
	
	public boolean isEmpty(){
		if(list.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
}
