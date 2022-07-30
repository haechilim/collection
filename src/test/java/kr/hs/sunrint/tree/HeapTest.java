package kr.hs.sunrint.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeapTest {
    private Heap<String> heap;
    private StringBuffer buffer;

    @BeforeEach
    public void setup() {
        heap = new TreeHeap<>(new Node<>("9", 9), true);

        heap.insertNode(new Node<>("7", 7));
        heap.insertNode(new Node<>("6", 6));
        heap.insertNode(new Node<>("5", 5));
        heap.insertNode(new Node<>("4", 4));
        heap.insertNode(new Node<>("3", 3));
        heap.insertNode(new Node<>("2", 2));
        heap.insertNode(new Node<>("2", 2));
        heap.insertNode(new Node<>("1", 1));
        heap.insertNode(new Node<>("3", 3));
    }

    @Test
    public void 우선순위가_낮은_데이터_순으로_리스트_정렬() {
        ArrayHeap<String> heap = new ArrayHeap<>(new Node<>("1", 1), false);
        heap.insertNode(new Node<>("3", 3));
        heap.insertNode(new Node<>("4", 4));
        heap.insertNode(new Node<>("2", 2));
        heap.insertNode(new Node<>("6", 6));
        heap.insertNode(new Node<>("3", 3));
        heap.insertNode(new Node<>("2", 2));
        heap.insertNode(new Node<>("5", 5));
        heap.insertNode(new Node<>("7", 7));
        heap.insertNode(new Node<>("9", 9));

        assertEquals("1 2 2 3 6 4 3 5 7 9", heap.toStringArray());
    }

    @Test
    public void 우선순위가_낮은_데이터_순으로_정렬() {
        buffer = new StringBuffer();

        heap = new TreeHeap<>(new Node<>("1", 1), false);
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

    @Test
    public void 리스트로_데이터_삽입() {
        buffer = new StringBuffer();
        heap.insertNode(new Node<>("8", 8));

        heap.traverseLevel(visit -> addText(visit));
        assertEquals("9 8 6 5 7 3 2 2 1 3 4", buffer.toString());
    }

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

    @Test
    public void 리스트로_노드_삭제() {
        ArrayHeap<String> heap = new ArrayHeap<>(new Node<>("1", 1), false);
        heap.insertNode(new Node<>("3", 3));
        heap.insertNode(new Node<>("4", 4));
        heap.insertNode(new Node<>("2", 2));
        heap.insertNode(new Node<>("6", 6));
        heap.insertNode(new Node<>("3", 3));
        heap.insertNode(new Node<>("5", 5));
        heap.insertNode(new Node<>("7", 7));
        heap.insertNode(new Node<>("9", 9));

        heap.removeRootNode();

        assertEquals("2 3 3 7 6 4 5 9", heap.toStringArray());
    }

    private void addText(Node<String> node) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(node.getData());
    }
}
