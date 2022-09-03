package io.haechi.collection.performance;

import io.haechi.collection.list.ArrayList;
import io.haechi.collection.list.LinkedList;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.*;

import java.util.Random;

public class ListPerformanceTest2 {
    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> linkedList;
    private Random random = new Random();

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @BeforeClass
    public static void setupClass() {}

    @Before
    public void setup() {
        arrayList = new ArrayList<>(1024, 20);
        linkedList = new LinkedList<>();

        for (int i = 0; i < 50000; i++) {
            arrayList.add(i);
        }

        for (int i = 0; i < 50000; i++) {
            linkedList.add(i);
        }
    }

    @Test
    @PerfTest(invocations = 1000)
    @Required(median = 0)
    public void ArrayList_데이터_엑세스() {
        arrayList.get(2500000);
    }

    @Test
    @PerfTest(invocations = 1000)
    @Required(median = 16)
    public void LinkedList_데이터_엑세스() {
        linkedList.get(2500000);
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1000)
    //@Required(median = 1400)
    public void ArrayList_중간에_데이터_삽입() {
        arrayList.add(2500000, 0);
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1000)
    //@Required(median = 3)
    public void LinkedList_중간에_데이터_삽입() {
        linkedList.add(2500000, 0);
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1)
    @Required(median = 700)
    public void ArrayList_랜덤_위치에_데이터_삭제() {
        for (int i = 0; i < 50000; i++) {
            int index = random.nextInt(arrayList.size());

            arrayList.remove(index);
        }
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1)
    @Required(median = 1000)
    public void LinkedList_랜덤_위치에_데이터_삭제() {
        for (int i = 0; i < 50000; i++) {
            int index = random.nextInt(linkedList.size());

            linkedList.remove(index);
        }
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1)
    @Required(median = 25)
    public void ArrayList_랜덤_위치에_데이터_접근() {
        for (int i = 0; i < 50000; i++) {
            int index = random.nextInt(arrayList.size());

            arrayList.get(index);
        }
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1)
    @Required(median = 2000)
    public void LinkedList_랜덤_위치에_데이터_접근() {
        for (int i = 0; i < 50000; i++) {
            int index = random.nextInt(linkedList.size());

            linkedList.get(index);
        }
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1)
    @Required(median = 700)
    public void ArrayList_랜덤_데이터_찾기() {
        for (int i = 0; i < 50000; i++) {
            int data = random.nextInt(arrayList.size());

            arrayList.contains(data);
        }
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1)
    @Required(median = 7000)
    public void LinkedList_랜덤_데이터_찾기() {
        for (int i = 0; i < 50000; i++) {
            int data = random.nextInt(linkedList.size());

            linkedList.contains(data);
        }
    }
}
