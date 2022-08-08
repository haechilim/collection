package kr.hs.sunrint.graph;

import kr.hs.sunrint.list.LinkedList;

public class Node<T> {
    protected T data;
    protected LinkedList<T> adjacencyList;

    public Node(T data, LinkedList<T> adjacencyList) {
        this.data = data;
        this.adjacencyList = adjacencyList == null ? new LinkedList<>() : adjacencyList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedList<T> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(LinkedList<T> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", adjacencyList=" + adjacencyList +
                '}';
    }
}
