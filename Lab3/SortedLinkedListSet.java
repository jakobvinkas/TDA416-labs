public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet<E>{
	class Node{
		public Node(E value){
			this.next = null;
			this.value = value;
		}
		private E value;
		protected Node next;

		public E getValue(){
			return this.value;
		}

	}

	private Node head;
	private int size;


	public SortedLinkedListSet(){
		this.size = 0;
		this.head = null;
	}

	int size() {
		return this.size;
	}

	boolean add2(E x) {
		return addRecursive(head, null, x);
	}

	boolean addRecursive(Node current, Node previous, E x) {
		if (current == null) {
			// insert node last
			Node node = new Node(x);
			previous.next = node;
			return true;
		}

		int compareResult = x.compareTo(current.getValue());
		if (compareResult == 0) {
			return false;
		} else if (compareResult < 0) {
			// insert node in the middle
			Node node = new Node(x);
			node.next = current;
			if (previous != null) {
				previous.next = node;
			}
			return true;
		} else {
			return addRecursive(current.next, current, x);
		}
	}

	boolean add(E x) {
		if(head == null){
			head = new Node(x);
			size++;
			return true;
		}

		Node prev;
		Node current = head;
		while(x.compareTo(current.getValue()) > 0) {
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
	boolean remove(E x) {
		return false;
	}
	boolean contains(E x) {
		return false;
	}


}