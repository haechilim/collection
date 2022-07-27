package kr.hs.sunrint.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeTest {
    private BinaryTree<String> binaryTree;
    private StringBuffer buffer;

    @BeforeEach
    public void setup() {
        Node<String> root = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        Node<String> nodeF = new Node<>("F");
        Node<String> nodeG = new Node<>("G");

        binaryTree = new BinaryTree<>(root);

        binaryTree.insertLeftNode(root, nodeB);
        binaryTree.insertRightNode(root, nodeC);
        binaryTree.insertLeftNode(nodeB, nodeD);
        binaryTree.insertRightNode(nodeB, nodeE);
        binaryTree.insertLeftNode(nodeE, nodeF);
        binaryTree.insertRightNode(nodeE, nodeG);
    }

    @Test
    public void 삽입된_노드의_부모_노드_확인() {
        Node<String> rootNode = binaryTree.getRootNode();
        Node<String> node = rootNode.getLeft();

        assertEquals(rootNode.getParent(), null);
        assertEquals(node.getParent(), rootNode);
    }

    @Test
    public void traverse_재귀() {
        buffer = new StringBuffer();
        binaryTree.traversePreorderRecursively(visit -> addText(visit));
        assertEquals("A B D E F G C", buffer.toString());

        buffer = new StringBuffer();
        binaryTree.traverseInorderRecursively(visit -> addText(visit));
        assertEquals("D B F E G A C", buffer.toString());

        buffer = new StringBuffer();
        binaryTree.traversePostorderRecursively(visit -> addText(visit));
        assertEquals("D F G E B C A", buffer.toString());
    }

    @Test
    public void traverse() {
        buffer = new StringBuffer();
        binaryTree.traversePreorder(visit -> addText(visit));
        assertEquals("A B D E F G C", buffer.toString());

        buffer = new StringBuffer();
        binaryTree.traverseInorder(visit -> addText(visit));
        assertEquals("D B F E G A C", buffer.toString());

        buffer = new StringBuffer();
        binaryTree.traversePostorder(visit -> addText(visit));
        assertEquals("D F G E B C A", buffer.toString());

        buffer = new StringBuffer();
        binaryTree.traverseLevel(visit -> addText(visit));
        assertEquals("A B C D E F G", buffer.toString());
    }

    private void addText(Node<String> node) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(node.getData());
    }
}
