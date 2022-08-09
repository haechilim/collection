package kr.hs.sunrint.graph;

import kr.hs.sunrint.exception.NotExistElementException;
import kr.hs.sunrint.list.ArrayList;

public abstract class Graph<T> {
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
