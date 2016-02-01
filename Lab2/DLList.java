/** Doubly-linked list with user access to nodes
 */
public class DLList<E extends Comparable<E>> {
    public static class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
     /** The contents of the node is public */
     public E value;
    
     protected Node prev, next;
    
     Node() {
       this(null);
     }

     Node(E value) {
       this.value = value;
       prev = next = null;
     }
    
     public Node getNext() {
       return this.next;
     }
    
     public Node getPrev() {
       return this.prev;
     }

     public int compareTo(Node<E> o) {
       return getValue().compareTo(o.getValue());
     }

     public E getValue(){
      return this.value;
     }
  }
  
  /** first and last nodes in list, null when list is empty */
  Node first, last;
  
  DLList() {
   first = last = null;
  }
  
  /** inserts an element at the beginning of the list
  * @param e   the new element value
  * @return    the node holding the added element
  */
  public Node addFirst(E e) {
      if (first == null){
        Node p = new Node(e);
        last = p;
        first = p;
        return p;
      } else {
          Node p = new Node(e);
          p.next = first;
          first.prev = p;
          first = p;
          return p;
      }
  }
  
  /** inserts an element at then end of the list
  * @param e   the new element
  * @return    the node holding the added element
  */
  public Node addLast(E e) {
     if (last == null) {
        Node p = new Node(e);
        last = p;
        first = p;
        return p;
     } else {
        Node p = new Node(e);
        p.prev = last;
        last.next = p;
        last = p;
        return p;
     }
  }
  
  /**
  * @return    the node of the list's first element, null if list is empty
  */
  public Node getFirst() {
     return first;
  }
  
  /**
  * @return    the node of the list's last element, null if list is empty
  */
  public Node getLast() {
     return last;
  }
  
  /** inserts a new element after a specified node
   * @param e   the new element
   * @param l   the node after which to insert the element, must be non-null and a node belonging to this list
   * @return    the node holding the inserted element
   */
  public Node insertAfter(E e, Node l) {
    if (l.equals(last)) {
      return addLast(e);
    } else {
      Node p = new Node(e);
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
  public Node insertBefore(E e, Node l) {
     if (l.equals(first)) { 
        return addFirst(e);
     }else {
        return insertAfter(e, l.prev);
     }
  }
  
  /** removes an element
   * @param l   then node containing the element that will be removed, must be non-null and a node belonging to this list
   */
  public void remove(Node l) {
     l.next.prev = l.prev;
     l.prev = l.next;
  }
}
