package kr.hs.sunrint.tree;

import kr.hs.sunrint.list.ArrayList;

public class ArrayHeap<T> extends Heap<T> {
    private ArrayList<Data<T>> arrayList = new ArrayList<>();

    public ArrayHeap(Node<T> rootNode, boolean desc) {
        super(rootNode, desc);
        arrayList.add(null);
        arrayList.add(new Data<T>(rootNode.getKey(), rootNode.getData()));
    }

    @Override
    public boolean insertNode(Node<T> node) {
        Data<T> data = new Data<>(node.getKey(), node.getData());
        arrayList.add(data);

        while (true) {
            int index = arrayList.indexOf(data);
            Data<T> parent = arrayList.get(index / 2);

            if(parent == null) continue;

            if((desc && parent.getKey() < node.getKey()) || (!desc && parent.getKey() > node.getKey())) {
                arrayList.set(index, parent);
                arrayList.set(index / 2, data);
            }
            else break;
        }

        return true;
    }

    @Override
    public Node<T> removeRootNode() {
        if(arrayList.size() < 2) return null;

        Data<T> root = arrayList.get(1);
        Data<T> data = arrayList.get(arrayList.size() - 1);
        arrayList.remove(arrayList.size() - 1);
        arrayList.set(1, data);
        int index;

        while (true) {
            index = arrayList.indexOf(data);
            int leftIndex = index * 2;
            int rightIndex = index * 2 + 1;

            Data<T> leftData = leftIndex < arrayList.size() ? arrayList.get(leftIndex) : null;
            Data<T> rightData = rightIndex < arrayList.size() ? arrayList.get(rightIndex) : null;

            if(leftData == null && rightData == null) break;

            if(desc) {
                if(leftData != null && data.getKey() > leftData.getKey()) leftData = null;
                if(rightData != null && data.getKey() > rightData.getKey()) rightData = null;
            }
            else {
                if(leftData != null && data.getKey() < leftData.getKey()) leftData = null;
                if(rightData != null && data.getKey() < rightData.getKey()) rightData = null;
            }

            if(leftData == null && rightData != null) swap(data, rightData);
            else if(rightData == null && leftData != null) swap(data, leftData);
            else if(leftData != null && rightData != null) swap(data, (!desc && leftData.getKey() < rightData.getKey()) || (desc && leftData.getKey() > rightData.getKey()) ? leftData : rightData);
            else break;
        }

        return new Node<>(root.data, root.key);
    }

    public String toStringArray() {
        String string = "";

        for(int i = 1; i < arrayList.size(); i++) {
            if(!string.isEmpty()) string += " ";

            string += arrayList.get(i).getData();
        }

        return string;
    }

    private void swap(Data<T> data1, Data<T> data2) {
        int index1 = arrayList.indexOf(data1);
        int index2 = arrayList.indexOf(data2);

        arrayList.set(index1, data2);
        arrayList.set(index2, data1);
    }

    public class Data<T> {
        private int key;
        private T data;

        public Data(int key, T data) {
            this.key = key;
            this.data = data;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "key=" + key +
                    ", data=" + data +
                    '}';
        }
    }
}
