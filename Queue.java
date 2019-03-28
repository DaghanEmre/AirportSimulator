
public class Queue<G> {
	
	private int  total;
	private int queue_Size;
	
	private Node first_Node, last_Node;
	
	private class Node{
		private G ele;
		private Node next;
	}
	
	public Queue(){
		
	}
	
	public Queue<G> enqueue(G ele){
		Node current = last_Node;
		last_Node = new Node();
		last_Node.ele = ele;
		
		if(total++ == 0)
			first_Node = last_Node;
		else
			current.next = last_Node;
		queue_Size++;
		return this;
	}
	
	public G dequeue(){
		if (total == 0)
			throw new java.util.NoSuchElementException();
		G ele = first_Node.ele;
		first_Node = first_Node.next;
		if (--total == 0)
			last_Node = null;
		queue_Size--;
		return ele;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Node tmp = first_Node;
		while(tmp != null){
			sb.append(tmp.ele).append(", ");
			tmp = tmp.next;
		}
		return sb.toString();
	}
	
	public int size(){
		return queue_Size;
	}

}
