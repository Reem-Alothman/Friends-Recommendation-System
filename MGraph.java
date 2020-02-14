public class MGraph<K extends Comparable<K>> implements Graph<K> {
	public Map<K, List<K>> adj; // Do not change this
//	public Map<K, Integer> Vertices = new BSTMap<K, Integer>();
	
	
	public MGraph() {
		adj = new BSTMap<K, List<K>>();
	//	List<K> neighbours = new LinkedList<K>();
		
	}
	
	// Add a node to the graph if it does not exist and return true. If the node already exists, return false.
	public boolean addNode(K i) {
		if (adj.retrieve(i).first)
			return false;
		
		adj.insert(i,new LinkedList<K>());
		//System.out.println("the node is added successfully");
		return true;
	}

		// Check if i is a node
	public boolean isNode(K i) {
		if (adj.getKeys().exists(i))
			return true;
		return false;
	}

		// Add an edge to the graph if it does not exist and return true. If i or j do not exist or the edge (i, j) already exists, return false.
	public boolean addEdge(K i, K j) {

		if( !adj.getKeys().exists(i) || !adj.getKeys().exists(j) )
			return false;

		if(isEdge(i,j))
			return false;
		
		//System.out.println("========inside add edge======");
		if(adj.retrieve(i).second==null || adj.retrieve(j).second == null)
			return false;
		
		adj.retrieve(i).second.insert(j);
		adj.retrieve(j).second.insert(i);
		//System.out.println("the edge is added successfully");
		return true;
		
		
	}

		// Check if (i, j) is an edge.
	public boolean isEdge(K i, K j) {
		try {
		if(i == null || j == null)
			return false;
		
		if(adj.retrieve(i).second.exists(j))
			return true;
		else if(adj.retrieve(j).second.exists(i))
			return true;
		
		return false;
		}
		catch(Exception e) {
			//System.out.println(e.toString());
			return false;
		}
	}

		// Return the set of neighbors of node i. If i does not exist, the method returns null.
	public List<K> neighb(K i){
		if(!adj.retrieve(i).first)
			return null;
		
		return adj.retrieve(i).second;
		
	}

		// Return the degree (the number of neighbors) of node i. If i does not exist, the method returns -1.
	public int deg(K i) {
		if(!adj.getKeys().exists(i))
			return -1;
		return adj.retrieve(i).second.size();
	}

		// Return a list containing the nodes in increasing order.
	public List<K> getNodes(){
		return adj.getKeys();
	}
}
