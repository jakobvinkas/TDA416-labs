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
		return addRecursive(head, null, x);
	}

	private boolean addRecursive(Node current, Node previous, E x) {
		if (current == null) {
			// insert node last
			add2Internal(current, previous, x);
			return true;
		}

		int compareResult = x.compareTo(current.getValue());

		if (compareResult == 0) {
			return false;
		} else if (compareResult < 0) {
			// insert node in the middle
			add2Internal(current, previous, x);
			return true;
		} else {
			return addRecursive(current.next, current, x);
		}
	}

	private void add2Internal(Node current, Node previous, E x) {
		Node node = new Node(x);
		node.next = current;

		if (previous != null) {
			previous.next = node;
		}

		if (current == head) {
			head = node;
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
			if(x.compareTo(current.getValue()) == 0){
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
		return false;
	}
	public boolean contains(E x) {
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
