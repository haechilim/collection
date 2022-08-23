package kr.hs.sunrint.performance;

import kr.hs.sunrint.list.ArrayList;
import kr.hs.sunrint.list.LinkedList;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.*;

public class PerformanceTest {
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
    @PerfTest(invocations = 10)
    @Required(median = 3)
    public void LinkedList_가장_앞에_데이터_삽입() {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 50000; i++) {
            list.add(0, i);
        }
    }

    @Test
    @Ignore
    @PerfTest(invocations = 10)
    @Required(median = 1400)
    public void ArrayList_가장_앞에_데이터_삽입() {
        ArrayList<Integer> list = new ArrayList<>(1024, 20);

        for (int i = 0; i < 50000; i++) {
            list.add(0, i);
        }
    }
}
