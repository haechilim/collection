package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.NotExistElementException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueueTest {
    private Queue<String> queue;

    @Before
    public void setup() {
        queue = new Queue<>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
    }

    @Test(expected = NotExistElementException.class)
    public void 큐에서_데이터_dequeue() {
        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
        assertEquals("D", queue.dequeue());
        queue.dequeue();
    }
}
