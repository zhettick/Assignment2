package physical;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>();

        arrayList.add(50);
        arrayList.add(30);
        arrayList.add(1);
        arrayList.addFirst(5);
        arrayList.addLast(25);
        System.out.println("List:");
        Object[] arr = arrayList.toArray();
        for (Object obj : arr) {
            System.out.println(obj);
        }
        System.out.println("First element: " + arrayList.getFirst());
        System.out.println("Last element: " + arrayList.getLast());
        System.out.println("Element at index 1: " + arrayList.get(1));
        arrayList.set(1, 15);
        System.out.println("Element at index 1 after set: " + arrayList.get(1));
        arrayList.remove(2);
        System.out.println("After remove at index 2: " + arrayList.get(2));
        arrayList.removeFirst();
        System.out.println("After removeFirst: " + arrayList.getFirst());
        arrayList.removeLast();
        System.out.println("After removeLast: " + arrayList.getLast());
        System.out.println("Size of list: " + arrayList.size());
        System.out.println("List:");
        Object[] arr1 = arrayList.toArray();
        for (Object obj : arr1) {
            System.out.println(obj);
        }
        arrayList.sort();
        System.out.println("Sorted list:");
        Object[] arr2 = arrayList.toArray();
        for (Object obj : arr2) {
            System.out.println(obj);
        }
        System.out.println("Index of 10: " + arrayList.indexOf(10));
        System.out.println("Last index of 40: " + arrayList.lastIndexOf(40));
        System.out.println("Exists 50: " + arrayList.exists(50));
        arrayList.clear();
        System.out.println("Size after clear: " + arrayList.size());
    }
}
