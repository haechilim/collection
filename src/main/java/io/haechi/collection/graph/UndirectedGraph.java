package io.haechi.collection.graph;

import io.haechi.collection.exception.NotExistElementException;
import io.haechi.collection.list.ArrayList;
import io.haechi.collection.list.Queue;
import io.haechi.collection.list.Stack;
import io.haechi.collection.list.LinkedList;

public class UndirectedGraph<T> extends Graph<T> {
    @Override
    protected boolean addNode(GraphNode<T> graphNode) {
        updateAdjacencyLists(graphNode);
        nodeList.add(graphNode);
        size++;
        return true;
    }

    @Override
    protected boolean removeNode(GraphNode<T> graphNode) {
        T data = graphNode.getData();
        int index = 0;

        for(int i = 0; i < nodeList.size(); i++) {
            GraphNode<T> currentGraphNode = nodeList.get(i);
            LinkedList<T> adjacencyList = currentGraphNode.getAdjacencyList();

            if (currentGraphNode.data == data) index = i;
            if(adjacencyList.contains(data)) adjacencyList.remove(data);
        }

        nodeList.remove(index);

        size--;

        return true;
    }

    @Override
    protected void depthFirstSearch(VisitCallback callback) {
        Stack<T> stack = new Stack<>();
        ArrayList<T> arrayList = new ArrayList<>();

        T firstData = nodeList.get(0).getData();

        stack.push(firstData);
        arrayList.add(firstData);

        while (!stack.isEmpty()) {
            GraphNode<T> graphNode = searchNodeByData(stack.pop());

            callback.action(graphNode);
            LinkedList<T> adjacencyList = graphNode.getAdjacencyList();

            for(int i = 0; i < adjacencyList.size(); i++) {
                T data = adjacencyList.get(i);
                if(arrayList.contains(data)) continue;

                stack.push(data);
                arrayList.add(data);
            }
        }
    }

    @Override
    protected void breadthFirstSearch(VisitCallback callback) {
        Queue<T> queue = new Queue<>();
        ArrayList<T> arrayList = new ArrayList<>();

        T firstData = nodeList.get(0).getData();

        queue.enqueue(firstData);
        arrayList.add(firstData);

        while (!queue.isEmpty()) {
            GraphNode<T> graphNode = searchNodeByData(queue.dequeue());

            callback.action(graphNode);
            LinkedList<T> adjacencyList = graphNode.getAdjacencyList();

            for(int i = 0; i < adjacencyList.size(); i++) {
                T data = adjacencyList.get(i);
                if(arrayList.contains(data)) continue;

                queue.enqueue(data);
                arrayList.add(data);
            }
        }
    }

    private void updateAdjacencyLists(GraphNode<T> graphNode) {
        LinkedList<T> adjacencyList = graphNode.getAdjacencyList();
        T data = graphNode.getData();

        for(int i = 0; i < adjacencyList.size(); i++) {
            try {
                LinkedList<T> linkedList = searchNodeByData(adjacencyList.get(i)).getAdjacencyList();

                if(!linkedList.contains(data)) linkedList.add(data);
            } catch (NotExistElementException e) {
                continue;
            }
        }
    }
}
