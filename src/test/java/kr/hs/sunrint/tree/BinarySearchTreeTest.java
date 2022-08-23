package kr.hs.sunrint.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinarySearchTreeTest {
    private BinarySearchTree<String> binarySearchTree;
    private StringBuffer buffer;

    @Before
    public void setup() {
        buffer = new StringBuffer();

        TreeNode<String> root = new TreeNode<>("50", 50);
        TreeNode<String> treeNode1 = new TreeNode<>("1", 1);
        TreeNode<String> treeNode30 = new TreeNode<>("30", 30);
        TreeNode<String> treeNode35 = new TreeNode<>("35", 35);
        TreeNode<String> treeNode60 = new TreeNode<>("60", 60);
        TreeNode<String> treeNode70 = new TreeNode<>("70", 70);
        TreeNode<String> treeNode80 = new TreeNode<>("80", 80);

        binarySearchTree = new BinarySearchTree<>(root);

        binarySearchTree.insertNode(treeNode30);
        binarySearchTree.insertNode(treeNode70);
        binarySearchTree.insertNode(treeNode80);
        binarySearchTree.insertNode(treeNode60);
        binarySearchTree.insertNode(treeNode1);
        binarySearchTree.insertNode(treeNode35);
        binarySearchTree.insertNode(new TreeNode<>("34", 34));
        binarySearchTree.insertNode(new TreeNode<>("65", 65));
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
        BinarySearchTree<String> tree = new BinarySearchTree<>(new TreeNode<>("50", 50));
        tree.insertNode(new TreeNode<>("35", 35));

        tree.removeNode(50);
        tree.traverseLevel(visit -> addText(visit));
        assertEquals("35", buffer.toString());
    }

    @Test
    public void 자식_노드가_오른쪽_1개인_루트노드_삭제() {
        BinarySearchTree<String> tree = new BinarySearchTree<>(new TreeNode<>("50", 50));
        tree.insertNode(new TreeNode<>("70", 70));

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

    private void addText(TreeNode<String> treeNode) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(treeNode.getData());
    }
}
