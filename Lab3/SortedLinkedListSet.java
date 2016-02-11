public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet<E>{
	class Node{
		private E value;
		protected Node next;

		public Node(E value) {
			this.next = null;
			this.value = value;
		}

		public E getValue(){
			return this.value;
		}

		public String toString() {
			return value.toString();
		}
	}

	private Node head;
	private int size;


	public SortedLinkedListSet(){
		this.size = 0;
		this.head = null;
	}

	public int size() {
		return this.size;
	}

	public boolean add2(E x) {
		return addRecursive(null, head, x);
	}

	private boolean addRecursive(Node previous, Node current, E x) {
		if (current == null) {
			// insert node last
			addAfter(previous, x);
			return true;
		}

		if (current.getValue().equals(x)) {
			return false;
		} else if (x.compareTo(current.getValue()) < 0) {
			// insert node in the middle
			addAfter(previous, x);
			return true;
		} else {
			return addRecursive(current, current.next, x);
		}
	}

	private void addAfter(Node node, E x) {
		Node inserted = new Node(x);

		// if node is null we add to head
		if (node == null) {
			inserted.next = head;
			head = inserted;
		} else {
			inserted.next = node.next;
			node.next = inserted;
		}

		size++;
	}

	public boolean add(E x) {
		if(addHelper(x)){
			size++;
			return true;
		}
		return false;
	}

	private boolean addHelper(E x){
		if(head == null){
			head = new Node(x);
			return true;
		}

		Node prev = null;
		Node current = head;
		while(x.compareTo(current.getValue()) >= 0) {
			if(x.equals(current.getValue())){
				return false;
			}

			if(current.next == null){
				Node n = new Node(x);
				current.next = n;
				return true;
			}
			prev = current;
			current = current.next;
		}

		if (prev == null) {
			head = new Node(x);
			head.next = current;

			return true;
		} else {
			Node n = new Node(x);
			prev.next = n;
			n.next = current;
			return true;
		}
	}

	public boolean remove(E x) {
		Node prev = null;
		Node node = head;

		while (node != null) {
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

		return false;
	}

	public boolean contains(E x) {
		Node node = head;
		while (node != null) {
			if (node.getValue().equals(x)) {
				return true;
			}
			node = node.next;
		}
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
