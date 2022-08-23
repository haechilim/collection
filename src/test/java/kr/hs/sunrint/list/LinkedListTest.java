package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.IndexOutOfBoundException;
import kr.hs.sunrint.exception.NotExistElementException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkedListTest {
    LinkedList<String> list;

    @Before
    public void setup() {
        list = new LinkedList();

        list.add("abc");
        list.add("def");
        list.add("ghi");
    }

    @Test
    public void 요소를_리스트_끝에_추가한다() {
        list.add("cat");

        assertEquals("abc", list.get(0));
        assertEquals("def", list.get(1));
        assertEquals("ghi", list.get(2));
        assertEquals("cat", list.get(3));
        assertEquals(4, list.size());
    }

    @Test
    public void 요소를_리스트_중간에_추가한다() {
        list.add(1, "xyz");
        assertEquals("abc", list.get(0));
        assertEquals("xyz", list.get(1));
        assertEquals("def", list.get(2));
    }

    @Test
    public void 요소를_가장_앞에_추가한다() {
        list.add(0, "xyz");

        assertEquals("xyz", list.get(0));
        assertEquals("abc", list.get(1));
        assertEquals("def", list.get(2));
        assertEquals("ghi", list.get(3));
    }

    @Test(expected = IndexOutOfBoundException.class)
    public void 엾는_인덱스에_추가_하려고_하면_예외를_던진다() {
        list.add(3, "cat");
    }

    @Test
    public void 중간_요소를_삭제한다() {
        list.remove("def");

        assertEquals("abc", list.get(0));
        assertEquals("ghi", list.get(1));
    }

    @Test
    public void 마지막_요소를_삭제한다() {
        list.removeLast();

        assertEquals("abc", list.get(0));
        assertEquals("def", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void 처음_요소를_삭제한다() {
        list.remove(0);

        assertEquals("def", list.get(0));
        assertEquals("ghi", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void 리스트_초기화() {
        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    public void 요소_교체() {
        list.set(1, "qwerty");

        assertEquals("abc", list.get(0));
        assertEquals("qwerty", list.get(1));
        assertEquals("ghi", list.get(2));
    }

    @Test
    public void 요소가_있는지_체크() {
        assertEquals(true, list.contains("ghi"));
        assertEquals(false, list.contains("cat"));
    }

    @Test(expected = NotExistElementException.class)
    public void 요소가_어디에_있는지_체크() {
        assertEquals(2, list.indexOf("ghi"));
        list.indexOf("cat");
    }
}