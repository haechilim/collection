package kr.hs.sunrint.tree;

import kr.hs.sunrint.exception.DuplicatedTreeKeyException;
import kr.hs.sunrint.exception.NotExistElementException;

public class BinarySearchTree<T> extends BinaryTree<T> {
    public BinarySearchTree(TreeNode<T> rootTreeNode) {
        super(rootTreeNode);
    }

    public boolean insertNode(TreeNode treeNode) throws DuplicatedTreeKeyException {
        TreeNode<T> current = getRootTreeNode();

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

    public boolean removeNode(int key) {
        TreeNode<T> treeNode = searchNode(key);
        TreeNode<T> leftTreeNode = treeNode.getLeft();
        TreeNode<T> rightTreeNode = treeNode.getRight();

        if(leftTreeNode != null && rightTreeNode != null) {
            TreeNode<T> current = rightTreeNode;

            while (true) {
                TreeNode<T> left = current.getLeft();
                TreeNode<T> right = current.getRight();

                if(left != null) current = left;
                else {
                    if (right != null) {
                        if(current == rightTreeNode) current.getParent().setRight(right);
                        else current.getParent().setLeft(right);
                    }

                    TreeNode<T> parentTreeNode = treeNode.getParent();

                    if(parentTreeNode == null) {
                        if(leftTreeNode != current) current.setLeft(leftTreeNode);
                        if(rightTreeNode != current) current.setRight(rightTreeNode);
                        rootTreeNode = current;
                    }
                    else {
                        if(treeNode == parentTreeNode.getLeft()) {
                            parentTreeNode.setLeft(current);
                            current.setRight(rightTreeNode);
                        }
                        else {
                            parentTreeNode.setRight(current);
                            current.setLeft(leftTreeNode);
                        }
                    }

                    break;
                }
            }
        }
        else if(leftTreeNode != null || rightTreeNode != null) {
            TreeNode<T> parentTreeNode = treeNode.getParent();
            TreeNode<T> childTreeNode = leftTreeNode != null ? leftTreeNode : rightTreeNode;

            if(parentTreeNode == null) rootTreeNode = childTreeNode;
            else {
                if(treeNode == parentTreeNode.getLeft()) parentTreeNode.setLeft(childTreeNode);
                else parentTreeNode.setRight(childTreeNode);
            }
        }
        else remove(key);

        return true;
    }

    public TreeNode searchNode(int key) throws NotExistElementException {
        TreeNode<T> current = getRootTreeNode();

        while (true) {
            if(key < current.getKey()) current = current.getLeft();
            else if(key > current.getKey()) current = current.getRight();
            else return current;

            if(current == null) {
                throw new NotExistElementException();
            }
        }
    }

    public TreeNode<T> getLeftNode(TreeNode parent) {
        return super.getLeftNode(parent);
    }

    public TreeNode<T> getRightNode(TreeNode parent) {
        return super.getRightNode(parent);
    }

    public TreeNode<T> getRootTreeNode() {
        return super.getRootTreeNode();
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

    private void remove(int key) {
        TreeNode<T> current = getRootTreeNode();
        TreeNode<T> leftTreeNode;
        TreeNode<T> rightTreeNode;

        while (true) {
            leftTreeNode = current.getLeft();
            rightTreeNode = current.getRight();

            if(leftTreeNode != null && key == leftTreeNode.getKey()) {
                current.setLeft(null);
                return;
            }
            else if(rightTreeNode != null && key == rightTreeNode.getKey()) {
                current.setRight(null);
                return;
            }
            else if(key < current.getKey()) {
                if(leftTreeNode != null) current = leftTreeNode;
                else throw new NotExistElementException();
            }
            else if(key > current.getKey()) {
                if(rightTreeNode != null) current = rightTreeNode;
                else throw new NotExistElementException();
            }
        }
    }
}
