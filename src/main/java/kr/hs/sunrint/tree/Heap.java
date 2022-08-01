package kr.hs.sunrint.tree;

import kr.hs.sunrint.exception.NotSupportedException;

public abstract class Heap<T> extends BinaryTree {
    protected boolean desc;

    public Heap(Node rootNode, boolean desc) {
        super(rootNode);
        this.desc = desc;
    }

    protected boolean insertNode(Node<T> node) {
        appendLeafNode(node);
        swapUntilOk(node);
        return true;
    }

    protected Node<T> removeRootNode() {
        Node<T> root = rootNode;

        replaceRootNode();
        swapUntilOkRemove(rootNode);

        return root;
    }

    protected abstract void appendLeafNode(Node<T> node);
    protected abstract void swapUntilOk(Node<T> node);
    protected abstract void replaceRootNode();
    protected abstract void swapUntilOkRemove(Node<T> node);
    protected abstract Node<T> getParent(Node<T> node);

    @Override
    public boolean insertLeftNode(Node parent, Node node) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public boolean insertRightNode(Node parent, Node node) throws NotSupportedException {
        throw new NotSupportedException();
    }
}
