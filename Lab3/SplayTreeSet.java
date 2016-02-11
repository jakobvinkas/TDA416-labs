public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {
    class Node {
        private E value;
        protected Node parent;
        protected Node leftChild;
        protected Node rightChild;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return this.value;
        }
    }

    Node root;
    int size;

    public SplayTreeSet() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean add(E x) {
        return false;
    }

    public boolean remove(E x) {
        return false;
    }

    public boolean contains(E x) {
        return false;
    }

    private void zig(Node node) {
        root.left = node.right;
        node.right = root;
        root = node;
    }

    private void zag(Node node) {
        root.right = node.left;
        node.left = root;
        root = node;
    }
}
