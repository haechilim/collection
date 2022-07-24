package kr.hs.sunrint.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeTest {
    private BinaryTree<String> binaryTree;

    @BeforeEach
    public void setup() {
        Node<String> root = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        Node<String> nodeF = new Node<>("F");
        Node<String> nodeG = new Node<>("G");
//        Node<String> nodeH = new Node<>("H");
//        Node<String> nodeI = new Node<>("I");
//        Node<String> nodeJ = new Node<>("J");

        binaryTree = new BinaryTree<>(root);

        binaryTree.insertLeftNode(root, nodeB);
        binaryTree.insertRightNode(root, nodeC);
        binaryTree.insertLeftNode(nodeB, nodeD);
        binaryTree.insertRightNode(nodeB, nodeE);
        binaryTree.insertLeftNode(nodeE, nodeF);
        binaryTree.insertRightNode(nodeE, nodeG);
//        binaryTree.insertLeftNode(nodeC, nodeH);
//        binaryTree.insertRightNode(nodeC, nodeI);
//        binaryTree.insertLeftNode(nodeI, nodeJ);
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
        binaryTree.traversePreorderRecursively();
        assertEquals("A B D E F G C", binaryTree.getTraversalNodes());
        binaryTree.traverseInorderRecursively();
        assertEquals("D B F E G A C", binaryTree.getTraversalNodes());
        binaryTree.traversePostorderRecursively();
        assertEquals("D F G E B C A", binaryTree.getTraversalNodes());
    }

    @Test
    public void traverse() {
        binaryTree.traversePreorder();
        assertEquals("A B D E F G C", binaryTree.getTraversalNodes());
        binaryTree.traverseInorder();
        assertEquals("D B F E G A C", binaryTree.getTraversalNodes());
        binaryTree.traversePostorder();
        assertEquals("D F G E B C A", binaryTree.getTraversalNodes());
        binaryTree.traverseLevel();
        assertEquals("A B C D E F G", binaryTree.getTraversalNodes());
    }
}
