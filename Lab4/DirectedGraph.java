
import java.util.*;

public class DirectedGraph<E extends Edge> {
	private List<E>[] nodes;

	private class DijkstraNode implements Comparable<DijkstraNode> {
		int number;
		double distance;
		List<E> path;

		public DijkstraNode(int number, double distance, List<E> path) {
			this.number = number;
			this.distance = distance;
			this.path = path;
		}

		@Override
		public int compareTo(DijkstraNode node) {
			return Double.compare(this.distance, node.distance);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			} else if (o == null || o.getClass() != this.getClass()) {
				return false;
			}

			return ((DijkstraNode) o).number == this.number;
		}
	}

	public DirectedGraph(int noOfNodes) {
		nodes = (List<E>[]) new List[noOfNodes];
		for (int i = 0; i < noOfNodes; i++) {
			nodes[i] = new LinkedList<>();
		}
	}

	public void addEdge(E e) {
		nodes[e.getSource()].add(e);
	}

	public Iterator<E> shortestPath(int from, int to) {
		// Each boolean in the array corresponds to a node in the graph.
		// The boolean at index 0 indicates whether or not node 0 has been visited.
		boolean[] visitedNodes = new boolean[nodes.length];

		Queue<DijkstraNode> queue = new PriorityQueue<>();
		queue.add(new DijkstraNode(from, 0, new ArrayList<E>()));

		while (!queue.isEmpty()) {
			DijkstraNode node = queue.remove();
			if (!visitedNodes[node.number]) {
				if (node.number == to) {
					return node.path.iterator();
				}

				visitedNodes[node.number] = true;

				for (E edge : this.nodes[node.number]) {
					if (!visitedNodes[edge.to]) {
						double newDistance = node.distance + edge.getWeight();
						List<E> newPath = new ArrayList<>(node.path);
						newPath.add(edge);
						queue.add(new DijkstraNode(edge.to, newDistance, newPath));
					}
				}
			}
		}

		return null;
	}

	public Iterator<E> minimumSpanningTree() {
		Set<E>[] trees = (Set<E>[]) new Set[this.nodes.length];
		for (int i = 0; i < this.nodes.length; i++) {
			trees[i] = new HashSet<>();
		}

		Queue<E> queue = new PriorityQueue<>(this.nodes.length, new EdgeComparator());

		for (List<E> edges : this.nodes) {
			queue.addAll(edges);
		}

		while (!queue.isEmpty() && trees[0].size() < this.nodes.length - 1) {
			E edge = queue.remove();
			if (trees[edge.from] != trees[edge.to]) {
				merge(trees, edge.from, edge.to);
			}
			trees[edge.from].add(edge);
		}

		return trees[0].iterator();
	}

	private void merge(Set<E>[] trees, int from, int to) {
		if (trees[from].size() < trees[to].size()) {
			for (E edge : trees[from]) {
				trees[to].add(edge);
				trees[edge.from] = trees[to];
				trees[edge.to] = trees[to];
			}
		} else {
			for (E edge : trees[to]) {
				trees[from].add(edge);
				trees[edge.from] = trees[from];
				trees[edge.to] = trees[from];
			}
		}
	}

	private class EdgeComparator implements Comparator<E> {
		@Override
		public int compare(E e1, E e2) {
			return Double.compare(e1.getWeight(), e2.getWeight());
		}
	}
}
