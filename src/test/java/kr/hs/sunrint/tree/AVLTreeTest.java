package kr.hs.sunrint.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AVLTreeTest {
    private AVLTree<String> avlTree;
    private StringBuffer buffer;

    @Before
    public void setup() {
        buffer = new StringBuffer();
    }

    @Test
    public void 특정_노드의_BF값_부여() {
        AVLTree<String> avlTree = new AVLTree<>(new TreeNode<>("50", 50));
        avlTree.insertNode(new TreeNode<>("30", 30));
        avlTree.insertNode(new TreeNode<>("70", 70));
        avlTree.insertNode(new TreeNode<>("80", 80));
        avlTree.insertNode(new TreeNode<>("60", 60));
        avlTree.insertNode(new TreeNode<>("1", 1));
        avlTree.insertNode(new TreeNode<>("35", 35));
        avlTree.insertNode(new TreeNode<>("34", 34));
        avlTree.insertNode(new TreeNode<>("65", 65));

        assertEquals(1, avlTree.getBalanceFactor(avlTree.searchNode(70)));
        assertEquals(0, avlTree.getBalanceFactor(avlTree.searchNode(50)));
        assertEquals(-1, avlTree.getBalanceFactor(avlTree.searchNode(60)));
        assertEquals(0, avlTree.getBalanceFactor(avlTree.searchNode(34)));

//        assertEquals(3, this.avlTree.getBalanceFactor(this.avlTree.searchNode(9)));
//        assertEquals(4, this.avlTree.getBalanceFactor(this.avlTree.searchNode(10)));
    }

    @Test
    public void 루트_노드의_BF가_2인_트리_밸런싱() {
        avlTree = new AVLTree<>(new TreeNode<>("50", 50));

        avlTree.insertNode(new TreeNode<>("40", 40));
        avlTree.insertNode(new TreeNode<>("60", 60));
        avlTree.insertNode(new TreeNode<>("55", 55));
        avlTree.insertNode(new TreeNode<>("65", 65));
        avlTree.insertNode(new TreeNode<>("35", 35));
        avlTree.insertNode(new TreeNode<>("45", 45));
        avlTree.insertNode(new TreeNode<>("42", 42));
        avlTree.insertNode(new TreeNode<>("47", 47));
        avlTree.insertNode(new TreeNode<>("37", 37));
        avlTree.insertNode(new TreeNode<>("33", 33));
        avlTree.insertNode(new TreeNode<>("34", 34));
        avlTree.insertNode(new TreeNode<>("32", 32));
        avlTree.insertNode(new TreeNode<>("36", 36));
        avlTree.insertNode(new TreeNode<>("38", 38));

        avlTree.balanceTree();
        avlTree.traverseLevel(visit -> addText(visit));
        assertEquals("40 35 50 33 37 45 60 32 34 36 38 42 47 55 65", buffer.toString());
    }

    @Test
    public void 루트_노드의_BF가_마이너스_2인_트리_밸런싱() {
        avlTree = new AVLTree<>(new TreeNode<>("50", 50));

        avlTree.insertNode(new TreeNode<>("40", 40));
        avlTree.insertNode(new TreeNode<>("60", 60));
        avlTree.insertNode(new TreeNode<>("35", 35));
        avlTree.insertNode(new TreeNode<>("45", 45));
        avlTree.insertNode(new TreeNode<>("55", 55));
        avlTree.insertNode(new TreeNode<>("65", 65));
        avlTree.insertNode(new TreeNode<>("54", 54));
        avlTree.insertNode(new TreeNode<>("56", 56));
        avlTree.insertNode(new TreeNode<>("63", 63));
        avlTree.insertNode(new TreeNode<>("67", 67));
        avlTree.insertNode(new TreeNode<>("62", 62));
        avlTree.insertNode(new TreeNode<>("64", 64));
        avlTree.insertNode(new TreeNode<>("66", 66));
        avlTree.insertNode(new TreeNode<>("68", 68));

        avlTree.balanceTree();
        avlTree.traverseLevel(visit -> addText(visit));
        assertEquals("60 50 65 40 55 63 67 35 45 54 56 62 64 66 68", buffer.toString());
    }

    @Test
    public void LR_불균형_트리_밸런싱() {
        avlTree = new AVLTree<>(new TreeNode<>("50", 50));

        avlTree.insertNode(new TreeNode<>("30", 30));
        avlTree.insertNode(new TreeNode<>("60", 60));
        avlTree.insertNode(new TreeNode<>("55", 55));
        avlTree.insertNode(new TreeNode<>("65", 65));
        avlTree.insertNode(new TreeNode<>("20", 20));
        avlTree.insertNode(new TreeNode<>("40", 40));
        avlTree.insertNode(new TreeNode<>("15", 15));
        avlTree.insertNode(new TreeNode<>("25", 25));
        avlTree.insertNode(new TreeNode<>("35", 35));
        avlTree.insertNode(new TreeNode<>("45", 45));
        avlTree.insertNode(new TreeNode<>("34", 34));
        avlTree.insertNode(new TreeNode<>("36", 36));
        avlTree.insertNode(new TreeNode<>("44", 44));
        avlTree.insertNode(new TreeNode<>("46", 46));

        avlTree.balanceTree();
        avlTree.traverseLevel(visit -> addText(visit));
        assertEquals("40 30 50 20 35 45 60 15 25 34 36 44 46 55 65", buffer.toString());
    }

    private void addText(TreeNode<String> treeNode) {
        if(buffer.length() > 0) buffer.append(" ");
        buffer.append(treeNode.getData());
    }
}
