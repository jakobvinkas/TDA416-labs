
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

		public int compareTo(DijkstraNode node) {
			return Double.compare(this.distance, node.distance);
		}

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
		return null;
	}
}
