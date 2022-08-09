package kr.hs.sunrint.tree;

import kr.hs.sunrint.exception.NotSupportedException;

public abstract class Heap<T> extends BinaryTree {
    protected boolean desc;

    public Heap(TreeNode rootTreeNode, boolean desc) {
        super(rootTreeNode);
        this.desc = desc;
    }

    protected boolean insertNode(TreeNode<T> treeNode) {
        appendLeafNode(treeNode);
        swapUntilOk(treeNode);
        return true;
    }

    protected TreeNode<T> removeRootNode() {
        TreeNode<T> root = rootTreeNode;

        replaceRootNode();
        swapUntilOkRemove(rootTreeNode);

        return root;
    }

    protected abstract void appendLeafNode(TreeNode<T> treeNode);
    protected abstract void swapUntilOk(TreeNode<T> treeNode);
    protected abstract void replaceRootNode();
    protected abstract void swapUntilOkRemove(TreeNode<T> treeNode);
    protected abstract TreeNode<T> getParent(TreeNode<T> treeNode);

    @Override
    public boolean insertLeftNode(TreeNode parent, TreeNode treeNode) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public boolean insertRightNode(TreeNode parent, TreeNode treeNode) throws NotSupportedException {
        throw new NotSupportedException();
    }
}
