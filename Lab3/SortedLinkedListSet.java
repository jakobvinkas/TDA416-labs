public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet<E> {
	class Node {
		private E value;
		protected Node next;

		public Node(E value) {
			this.next = null;
			this.value = value;
		}

		public E getValue() {
			return this.value;
		}

		public String toString() {
			return value.toString();
		}
	}

	private Node head;
	private int size;

	public SortedLinkedListSet() {
		this.size = 0;
		this.head = null;
	}

	public int size() {
		return this.size;
	}

	public boolean add(E x) {
		if (addHelper(x)) {
			size++;
			return true;
		}
		return false;
	}

	private boolean addHelper(E x){
		Node node = new Node(x);

		// if head is null the list is empty, and we simply set the inserted
		// node as the new head
		if (head == null) {
			head = node;
			return true;
		}

		Node prev = null;
		Node current = head;

		// iterate through the list until we reach a value that is larger than x
		while (x.compareTo(current.getValue()) >= 0) {
			// if we find a value in the list that is equal to x we just return false
			if (x.equals(current.getValue())) {
				return false;
			}

			// if we have reached the end of the list we add the new node after
			// the last node
			if (current.next == null) {
				current.next = node;
				return true;
			}

			prev = current;
			current = current.next;
		}

		// if x is smaller than the first node in the list we never entered
		// the loop above, and we add x as the new head of the list. otherwise
		// we insert the node in the middle of the list by setting the previous
		// node's pointer to the new node and the new node's pointer to the
		// next node.
		if (prev == null) {
			head = node;
			head.next = current;
			return true;
		} else {
			prev.next = node;
			node.next = current;
			return true;
		}
	}

	public boolean remove(E x) {
		Node prev = null;
		Node node = head;

		// iterate through the list until we reach the end of it
		while (node != null) {
			// if the current node's value is equal to x, we remove that node
			if (node.getValue().equals(x)) {
				if (node == head) {
					head = node.next;
				} else {
					prev.next = node.next;
				}
				size--;
				return true;
			}
			prev = node;
			node = node.next;
		}

		// if the end was reached the value is not in the list
		return false;
	}

	public boolean contains(E x) {
		Node node = head;

		// iterate through the list until we reach the end of it
		while (node != null) {
			if (node.getValue().equals(x)) {
				return true;
			}
			node = node.next;
		}

		// if the end was reached the value is not in the list
		return false;
	}

	public String toString() {
		StringBuilder result = new StringBuilder("[ ");
		Node node = head;
		while (node != null) {
			result.append(node.toString() + " ");
			node = node.next;
		}
		result.append("]");
		return result.toString();
	}
}
