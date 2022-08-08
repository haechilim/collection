package kr.hs.sunrint.graph;

import kr.hs.sunrint.list.LinkedList;

public class WeightedNode<T> extends Node<T> {
    private LinkedList<Integer> weightList;

    public WeightedNode(T data, LinkedList<T> adjacencyList, LinkedList<Integer> weightList) {
        super(data, adjacencyList);
        this.weightList = weightList == null ? new LinkedList<>() : weightList;
    }

    public LinkedList<Integer> getWeightList() {
        return weightList;
    }

    public void setWeightList(LinkedList<Integer> weightList) {
        this.weightList = weightList;
    }

    @Override
    public String toString() {
        return "WeightedNode{" +
                "weightList=" + weightList +
                '}';
    }
}