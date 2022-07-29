package kr.hs.sunrint.tree;

import kr.hs.sunrint.list.ArrayList;

public class Heap<T> extends BinaryTree {
    private boolean desc;
    private boolean insert;
    private boolean find;
    private Node<T> lastNode;
    private ArrayList<Node<T>> arrayList;

    public Heap(Node rootNode, boolean desc) {
        super(rootNode);
        this.desc = desc;
    }

    public boolean insertNode(Node<T> node) {
        insert = false;

        traverseLevel(visit -> insert(visit, node));

        return true;
    }

    public Node<T> removeRootNode() {
        return remove();
    }

    public boolean insertNodeToArray(Node<T> node) {
        arrayList = new ArrayList<>();
        updateArray();
        arrayList.add(node);

        while (true) {
            int index = arrayList.indexOf(node);
            Node<T> parent = arrayList.get(index / 2);

            if(parent == null) continue;

            if((desc && parent.getKey() < node.getKey()) || (!desc && parent.getKey() > node.getKey())) {
                arrayList.set(index, parent);
                arrayList.set(index / 2, node);
            }
            else break;
        }

        updateTree();

        return true;
    }

    private void updateArray() {
        arrayList.add(null);
        traverseLevel(visit -> arrayList.add(visit));
    }

    private void updateTree() {
        for(int i = 1; i < arrayList.size(); i++) {
            Node<T> node = arrayList.get(i);
            int leftIndex = i * 2;
            int rightIndex = i * 2 + 1;
            Node<T> leftNode = arrayList.size() > leftIndex ? arrayList.get(leftIndex) : null;
            Node<T> rightNode = arrayList.size() > rightIndex ? arrayList.get(rightIndex) : null;

            if(i == 0) {
                rootNode = node;
                node.setParent(null);
            }

            node.setLeft(leftNode);
            node.setRight(rightNode);
            if(leftNode != null) leftNode.setParent(node);
            if(rightNode != null) rightNode.setParent(node);
        }
    }

    private Node<T> remove() {
        find = false;
        Node<T> root = rootNode;

        traverseLevel(visit -> {
            lastNode = visit;
            Node<T> node = null;

            if(find) return;

            if(visit.getLeft() != null && visit.getRight() != null) return;
            else if(visit.getLeft() == null) {
                node = lastNode.getRight();
                lastNode.setRight(null);
            }
            else if(visit.getRight() == null) {
                node = visit.getLeft();
                visit.setLeft(null);
            }

            find = true;

            node.setParent(null);
            node.setLeft(rootNode.getLeft());
            node.setRight(rootNode.getRight());
            rootNode = node;

            swapNodes(node);
        });

        return root;
    }

    private void insert(Node<T> parent, Node<T> node) {
        if(insert) return;

        if(parent.getLeft() != null && parent.getRight() != null) return;
        else if(parent.getLeft() == null) parent.setLeft(node);
        else if(parent.getRight() == null) parent.setRight(node);

        node.setParent(parent);

        insert = true;

        while ((desc && parent.getKey() < node.getKey()) || (!desc && parent.getKey() > node.getKey())) {
            swap(parent, node);
            parent = node.getParent();

            if(parent == null) return;
        }
    }

    private void swapNodes(Node<T> node) {
        while (true) {
            Node<T> leftNode = node.getLeft();
            Node<T> rightNode = node.getRight();

            if(leftNode == null && rightNode == null) break;
            else if((desc && node.getKey() > leftNode.getKey() && node.getKey() > rightNode.getKey()) ||
                    (!desc && node.getKey() < leftNode.getKey() && node.getKey() < rightNode.getKey())) break;
            else if((desc && node.getKey() < leftNode.getKey() && node.getKey() < rightNode.getKey()) ||
                    (!desc && node.getKey() > leftNode.getKey() && node.getKey() > rightNode.getKey())) {
                swap(node, leftNode.getKey() < rightNode.getKey() ? rightNode : leftNode);
            }
            else swap(node, node.getKey() < leftNode.getKey() ? leftNode : rightNode);
        }
    }

    private void swap(Node<T> parent, Node<T> node) {
        Node<T> leftNode = node.getLeft();
        Node<T> rightNode = node.getRight();
        Node<T> grandParent = parent.getParent();

        if(grandParent == null) rootNode = node;
        else if(grandParent.getRight() == parent) grandParent.setRight(node);
        else if(grandParent.getLeft() == parent) grandParent.setLeft(node);

        node.setParent(grandParent);

        if(parent.getLeft() == node) {
            Node<T> right = parent.getRight();

            node.setRight(right);
            node.setLeft(parent);

            if(right != null) right.setParent(node);
        }
        else if(parent.getRight() == node) {
            Node<T> left = parent.getLeft();

            node.setRight(parent);
            node.setLeft(left);

            if(left != null) left.setParent(node);
        }

        parent.setLeft(leftNode);
        parent.setRight(rightNode);
        parent.setParent(node);
    }
}
