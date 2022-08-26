package kr.hs.sunrint.tree;

import kr.hs.sunrint.list.ArrayList;

public class ArrayHeap<T> extends Heap<T> {
    private ArrayList<TreeNode<T>> arrayList = new ArrayList<>();

    public ArrayHeap(TreeNode<T> rootTreeNode, boolean desc) {
        super(rootTreeNode, desc);
        arrayList.add(null);
        arrayList.add(rootTreeNode);
    }

    @Override
    protected void appendLeafNode(TreeNode<T> treeNode) {
        arrayList.add(treeNode);
    }

    @Override
    protected void swapUntilOk(TreeNode<T> treeNode) {
        while (true) {
            int index = arrayList.indexOf(treeNode);
            TreeNode<T> parent = arrayList.get(index / 2);

            if(parent == null) break;

            if((desc && parent.getKey() < treeNode.getKey()) || (!desc && parent.getKey() > treeNode.getKey())) {
                arrayList.set(index, parent);
                arrayList.set(index / 2, treeNode);
            }
            else break;
        }
    }

    @Override
    protected void replaceRootNode() {
        if(arrayList.size() < 2) return;

        TreeNode<T> data = arrayList.get(arrayList.size() - 1);
        arrayList.remove(arrayList.size() - 1);
        if(arrayList.size() > 1) {
            arrayList.set(1, data);
            rootTreeNode = data;
        }
        else rootTreeNode = null;
    }

    @Override
    protected void swapUntilOkRemove(TreeNode<T> treeNode) {
        int index;

        while (true) {
            index = arrayList.indexOf(treeNode);
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

            if(leftData == null && rightData != null) swap(treeNode, rightData);
            else if(rightData == null && leftData != null) swap(treeNode, leftData);
            else if(leftData != null && rightData != null) swap(treeNode, (!desc && leftData.getKey() < rightData.getKey()) || (desc && leftData.getKey() > rightData.getKey()) ? leftData : rightData);
            else break;
        }
    }

    @Override
    protected TreeNode<T> getParent(TreeNode<T> treeNode) {
        return arrayList.get(arrayList.indexOf(treeNode) / 2);
    }

    private void swap(TreeNode<T> data1, TreeNode<T> data2) {
        int index1 = arrayList.indexOf(data1);
        int index2 = arrayList.indexOf(data2);

        arrayList.set(index1, data2);
        arrayList.set(index2, data1);
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
