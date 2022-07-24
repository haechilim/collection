package kr.hs.sunrint.tree;

import kr.hs.sunrint.exception.DuplicatedTreeKeyException;
import kr.hs.sunrint.exception.NotExistElementException;

public class BinarySearchTree<T> extends BinaryTree<T> {
    public BinarySearchTree(Node<T> rootNode) {
        super(rootNode);
    }

    public boolean insertNode(Node node) throws DuplicatedTreeKeyException {
        Node<T> current = getRootNode();

        while (true) {
            Node<T> leftNode = current.getLeft();
            Node<T> rightNode = current.getRight();

            if(node.getKey() < current.getKey()) {
                if(leftNode == null) {
                    insertLeftNode(current, node);
                    break;
                }
                else current = leftNode;
            }
            else if(node.getKey() > current.getKey()) {
                if(rightNode == null) {
                    insertRightNode(current, node);
                    break;
                }
                else current = rightNode;
            }
            else throw new DuplicatedTreeKeyException();
        }

        return true;
    }

    public boolean removeNode(int key) {
        Node<T> node = searchNode(key);
        Node<T> leftNode = node.getLeft();
        Node<T> rightNode = node.getRight();

        if(leftNode != null && rightNode != null) {
            Node<T> current = rightNode;

            while (true) {
                Node<T> left = current.getLeft();
                Node<T> right = current.getRight();

                if(left != null) current = left;
                else {
                    if (right != null) {
                        if(current == rightNode) current.getParent().setRight(right);
                        else current.getParent().setLeft(right);
                    }

                    Node<T> parentNode = node.getParent();

                    if(parentNode == null) {
                        if(leftNode != current) current.setLeft(leftNode);
                        if(rightNode != current) current.setRight(rightNode);
                        rootNode = current;
                    }
                    else {
                        if(node == parentNode.getLeft()) {
                            parentNode.setLeft(current);
                            current.setRight(rightNode);
                        }
                        else {
                            parentNode.setRight(current);
                            current.setLeft(leftNode);
                        }
                    }

                    break;
                }
            }
        }
        else if(leftNode != null || rightNode != null) {
            Node<T> parentNode = node.getParent();
            Node<T> childNode = leftNode != null ? leftNode : rightNode;

            if(parentNode == null) rootNode = childNode;
            else {
                if(node == parentNode.getLeft()) parentNode.setLeft(childNode);
                else parentNode.setRight(childNode);
            }
        }
        else remove(key);

        return true;
    }

    public Node searchNode(int key) throws NotExistElementException {
        Node<T> current = getRootNode();

        while (true) {
            if(key < current.getKey()) current = current.getLeft();
            else if(key > current.getKey()) current = current.getRight();
            else return current;

            if(current == null) throw new NotExistElementException();
        }
    }

    public Node<T> getLeftNode(Node parent) {
        return super.getLeftNode(parent);
    }

    public Node<T> getRightNode(Node parent) {
        return super.getRightNode(parent);
    }

    public Node<T> getRootNode() {
        return super.getRootNode();
    }

    public void traverseInorder() {
        super.traverseInorder();
    }

    public void traversePreorder() {
        super.traversePreorder();
    }

    public void traversePostorder() {
        super.traversePostorder();
    }

    public void traverseLevel() {
        super.traverseLevel();
    }

    private void remove(int key) {
        Node<T> current = getRootNode();
        Node<T> leftNode;
        Node<T> rightNode;

        while (true) {
            leftNode = current.getLeft();
            rightNode = current.getRight();

            if(leftNode != null && key == leftNode.getKey()) {
                current.setLeft(null);
                return;
            }
            else if(rightNode != null && key == rightNode.getKey()) {
                current.setRight(null);
                return;
            }
            else if(key < current.getKey()) {
                if(leftNode != null) current = leftNode;
                else throw new NotExistElementException();
            }
            else if(key > current.getKey()) {
                if(rightNode != null) current = rightNode;
                else throw new NotExistElementException();
            }
        }
    }
}
