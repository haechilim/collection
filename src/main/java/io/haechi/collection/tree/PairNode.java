package io.haechi.collection.tree;

import io.haechi.collection.graph.WeightedGraphNode;

public class PairNode<T> {
    private WeightedGraphNode<T> node1;
    private WeightedGraphNode<T> node2;
    private int weight;

    public PairNode(WeightedGraphNode<T> node1, WeightedGraphNode<T> node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public WeightedGraphNode<T> getNode1() {
        return node1;
    }

    public void setNode1(WeightedGraphNode<T> node1) {
        this.node1 = node1;
    }

    public WeightedGraphNode<T> getNode2() {
        return node2;
    }

    public void setNode2(WeightedGraphNode<T> node2) {
        this.node2 = node2;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PairNodes{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", weight=" + weight +
                '}';
    }
}
