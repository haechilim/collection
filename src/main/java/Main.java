import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        System.out.println(arrayList.toString());

        arrayList.add(2);
        System.out.println(arrayList.toString());

        arrayList.add(3);
        System.out.println(arrayList.toString());

        arrayList.add(4);
        System.out.println(arrayList.toString());

        arrayList.add(2, 5);
        System.out.println(arrayList.toString());

        arrayList.add(6);
        System.out.println(arrayList.toString());

        arrayList.add(2, 11);
        System.out.println(arrayList.toString());

        arrayList.add(2, 14);
        System.out.println(arrayList.toString());

        arrayList.add(2, 55);
        System.out.println(arrayList.toString());

        arrayList.remove(2);
        System.out.println(arrayList.toString());

        arrayList.add(5);
        System.out.println(arrayList.toString());

        arrayList.remove(5);
        System.out.println(arrayList.toString());

        arrayList.add(5);
        System.out.println(arrayList.toString());

        arrayList.set(4, 2);
        System.out.println(arrayList.toString());

        arrayList.set(0, 2);
        System.out.println(arrayList.toString());

        System.out.println(arrayList.get(0));

        System.out.println(arrayList.get(8));

        System.out.println(arrayList.size());

        System.out.println(arrayList.contains(2));

        System.out.println(arrayList.contains(7));

        System.out.println(arrayList.indexOf(4));

        System.out.println(arrayList.indexOf(2));

        arrayList.clear();

        System.out.println(arrayList.size());
    }
}
