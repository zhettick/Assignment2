package physical;

import java.util.Iterator;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    public MyLinkedList() {
    }

    @Override
    public void add(T item) {
        //make new node with item; if it is empty list, head and tail are the same(node), otherwise now tail is new node; increase size
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void set(int index, T item) {
        //first of all, checking if provided index is between 0 and size-1; after that search for element on this index depending on which side it is closer; then just put the item
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
        //first of all, checking if provided index is between 0 and size; create new node with item; check for the index(is it head or tail or somewhere in the middle); shift(if need) elements to the right and add a new node; increase size
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        MyNode<T> newNode = new MyNode<>(item);
        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else if (index == size) {
            newNode.prev = tail;
            if (tail != null) {
                tail.next = newNode;
            }
            tail = newNode;
            if (head == null) {
                head = newNode;
            }
        } else {
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
        //create new node with item; if it is empty list, head and tail are the same(node), otherwise shift elements to the right and add a new node; increase size
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
        //create new node with item; if it is empty list, head and tail are the same(node), otherwise add a new node as a tail; increase size
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        //first of all, checking if provided index is between 0 and size; after that search for element on this index depending on which side it is closer; then just return element on needed index
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
        //check for the size(head == null), if it is empty, we can not return anything; then return head
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        return head.data;
    }

    @Override
    public T getLast() {
        //check for the size(tail == null), if it is empty, we can not return anything; then return tail
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        return tail.data;
    }

    @Override
    public void remove(int index) {
        //first of all, checking if provided index is between 0 and size; then checking for the size, if size is 0, we can not remove anything; if everything is okay, remove item(depending on index: head, tail, middle); decrease size
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
        //if head is tail, make both of them null, else remove frist element and move head to the left; decrease size
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
        //if head is tail, make both of them null, else remove last element; decrease size
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    @Override
    public void sort() {
        //bubble sort with Comparable<T>
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
        //searching object in whole linked list and if we find it return index, otherwise -1
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
        //searching object in whole linked list starting from the end and if we find it return index, otherwise -1
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
        //searching object in whole linked list and if we find it return true, otherwise false
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
        //create an array to print it out
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
        //make all null and set size as 0
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
        //return size of array list
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        //used to iterate through elements in order
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

        MyNode(E data) {
            this.data = data;
        }
    }
}