package logical;

import physical.MyLinkedList;

public class MyQueue<T extends Comparable<T>> {
    public MyLinkedList<T> list = new MyLinkedList<>();

    public boolean empty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.getFirst();
    }

    public void enqueue(T item) {
        list.add(item);
    }

    public void dequeue() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        list.removeFirst();
    }
}
