package kr.hs.sunrint.graph;

import kr.hs.sunrint.exception.NotExistElementException;
import kr.hs.sunrint.list.ArrayList;

public abstract class Graph<T> {
    protected ArrayList<Node<T>> nodeList = new ArrayList<>();

    protected abstract boolean addNode(Node<T> node);
    protected abstract boolean removeNode(Node<T> node);
    protected abstract void depthFirstSearch(VisitCallback callback);
    protected abstract void breadthFirstSearch(VisitCallback callback);

    protected Node<T> searchNodeByData(T data) throws NotExistElementException {
        for(int i = 0; i < nodeList.size(); i++) {
            Node<T> node = nodeList.get(i);

            if(node.data == data) return node;
        }

        throw new NotExistElementException();
    }

    protected interface VisitCallback {
        void action(Node visit);
    }
}
