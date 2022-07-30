package kr.hs.sunrint.tree;

import kr.hs.sunrint.exception.NotSupportedException;

public abstract class Heap<T> extends BinaryTree {
    protected boolean desc;

    public Heap(Node rootNode, boolean desc) {
        super(rootNode);
        this.desc = desc;
    }

    protected abstract boolean insertNode(Node<T> node);

    protected abstract Node<T> removeRootNode();

    @Override
    public boolean insertLeftNode(Node parent, Node node) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public boolean insertRightNode(Node parent, Node node) throws NotSupportedException {
        throw new NotSupportedException();
    }
}
