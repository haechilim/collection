package io.haechi.collection.graph;

import io.haechi.collection.list.LinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UndirectedGraphTest {
    private Graph<String> undirectedGraph;
    private StringBuffer buffer;

    @Before
    public void setup() {
        undirectedGraph = new UndirectedGraph<>();

        undirectedGraph.addNode(new GraphNode<>("1", null));
    }

    @Test
    public void 노드_추가() {
        LinkedList<String> adjacencyList2 = new LinkedList<>();
        adjacencyList2.add("1");
        undirectedGraph.addNode(new GraphNode<>("2", adjacencyList2));

        LinkedList<String> adjacencyList3 = new LinkedList<>();
        adjacencyList3.add("1");
        adjacencyList3.add("2");
        undirectedGraph.addNode(new GraphNode<>("3", adjacencyList3));

        LinkedList<String> adjacencyList4 = new LinkedList<>();
        adjacencyList4.add("2");
        adjacencyList4.add("3");
        undirectedGraph.addNode(new GraphNode<>("4", adjacencyList4));


        LinkedList<String> adjacencyList5 = new LinkedList<>();
        adjacencyList5.add("3");
        adjacencyList5.add("4");
        undirectedGraph.addNode(new GraphNode<>("5", adjacencyList5));


        LinkedList<String> adjacencyList6 = new LinkedList<>();
        adjacencyList6.add("4");
        undirectedGraph.addNode(new GraphNode<>("6", adjacencyList6));

        assertEquals("2 3", getDataAll(undirectedGraph.searchNodeByData("1")));
        assertEquals("1 3 4", getDataAll(undirectedGraph.searchNodeByData("2")));
        assertEquals("1 2 4 5", getDataAll(undirectedGraph.searchNodeByData("3")));
        assertEquals("2 3 5 6", getDataAll(undirectedGraph.searchNodeByData("4")));
        assertEquals("3 4", getDataAll(undirectedGraph.searchNodeByData("5")));
        assertEquals("4", getDataAll(undirectedGraph.searchNodeByData("6")));
    }

    @Test
    public void 노드_삭제() {
        LinkedList<String> adjacencyList2 = new LinkedList<>();
        adjacencyList2.clear();
        adjacencyList2.add("1");
        undirectedGraph.addNode(new GraphNode<>("2", adjacencyList2));

        LinkedList<String> adjacencyList3 = new LinkedList<>();
        adjacencyList3.add("1");
        adjacencyList3.add("2");
        undirectedGraph.addNode(new GraphNode<>("3", adjacencyList3));

        LinkedList<String> adjacencyList4 = new LinkedList<>();
        adjacencyList4.add("2");
        adjacencyList4.add("3");
        GraphNode<String> graphNode4 = new GraphNode<>("4", adjacencyList4);
        undirectedGraph.addNode(graphNode4);


        LinkedList<String> adjacencyList5 = new LinkedList<>();
        adjacencyList5.add("3");
        adjacencyList5.add("4");
        undirectedGraph.addNode(new GraphNode<>("5", adjacencyList5));


        LinkedList<String> adjacencyList6 = new LinkedList<>();
        adjacencyList6.add("4");
        undirectedGraph.addNode(new GraphNode<>("6", adjacencyList6));

        undirectedGraph.removeNode(graphNode4);

        assertEquals("2 3", getDataAll(undirectedGraph.searchNodeByData("1")));
        assertEquals("1 3", getDataAll(undirectedGraph.searchNodeByData("2")));
        assertEquals("1 2 5", getDataAll(undirectedGraph.searchNodeByData("3")));
        assertEquals("3", getDataAll(undirectedGraph.searchNodeByData("5")));
        assertEquals("", getDataAll(undirectedGraph.searchNodeByData("6")));
    }

    @Test
    public void DFS() {
        buffer = new StringBuffer();

        LinkedList<String> adjacencyList2 = new LinkedList<>();
        adjacencyList2.add("1");
        undirectedGraph.addNode(new GraphNode<>("2", adjacencyList2));

        LinkedList<String> adjacencyList3 = new LinkedList<>();
        adjacencyList3.add("1");
        adjacencyList3.add("2");
        undirectedGraph.addNode(new GraphNode<>("3", adjacencyList3));

        LinkedList<String> adjacencyList4 = new LinkedList<>();
        adjacencyList4.add("2");
        adjacencyList4.add("3");
        GraphNode<String> graphNode4 = new GraphNode<>("4", adjacencyList4);
        undirectedGraph.addNode(graphNode4);


        LinkedList<String> adjacencyList5 = new LinkedList<>();
        adjacencyList5.add("3");
        adjacencyList5.add("4");
        undirectedGraph.addNode(new GraphNode<>("5", adjacencyList5));


        LinkedList<String> adjacencyList6 = new LinkedList<>();
        adjacencyList6.add("4");
        undirectedGraph.addNode(new GraphNode<>("6", adjacencyList6));

        undirectedGraph.depthFirstSearch(visit -> addText(visit));

        assertEquals("1 3 5 4 6 2", buffer.toString());
    }

    @Test
    public void BFS() {
        buffer = new StringBuffer();

        LinkedList<String> adjacencyList2 = new LinkedList<>();
        adjacencyList2.add("1");
        undirectedGraph.addNode(new GraphNode<>("2", adjacencyList2));

        LinkedList<String> adjacencyList3 = new LinkedList<>();
        adjacencyList3.add("1");
        adjacencyList3.add("2");
        undirectedGraph.addNode(new GraphNode<>("3", adjacencyList3));

        LinkedList<String> adjacencyList4 = new LinkedList<>();
        adjacencyList4.add("2");
        adjacencyList4.add("3");
        GraphNode<String> graphNode4 = new GraphNode<>("4", adjacencyList4);
        undirectedGraph.addNode(graphNode4);


        LinkedList<String> adjacencyList5 = new LinkedList<>();
        adjacencyList5.add("3");
        adjacencyList5.add("4");
        undirectedGraph.addNode(new GraphNode<>("5", adjacencyList5));


        LinkedList<String> adjacencyList6 = new LinkedList<>();
        adjacencyList6.add("4");
        undirectedGraph.addNode(new GraphNode<>("6", adjacencyList6));

        undirectedGraph.breadthFirstSearch(visit -> addText(visit));

        assertEquals("1 2 3 4 5 6", buffer.toString());
    }

    private void addText(GraphNode<String> graphNode) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(graphNode.getData());
    }

    private String getDataAll(GraphNode<String> graphNode) {
        String result = "";
        LinkedList<String> adjacencyList = graphNode.getAdjacencyList();

        for(int i = 0; i < adjacencyList.size(); i++) {
            String data = adjacencyList.get(i);

            if(!result.isEmpty()) result += " ";
            result += data;
        }

        return result;
    }
}
