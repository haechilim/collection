package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.NotExistElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueueTest {
    private Queue<String> queue;

    @BeforeEach
    public void setup() {
        queue = new Queue<>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
    }

    @Test
    public void 큐에서_데이터_dequeue() {
        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
        assertEquals("D", queue.dequeue());
        assertThrows(NotExistElementException.class, () -> queue.dequeue());
    }
}
