import java.util.*;

public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {
    class Node implements Comparable<Node> {
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

        public void setLeftChild(Node node) {
            this.left = node;
            if (node != null) {
                node.parent = this;
            }
        }

        public void setRightChild(Node node) {
            this.right = node;
            if (node != null) {
                node.parent = this;
            }
        }

        public void replaceChild(Node child, Node node) {
            if (child == this.left) {
                this.setLeftChild(node);
            } else if (child == this.right) {
                this.setRightChild(node);
            }
        }

        public int compareTo(Node other) {
            return this.value.compareTo(other.value);
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
        Node node = new Node(x);
        boolean added = false;

        if (root == null) {
            root = node;
            added = true;
        }

        if (addRecursive(node, root)) {
            splay(node);
            added = true;
        }

        if (added) {
            size++;
            return true;
        } else {
            return false;
        }
    }

    private boolean addRecursive(Node node, Node root) {
        int comparison = node.compareTo(root);
        if (comparison == 0) {
            return false;
        } else if (comparison < 0) {
            if (root.left == null) {
                root.setLeftChild(node);
                return true;
            } else {
                return addRecursive(node, root.left);
            }
        } else {
            if (root.right == null) {
                root.setRightChild(node);
                return true;
            } else {
                return addRecursive(node, root.right);
            }
        }
    }

    private void debug(Node node) {
        System.out.println("Root " + root);
        System.out.println("Node " + node);
        System.out.println("Parent " + node.parent);
        if (node.parent != null) {
            System.out.println("Grand parent " + node.parent.parent);
        }
        if (root != null) {
            System.out.println("Root left child " + root.left);
            System.out.println("Root right child " + root.right);
        }
        System.out.println();
    }

    private void splay(Node node) {
        if (node == root) {
    		return;
    	}

        if (node.parent == root) {
        	if (node.isLeftChild()) {
        		zig(node);
	        } else if(node.isRightChild()) {
	        	zag(node);
	        }
        } else {
       		if (node.isLeftChild() && node.parent.isLeftChild()) {
        		zigzig(node);
        	} else if (node.isRightChild() && node.parent.isRightChild()) {
	        	zagzag(node);
	        } else if (node.isRightChild() && node.parent.isLeftChild()) {
	        	zigzag(node);
	        } else if(node.isLeftChild() && node.parent.isRightChild()) {
	        	zagzig(node);
	        }
            splay(node);
       	}
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node grandParent = parent.parent;

        if (grandParent == null) {
            root = node;
            node.parent = null;
        } else {
            grandParent.replaceChild(parent, node);
        }

        parent.setLeftChild(node.right);
        node.setRightChild(parent);
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node grandParent = parent.parent;

        if (grandParent == null) {
            root = node;
            node.parent = null;
        } else {
            grandParent.replaceChild(parent, node);
        }

        parent.setRightChild(node.left);
        node.setLeftChild(parent);
    }

    private void zig(Node node) {
        this.rotateRight(node);
    }

    private void zag(Node node) {
        this.rotateLeft(node);
    }

    private void zigzig(Node node) {
    	zig(node.parent);
    	zig(node);
    }

    private void zagzag(Node node) {
    	zag(node.parent);
    	zag(node);
    }

    private void zigzag(Node node) {
    	zag(node);
    	zig(node);
    }

    private void zagzig(Node node) {
    	zig(node);
    	zag(node);
    }

    public boolean remove(E x) {
        return false;
    }

    public boolean contains(E x) {
        return false;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        toStringRecursive(root, builder);
        return builder.toString();
    }

    private void toStringRecursive(Node node, StringBuilder builder) {
        if (node == null) {
            return;
        }

        builder.append(node.toString());
        builder.append(" ");
        toStringRecursive(node.left, builder);
        toStringRecursive(node.right, builder);
    }

    // public String toString() {
    //     if (root == null) {
    //         return "";
    //     }
    //
    //     Queue<Node> queue = new LinkedList<>();
    //     queue.add(root);
    //
    //     StringBuilder builder = new StringBuilder();
    //     while (!queue.isEmpty()) {
    //         Node node = queue.remove();
    //         builder.append(node.toString() + " ");
    //         if (node.left != null) {
    //             queue.add(node.left);
    //         }
    //         if (node.right != null) {
    //             queue.add(node.right);
    //         }
    //     }
    //
    //     return builder.toString();
    // }
}
