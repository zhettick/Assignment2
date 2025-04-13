package logical;

import physical.MyLinkedList;

public class MyQueue<T extends Comparable<T>> {
    public MyLinkedList<T> list = new MyLinkedList<>();

    public boolean empty() {
        //checking do list have elements or not
        return list.size() == 0;
    }

    public int size() {
        //returning size
        return list.size();
    }

    public T peek() {
        //check for is it empty or not; return first element(first to out)
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.getFirst();
    }

    public void enqueue(T item) {
        //add element to the end
        list.add(item);
    }

    public void dequeue() {
        //check for is it empty or not; remove first element(first to out)
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        list.removeFirst();
    }
}
