/** Doubly-linked list with user access to nodes
 */
public class DLList<E extends Comparable<E>> {
    public static class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
        private E value;

        protected Node<E> prev, next;

        Node() {
            this(null);
        }

        Node(E value) {
            this.value = value;
            prev = next = null;
        }

        public int compareTo(Node<E> o) {
            return getValue().compareTo(o.getValue());
        }

        public E getValue(){
            return this.value;
        }

        public String toString() {
            return value.toString();
        }
    }

    /** first and last nodes in list, null when list is empty */
    Node<E> first, last;

    DLList() {
        first = last = null;
    }

    /** inserts an element at the beginning of the list
     * @param e   the new element value
     * @return    the node holding the added element
     */
    public Node<E> addFirst(E e) {
        Node<E> p = new Node<>(e);

        if (first == null) {
            last = p;
            first = p;
        } else {
            p.next = first;
            first.prev = p;
            first = p;
        }

        return p;
    }

    /** inserts an element at then end of the list
     * @param e   the new element
     * @return    the node holding the added element
     */
    public Node<E> addLast(E e) {
        Node<E> p = new Node<>(e);

        if (last == null) {
            last = p;
            first = p;
        } else {
            p.prev = last;
            last.next = p;
            last = p;
        }

        return p;
    }

    /**
     * @return    the node of the list's first element, null if list is empty
     */
    public Node<E> getFirst() {
        return first;
    }

    /**
     * @return    the node of the list's last element, null if list is empty
     */
    public Node<E> getLast() {
        return last;
    }

    /** inserts a new element after a specified node
     * @param e   the new element
     * @param l   the node after which to insert the element, must be non-null and a node belonging to this list
     * @return    the node holding the inserted element
     */
    public Node<E> insertAfter(E e, Node<E> l) {
        if (l == last) {
            return addLast(e);
        } else {
            Node<E> p = new Node<>(e);
            l.next.prev = p;
            p.next = l.next;
            p.prev = l;
            l.next = p;
            return p;
        }

    }

    /** inserts a new element before a specified node
     * @param e   the new element
     * @param l   the node before which to insert the element, must be non-null and a node belonging to this list
     * @return    the node holding the inserted element
     */
    public Node<E> insertBefore(E e, Node<E> l) {
        if (l == first) { 
            return addFirst(e);
        } else {
            return insertAfter(e, l.prev);
        }
    }

    /** removes an element
     * @param l   then node containing the element that will be removed, must be non-null and a node belonging to this list
     */
    public void remove(Node<E> l) {
        if (l.next != null) {
            l.next.prev = l.prev;
        }

        if (l.prev != null) {
            l.prev.next = l.next;
        }

        if (l == first) {
            first = l.next;
        }

        if (l == last) {
            last = l.prev;
        }
    }

    @Override
    public String toString() {
        DLList.Node<E> node = first;

        StringBuilder builder = new StringBuilder("[ ");

        while (node != null) {
            builder.append(node + " ");
            node = node.next;
        }

        builder.append("]");
        return builder.toString();
    }
}
