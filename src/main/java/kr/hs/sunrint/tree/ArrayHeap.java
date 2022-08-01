package kr.hs.sunrint.tree;

import kr.hs.sunrint.list.ArrayList;

public class ArrayHeap<T> extends Heap<T> {
    private ArrayList<Node<T>> arrayList = new ArrayList<>();

    public ArrayHeap(Node<T> rootNode, boolean desc) {
        super(rootNode, desc);
        arrayList.add(null);
        arrayList.add(rootNode);
    }

    @Override
    protected void appendLeafNode(Node<T> node) {
        arrayList.add(node);
    }

    @Override
    protected void swapUntilOk(Node<T> node) {
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
    }

    @Override
    protected void replaceRootNode() {
        if(arrayList.size() < 2) return;

        Node<T> data = arrayList.get(arrayList.size() - 1);
        arrayList.remove(arrayList.size() - 1);
        arrayList.set(1, data);
        rootNode = data;
    }

    @Override
    protected void swapUntilOkRemove(Node<T> node) {
        int index;

        while (true) {
            index = arrayList.indexOf(node);
            int leftIndex = index * 2;
            int rightIndex = index * 2 + 1;

            Node<T> leftData = leftIndex < arrayList.size() ? arrayList.get(leftIndex) : null;
            Node<T> rightData = rightIndex < arrayList.size() ? arrayList.get(rightIndex) : null;

            if(leftData == null && rightData == null) break;

            if(desc) {
                if(leftData != null && node.getKey() > leftData.getKey()) leftData = null;
                if(rightData != null && node.getKey() > rightData.getKey()) rightData = null;
            }
            else {
                if(leftData != null && node.getKey() < leftData.getKey()) leftData = null;
                if(rightData != null && node.getKey() < rightData.getKey()) rightData = null;
            }

            if(leftData == null && rightData != null) swap(node, rightData);
            else if(rightData == null && leftData != null) swap(node, leftData);
            else if(leftData != null && rightData != null) swap(node, (!desc && leftData.getKey() < rightData.getKey()) || (desc && leftData.getKey() > rightData.getKey()) ? leftData : rightData);
            else break;
        }
    }

    @Override
    protected Node<T> getParent(Node<T> node) {
        return arrayList.get(arrayList.indexOf(node) / 2);
    }

    private void swap(Node<T> data1, Node<T> data2) {
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
