package vega.dataStructures.queue;

import interfaces.dataStructures.queue.Queue;

import java.util.LinkedList;

/**
 * 
 * @author w_jossey
 *
 */
public class QueueImpl<K> implements Queue<K>{
	private LinkedList<K> list;
	
	public QueueImpl(){
		list = new LinkedList<K>();
	}
	
	/*Returns true if the object did not already exist on the queue.  Returns false
	 * if the object did exist already on the queue
	 */
	public boolean enQueue(K k){
		if(!list.contains(k)){
			list.addLast(k);
			return true;
		}else{
			return false;
		}
	}
	
	public K deQueue(){
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
