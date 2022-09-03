package io.haechi.collection.tree;

import io.haechi.collection.graph.WeightedGraphNode;
import io.haechi.collection.list.ArrayList;
import io.haechi.collection.list.Stack;

public class MinimumSpanningTree<T> {
    private ArrayList<PairNode<T>> pairNodes;

    public MinimumSpanningTree(ArrayList<PairNode<T>> pairNodes) {
        this.pairNodes = pairNodes;
    }

    public ArrayList<PairNode<T>> getPairNodes() {
        return pairNodes;
    }

    public void setPairNodes(ArrayList<PairNode<T>> pairNodes) {
        this.pairNodes = pairNodes;
    }

    public String traverse() {
        String result = " ";
        ArrayList<WeightedGraphNode<T>> usedNodes = new ArrayList<>();
        Stack<WeightedGraphNode<T>> stack = new Stack<>();
        stack.push(pairNodes.get(0).getNode1());

        while (!stack.isEmpty()) {
            WeightedGraphNode<T> node = stack.pop();
            usedNodes.add(node);

            result += node.getData() + " ";

            for(int i = 0; i < pairNodes.size(); i++) {
                PairNode<T> pairNode = pairNodes.get(i);

                if(pairNode.getNode1() == node && !usedNodes.contains(pairNode.getNode2())) stack.push(pairNode.getNode2());
                else if(pairNode.getNode2() == node && !usedNodes.contains(pairNode.getNode2())) stack.push(pairNode.getNode1());
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "SpanningTree{" +
                "pairNodes=" + pairNodes +
                '}';
    }
}
