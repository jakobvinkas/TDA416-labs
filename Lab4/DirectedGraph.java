
import java.util.*;

public class DirectedGraph<E extends Edge> {
	private List<E>[] nodes;

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
		return null;
	}

	public Iterator<E> minimumSpanningTree() {
		return null;
	}
}
