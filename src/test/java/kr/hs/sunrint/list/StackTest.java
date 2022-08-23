package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.NotExistElementException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {
    private Stack<String> stack;

    @Before
    public void setup() {
        stack = new Stack<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");
    }

    @Test(expected = NotExistElementException.class)
    public void 스택에서_데이터_pop() {
        assertEquals("C", stack.pop());
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
        stack.pop();
    }
}
