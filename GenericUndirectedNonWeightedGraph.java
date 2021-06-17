import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GenericUndirectedNonWeightedGraph<T> {

	Map<T, List<T>> adjList;

	public GenericUndirectedNonWeightedGraph() {
		this.adjList = new HashMap<>();
	}

	public void addEdge(T u, T v) {

		List<T> neighboursOfU = this.adjList.getOrDefault(u, new ArrayList<>());
		neighboursOfU.add(v);

		List<T> neighboursOfV = this.adjList.getOrDefault(v, new ArrayList<>());
		neighboursOfV.add(u);
		this.adjList.put(v, neighboursOfV);
		this.adjList.put(u, neighboursOfU);
	}

	public void printAdjList() {
		for (Map.Entry<T, List<T>> entry : this.adjList.entrySet()) {
			System.out.println(entry.getKey() + "->" + entry.getValue());
		}
	}

	public void BFS(T src) {
		Queue<T> bfs = new LinkedList<>();
		bfs.add(src);
		Map<T, Boolean> map = new HashMap<>();
		map.put(src, true);

		while (!bfs.isEmpty()) {
			T node = bfs.remove();

			List<T> neighbours = this.adjList.get(node);
			for (T neighbour : neighbours) {
				if (!map.containsKey(neighbour)) {
					bfs.add(neighbour);
					map.put(neighbour, true);
				}
			}

			System.out.print(node + " ");
		}
	}

	public void SSSP(T src) {
		Queue<T> bfs = new LinkedList<>();
		bfs.add(src);
		Map<T, Integer> map = new HashMap<>();

		for (T key : this.adjList.keySet()) {
			map.put(key, Integer.MAX_VALUE);
		}

		map.put(src, 0);
		while (!bfs.isEmpty()) {
			T node = bfs.remove();

			List<T> neighbours = this.adjList.get(node);
			for (T neighbour : neighbours) {
				if (map.get(neighbour) == Integer.MAX_VALUE) {
					bfs.add(neighbour);
					map.put(neighbour, map.get(node) + 1);
					System.out.println(neighbour + " is at distance " + map.get(neighbour) + " from " + src);
				}
			}
		}
	}
	
	public void dfsHelper(T src, Map<T, Boolean> vis) {
		System.out.print(src + " ");
		vis.put(src, true);
		
		List<T> neighbours = this.adjList.get(src);
		for(T neighbour : neighbours) {
			if(!vis.containsKey(neighbour)) {
				dfsHelper(neighbour, vis);
			}
		}
	}
	
	public void DFS(T src) {
		Map<T, Boolean> vis = new HashMap<>();
		dfsHelper(src, vis);
	}
	
	public void numConnComponents() {
		Map<T, Boolean> vis = new HashMap<>();
		
		int count = 1;
		for(T key : this.adjList.keySet()) {
			if(!vis.containsKey(key)) {
				System.out.print("Component " + count + " nodes are -> " );
				count++;
				this.dfsHelper(key, vis);
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericUndirectedNonWeightedGraph<Integer> graph = new GenericUndirectedNonWeightedGraph<>();
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(2, 3);
		graph.addEdge(1, 2);
		graph.addEdge(0, 4);
		graph.addEdge(5, 6);
		graph.addEdge(6, 7);
		graph.addEdge(8, 9);

		graph.printAdjList();
//		graph.BFS(0);
//		graph.SSSP(0);
//		graph.DFS(0);
		graph.numConnComponents();
	}

}
