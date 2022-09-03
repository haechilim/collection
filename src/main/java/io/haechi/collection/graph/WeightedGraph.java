package io.haechi.collection.graph;

import io.haechi.collection.exception.NotExistElementException;
import io.haechi.collection.list.ArrayList;
import io.haechi.collection.tree.BinaryTree;
import io.haechi.collection.tree.MinimumSpanningTree;
import io.haechi.collection.tree.TreeNode;
import io.haechi.collection.list.LinkedList;
import io.haechi.collection.tree.PairNode;

public class WeightedGraph<T> extends UndirectedGraph<T> {
    private ArrayList<WeightedGraphNode<T>> availableNodes;
    private ArrayList<WeightedGraphNode<T>> usedNodes;
    private ArrayList<Integer> availableWeights;
    private ArrayList<Integer> usedWeights;
    private WeightedGraphNode<T> currentNode;

    @Override
    protected boolean addNode(GraphNode<T> graphNode) {
        if(!(graphNode instanceof WeightedGraphNode)) return false;

        updateWeightList((WeightedGraphNode<T>) graphNode);
        return super.addNode(graphNode);
    }

    @Override
    protected boolean removeNode(GraphNode<T> graphNode) {
        if(!(graphNode instanceof WeightedGraphNode)) return false;

        removeWeight((WeightedGraphNode<T>) graphNode);
        return super.removeNode(graphNode);
    }

    @Override
    protected void depthFirstSearch(VisitCallback callback) {
        super.depthFirstSearch(callback);
    }

    @Override
    protected void breadthFirstSearch(VisitCallback callback) {
        super.breadthFirstSearch(callback);
    }

    private void updateWeightList(WeightedGraphNode<T> node) {
        LinkedList<T> adjacencyList = node.adjacencyList;

        for(int i = 0; i < adjacencyList.size(); i++) {
            try {
                LinkedList<Integer> weightList = ((WeightedGraphNode<T>) searchNodeByData(adjacencyList.get(i))).getWeightList();
                if(node.getWeightList().size() > 0) weightList.add(node.getWeightList().get(i));
            } catch (NotExistElementException e) {
                continue;
            }
        }
    }

    private void removeWeight(WeightedGraphNode<T> node) {
        LinkedList<T> adjacencyList = node.adjacencyList;

        for(int i = 0;i < adjacencyList.size(); i++) {
            WeightedGraphNode<T> currentNode = (WeightedGraphNode<T>) searchNodeByData(adjacencyList.get(i));
            int weight = node.getWeightList().get(i);
            LinkedList<Integer> weightList = currentNode.getWeightList();

            weightList.remove(weightList.indexOf(weight));
        }
    }

    public BinaryTree<T> prim() {
        init();

        BinaryTree<T> binaryTree = new BinaryTree<>(new TreeNode<>(currentNode.data));

        while (usedNodes.size() != nodeList.size()) {
            LinkedList<T> adjacencyList = currentNode.getAdjacencyList();

            addAvailables(adjacencyList);
            int indexToUse = getIndexToUse();
            insertNode(binaryTree, indexToUse);
            updateLists(indexToUse);
        }

        return binaryTree;
    }

    public MinimumSpanningTree<T> kruskal() {
        ArrayList<PairNode<T>> pairNodes = getPairNodes();
        ArrayList<PairNode<T>> usedNodes = new ArrayList<>();
        usedWeights = new ArrayList<>();

        while (usedNodes.size() < size - 1) {
            PairNode<T> node = getPairNodesMinWeight(pairNodes, usedNodes);

            usedNodes.add(node);
        }

        return new MinimumSpanningTree<>(usedNodes);
    }

    private ArrayList<PairNode<T>> getPairNodes() {
        ArrayList<PairNode<T>> pairNodes = new ArrayList<>();

        depthFirstSearch(visit -> {
            WeightedGraphNode<T> node = (WeightedGraphNode<T>) visit;
            LinkedList<T> adjacencyList = node.getAdjacencyList();
            LinkedList<Integer> weightList = node.getWeightList();

            for(int i = 0; i < adjacencyList.size(); i++) {
                if(containsWeight(pairNodes, weightList.get(i))) continue;

                pairNodes.add(new PairNode<T>(node, (WeightedGraphNode<T>) searchNodeByData(adjacencyList.get(i)), weightList.get(i)));
            }
        });

        return pairNodes;
    }

    private boolean containsWeight(ArrayList<PairNode<T>> pairNodes, int weight) {
        for(int i = 0; i < pairNodes.size(); i++) {
            if(pairNodes.get(i).getWeight() == weight) return true;
        }

        return false;
    }

    private boolean containsNodes(ArrayList<PairNode<T>> pairNodes, WeightedGraphNode<T> weightedGraphNode) {
        for(int i = 0; i < pairNodes.size(); i++) {
            PairNode<T> pairNode = pairNodes.get(i);
            if(pairNode.getNode1().data == weightedGraphNode.data || pairNode.getNode2().data == weightedGraphNode.data) return true;
        }

        return false;
    }

    private PairNode<T> getPairNodesMinWeight(ArrayList<PairNode<T>> pairNodes, ArrayList<PairNode<T>> usedNodes) {
        PairNode<T> minPairNode = pairNodes.get(0);

        for(int i = 0; i < pairNodes.size(); i++) {
            PairNode<T> pairNode = pairNodes.get(i);

            if(usedWeights.contains(pairNode.getWeight())) continue;
            if(containsNodes(usedNodes, pairNode.getNode1()) && containsNodes(usedNodes, pairNode.getNode2())) continue;

            if(pairNode.getWeight() < minPairNode.getWeight()) {
                minPairNode = pairNode;
                usedWeights.add(pairNode.getWeight());
            }
        }

        return minPairNode;
    }

    private void init() {
        availableNodes = new ArrayList<>();
        availableWeights = new ArrayList<>();
        usedNodes = new ArrayList<>();
        usedWeights = new ArrayList<>();
        currentNode = (WeightedGraphNode<T>) nodeList.get(0);
        usedNodes.add(currentNode);
    }

    private void addAvailables(LinkedList<T> adjacencyList) {
        for(int i = 0; i < adjacencyList.size(); i++) {
            WeightedGraphNode<T> node = (WeightedGraphNode<T>) searchNodeByData(adjacencyList.get(i));
            int index = node.adjacencyList.indexOf(currentNode.data);

            if(usedNodes.contains(node)) continue;
            if(!availableNodes.contains(node)) availableNodes.add(node);
            availableWeights.add(node.getWeightList().get(index));
        }
    }

    private int getIndexToUse() {
        while (true) {
            int index = getIndexMinWeight();

            if(getWeightGraphNodeByIndex(index) == null) availableWeights.remove(index);
            else if(!usedNodes.contains(getWeightGraphNodeByIndex(index))) return index;
        }
    }

    private int getIndexMinWeight() {
        int minIndex = 0;

        for(int i = 0; i < availableWeights.size(); i++) {
            if(availableWeights.get(i) < availableWeights.get(minIndex)) minIndex = i;
        }

        return minIndex;
    }

    private void insertNode(BinaryTree<T> binaryTree, int index) {
        binaryTree.traverseLevel(visit -> {
            if(visit.getData() != getExNode(index).data) return;
            TreeNode<T> treeNode = new TreeNode(getWeightGraphNodeByIndex(index).data);

            if(visit.getLeft() == null) visit.setLeft(treeNode);
            else if(visit.getRight() == null) visit.setRight(treeNode);
        });
    }

    private WeightedGraphNode<T> getExNode(int index) {
        WeightedGraphNode<T> exNode = null;

        int weight = availableWeights.get(index);

        for(int i = 0; i < nodeList.size(); i++) {
            WeightedGraphNode<T> node = (WeightedGraphNode<T>) nodeList.get(i);

            if(node == getWeightGraphNodeByIndex(index)) continue;

            if(node.getWeightList().contains(weight)) exNode = node;
        }

        return exNode;
    }

    private void updateLists(int index) {
        currentNode = getWeightGraphNodeByIndex(index);
        usedNodes.add(currentNode);
        usedWeights.add(availableWeights.get(index));
        availableNodes.remove(availableNodes.indexOf(currentNode));
        availableWeights.remove(index);
    }

    private WeightedGraphNode<T> getWeightGraphNodeByIndex(int index) {
        int weight = availableWeights.get(index);

        for(int i = 0; i < availableNodes.size(); i++) {
            WeightedGraphNode<T> node = availableNodes.get(i);

            if(node.getWeightList().contains(weight)) return node;
        }

        return null;
    }
}
