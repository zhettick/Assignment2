package logical;

import physical.MyArrayList;

public class MyStack<T> {
    public MyArrayList<T> list = new MyArrayList<>();

    public boolean empty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.getLast();
    }

    public T push(T item) {
        list.add(item);
        return item;
    }

    public T pop() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T item = list.getLast();
        list.removeLast();
        return item;
    }
}