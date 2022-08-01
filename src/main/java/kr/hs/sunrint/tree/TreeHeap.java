package kr.hs.sunrint.tree;

public class TreeHeap<T> extends Heap<T> {
    private boolean inserted;
    private boolean find;
    private Node<T> lastNode;

    public TreeHeap(Node rootNode, boolean desc) {
        super(rootNode, desc);
    }

    @Override
    protected void appendLeafNode(Node<T> node) {
        inserted = false;

        traverseLevel(parent -> {
            if(inserted) return;

            if(parent.getLeft() == null) parent.setLeft(node);
            else if(parent.getRight() == null) parent.setRight(node);
            else return;

            node.setParent(parent);

            inserted = true;
        });
    }

    @Override
    protected void swapUntilOk(Node<T> node) {
        Node<T> parent = node.getParent();

        if(parent == null) return;

        while ((desc && parent.getKey() < node.getKey()) || (!desc && parent.getKey() > node.getKey())) {
            swap(parent, node);
            parent = node.getParent();

            if(parent == null) return;
        }
    }

    @Override
    public void replaceRootNode() {
        find = false;

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
        });
    }

    @Override
    protected void swapUntilOkRemove(Node<T> node) {
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

    @Override
    protected Node<T> getParent(Node<T> node) {
        return node.getParent();
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
