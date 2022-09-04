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

public class ListPerformanceTest1 {
    private static final int INVOCATION_COUNT = 100;
    private static final int ELEMENT_COUNT = 1000000;
    private Random random = new Random();

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @BeforeClass
    public static void setupClass() {}

    @Before
    public void setup() {

    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 배열리스트_삽입_초기값_10() {
        List<Integer> list = new ArrayList<>(10);

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            list.add(i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 배열리스트_삽입_초기값_100() {
        List<Integer> list = new ArrayList<>(100);

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            list.add(i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 배열리스트_삽입_초기값_1000() {
        List<Integer> list = new ArrayList<>(1000);

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            list.add(i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 연결리스트_맨앞에_삽입() {
        List<Integer> list = new LinkedList<>();

        list.add(0);

        for (int i = 1; i <= ELEMENT_COUNT; i++) {
            list.add(0, i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 연결리스트_맨뒤에_삽입() {
        List<Integer> list = new LinkedList<>();

        list.add(0);

        for (int i = 1; i <= ELEMENT_COUNT; i++) {
            list.add(list.size() - 1, i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 배열리스트_삽입_랜덤_위치() {
        List<Integer> list = new ArrayList<>();

        list.add(0);

        for (int i = 1; i <= 20000; i++) {
            int index = random.nextInt(list.size());

            list.add(index, i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    public void 연결리스트_삽입_랜덤_위치() {
        List<Integer> list = new LinkedList<>();

        list.add(0);

        for (int i = 1; i <= 20000; i++) {
            int index = random.nextInt(list.size());

            list.add(index, i);
        }
    }
}
