package kr.hs.sunrint.graph;

import kr.hs.sunrint.exception.NotExistElementException;
import kr.hs.sunrint.list.ArrayList;
import kr.hs.sunrint.list.LinkedList;
import kr.hs.sunrint.list.Queue;
import kr.hs.sunrint.list.Stack;

public class UndirectedGraph<T> extends Graph<T> {
    @Override
    protected boolean addNode(Node<T> node) {
        updateAdjacencyLists(node);
        nodeList.add(node);
        return true;
    }

    @Override
    protected boolean removeNode(Node<T> node) {
        T data = node.getData();
        int index = 0;

        for(int i = 0; i < nodeList.size(); i++) {
            Node<T> currentNode = nodeList.get(i);
            LinkedList<T> adjacencyList = currentNode.getAdjacencyList();

            if (currentNode.data == data) index = i;
            if(adjacencyList.contains(data)) adjacencyList.remove(data);
        }

        nodeList.remove(index);

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
            Node<T> node = searchNodeByData(stack.pop());

            callback.action(node);
            LinkedList<T> adjacencyList = node.getAdjacencyList();

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
            Node<T> node = searchNodeByData(queue.dequeue());

            callback.action(node);
            LinkedList<T> adjacencyList = node.getAdjacencyList();

            for(int i = 0; i < adjacencyList.size(); i++) {
                T data = adjacencyList.get(i);
                if(arrayList.contains(data)) continue;

                queue.enqueue(data);
                arrayList.add(data);
            }
        }
    }

    private void updateAdjacencyLists(Node<T> node) {
        LinkedList<T> adjacencyList = node.getAdjacencyList();
        T data = node.getData();

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
