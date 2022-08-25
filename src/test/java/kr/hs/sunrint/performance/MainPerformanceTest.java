package kr.hs.sunrint.performance;

import kr.hs.sunrint.list.ArrayList;
import kr.hs.sunrint.list.LinkedList;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.*;

import java.util.Random;

public class MainPerformanceTest {
    private Random random = new Random();

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @BeforeClass
    public static void setupClass() {}

    @Before
    public void setup() {

    }

    @Test
    @Ignore
    @PerfTest(invocations = 1000)
    @Required(median = 0)
    public void ArrayList_증분이_2인_리스트_삽입_테스트() {
        ArrayList<Integer> list = new ArrayList<>(1024, 2);

        //StopWatch stopWatch = new StopWatch(new Object(){}.getClass().getEnclosingMethod().getName());

        //long start = System.currentTimeMillis();
        //stopWatch.start();

        for (int i = 0; i < 5000000; i++) {
            list.add(i);
        }

        //System.out.println(System.currentTimeMillis() - start);
        //stopWatch.stop();
        //System.out.println(stopWatch.prettyPrint());
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1000)
    @Required(median = 16)
    public void ArrayList_증분이_20인_리스트_삽입_테스트() {
        ArrayList<Integer> list = new ArrayList<>(1024, 20);

        for (int i = 0; i < 5000000; i++) {
            list.add(i);
        }
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1)
    @Required(median = 1700)
    public void ArrayList_랜덤한_위치에_데이터_삽입() {
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
    public void LinkedList_랜덤한_위치에_데이터_삽입() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(50001);

        for (int i = 0; i < 50000; i++) {
            int index = random.nextInt(list.size());

            list.add(index, i);
        }
    }
}
