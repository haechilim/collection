package kr.hs.sunrint.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryTreeTest {
    private BinaryTree<String> binaryTree;
    private StringBuffer buffer;

    @Before
    public void setup() {
        TreeNode<String> root = new TreeNode<>("A");
        TreeNode<String> treeNodeB = new TreeNode<>("B");
        TreeNode<String> treeNodeC = new TreeNode<>("C");
        TreeNode<String> treeNodeD = new TreeNode<>("D");
        TreeNode<String> treeNodeE = new TreeNode<>("E");
        TreeNode<String> treeNodeF = new TreeNode<>("F");
        TreeNode<String> treeNodeG = new TreeNode<>("G");

        binaryTree = new BinaryTree<>(root);

        binaryTree.insertLeftNode(root, treeNodeB);
        binaryTree.insertRightNode(root, treeNodeC);
        binaryTree.insertLeftNode(treeNodeB, treeNodeD);
        binaryTree.insertRightNode(treeNodeB, treeNodeE);
        binaryTree.insertLeftNode(treeNodeE, treeNodeF);
        binaryTree.insertRightNode(treeNodeE, treeNodeG);
    }

    @Test
    public void 삽입된_노드의_부모_노드_확인() {
        TreeNode<String> rootTreeNode = binaryTree.getRootNode();
        TreeNode<String> treeNode = rootTreeNode.getLeft();

        assertEquals(rootTreeNode.getParent(), null);
        assertEquals(treeNode.getParent(), rootTreeNode);
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

    private void addText(TreeNode<String> treeNode) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(treeNode.getData());
    }
}
