package io.haechi.collection.performance;

import io.haechi.collection.list.ArrayList;
import io.haechi.collection.list.LinkedList;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.*;

import java.util.Random;

public class ListPerformanceTest1 {
    private static final int INVOCATION_COUNT = 1000;
    private static final int ELEMENT_COUNT = 5000000;
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
    @Required(median = 0)
    public void 배열리스트_삽입_초기값_1024_증분_2() {
        ArrayList<Integer> list = new ArrayList<>(1024, 2);

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            list.add(i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    @Required(median = 16)
    public void 배열리스트_삽입_초기값_1024_증분_200() {
        ArrayList<Integer> list = new ArrayList<>(1024, 200);

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            list.add(i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    @Required(median = 0)
    public void 배열리스트_삽입_초기값_8192_증분_2() {
        ArrayList<Integer> list = new ArrayList<>(8192, 2);

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            list.add(i);
        }
    }

    @Test
    @PerfTest(invocations = INVOCATION_COUNT)
    @Required(median = 16)
    public void 배열리스트_삽입_초기값_8192_증분_20() {
        ArrayList<Integer> list = new ArrayList<>(8192, 20);

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            list.add(i);
        }
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1)
    @Required(median = 1700)
    public void 배열리스트_삽입_랜덤_위치() {
        ArrayList<Integer> list = new ArrayList<>(1024, 20);

        list.add(50001);

        for (int i = 0; i < 50000; i++) {
            int index = random.nextInt(list.size());

            list.add(index, i);
        }
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1)
    @Required(median = 3000)
    public void 연결리스트_삽입_랜덤_위치() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(50001);

        for (int i = 0; i < 50000; i++) {
            int index = random.nextInt(list.size());

            list.add(index, i);
        }
    }
}
