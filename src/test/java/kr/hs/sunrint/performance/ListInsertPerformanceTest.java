package kr.hs.sunrint.performance;

import kr.hs.sunrint.list.ArrayList;
import kr.hs.sunrint.list.LinkedList;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.*;

public class ListInsertPerformanceTest {
    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> linkedList;
    private int arrayListIndex;
    private int linkedListIndex;

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @BeforeClass
    public static void setupClass() {}

    @Before
    public void setup() {
        arrayList = new ArrayList<>(1024, 20);
        linkedList = new LinkedList<>();

        for (int i = 0; i < 5000000; i++) {
            arrayList.add(i);
        }

        for (int i = 0; i < 5000000; i++) {
            linkedList.add(i);
        }

        arrayListIndex = 2500000;


        System.out.println("setup");
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
        //System.out.println("test");
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
    @PerfTest(invocations = 1000)
    //@Required(median = 1400)
    public void ArrayList_중간에_데이터_삭제() {
        arrayList.remove(2500000);
    }

    @Test
    @Ignore
    @PerfTest(invocations = 1000)
    //@Required(median = 3)
    public void LinkedList_중간에_데이터_삭제() {
        linkedList.remove(2500000);
    }
}
