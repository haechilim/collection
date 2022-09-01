package kr.hs.sunrint.performance;

import kr.hs.sunrint.tree.AVLTree;
import kr.hs.sunrint.tree.BinarySearchTree;
import kr.hs.sunrint.tree.TreeNode;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreePerformanceTest {
    private static final int NODE_COUNT = 1000000;
    
    private AVLTree<Integer> avlTree;
    private BinarySearchTree<Integer> binarySearchTree;
    private List<Integer> samples;

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @BeforeClass
    public static void setupClass() {
    }

    @Before
    public void setup() {
    }

    public TreePerformanceTest() {
        samples = new ArrayList<>();

        System.out.println("-- begin --");

        for(int i = 0; i < NODE_COUNT; i++) {
            samples.add(i);
        }

        Collections.shuffle(samples);

        for(int i = 0; i < NODE_COUNT; i++) {
            int data = samples.get(i);

            if (i == 0) {
                binarySearchTree = new BinarySearchTree<>(new TreeNode(data, data));
                avlTree = new AVLTree<>(new TreeNode(data, data));
            }
            else {
                binarySearchTree.insertNode(new TreeNode(data, data));
                avlTree.insertNode(new TreeNode(data, data));
                //avlTree.balanceTree();
            }
        }

        System.out.println("-- end --");
    }

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 2000)
    public void BinarySearchTree_데이터_삽입() {
        for(int i = 0; i < NODE_COUNT; i++) {
            int data = samples.get(i);

            if (i == 0) binarySearchTree = new BinarySearchTree<>(new TreeNode(data, data));
            else binarySearchTree.insertNode(new TreeNode(data, data));
        }

        System.out.println(binarySearchTree.getRootTreeNode().getBalanceFactor());
    }

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 2000)
    public void AVLTree_데이터_삽입() {
        for(int i = 0; i < NODE_COUNT; i++) {
            int data = samples.get(i);

            if (i == 0) avlTree = new AVLTree<>(new TreeNode(data, data));
            else avlTree.insertNode(new TreeNode(data, data));

            avlTree.balanceTree();
        }

        System.out.println(avlTree.getRootTreeNode().getBalanceFactor());
    }

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 100000)
    public void BinarySearchTree_데이터_삭제() {
        for(int i = 0; i < NODE_COUNT; i++) {
            binarySearchTree.removeNode(i);
        }

        System.out.println(binarySearchTree.getRootTreeNode());
    }

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 100000)
    public void AVLTree_데이터_삭제() {
        for(int i = 0; i < NODE_COUNT; i++) {
            avlTree.removeNode(i);
        }
    }

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 2000)
    public void BinarySearchTree_데이터_찾기() {
        for(int i = 0; i < NODE_COUNT; i++) {
            binarySearchTree.searchNode(i);
        }

        System.out.println(binarySearchTree.getRootTreeNode().getBalanceFactor());
    }

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 2000)
    public void AVLTree_데이터_찾기() {
    for(int i = 0; i < NODE_COUNT; i++) {
            avlTree.searchNode(i);
        }

        System.out.println(avlTree.getRootTreeNode().getBalanceFactor());
    }
}
