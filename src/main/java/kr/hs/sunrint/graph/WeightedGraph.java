package kr.hs.sunrint.graph;

import kr.hs.sunrint.exception.NotExistElementException;
import kr.hs.sunrint.list.LinkedList;

public class WeightedGraph<T> extends UndirectedGraph<T> {
    @Override
    protected boolean addNode(Node<T> node) {
        if(!(node instanceof WeightedNode)) return false;

        updateWeightList((WeightedNode<T>) node);
        return super.addNode(node);
    }

    @Override
    protected boolean removeNode(Node<T> node) {
        if(!(node instanceof WeightedNode)) return false;

        removeWeight((WeightedNode<T>) node);
        return super.removeNode(node);
    }

    @Override
    protected void depthFirstSearch(VisitCallback callback) {
        super.depthFirstSearch(callback);
    }

    @Override
    protected void breadthFirstSearch(VisitCallback callback) {
        super.breadthFirstSearch(callback);
    }

    private void updateWeightList(WeightedNode<T> node) {
        LinkedList<T> adjacencyList = node.adjacencyList;

        for(int i = 0; i < adjacencyList.size(); i++) {
            try {
                LinkedList<Integer> weightList = ((WeightedNode<T>) searchNodeByData(adjacencyList.get(i))).getWeightList();
                if(node.getWeightList().size() > 0) weightList.add(node.getWeightList().get(i));
            } catch (NotExistElementException e) {
                continue;
            }
        }
    }

    private void removeWeight(WeightedNode<T> node) {
        LinkedList<T> adjacencyList = node.adjacencyList;

        for(int i = 0;i < adjacencyList.size(); i++) {
            WeightedNode<T> currentNode = (WeightedNode<T>) searchNodeByData(adjacencyList.get(i));
            int weight = node.getWeightList().get(i);
            LinkedList<Integer> weightList = currentNode.getWeightList();

            weightList.remove(weightList.indexOf(weight));
        }
    }
}
