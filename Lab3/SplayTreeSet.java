import java.util.*;

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

        /**
         * Replaces a child of this node with a new node.
         */
        */
        public void replaceChild(Node child, Node node) {
            if (child == this.left) {
                this.setLeftChild(node);
            } else if (child == this.right) {
                this.setRightChild(node);
            }
        }

        public String toString() {
            return value.toString();
        }
    }

    private Node root;
    private int size;

    public SplayTreeSet() {
        setRoot(null);
        this.size = 0;
    }

    private void setRoot(Node node) {
        this.root = node;
        if (node != null) {
            node.parent = null;
        }
    }

    public int size() {
        return this.size;
    }
    /**
     * Inserts an element to the tree if and only if the element does not
     * already exist. No duplicates are allowed.
     *
	 * @param x The element to insert
	 * @return true if the element has been added, false otherwise
     */
    public boolean add(E x) {
        Node node = new Node(x);
        boolean added = false;

        if (this.root == null) {
            setRoot(node);
            added = true;
        }

        if (addRecursive(node, this.root)) {
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

    /**
     * Using binary-search to find correct place to insert our node.
     * No duplicates allowed.
     *
     * @param node Node to be added
     * @param root Root of a given subtree.
     * @return true if the element has been added, false otherwise.
     */
    private boolean addRecursive(Node node, Node root) {
        int comparison = node.value.compareTo(root.value);
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

    /**
     * Performs splaying on a node. This is done by rotation of the node up to
     * the root and keeping the properties of a splay tree.
     *
     * @param node The node to be splayed.
     */
    private void splay(Node node) {
        if (node == root) { //We have splayed the node to the root, we are done.
    		return;
    	}

        if (node.parent == root) { //The node is one level away from the root.
        	if (node.isLeftChild()) { //If the node is the left child of the root, perform right rotation
        		zig(node);
	        } else if(node.isRightChild()) {//If the node is the right child of the root, perform left rotation
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

    /**
     * Performs right rotation on a node. This method will update the root
     * node if the node's parent is the root.
     *
     * @param node Node to be right-rotated
     */
    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node grandParent = parent.parent;

        if (grandParent == null) {//No grandparent, means that the parent is the root
            setRoot(node);
        } else {
            grandParent.replaceChild(parent, node);//Replaces node with parent
        }

        parent.setLeftChild(node.right);
        node.setRightChild(parent);
    }

    /**
     * Performs left rotation on a node. This method will update the root
     * node if the node's parent is the root.
     *
     * @param node Node to be left-rotated
     */
    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node grandParent = parent.parent;

        if (grandParent == null) {
            setRoot(node);
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

    /**
     * Removes a given element from the tree.
     *
     * @param x The element to be removed
     * @return true if the element has been removed, false otherwise.
     */
    public boolean remove(E x) {
        Node nodeToDelete = find(x);
        if (nodeToDelete != null) { //If the node was found
            Node s = nodeToDelete.left;
            Node t = nodeToDelete.right;

            if (s == null) { //No left subtree exists
                setRoot(t);
            } else if (t == null) {//No right subtree exists
                setRoot(s);
            } else {
                setRoot(s);
                findMax(s); //Find max value of left subtree. This method splays the max to the root.
                this.root.setRightChild(t);
            }

            size--;
            return true;
        }

        return false;
    }

    /**
     * Finds a given element in the tree.
     *
     * @param x Element to search for in the tree
     * @return The node that has been found, null otherwise.
     */
    private Node find(E x) {
        Node node = new Node(x);

        Node result = findRecursive(node, this.root);
        if (result != null) {
            splay(result);
            return result;
        }

        return null;
    }

    /**
     * Implements binary search
     */
    private Node findRecursive(Node node, Node root) {
        if (root == null) {
            return null;
        }

        int comparison = node.value.compareTo(root.value);

        if (comparison == 0) {
            return root;
        } else if (comparison < 0) {
            return findRecursive(node, root.left);
        } else {
            return findRecursive(node, root.right);
        }
    }

    /**
     * Finds the highest node starting from a given node. To find the highest
     * value we need to traverse through the right children of a tree.
     *
     * @param root Starting node to search a Node with highest value
     * @return Node that is max, if the tree is empty then return null.
     */
    private Node findMax(Node root) {
        if (root == null) {
            return null;
        }

        if (root.right == null) {
            splay(root);
            return root;
        } else {
            return findMax(root.right);
        }
    }

    /**
     * Checks whether or not an element exists in the tree.
     */
    public boolean contains(E x) {
        if (find(x) == null) {
            return false;
        }
        return true;
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
}
