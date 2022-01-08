package DynamicArray;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class Dynamic<T> implements Iterable<T> {

    private int size;
    private int capacity;
    private T[] array;

    public Dynamic() { this(10); }

    public Dynamic(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    private void Reallocate(int newCapacity) {
        if(newCapacity < 0)
            throw new IllegalArgumentException("negative capacity " + newCapacity );
        else if(newCapacity < size) size = newCapacity;
        T[] temp = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, temp, 0, size);
        array = temp;
        capacity = newCapacity;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public T[] toArray() { return array; }

    public void clear() {
        for(int i = 0; i != size; ++i)
            array[i] = null;
        size = 0;
    }

    public int indexOf(T element) {
        for(int i = 0; i != size; ++i) {
            if(array[i] == element)
                return i;
        }
        return -1;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public void add(T input) {
        if(size == capacity) Reallocate((2*capacity));
        array[size] = input;
        size++;
    }

    public void add(int index, T element) {
        if(index < 0) throw new IllegalArgumentException("negative index " + index);
        else if(size == capacity) Reallocate((2*capacity));
        T temp1 = null, temp2;
        size++;
        for(int i = index; i <= size; ++i) {
            if(i == index) {
                temp1 = array[index];
                array[index] = element;
                continue;
            }
            temp2 = array[i];
            array[i] = temp1;
            temp1 = temp2;
        }
    }

    public void remove(int index) {
        if(index < 0) throw new IllegalArgumentException("negative index " + index);
        else if(index >= size)  throw new IllegalArgumentException("index out of bounds " + index);
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
    }

    public void remove(T element) {
        int index = indexOf(element);
        if(index == -1) throw new IllegalArgumentException("Element "+element+" not found");
        else remove(index);
    }

    public T get(int index) {
        if(index < 0) throw new IllegalArgumentException("Negative index "+ index);
        if(index >= size) throw new IllegalArgumentException("Index out of range "+ index);
        return array[index];
    }

    public void set(int index, T replacement) {
        if(index < 0) throw new IllegalArgumentException("Negative index "+ index);
        if(index >= size) throw new IllegalArgumentException("Index out of range "+ index);
        array[index] = replacement;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() { return index < size; }
            @Override
            public T next() { return array[index++]; }
        };
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(int i = 0; i != size; ++i) {
            String temp = array[i] + "";
            if(i != size-1)
                temp += ", ";
            builder.append(temp);
        }
        builder.append("]");
        return builder.toString();
    }
}
