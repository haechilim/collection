package io.haechi.collection.list;


import io.haechi.collection.exception.IndexOutOfBoundException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayListTest {
    private ArrayList<String> list;

    @Before
    public void setup() {
        list = new ArrayList<>();

        list.add("abc");
        list.add("def");
        list.add("ghi");
    }

    @Test
    public void 요소를_리스트_끝에_추가한다() {
        assertEquals("abc", list.get(0));
        assertEquals("def", list.get(1));
        assertEquals(3, list.size());
    }

    @Test
    public void 요소를_리스트_중간에_추가한다() {
        list.add(1, "xyz");
        assertEquals("abc", list.get(0));
        assertEquals("xyz", list.get(1));
        assertEquals("def", list.get(2));
    }

    @Test(expected = IndexOutOfBoundException.class)
    public void 엾는_인덱스에_추가_하려고_하면_예외를_던진다() {
        list.add(4, "cat");
    }

    @Test
    public void 중간_요소를_삭제한다() {
        list.remove("def");

        assertEquals("ghi", list.get(1));
    }

    @Test
    public void 마지막_요소를_삭제한다() {
        list.remove(2);

        assertEquals(2, list.size());
    }

    @Test
    public void 리스트_초기화() {
        list.clear();

        assertEquals(0, list.size());
    }
}
