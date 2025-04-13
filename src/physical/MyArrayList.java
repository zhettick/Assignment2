package physical;

import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {
    private Object[] array;
    private int size;
    private int capacity = 15;

    public MyArrayList() {
        //no-arg constructor to initialize array list
        array = new Object[capacity];
    }

    @Override
    public void add(T item) {
        //first of all, checking capacity, if we have more items, than capacity we have to increase it with private method, otherwise just add an element to the end; increase size
        if (size == capacity) {
            increaseBuffer();
        }
        array[size++] = item;
    }

    private void increaseBuffer() {
        //we change the value of capacity to bigger and create another array list with this capacity; all elements from first array list move to second; in the end replace the old array list
        capacity = (int) (1.5 * capacity);
        Object[] array2 = new Object[capacity];
        for (int i = 0; i < size; i++) {
            array2[i] = array[i];
        }
        array = array2;
    }

    @Override
    public void set(int index, T item) {
        //first of all, checking if provided index is between 0 and size-1; after that just put our item on index we need
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        array[index] = item;
    }

    @Override
    public void add(int index, T item) {
        //first of all, checking if provided index is between 0 and size; then move all elements after needed index to the right; put element on provided index; increase size
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == capacity) {
            increaseBuffer();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        size++;
        array[index] = item;
    }

    @Override
    public void addFirst(T item) {
        //first of all, checking capacity, if we have more items, than capacity we have to increase it with private method, otherwise move all elements to the right; put item on 0 index; increase size
        if (size == capacity) {
            increaseBuffer();
        }
        for (int i = size; i > 0; i--) {
            array[i] = array[i - 1];
        }
        size++;
        array[0] = item;
    }

    @Override
    public void addLast(T item) {
        //first of all, checking capacity, if we have more items, than capacity we have to increase it with private method, otherwise just add an element to the end; increase size
        if (size == capacity) {
            increaseBuffer();
        }
        array[size++] = item;
    }

    @Override
    public T get(int index) {
        //first of all, checking if provided index is between 0 and size; then just return element on needed index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return (T) array[index];
    }

    @Override
    public T getFirst() {
        //first of all, checking for the size, if size is 0, we can not return anything; if everything is okay, returning first element
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return (T) array[0];
    }

    @Override
    public T getLast() {
        //first of all, checking for the size, if size is 0, we can not return anything; if everything is okay, returning last element
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return (T) array[size - 1];
    }

    @Override
    public void remove(int index) {
        //first of all, checking if provided index is between 0 and size; then checking for the size, if size is 0, we can not remove anything; if everything is okay, move all elements after index to the left; decrease size
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @Override
    public void removeFirst() {
        //first of all, checking for the size, if size is 0, we can not remove anything; if everything is okay, move all elements to the left; decrease size
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @Override
    public void removeLast() {
        //first of all, checking for the size, if size is 0, we can not remove anything; if everything is okay, make last element null; decrease size
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        array[--size] = null;
    }

    @Override
    public void sort() {
        //bubble sort with Comparable<T>
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                Comparable<T> a = (Comparable<T>) array[j];
                T b = (T) array[j + 1];
                if (a.compareTo(b) > 0) {
                    Object temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        //searching object in whole array list and if we find it return index, otherwise -1
        for (int i = 0; i < size; i++) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        //searching object in whole array list starting from the end and if we find it return index, otherwise -1
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        //searching object in whole array list and if we find it return true, otherwise false
        for (int i = 0; i < size; i++) {
            if (array[i].equals(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        //create an array to print it out
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }

    @Override
    public void clear() {
        //make all null and set size as 0
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
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
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            T nextItem = get(cursor);
            cursor++;
            return nextItem;
        }
    }
}
