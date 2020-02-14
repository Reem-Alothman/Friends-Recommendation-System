public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
		
	    public BSTNode<K, T> root; // Do not change this
	    private BSTNode<K, T> current;
	    private int size = 0;
	    //public List<T> list = new LinkedList<K,T>();
	    
	    
	    public BSTMap(){
			
			root  = null;
		}
		// Return the size of the map.
		public int size() {
			return size;
		}

		// Return true if the map is full.
		public boolean full() {
			return false;
		}
		
		private boolean findkey(K tkey) {
			
			BSTNode<K,T> p = root ,q = root;
					
			if(size == 0)
				return false;
			
			while(p != null) {
				q = p;
				if(p.key.equals(tkey)) {
					current = p;
					return true;
				}
				else if(tkey.compareTo(p.key) < 0)
					p = p.left;
				else
					p = p.right;
			}
			
			current = q;
			return false;
			
		}

		// Remove all elements from the map.
		public void clear() {
			size = 0;
			root = null;
			
		}

		// Update the data of the key k if it exists and return true. If k does not exist, the method returns false.
		public boolean update(K k, T e) {
			if(findkey(k)) {
				current.data = e;
				return true;
			}
			else return false;
		}

		// Search for element with key k and returns a pair containing true and its data if it exists. If k does not exist, the method returns false and null.
		public Pair<Boolean, T> retrieve(K k){
		
			if(findkey(k))
				return new Pair<Boolean, T>(true,current.data);
			else 
				return new Pair<Boolean, T>(false,null);
			
			
		}
		
		/*private Pair<Boolean, T> get(BSTNode<K, T> x,K k){
			if(k == null)
				return new Pair<Boolean,T>(false,null);
			int cmp = k.compareTo((K) x.key);
			if(cmp < 0) return get(x.left,k);
			else if (cmp > 0) return get(x.right,k);
			else return new Pair<Boolean,T>(true,(T)x.data);
		}*/

		// Insert a new element if does not exist and return true. If k already exists, return false.
		public boolean insert(K k, T e) {
			if(k == null)
				return false;
			if(e == null) 
				return false;
			
			BSTNode<K,T> p, q = current;
			
			if(findkey(k)) {
				current = q;
				return false;
			}
			
			p = new BSTNode<K,T>(k, e);			
			if(size == 0) {
				root = current = p;
				size++;
				return true;// key already in the BST
			}
			else {
				// current is pointing to parent of the new key
				if (k.compareTo(current.key) < 0)
					current.left = p;
				else
					current.right = p;
				
				current = p;
				size++;
				return true;
			}

				
			
		}

		// Remove the element with key k if it exists and return true. If the element does not exist return false.
		public boolean remove(K k) {
			K k1 = k;
			BSTNode<K,T> p = root , q = null;
			
			if(size == 0)
				return false;
			
			while(p != null) {
				if(k1.compareTo(p.key) < 0) {
					q = p;
					p = p.left;					
				}
				else if(k1.compareTo(p.key) > 0) {
					q = p;
					p = p.right;
				}
				else {
					
					if((p.left != null) && (p.right != null)) {
						BSTNode<K,T> min = p.right;
						q = p;
						while(min.left != null) {
							q = min;
							min = min.left;
						}
						p.key = min.key;
						p.data = min.data;
						k1 = min.key;
						p= min;
					}
					
					if(p.left != null) {
						p=p.left;
					} else 
						p=p.right;
					
					if(q == null) {
						root = p;
					} else {
						if(k1.compareTo(q.key) < 0) {
							q.left = p;
						} else 
							q.right = p;
					}
					
					current = root;
					size--;
					return true;
				}
			}
			
			return false;	
		}

		// Return the list of keys in increasing order.
		public List<K> getKeys(){
			List<K> list = new LinkedList<K>();			
			getInOrder(root,list);
			
			return list;
		}
		
		private void getInOrder(BSTNode<K,T> node ,List<K> list) {
		    if (node == null) {
		      return ;
		    }

		    getInOrder(node.left,list);
		    list.insert(node.key);
		    getInOrder(node.right,list);
		}
}









