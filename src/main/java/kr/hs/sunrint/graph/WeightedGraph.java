package kr.hs.sunrint.graph;

import kr.hs.sunrint.exception.NotExistElementException;
import kr.hs.sunrint.list.ArrayList;
import kr.hs.sunrint.list.LinkedList;

public class WeightedGraph<T> extends UndirectedGraph<T> {
    private ArrayList<WeightedNode<T>> nodeList = new ArrayList<>();

    @Override
    protected boolean addNode(Node<T> node) {
        if(!(node instanceof WeightedNode)) return false;

        updateWeightList((WeightedNode<T>) node);
        return super.addNode(node);
    }

    @Override
    protected boolean removeNode(Node<T> node) {
        return false;
    }

    @Override
    protected void depthFirstSearch(VisitCallback callback) {

    }

    @Override
    protected void breadthFirstSearch(VisitCallback callback) {

    }

    private void updateWeightList(WeightedNode<T> node) {
        LinkedList<T> adjacencyList = node.adjacencyList;

        for(int i = 0; i < adjacencyList.size(); i++) {
            try {
                LinkedList<T> weightList = ((WeightedNode<T>) searchNodeByData(adjacencyList.get(i))).getWeightList();
                if(node.getWeightList().size() > 0) weightList.add(node.getWeightList().get(i));
            } catch (NotExistElementException e) {
                continue;
            }
        }
    }
}
