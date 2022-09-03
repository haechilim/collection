package io.haechi.collection.tree;

import io.haechi.collection.exception.NotExistElementException;
import io.haechi.collection.exception.DuplicatedTreeKeyException;

public class BinarySearchTree<T> extends BinaryTree<T> {
    public BinarySearchTree(TreeNode<T> rootTreeNode) {
        super(rootTreeNode);
    }

    public boolean insertNode(TreeNode treeNode) throws DuplicatedTreeKeyException {
        TreeNode<T> current = getRootNode();

        while (true) {
            TreeNode<T> leftTreeNode = current.getLeft();
            TreeNode<T> rightTreeNode = current.getRight();

            if(treeNode.getKey() < current.getKey()) {
                if(leftTreeNode == null) {
                    insertLeftNode(current, treeNode);
                    break;
                }
                else current = leftTreeNode;
            }
            else if(treeNode.getKey() > current.getKey()) {
                if(rightTreeNode == null) {
                    insertRightNode(current, treeNode);
                    break;
                }
                else current = rightTreeNode;
            }
            else throw new DuplicatedTreeKeyException();
        }

        return true;
    }

    private TreeNode<T> findSmallestNode(TreeNode<T> node) {
        TreeNode<T> current = node;

        while (current != null) {
            TreeNode<T> left = current.getLeft();
            if(left == null) break;
            current = left;
        }

        return current;
    }

    public boolean removeNode(int key) {
        return removeNodeByKey(key) != null;
    }

    protected TreeNode<T> removeNodeByKey(int key) {
        TreeNode<T> targetNode = searchNode(key);
        TreeNode<T> parentNode = targetNode.getParent();
        TreeNode<T> leftNode = targetNode.getLeft();
        TreeNode<T> rightNode = targetNode.getRight();
        TreeNode<T> updatedNode = null;

        if(leftNode != null && rightNode != null) {
            TreeNode<T> smallestNode = findSmallestNode(rightNode);
            updatedNode = smallestNode;

            if(smallestNode.getRight() != null && isLeftChild(smallestNode)) {
                setLeftNode(smallestNode.getParent(), smallestNode.getRight());
                updatedNode = smallestNode.getRight();
            }

            if(parentNode == null) setRootNode(smallestNode);
            else {
                if(isLeftChild(targetNode)) setLeftNode(parentNode, smallestNode);
                else setRightNode(parentNode, smallestNode);
            }

            if(leftNode != smallestNode) setLeftNode(smallestNode, leftNode);
            if(rightNode != smallestNode) setRightNode(smallestNode, rightNode);
        }
        else if(leftNode != null || rightNode != null) {
            TreeNode<T> childNode = leftNode != null ? leftNode : rightNode;

            if(parentNode == null) setRootNode(childNode);
            else {
                if(isLeftChild(targetNode)) setLeftNode(parentNode, childNode);
                else setRightNode(parentNode, childNode);
            }
        }
        else {
            if(parentNode != null) {
                if(isLeftChild(targetNode)) parentNode.setLeft(null);
                else parentNode.setRight(null);
            }
            else rootNode = null;
        }

        return updatedNode;
    }

    public TreeNode searchNode(int key) throws NotExistElementException {
        TreeNode<T> current = getRootNode();

        while (true) {
            if(key < current.getKey()) current = current.getLeft();
            else if(key > current.getKey()) current = current.getRight();
            else return current;

            if(current == null) throw new NotExistElementException();
        }
    }

    public TreeNode<T> getLeftNode(TreeNode parent) {
        return super.getLeftNode(parent);
    }

    public TreeNode<T> getRightNode(TreeNode parent) {
        return super.getRightNode(parent);
    }

    public TreeNode<T> getRootNode() {
        return super.getRootNode();
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
}
