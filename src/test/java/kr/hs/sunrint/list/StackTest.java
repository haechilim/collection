package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.IndexOutOfBoundException;
import kr.hs.sunrint.exception.NotExistElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StackTest {
    private Stack<String> stack;

    @BeforeEach
    public void setup() {
        stack = new Stack<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");
    }

    @Test
    public void 스택에서_데이터_pop() {
        assertEquals("C", stack.pop());
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
        assertThrows(NotExistElementException.class, () -> stack.pop());
    }
}
