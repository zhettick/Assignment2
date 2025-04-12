package physical;

import java.util.Iterator;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    public MyLinkedList(){

    }

    @Override
    public void add(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        } else{
            tail.next = newNode;
            newNode.prev = tail;
            tail=newNode;
        }
        size++;
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        MyNode<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        current.data = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        MyNode<T> newNode = new MyNode<>(item);
        if (index==0){
            newNode.next = head;
            if (head!=null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail==null) {
                tail=newNode;
            }
        }
        else if (index == size) {
            newNode.prev = tail;
            if (tail != null){
                tail.next = newNode;
            }
            tail = newNode;
            if (head == null){
                head = newNode;
            }
        }
        else {
            MyNode<T> current;
            if (index < size / 2) {
                current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
            } else {
                current = tail;
                for (int i = size - 1; i >= index; i--) {
                    current = current.prev;
                }
            }
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    @Override
    public void addFirst(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        } else{
            tail.next = newNode;
            newNode.prev = tail;
            tail=newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        MyNode<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.data;
    }

    @Override
    public T getFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        return tail.data;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        MyNode<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        if (current == head) {
            removeFirst();
            return;
        } else if (current == tail) {
            removeLast();
            return;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        current.next = current.prev = null;
        current.data = null;
        size--;
    }

    @Override
    public void removeFirst() {
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    @Override
    public void removeLast() {
        if (head == tail) {
            head = tail = null;
        } else{
            tail = tail.prev;
            tail.next=null;
        }
        size--;
    }

    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            MyNode<T> current = head;
            for (int j = 0; j < size - 1 - i; j++) {
                T a = current.data;
                T b = current.next.data;
                if (a.compareTo(b) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                current = current.next;
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        MyNode<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(object)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        MyNode<T> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.data.equals(object)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        MyNode<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(object)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        MyNode<T> current = head;
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = current.data;
            current = current.next;
        }
        return result;
    }

    @Override
    public void clear() {
        MyNode<T> current = head;
        while (current != null) {
            MyNode<T> next = current.next;
            current.data = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        MyNode<T> cursor = head;
        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            T nextItem = cursor.data;
            cursor = cursor.next;
            return nextItem;
        }
    }
    private static class MyNode<E> {
        E data;
        MyNode<E> next;
        MyNode<E> prev;
        MyNode(E data){
            this.data=data;
        }
    }
}
