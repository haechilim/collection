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
    public void traverse_재귀() {
        binaryTree.traversePreorderRecursively();
        assertEquals("ABDEFGC", binaryTree.getTraversalNodes());
        binaryTree.traverseInorderRecursively();
        assertEquals("DBFEGAC", binaryTree.getTraversalNodes());
        binaryTree.traversePostorderRecursively();
        assertEquals("DFGEBCA", binaryTree.getTraversalNodes());
    }

    @Test
    public void traverse() {
        binaryTree.traversePreorder();
        assertEquals("ABDEFGC", binaryTree.getTraversalNodes());
        binaryTree.traverseInorder();
        assertEquals("DBFEGAC", binaryTree.getTraversalNodes());
        binaryTree.traversePostorder();
        assertEquals("DFGEBCA", binaryTree.getTraversalNodes());
        binaryTree.traverseLevel();
        assertEquals("ABCDEFG", binaryTree.getTraversalNodes());
    }
}
