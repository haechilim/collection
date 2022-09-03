package io.haechi.collection.graph;

import io.haechi.collection.list.LinkedList;
import io.haechi.collection.tree.TreeNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeightedGraphTest {
    private WeightedGraph<String> weightedGraph;
    private StringBuffer buffer;

    @Before
    public void setup() {
        weightedGraph = new WeightedGraph<>();

        weightedGraph.addNode(new WeightedGraphNode<>("0", null, null));

        LinkedList<String> adjacencyList1 = new LinkedList<>();
        adjacencyList1.add("0");
        LinkedList<Integer> weightList1 = new LinkedList<>();
        weightList1.add(8);
        weightedGraph.addNode(new WeightedGraphNode<>("1", adjacencyList1, weightList1));

        LinkedList<String> adjacencyList2 = new LinkedList<>();
        adjacencyList2.add("1");
        LinkedList<Integer> weightList2 = new LinkedList<>();
        weightList2.add(17);
        weightedGraph.addNode(new WeightedGraphNode<>("2", adjacencyList2, weightList2));

        LinkedList<String> adjacencyList3 = new LinkedList<>();
        adjacencyList3.add("2");
        LinkedList<Integer> weightList3 = new LinkedList<>();
        weightList3.add(27);
        weightedGraph.addNode(new WeightedGraphNode<>("3", adjacencyList3, weightList3));

        LinkedList<String> adjacencyList4 = new LinkedList<>();
        adjacencyList4.add("3");
        LinkedList<Integer> weightList4 = new LinkedList<>();
        weightList4.add(29);
        weightedGraph.addNode(new WeightedGraphNode<>("4", adjacencyList4, weightList4));

        LinkedList<String> adjacencyList5 = new LinkedList<>();
        adjacencyList5.add("0");
        adjacencyList5.add("4");
        LinkedList<Integer> weightList5 = new LinkedList<>();
        weightList5.add(15);
        weightList5.add(21);
        weightedGraph.addNode(new WeightedGraphNode<>("5", adjacencyList5, weightList5));
    }

    @Test
    public void 노드_추가() {
        buffer = new StringBuffer();

        LinkedList<String> adjacencyList6 = new LinkedList<>();
        adjacencyList6.add("1");
        adjacencyList6.add("3");
        adjacencyList6.add("4");
        LinkedList<Integer> weightList6 = new LinkedList<>();
        weightList6.add(10);
        weightList6.add(25);
        weightList6.add(23);
        weightedGraph.addNode(new WeightedGraphNode<>("6", adjacencyList6, weightList6));

        assertEquals("1 5", getDataAdjacencyList(weightedGraph.searchNodeByData("0")));
        assertEquals("0 2 6", getDataAdjacencyList(weightedGraph.searchNodeByData("1")));
        assertEquals("1 3", getDataAdjacencyList(weightedGraph.searchNodeByData("2")));
        assertEquals("2 4 6", getDataAdjacencyList(weightedGraph.searchNodeByData("3")));
        assertEquals("3 5 6", getDataAdjacencyList(weightedGraph.searchNodeByData("4")));
        assertEquals("0 4", getDataAdjacencyList(weightedGraph.searchNodeByData("5")));
        assertEquals("1 3 4", getDataAdjacencyList(weightedGraph.searchNodeByData("6")));

        assertEquals("8 15", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("0")));
        assertEquals("8 17 10", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("1")));
        assertEquals("17 27", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("2")));
        assertEquals("27 29 25", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("3")));
        assertEquals("29 21 23", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("4")));
        assertEquals("15 21", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("5")));
        assertEquals("10 25 23", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("6")));
    }

    @Test
    public void 노드_삭제() {
        buffer = new StringBuffer();

        LinkedList<String> adjacencyList6 = new LinkedList<>();
        adjacencyList6.add("1");
        adjacencyList6.add("3");
        adjacencyList6.add("4");
        LinkedList<Integer> weightList6 = new LinkedList<>();
        weightList6.add(10);
        weightList6.add(25);
        weightList6.add(23);
        weightedGraph.addNode(new WeightedGraphNode<>("6", adjacencyList6, weightList6));

        weightedGraph.removeNode(weightedGraph.searchNodeByData("4"));

        assertEquals("1 5", getDataAdjacencyList(weightedGraph.searchNodeByData("0")));
        assertEquals("0 2 6", getDataAdjacencyList(weightedGraph.searchNodeByData("1")));
        assertEquals("1 3", getDataAdjacencyList(weightedGraph.searchNodeByData("2")));
        assertEquals("2 6", getDataAdjacencyList(weightedGraph.searchNodeByData("3")));
        assertEquals("0", getDataAdjacencyList(weightedGraph.searchNodeByData("5")));
        assertEquals("1 3", getDataAdjacencyList(weightedGraph.searchNodeByData("6")));

        assertEquals("8 15", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("0")));
        assertEquals("8 17 10", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("1")));
        assertEquals("17 27", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("2")));
        assertEquals("27 25", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("3")));
        assertEquals("15", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("5")));
        assertEquals("10 25", getDataWeightList((WeightedGraphNode<String>) weightedGraph.searchNodeByData("6")));
    }

    @Test
    public void 프림_알고리즘_테스트() {
        buffer = new StringBuffer();

        LinkedList<String> adjacencyList6 = new LinkedList<>();
        adjacencyList6.add("1");
        adjacencyList6.add("3");
        adjacencyList6.add("4");
        LinkedList<Integer> weightList6 = new LinkedList<>();
        weightList6.add(10);
        weightList6.add(25);
        weightList6.add(23);
        weightedGraph.addNode(new WeightedGraphNode<>("6", adjacencyList6, weightList6));

        weightedGraph.prim().traverseLevel(visit -> addTextTree(visit));
        assertEquals("0 1 5 6 2 4 3", buffer.toString());
    }

    @Test
    public void 프림_알고리즘_테스트2() {
        buffer = new StringBuffer();

        weightedGraph = new WeightedGraph<>();

        weightedGraph.addNode(new WeightedGraphNode<>("1", null, null));

        LinkedList<String> adjacencyList2 = new LinkedList<>();
        adjacencyList2.add("1");
        LinkedList<Integer> weightList2 = new LinkedList<>();
        weightList2.add(5);
        weightedGraph.addNode(new WeightedGraphNode<>("2", adjacencyList2, weightList2));

        LinkedList<String> adjacencyList3 = new LinkedList<>();
        adjacencyList3.add("1");
        adjacencyList3.add("2");
        LinkedList<Integer> weightList3 = new LinkedList<>();
        weightList3.add(4);
        weightList3.add(2);
        weightedGraph.addNode(new WeightedGraphNode<>("3", adjacencyList3, weightList3));

        LinkedList<String> adjacencyList4 = new LinkedList<>();
        adjacencyList4.add("2");
        adjacencyList4.add("3");
        LinkedList<Integer> weightList4 = new LinkedList<>();
        weightList4.add(7);
        weightList4.add(6);
        weightedGraph.addNode(new WeightedGraphNode<>("4", adjacencyList4, weightList4));

        LinkedList<String> adjacencyList5 = new LinkedList<>();
        adjacencyList5.add("3");
        adjacencyList5.add("4");
        LinkedList<Integer> weightList5 = new LinkedList<>();
        weightList5.add(11);
        weightList5.add(3);
        weightedGraph.addNode(new WeightedGraphNode<>("5", adjacencyList5, weightList5));

        LinkedList<String> adjacencyList6 = new LinkedList<>();
        adjacencyList6.add("4");
        adjacencyList6.add("5");
        LinkedList<Integer> weightList6 = new LinkedList<>();
        weightList6.add(9);
        weightList6.add(8);
        weightedGraph.addNode(new WeightedGraphNode<>("6", adjacencyList6, weightList6));

        weightedGraph.prim().traverseLevel(visit -> addTextTree(visit));
        assertEquals("1 3 2 4 5 6", buffer.toString());
    }

    @Test
    public void 크루스칼_알고리즘_테스트() {
        buffer = new StringBuffer();

        LinkedList<String> adjacencyList6 = new LinkedList<>();
        adjacencyList6.add("1");
        adjacencyList6.add("3");
        adjacencyList6.add("4");
        LinkedList<Integer> weightList6 = new LinkedList<>();
        weightList6.add(10);
        weightList6.add(25);
        weightList6.add(23);
        weightedGraph.addNode(new WeightedGraphNode<>("6", adjacencyList6, weightList6));

        assertEquals("0 1 5 6 2 4 3", weightedGraph.kruskal().traverse());
    }

    private void addTextTree(TreeNode<String> treeNode) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(treeNode.getData());
    }

    private void addTextGraph(GraphNode<String> graphNode) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(graphNode.getData());
    }

    private String getDataAdjacencyList(GraphNode<String> graphNode) {
        String result = "";
        LinkedList<String> adjacencyList = graphNode.getAdjacencyList();

        for(int i = 0; i < adjacencyList.size(); i++) {
            String data = adjacencyList.get(i);

            if(!result.isEmpty()) result += " ";
            result += data;
        }

        return result;
    }

    private String getDataWeightList(WeightedGraphNode<String> node) {
        String result = "";
        LinkedList<Integer> weightList = node.getWeightList();

        for(int i = 0; i < weightList.size(); i++) {
            String data = weightList.get(i) + "";

            if(!result.isEmpty()) result += " ";
            result += data;
        }

        return result;
    }
}
