package io.haechi.collection.performance;

import io.haechi.collection.list.ArrayList;
import io.haechi.collection.list.LinkedList;
import io.haechi.collection.list.List;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

public class ListPerformanceTest2 {
    private static final int INVOCATION_COUNT = 100;
    private static final int ELEMENT_COUNT = 100000;

    private List<Integer> arrayList;
    private List<Integer> linkedList;
    private Random random = new Random();

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @BeforeClass
    public static void setupClass() {}

    @Before
    public void setup() {
        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.add(i);
        }

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.add(i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 배열리스트_삭제_랜덤() {
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(arrayList.size());

            arrayList.remove(index);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 연결리스트_삭제_랜덤() {
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(linkedList.size());

            linkedList.remove(index);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 배열리스트_접근_랜덤() {
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(arrayList.size());

            arrayList.get(index);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 연결리스트_접근_랜덤() {
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(linkedList.size());

            linkedList.get(index);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 배열리스트_검색_랜덤() {
        for (int i = 0; i < 1000; i++) {
            int data = random.nextInt(arrayList.size());

            arrayList.contains(data);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 연결리스트_검색_랜덤() {
        for (int i = 0; i < 1000; i++) {
            int data = random.nextInt(linkedList.size());

            linkedList.contains(data);
        }
    }
}
