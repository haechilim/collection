package io.haechi.collection.performance;

import io.haechi.collection.tree.AVLTree;
import io.haechi.collection.tree.BinarySearchTree;
import io.haechi.collection.tree.TreeNode;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreePerformanceTest {
    private static final int INVOCATION_COUNT = 100;
    private static final int ELEMENT_COUNT = 1000000;
    
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
        init();
    }

    private void init() {
        System.out.println("초기화 시작");

        samples = new ArrayList<>();

        for(int i = 0; i < ELEMENT_COUNT; i++) {
            samples.add(i);
        }

        Collections.shuffle(samples);

        for(int i = 0; i < ELEMENT_COUNT; i++) {
            int data = samples.get(i);

            if (i == 0) {
                binarySearchTree = new BinarySearchTree<>(new TreeNode(data, data));
                avlTree = new AVLTree<>(new TreeNode(data, data));
            }
            else {
                binarySearchTree.insertNode(new TreeNode(data, data));
                avlTree.insertNode(new TreeNode(data, data));
            }
        }

        System.out.println("초기화 종료");
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void BinarySearchTree_데이터_삽입() {
        for(int i = 0; i < ELEMENT_COUNT; i++) {
            int data = samples.get(i);

            if (i == 0) binarySearchTree = new BinarySearchTree<>(new TreeNode(data, data));
            else binarySearchTree.insertNode(new TreeNode(data, data));
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void AVLTree_데이터_삽입() {
        for(int i = 0; i < ELEMENT_COUNT; i++) {
            int data = samples.get(i);

            if (i == 0) avlTree = new AVLTree<>(new TreeNode(data, data));
            else avlTree.insertNode(new TreeNode(data, data));
        }
    }

    @Test
    @PerfTest(invocations = 2)//INVOCATION_COUNT)
    public void BinarySearchTree_데이터_삭제() {
        System.out.println("테스트 시작");

        for(int i = 0; i < ELEMENT_COUNT; i++) {
            binarySearchTree.removeNode(i);
        }

        System.out.println("테스트 종료");
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void AVLTree_데이터_삭제() {
        for(int i = 0; i < ELEMENT_COUNT; i++) {
            avlTree.removeNode(i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void BinarySearchTree_데이터_찾기() {
        for(int i = 0; i < ELEMENT_COUNT; i++) {
            binarySearchTree.searchNode(i);
        }

        System.out.println(binarySearchTree.getRootNode().getBalanceFactor());
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void AVLTree_데이터_찾기() {
    for(int i = 0; i < ELEMENT_COUNT; i++) {
            avlTree.searchNode(i);
        }

        System.out.println(avlTree.getRootNode().getBalanceFactor());
    }

    @Test
    public void BinarySearchTree_vs_AVLTree() {
        StopWatch stopWatch = new StopWatch("이진탐색트리 vs AVL 트리");

        stopWatch.start("이진탐색트리 삽입");
        for(int i = 0; i < ELEMENT_COUNT; i++) {
            int data = samples.get(i);

            if (i == 0) binarySearchTree = new BinarySearchTree<>(new TreeNode(data, data));
            else binarySearchTree.insertNode(new TreeNode(data, data));
        }
        System.out.println("이진탐색트리 BF: " + binarySearchTree.getRootNode().getBalanceFactor());
        stopWatch.stop();

        stopWatch.start("이진탐색트리 검색");
        for(int i = 0; i < ELEMENT_COUNT; i++) {
            binarySearchTree.searchNode(samples.get(i));
        }
        stopWatch.stop();

        stopWatch.start("AVL 트리 삽입");
        for(int i = 0; i < ELEMENT_COUNT; i++) {
            int data = samples.get(i);

            if (i == 0) avlTree = new AVLTree<>(new TreeNode(data, data));
            else avlTree.insertNode(new TreeNode(data, data));
        }
        System.out.println("AVL 트리 BF: " + avlTree.getRootNode().getBalanceFactor());
        System.out.println();
        stopWatch.stop();

        stopWatch.start("AVL 트리 검색");
        for(int i = 0; i < ELEMENT_COUNT; i++) {
            avlTree.searchNode(samples.get(i));
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
