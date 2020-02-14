public class PQKImp<P extends Comparable<P>, T> implements PQK<P, T> {
	
	private int noRecomends ;
	private int size;
	private PQNode<P,T> head;

	
	public PQKImp(int k) {
		noRecomends = k;
		head = null;
		size = 0;

	}

	
	// Return the length of the queue
	public int length() {
		return size;	
	}

		// Enqueue a new element. The queue keeps the k elements with the highest priority. In case of a tie apply FIFO.في حال التعادل 
	public void enqueue(P pr, T e) {
		
		PQNode<P,T> tmp = new PQNode<P,T>(pr, e);
		
		
		if((size == 0) || (pr.compareTo(head.priority) > 0)) {
			
			tmp.next = head;
			head = tmp;
		}
		else {
			PQNode<P,T> p = head;
			PQNode<P,T> q = null;//<=
			while((p != null) && (pr.compareTo(p.priority) <= 0) ) {
				q = p;
				p = p.next;
			}
			tmp.next = p;
			q.next = tmp;
		}
		size++;
		
		if(noRecomends == size-1 ) {
			PQNode<P,T> p = head;
			for(int i=0 ;i<noRecomends ;i++)
				p = p.next;
			p.next = null;
			size--;
		}
	
	}

		// Serve the element with the highest priority. In case of a tie apply FIFO.
	public Pair<P, T> serve(){
		PQNode<P,T> node = head;
		Pair<P,T> pqe = new Pair<P,T>(node.priority,node.data);
		head = head.next;
		size--;
		return pqe;	
	}
	
}
