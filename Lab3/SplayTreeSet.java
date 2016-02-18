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
            if (this.left != null) {
                this.left.parent = null;
            }

            this.left = node;

            if (node != null) {
                node.parent = this;
            }
        }

        public void setRightChild(Node node) {
            if (this.right != null) {
                this.right.parent = null;
            }

            this.right = node;

            if (node != null) {
                node.parent = this;
            }
        }

        public void replaceChild(Node child, Node node) {
            if (child == left) {
                this.setLeftChild(node);
            } else if (child == right) {
                this.setRightChild(node);
            }
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
        if (root == null) {
            root = node;
            this.size++;
            return true;
        } else {
            if (addRecursive(node, root)) {
                splay(node);
                this.size++;
                return true;
            } else {
                return false;
            }
        }
    }

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

    private void splay(Node node) {

        if(node.parent == root){
        	if(node.isLeftChild()){
        		zig(node);
	        }else if(node.isRightChild()){
	        	zag(node);
	        }
        }
       	else{
       		if(node.isLeftChild() && node.parent.isLeftChild()){
        		zigzig(node);
        	}
	        else if(node.isRightChild() && node.parent.isRightChild()){
	        	zagzag(node);
	        }
	        else if(node.isRightChild() && node.parent.isLeftChild()){
	        	zigzag(node);
	        }else if(node.isLeftChild() && node.parent.isRightChild()){
	        	zagzig(node);
	        }
       	}
       
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node grandParent = parent.parent;

        parent.setLeftChild(node.right);
        node.setRightChild(parent);

        if (grandParent == null) {
            root = node;
        } else {
            grandParent.replaceChild(parent, node);
        }
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node grandParent = parent.parent;

        parent.setRightChild(node.left);
        node.setLeftChild(parent);

        if (grandParent == null) {
            root = node;
        } else {
            grandParent.replaceChild(parent, node);
        }
    }
    //RIGHT
    private void zig(Node node) {
        this.rotateRight(node);
    }
    //LEFT
    private void zag(Node node) {
        this.rotateLeft(node);
    }
    private void zigzig(Node node){
    	zig(node.parent);
    	zig(node);
    }
    private void zagzag(Node node){
    	zag(node.parent);
    	zag(node);
    }

    private void zigzag(Node node){
    	zag(node);
    	zig(node);
    }

    private void zagzig(Node node){
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
