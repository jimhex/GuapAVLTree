public class AVLTree<E> {
    private static class Node<T> {
        public T data;
        public Node<T> left;
        public Node<T> right;
        public Node<T> parent;
        public Node(T data) {
            this.data = data;
            parent = left = right = null;
        }
    }
    private Node<E> root;
    private int currentSize;
    public AVLTree() {
        root = null;
        currentSize = 0;
    }

    private int height(Node<E> node) {
        if (node == null) {
            return -1;
        }
        return Math.max(height(node.left),height(node.right)) + 1;
    }
    private Node<E> rightRotate(Node<E> node) {return null;}
    private Node<E> leftRotate(Node<E> node) {return null;}
    private Node<E> leftRightRotate(Node<E> node) {return null;}
    private Node<E> rightLeftRotate(Node<E> node) {return null;}

    private void balance(Node<E> node) {
        if (height(node.left) - height(node.right) > 1) {
            if (height(node.left.left) > height(node.left.right)) {
                node = rightRotate(node);
            } else {
                node = leftRightRotate(node);
            }
        } else {
            if (height(node.right.right) > height(node.right.left)) {
                node = leftRotate(node);
            } else {
                node = rightLeftRotate(node);
            }
        }
        if (node.parent == null) {
            root = node;
        }
    }
    private void checkBalance(Node<E> node) {
        if (Math.abs(height(node.left) - height(node.right)) > 1) {
            balance(node);
        }
        if (node.parent == null)
            return;
        checkBalance(node.parent);
    }
    private void add(Node<E> parent, Node<E> newNode) {
        if (((Comparable<E>)newNode.data).compareTo(parent.data) >= 0) {
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
                currentSize++;
                checkBalance(newNode);
            } else {
                add(parent.right, newNode);
            }
        } else {
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent;
                currentSize++;
                checkBalance(newNode);
            } else {
                add(parent.left, newNode);
            }
        }
    }
    public void add(E obj) {
        Node<E> node = new Node<E>(obj);
        if (root == null) {
            root = node;
            currentSize++;
            return;
        }
        add(root, node);
    }
}
