package io.haechi.collection.tree;

import io.haechi.collection.list.ArrayList;
import io.haechi.collection.exception.NotSupportedException;

public class ArrayHeap<T> extends Heap<T> {
    private ArrayList<TreeNode<T>> arrayList = new ArrayList<>();

    public ArrayHeap(TreeNode<T> rootTreeNode, boolean desc) {
        super(rootTreeNode, desc);
        arrayList.add(null);
        arrayList.add(rootTreeNode);
    }

    @Override
    protected void appendLeafNode(TreeNode<T> node) {
        arrayList.add(node);
    }

    @Override
    protected void swapUntilOk(TreeNode<T> node) {
        int index = arrayList.size() - 1;

        while (true) {
            TreeNode<T> parent = arrayList.get(index / 2);

            if(parent == null) break;

            if((desc && parent.getKey() < node.getKey()) || (!desc && parent.getKey() > node.getKey())) {
                arrayList.set(index, parent);
                arrayList.set(index / 2, node);
                index = index / 2;
            }
            else break;
        }
    }

    @Override
    protected void replaceRootNode() {
        if(arrayList.size() < 2) return;

        TreeNode<T> rootNode = getRootTreeNode();
        TreeNode<T> lastNode = arrayList.remove(arrayList.size() - 1);

        if(rootNode != lastNode) arrayList.set(1, lastNode);
    }

    @Override
    protected void swapUntilOkRemove() {
        TreeNode<T> treeNode = getRootTreeNode();

        if(treeNode == null) return;

        int index = 1;

        while(true) {
            int leftIndex = index * 2;
            int rightIndex = index * 2 + 1;

            TreeNode<T> leftData = leftIndex < arrayList.size() ? arrayList.get(leftIndex) : null;
            TreeNode<T> rightData = rightIndex < arrayList.size() ? arrayList.get(rightIndex) : null;

            if(leftData == null && rightData == null) break;

            if(desc) {
                if(leftData != null && treeNode.getKey() > leftData.getKey()) leftData = null;
                if(rightData != null && treeNode.getKey() > rightData.getKey()) rightData = null;
            }
            else {
                if(leftData != null && treeNode.getKey() < leftData.getKey()) leftData = null;
                if(rightData != null && treeNode.getKey() < rightData.getKey()) rightData = null;
            }

            if(leftData == null && rightData != null) {
                swap(index, rightIndex);
                index = rightIndex;
            }
            else if(rightData == null && leftData != null) {
                swap(index, leftIndex);
                index = leftIndex;
            }
            else if(leftData != null && rightData != null) {
                int swapIndex = (!desc && leftData.getKey() < rightData.getKey()) || (desc && leftData.getKey() > rightData.getKey()) ? leftIndex : rightIndex;
                swap(index, swapIndex);
                index = swapIndex;
            }
            else break;
        }
    }

    @Override
    protected TreeNode<T> getParent(TreeNode<T> node) {
        throw new NotSupportedException();
    }

    public TreeNode<T> getRootTreeNode() {
        return arrayList.size() >= 2 ? arrayList.get(1) : null;
    }

    private void swap(int index1, int index2) {
        TreeNode<T> node1 = arrayList.get(index1);
        TreeNode<T> node2 = arrayList.get(index2);

        arrayList.set(index1, node2);
        arrayList.set(index2, node1);
    }

    public String toStringArray() {
        String string = "";

        for(int i = 1; i < arrayList.size(); i++) {
            if(!string.isEmpty()) string += " ";

            string += arrayList.get(i).getData();
        }

        return string;
    }
}