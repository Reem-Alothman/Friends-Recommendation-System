import java.io.File;
import java.util.Scanner;

public class Recommender {

	// Return the top k recommended friends for user i using the popular nodes method. If i does not exist, return null. In case of a tie, users with the lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
		if (!g.getNodes().exists(i))
			return null;
		
		PQK<Double, K> q = new PQKImp<Double,K>(k);
		List<K> list = g.getNodes();
		
		list.findFirst();
		while(!list.last()) {
			if(list.retrieve().compareTo(i)==0 || g.neighb(i).exists(list.retrieve())) {
				list.remove();
				continue;
			}
			list.findNext();
		}
		if(list.retrieve().compareTo(i)==0 || g.neighb(i).exists(list.retrieve()))
			list.remove();
		
		list.findFirst();
		for(int j=0;j<list.size();j++) {
			K mm = list.retrieve();
			q.enqueue((double)g.deg(mm), mm);
			list.findNext();
		}
		return q;
	}
	
	// Return the top k recommended friends for user i using common neighbors method. If i does not exist, return null. In case of a tie, users with the lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
		if (!g.getNodes().exists(i))
			return null;
		List<K> list = g.getNodes();
		List<K> nighborsList = g.neighb(i);
		PQK<Double, K> q = new PQKImp<Double,K>(k);
		
		list.findFirst();
		while(!list.last()) {
			if(list.retrieve().compareTo(i)==0 || g.neighb(i).exists(list.retrieve())) {
				list.remove();
				continue;
			}
			list.findNext();
		}
		if(list.retrieve().compareTo(i)==0 || g.neighb(i).exists(list.retrieve()))
			list.remove();
		
		list.findFirst();
		// nighborsList.findFirst(); 
		double count = 0 ;
		List<K> nnodes ;
		
		for(int j=0;j<list.size();j++) {
			nnodes = g.neighb(list.retrieve());
			nnodes.findFirst();
			count = 0;
			for(int f=0;f<nnodes.size();f++) {
				if(nighborsList.exists(nnodes.retrieve()))
					count++;
				nnodes.findNext();
				
			}
			q.enqueue(count, list.retrieve());
			list.findNext();
		}
		
		return q;
		
	}

	// Read graph from file. The file is a text file where each line contains an edge. The end and start of the edge are separated by space(s) or tabs.
	public static Graph<Integer> read(String fileName) {

		try {
			Graph<Integer> g = new MGraph<Integer>();
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				int i = scanner.nextInt();
				g.addNode(i);
				int j = scanner.nextInt();
				g.addNode(j);
				g.addEdge(i, j);
			}
			scanner.close();
			return g;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
