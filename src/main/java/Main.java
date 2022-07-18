import kr.hs.sunrint.list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();

        arrayList.add("a");
        System.out.println(arrayList.toString());

        arrayList.add("b");
        System.out.println(arrayList.toString());

        arrayList.add("c");
        System.out.println(arrayList.toString());

        arrayList.add("d");
        System.out.println(arrayList.toString());

        arrayList.add(2, "e");
        System.out.println(arrayList.toString());

        arrayList.add("f");
        System.out.println(arrayList.toString());

        arrayList.add(2, "1");
        System.out.println(arrayList.toString());

        arrayList.add(2, "4");
        System.out.println(arrayList.toString());

        arrayList.add(2, "41");
        System.out.println(arrayList.toString());

        arrayList.remove(2);
        System.out.println(arrayList.toString());

        arrayList.add("e");
        System.out.println(arrayList.toString());

        arrayList.remove("e");
        System.out.println(arrayList.toString());

        arrayList.add("e");
        System.out.println(arrayList.toString());

        arrayList.set(4, "asddf");
        System.out.println(arrayList.toString());

        arrayList.set(0, "pqpqpqp");
        System.out.println(arrayList.toString());

        System.out.println(arrayList.get(0));

        System.out.println(arrayList.get(8));

        System.out.println(arrayList.size());

        System.out.println(arrayList.contains("asddf"));

        System.out.println(arrayList.contains("asddf1"));

        System.out.println(arrayList.indexOf("asddf"));

        System.out.println(arrayList.indexOf("asddf1"));

        arrayList.clear();

        System.out.println(arrayList.size());
    }
}
