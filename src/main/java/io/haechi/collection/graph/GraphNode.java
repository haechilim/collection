package io.haechi.collection.graph;

import io.haechi.collection.list.LinkedList;

public class GraphNode<T> {
    protected T data;
    protected LinkedList<T> adjacencyList;

    public GraphNode(T data, LinkedList<T> adjacencyList) {
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
