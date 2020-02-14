
public class LinkedList<T> implements List<T> {
	
	private Node<T> head;
	private Node<T> current;


	public LinkedList(){
	   
	   head = current = null;
	   
	   }
	//****************************************************************
	@Override
	public boolean empty(){

	   return head == null;

	   }

	//****************************************************************
	@Override
	public boolean full(){
	   
	   return false ;
	   
	   }
	   
	//****************************************************************   
	@Override
	public void findFirst(){
	   
	   current = head ;
	   
	   }
	//****************************************************************   
	@Override
	public void findNext(){
	   
	   current = current.next;
	   
	   }
	//****************************************************************   
	@Override
	public boolean last(){
	   
	   return current.next == null;
	   
	   }
	//****************************************************************   
	@Override
	public T retrieve(){
	   
	   return current.data;
	   
	   }
	//****************************************************************   
	@Override
	public void update(T e){
	   
	   current.data = e;
	   
	   }
	//****************************************************************   
	@Override
	public void insert(T e){
	   
		Node<T> tmp;
	   if(empty()){
	      current = head = new Node<T>(e);
	      }
	   else {
	         tmp = current.next;
	         current.next = new Node<T>(e);
	         current = current.next;
	         current.next = tmp;
	      }
	  
	      
	   }
	//****************************************************************   
	@Override
	public void remove(){
	      if(current == head){
	         head = head.next;
	       }  
	      else {
	    	  Node<T> tmp = head;
	            
	            while (tmp.next != current)
	               tmp = tmp.next;
	               
	            tmp.next = current.next;
	         }
	      if (current.next == null)
	            current = head;
	      else 
	            current = current.next;
	         
	         
	         
	   }
	
	// Return the number of elements in the list.
		public int size() {
			Node<T> cur = current;
			if (head == null)
				return 0;
			int count = 0 ;
			findFirst();
			while(!last()) {
				count++;
				findNext();
			}
			count++;
			current = cur;
			return count;
			
		}

		// Searches for e in the list. Current must not change.
		public boolean exists(T e) {
			Node<T> cur = current;
			boolean found = false;
			findFirst();
			while(!last()) {
				if(current.data.equals(e))
					found = true;
				findNext();
			}
			if(current.data.equals(e))
				found = true;
			current = cur;
			
			return found;
		}
	   
	
	   


}
