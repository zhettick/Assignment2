package logical;

import physical.MyArrayList;

public class MyStack<T> {
    public MyArrayList<T> list = new MyArrayList<>();

    public boolean empty() {
        //checking do list have elements or not
        return list.size() == 0;
    }

    public int size() {
        //returning size
        return list.size();
    }

    public T peek() {
        //check for is it empty or not; return last element(first to out)
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.getLast();
    }

    public T push(T item) {
        //add element to the end
        list.add(item);
        return item;
    }

    public T pop() {
        //check for is it empty or not; remove last element(first to out)
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T item = list.getLast();
        list.removeLast();
        return item;
    }
}