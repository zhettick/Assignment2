package logical;

import physical.MyArrayList;

public class MyMinHeap<T extends Comparable<T>> {
    public MyArrayList<T> list = new MyArrayList<>();

    public boolean empty() {
        //checking do list have elements or not
        return list.size() == 0;
    }

    public int size() {
        //returning size
        return list.size();
    }

    public T getMin() {
        //check for is it empty or not; return min element(first/root)
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return list.get(0);
    }

    public T extractMin() {
        //check for is it empty or not; change min element(first/root) to the last and remove last(because now it is root); use heapify
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = getMin();
        list.set(0, list.get(list.size() - 1));
        list.removeLast();
        heapify(0);
        return min;
    }

    public void insert(T item) {
        //add element to the end and use traversup
        list.addLast(item);
        traverseUp(list.size() - 1);
    }

    private void traverseUp(int index) {
        //if parent is bigger than cjild(last element) swap them
        while (index > 0 && list.get(index).compareTo(list.get(parentOf(index))) < 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    private void heapify(int index) {
        //if child is smaller than parent swap them
        int smallest = index;
        int left = leftChildOf(index);
        int right = rightChildOf(index);

        if (left < list.size() && list.get(left).compareTo(list.get(smallest)) < 0) {
            smallest = left;
        }

        if (right < list.size() && list.get(right).compareTo(list.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private int leftChildOf(int index) {
        //returning left child through index
        return 2 * index + 1;
    }

    private int rightChildOf(int index) {
        //returning right child through index
        return 2 * index + 2;
    }

    private int parentOf(int index) {
        //returning parent through index
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        //swap elements by their positions
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
