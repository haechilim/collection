package kr.hs.sunrint.tree;

public class TreeHeap<T> extends Heap<T> {
    private boolean insert;
    private boolean find;
    private Node<T> lastNode;

    public TreeHeap(Node rootNode, boolean desc) {
        super(rootNode, desc);
    }

    public boolean insertNode(Node<T> node) {
        insert = false;

        traverseLevel(visit -> insert(visit, node));

        return true;
    }

    @Override
    public Node<T> removeRootNode() {
        return remove();
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

    private void swapNodes(Node<T> node) {
        while (true) {
            Node<T> leftNode = node.getLeft();
            Node<T> rightNode = node.getRight();

            if(leftNode == null && rightNode == null) break;

            if(desc) {
                if(leftNode != null && node.getKey() > leftNode.getKey()) leftNode = null;
                if(rightNode != null && node.getKey() > rightNode.getKey()) rightNode = null;
            }
            else {
                if(leftNode != null && node.getKey() < leftNode.getKey()) leftNode = null;
                if(rightNode != null && node.getKey() < rightNode.getKey()) rightNode = null;
            }

            if(leftNode == null && rightNode != null) swap(node, rightNode);
            else if(rightNode == null && leftNode != null) swap(node, leftNode);
            else if(leftNode != null && rightNode != null) swap(node, (!desc && leftNode.getKey() < rightNode.getKey()) || (desc && leftNode.getKey() > rightNode.getKey()) ? leftNode : rightNode);
            else break;
        }
    }
}
