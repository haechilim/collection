package io.haechi.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 };
        //add(2, 8)
        List<Integer> a = new ArrayList<>(1024);
        System.arraycopy(array, 2, array, 3, 6 - 2);
        //System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(array));
    }
}
