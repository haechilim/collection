package kr.hs.sunrint.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTreeTest {
    private BinarySearchTree<String> binarySearchTree;
    private StringBuffer buffer;

    @BeforeEach
    public void setup() {
        buffer = new StringBuffer();

        Node<String> root = new Node<>("50", 50);
        Node<String> node1 = new Node<>("1", 1);
        Node<String> node30 = new Node<>("30", 30);
        Node<String> node35 = new Node<>("35", 35);
        Node<String> node60 = new Node<>("60", 60);
        Node<String> node70 = new Node<>("70", 70);
        Node<String> node80 = new Node<>("80", 80);

        binarySearchTree = new BinarySearchTree<>(root);

        binarySearchTree.insertNode(node30);
        binarySearchTree.insertNode(node70);
        binarySearchTree.insertNode(node80);
        binarySearchTree.insertNode(node60);
        binarySearchTree.insertNode(node1);
        binarySearchTree.insertNode(node35);
        binarySearchTree.insertNode(new Node<>("34", 34));
        binarySearchTree.insertNode(new Node<>("65", 65));
    }

    @Test
    public void leaf_노드_삭제() {
        binarySearchTree.removeNode(1);
        binarySearchTree.traverseLevel(visit -> addText(visit));
        assertEquals("50 30 70 35 60 80 34 65", buffer.toString());

        buffer = new StringBuffer();
        binarySearchTree.removeNode(80);
        binarySearchTree.traverseLevel(visit -> addText(visit));
        assertEquals("50 30 70 35 60 34 65", buffer.toString());
    }

    @Test
    public void 자식_노드가_왼쪽_1개인_노드_삭제() {
        binarySearchTree.removeNode(35);
        binarySearchTree.traverseLevel(visit -> addText(visit));
        assertEquals("50 30 70 1 34 60 80 65", buffer.toString());
    }

    @Test
    public void 자식_노드가_오른쪽_1개인_노드_삭제() {
        binarySearchTree.removeNode(60);
        binarySearchTree.traverseLevel(visit -> addText(visit));
        assertEquals("50 30 70 1 35 65 80 34", buffer.toString());
    }

    @Test
    public void 자식_노드가_왼쪽_1개인_루트노드_삭제() {
        BinarySearchTree<String> tree = new BinarySearchTree<>(new Node<>("50", 50));
        tree.insertNode(new Node<>("35", 35));

        tree.removeNode(50);
        tree.traverseLevel(visit -> addText(visit));
        assertEquals("35", buffer.toString());
    }

    @Test
    public void 자식_노드가_오른쪽_1개인_루트노드_삭제() {
        BinarySearchTree<String> tree = new BinarySearchTree<>(new Node<>("50", 50));
        tree.insertNode(new Node<>("70", 70));

        tree.removeNode(50);
        tree.traverseLevel(visit -> addText(visit));
        assertEquals("70", buffer.toString());
    }

    @Test
    public void 자식_노드가_2개인_노드_삭제() {
        binarySearchTree.removeNode(70);
        binarySearchTree.traverseLevel(visit -> addText(visit));
        assertEquals("50 30 80 1 35 60 34 65", buffer.toString());
    }

    @Test
    public void 자식_노드가_2개인_루트_노드_삭제() {
        binarySearchTree.removeNode(50);
        binarySearchTree.traverseLevel(visit -> addText(visit));
        assertEquals("60 30 70 1 35 65 80 34", buffer.toString());
    }

    private void addText(Node<String> node) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(node.getData());
    }
}
