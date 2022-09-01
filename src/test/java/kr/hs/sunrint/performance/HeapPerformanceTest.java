package kr.hs.sunrint.performance;

import kr.hs.sunrint.tree.*;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class HeapPerformanceTest {
    private ArrayList<Integer> samples;
    private Heap<Integer> treeHeap;
    private Heap<Integer> arrayHeap;
    private int count = 1000000;

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @BeforeClass
    public static void setupClass() {
    }

    @Before
    public void setup() {
    }

    public HeapPerformanceTest() {
        samples = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            samples.add(i);
        }

        Collections.shuffle(samples);

        for(int i = 0; i < count; i++) {
            int data = samples.get(i);

            if (i == 0) {
                //treeHeap = new TreeHeap<>(new TreeNode(data, data), false);
                arrayHeap = new ArrayHeap<>(new TreeNode(data, data), false);
            }
            else {
                //treeHeap.insertNode(new TreeNode(data, data));
                arrayHeap.insertNode(new TreeNode(data, data));
            }
        }
    }

    /*@Test
    @PerfTest(invocations = 1)
    @Required(median = 1000000)
    public void TreeHeap_데이터_삽입() {
        for(int i = 0; i < count; i++) {
            int data = samples.get(i);

            if (i == 0) treeHeap = new TreeHeap<>(new TreeNode(data, data), false);
            else treeHeap.insertNode(new TreeNode(data, data));
        }
    }

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 20000)
    public void TreeHeap_데이터_엑세스() {
        for(int i = 0; i < count; i++) {
            treeHeap.removeRootNode();
        }
    }*/

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 100000)
    public void ArrayHeap_데이터_삽입() {
        for(int i = 0; i < count; i++) {
            int data = samples.get(i);

            if (i == 0) arrayHeap = new ArrayHeap<>(new TreeNode(data, data), false);
            else arrayHeap.insertNode(new TreeNode(data, data));
        }
    }

    @Test
    @PerfTest(invocations = 1)
    @Required(median = 8000)
    public void ArrayHeap_데이터_엑세스() {
        for(int i = 0; i < count; i++) {
            arrayHeap.removeRootNode();
        }
    }

    /*@Test
    public void Heap_데이터_삭제시_정렬() {
        int max = -1;

        for(int i = 0; i < count; i++) {
            int data = treeHeap.removeRootNode().getData();

            if(data < max) {
                System.out.println("실패");
                return;
            }

            max = data;
        }

        System.out.println("성공");
    }*/
}
