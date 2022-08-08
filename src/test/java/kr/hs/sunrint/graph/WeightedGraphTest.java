package kr.hs.sunrint.graph;

import kr.hs.sunrint.list.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightedGraphTest {
    private WeightedGraph<String> weightedGraph;
    private StringBuffer buffer;

    @BeforeEach
    public void setup() {
        weightedGraph = new WeightedGraph<>();

        weightedGraph.addNode(new WeightedNode<>("0", null, null));

        LinkedList<String> adjacencyList1 = new LinkedList<>();
        adjacencyList1.add("0");
        LinkedList<String> weightList1 = new LinkedList<>();
        weightList1.add("8");
        weightedGraph.addNode(new WeightedNode<>("1", adjacencyList1, weightList1));

        LinkedList<String> adjacencyList2 = new LinkedList<>();
        adjacencyList2.add("1");
        LinkedList<String> weightList2 = new LinkedList<>();
        weightList2.add("17");
        weightedGraph.addNode(new WeightedNode<>("2", adjacencyList2, weightList2));

        LinkedList<String> adjacencyList3 = new LinkedList<>();
        adjacencyList3.add("2");
        LinkedList<String> weightList3 = new LinkedList<>();
        weightList3.add("27");
        weightedGraph.addNode(new WeightedNode<>("3", adjacencyList3, weightList3));

        LinkedList<String> adjacencyList4 = new LinkedList<>();
        adjacencyList4.add("3");
        LinkedList<String> weightList4 = new LinkedList<>();
        weightList4.add("29");
        weightedGraph.addNode(new WeightedNode<>("4", adjacencyList4, weightList4));

        LinkedList<String> adjacencyList5 = new LinkedList<>();
        adjacencyList5.add("0");
        adjacencyList5.add("4");
        LinkedList<String> weightList5 = new LinkedList<>();
        weightList3.add("15");
        weightList3.add("21");
        weightedGraph.addNode(new WeightedNode<>("5", adjacencyList5, weightList5));
    }

    @Test
    public void 노드_추가() {
        buffer = new StringBuffer();

        LinkedList<String> adjacencyList6 = new LinkedList<>();
        adjacencyList6.add("1");
        adjacencyList6.add("3");
        adjacencyList6.add("4");
        LinkedList<String> weightList6 = new LinkedList<>();
        weightList6.add("10");
        weightList6.add("25");
        weightList6.add("23");
        weightedGraph.addNode(new WeightedNode<>("6", adjacencyList6, weightList6));

        assertEquals("1 5", getDataAdjacencyList(weightedGraph.searchNodeByData("0")));
        assertEquals("0 2 6", getDataAdjacencyList(weightedGraph.searchNodeByData("1")));
        assertEquals("1 3", getDataAdjacencyList(weightedGraph.searchNodeByData("2")));
        assertEquals("2 4 6", getDataAdjacencyList(weightedGraph.searchNodeByData("3")));
        assertEquals("3 5 6", getDataAdjacencyList(weightedGraph.searchNodeByData("4")));
        assertEquals("0 4", getDataAdjacencyList(weightedGraph.searchNodeByData("5")));
        assertEquals("1 3 4", getDataAdjacencyList(weightedGraph.searchNodeByData("6")));
    }

    private void addText(Node<String> node) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(node.getData());
    }

    private String getDataAdjacencyList(Node<String> node) {
        String result = "";
        LinkedList<String> adjacencyList = node.getAdjacencyList();

        for(int i = 0; i < adjacencyList.size(); i++) {
            String data = adjacencyList.get(i);

            if(!result.isEmpty()) result += " ";
            result += data;
        }

        return result;
    }
}
