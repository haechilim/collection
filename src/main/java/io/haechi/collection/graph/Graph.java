package io.haechi.collection.graph;

import io.haechi.collection.exception.NotExistElementException;
import io.haechi.collection.list.ArrayList;

public abstract class Graph<T> {
    protected int size = 0;
    protected ArrayList<GraphNode<T>> nodeList = new ArrayList<>();

    protected abstract boolean addNode(GraphNode<T> graphNode);
    protected abstract boolean removeNode(GraphNode<T> graphNode);
    protected abstract void depthFirstSearch(VisitCallback callback);
    protected abstract void breadthFirstSearch(VisitCallback callback);

    protected GraphNode<T> searchNodeByData(T data) throws NotExistElementException {
        for(int i = 0; i < nodeList.size(); i++) {
            GraphNode<T> graphNode = nodeList.get(i);

            if(graphNode.data == data) return graphNode;
        }

        throw new NotExistElementException();
    }

    protected interface VisitCallback {
        void action(GraphNode visit);
    }
}
