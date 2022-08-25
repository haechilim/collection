package kr.hs.sunrint.performance;

import kr.hs.sunrint.tree.AVLTree;
import kr.hs.sunrint.tree.TreeNode;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreePerformanceTest {
    private AVLTree<Integer> avlTree;
    private List<Integer> arrayList;

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @BeforeClass
    public static void setupClass() {
    }

    @Before
    public void setup() {
    }

    public TreePerformanceTest() {
        arrayList = new ArrayList<>();

        for(int i = 0; i < 1000000; i++) {
            arrayList.add(i);
        }

        Collections.shuffle(arrayList);
    }

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 2000)
    public void BinarySearchTree_데이터_삽입() {
        for(int i = 0; i < 1000000; i++) {
            int data = arrayList.get(i);

            if (i == 0) avlTree = new AVLTree<>(new TreeNode(data, data));
            else avlTree.insertNode(new TreeNode(data, data));
        }

        System.out.println(avlTree.getRootTreeNode().getBalanceFactor());
    }

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 2000)
    public void AVLTree_데이터_삽입() {
        for(int i = 0; i < 1000000; i++) {
            int data = arrayList.get(i);

            if (i == 0) avlTree = new AVLTree<>(new TreeNode(data, data));
            else avlTree.insertNode(new TreeNode(data, data));

            avlTree.balanceTree();
        }

        System.out.println(avlTree.getRootTreeNode().getBalanceFactor());
    }

    /*@Test
    @PerfTest(invocations = 1)
    @Required(median = 20)
    public void BinarySearchTree_데이터_삭제() {
        for(int i = 0; i < 100000; i++) {

            try {
                tree.removeNode(BinarySearchTreeKeyList.get(i));
            } catch (NotExistElementException e) {

            }
        }
    }

    @Test
    @PerfTest(invocations = 1000)
    @Required(median = 10)
    public void BinarySearchTree_데이터_찾기() {
        for (int i = 0; i < 50000; i++) {
            int key = random.nextInt(50000);

            tree.searchNode(key);
        }
    }*/
}
