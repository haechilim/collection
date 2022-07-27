package kr.hs.sunrint.tree;

import kr.hs.sunrint.exception.DuplicatedTreeKeyException;
import kr.hs.sunrint.exception.NotExistElementException;

public class AVLTree<T> extends BinarySearchTree<T> {
    public AVLTree(Node<T> rootNode) {
        super(rootNode);
    }

    @Override
    public boolean insertNode(Node node) throws DuplicatedTreeKeyException {
        try {
            return super.insertNode(node);
        } catch (DuplicatedTreeKeyException e) {
            throw new DuplicatedTreeKeyException();
        }
    }

    @Override
    public boolean removeNode(int key) {
        return super.removeNode(key);
    }

    @Override
    public Node searchNode(int key) throws NotExistElementException {
        try {
            return super.searchNode(key);
        } catch (NotExistElementException e) {
            throw new NotExistElementException();
        }
    }

    @Override
    public void traversePreorder(VisitCallback callback) {
        super.traversePreorder(callback);
    }

    @Override
    public void traverseInorder(VisitCallback callback) {
        super.traverseInorder(callback);
    }

    @Override
    public void traversePostorder(VisitCallback callback) {
        super.traversePostorder(callback);
    }

    @Override
    public void traverseLevel(VisitCallback callback) {
        super.traverseLevel(callback);
    }

    public void balanceTree() {
        traverseLevel(visit -> balanceTree(visit));
    }

    public int getBalanceFactor(Node<T> node) {
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    public int getHeight(Node<T> node) {
        if(node == null) return 0;
        if(node.getLeft() == null && node.getRight() == null) return 1;

        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        int height = leftHeight > rightHeight ? leftHeight : rightHeight;

        return ++height;
    }

    public void balanceTree(Node<T> node) {
        int bf = getBalanceFactor(node);

        if( bf >= -1 && bf <= 1) return;

        if(bf > 1) {
            Node<T> leftNode = node.getLeft();

            if(getBalanceFactor(leftNode) < 0) leftRotate(leftNode);

            rightRotate(node);
        }
        else if(bf < -1) {
            Node<T> rightNode = node.getRight();

            if(getBalanceFactor(rightNode) > 0) rightRotate(rightNode);

            leftRotate(node);
        }
    }

    private void rightRotate(Node<T> node) {
        Node<T> parent = node.getParent();
        Node<T> leftNode = node.getLeft();
        Node<T> temp = leftNode.getRight();

        leftNode.setParent(parent);
        leftNode.setRight(node);
        node.setLeft(temp);

        if(parent == null) rootNode = leftNode;
        else parent.setRight(leftNode);
    }

    private void leftRotate(Node<T> node) {
        Node<T> parent = node.getParent();
        Node<T> rightNode = node.getRight();
        Node<T> temp = rightNode.getLeft();

        rightNode.setParent(parent);
        rightNode.setLeft(node);
        node.setRight(temp);

        if(parent == null) rootNode = rightNode;
        else parent.setLeft(rightNode);
    }
}
