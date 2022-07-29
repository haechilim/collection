package kr.hs.sunrint.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeapTest {
    private Heap<String> heap;
    private StringBuffer buffer;

    @BeforeEach
    public void setup() {
        Node<String> root = new Node<>("9", 9);
        Node<String> node7 = new Node<>("7", 7);
        Node<String> node6 = new Node<>("6", 6);
        Node<String> node5 = new Node<>("5", 5);
        Node<String> node4 = new Node<>("4", 4);
        Node<String> node3 = new Node<>("3", 3);
        Node<String> node2 = new Node<>("2", 2);
        Node<String> node2_ = new Node<>("2", 2);
        Node<String> node1 = new Node<>("1", 1);
        Node<String> node3_ = new Node<>("3", 3);

        heap = new Heap<>(root, true);

        heap.insertLeftNode(root, node7);
        heap.insertRightNode(root, node6);
        heap.insertLeftNode(node7, node5);
        heap.insertRightNode(node7, node4);
        heap.insertLeftNode(node6, node3);
        heap.insertRightNode(node6, node2);
        heap.insertLeftNode(node5, node2_);
        heap.insertRightNode(node5, node1);
        heap.insertLeftNode(node4, node3_);
    }

    @Test
    public void 우선순위가_낮은_데이터_순으로_리스트_정렬() {
        buffer = new StringBuffer();

        heap = new Heap<>(new Node<>("1", 1), false);
        heap.insertNodeToArray(new Node<>("3", 3));
        heap.insertNodeToArray(new Node<>("4", 4));
        heap.insertNodeToArray(new Node<>("2", 2));
        heap.insertNodeToArray(new Node<>("6", 6));
        heap.insertNodeToArray(new Node<>("3", 3));
        heap.insertNodeToArray(new Node<>("2", 2));
        heap.insertNodeToArray(new Node<>("5", 5));
        heap.insertNodeToArray(new Node<>("7", 7));
        heap.insertNodeToArray(new Node<>("9", 9));

        heap.traverseLevel(visit -> addText(visit));
        assertEquals("1 2 2 3 6 4 3 5 7 9", buffer.toString());
    }

    @Test
    public void 우선순위가_낮은_데이터_순으로_정렬() {
        buffer = new StringBuffer();

        heap = new Heap<>(new Node<>("1", 1), false);
        heap.insertNode(new Node<>("3", 3));
        heap.insertNode(new Node<>("4", 4));
        heap.insertNode(new Node<>("2", 2));
        heap.insertNode(new Node<>("6", 6));
        heap.insertNode(new Node<>("3", 3));
        heap.insertNode(new Node<>("2", 2));
        heap.insertNode(new Node<>("5", 5));
        heap.insertNode(new Node<>("7", 7));
        heap.insertNode(new Node<>("9", 9));

        heap.traverseLevel(visit -> addText(visit));
        assertEquals("1 2 2 3 6 4 3 5 7 9", buffer.toString());
    }

    @Test
    public void 일반적인_데이터_삽입() {
        buffer = new StringBuffer();
        heap.insertNode(new Node<>("8", 8));

        heap.traverseLevel(visit -> addText(visit));
        assertEquals("9 8 6 5 7 3 2 2 1 3 4", buffer.toString());
    }

//    @Test
//    public void 리스트로_데이터_삽입() {
//        buffer = new StringBuffer();
//        heap.insertNodeToArray(new Node<>("8", 8));
//
//        heap.traverseLevel(visit -> addText(visit));
//        assertEquals("9 8 6 5 7 3 2 2 1 3 4", buffer.toString());
//    }

    @Test
    public void 포화_이진_트리에서_새로운_데이터_삽입() {
        buffer = new StringBuffer();
        heap.insertNode(new Node<>("8", 8));
        heap.insertNode(new Node<>("-1", -1));
        heap.insertNode(new Node<>("-2", -2));
        heap.insertNode(new Node<>("0", 0));
        heap.insertNode(new Node<>("-3", -3));
        heap.insertNode(new Node<>("10", 10));

        heap.traverseLevel(visit -> addText(visit));
        assertEquals("10 9 6 8 7 3 2 5 1 3 4 -1 -2 0 -3 2", buffer.toString());
    }

    @Test
    public void 노드_삭제() {
        buffer = new StringBuffer();

        heap.removeRootNode();

        heap.traverseLevel(visit -> addText(visit));
        assertEquals("7 5 6 3 4 3 2 2 1", buffer.toString());
    }

    private void addText(Node<String> node) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(node.getData());
    }
}
