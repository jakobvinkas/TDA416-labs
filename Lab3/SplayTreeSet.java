public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {
    class Node {
        protected E value;
        protected Node parent;
        protected Node left;
        protected Node right;

        public Node(E value) {
            this.value = value;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        public boolean isLeftChild() {
            return this.parent != null && this.parent.left == this;
        }

        public boolean isRightChild() {
            return this.parent != null && this.parent.right == this;
        }

        public boolean isRoot() {
            return this.parent == null;
        }

        public void setLeftChild(Node node) {
            this.left = node;
            node.parent = this;
        }

        public void setRightChild(Node node) {
            this.right = node;
            node.parent = this;
        }

        public String toString() {
            return value.toString();
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

    public String toString() {
        return toStringRecursive(root);
    }

    private String toStringRecursive(Node node) {
        if (node == null) {
            return "";
        }
        String str = node.toString() + " ";
        str += toStringRecursive(node.left);
        str += toStringRecursive(node.right);
        return str;
    }
}
