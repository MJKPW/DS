package LinkedList;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private static class Node<T> {

        T data;
        private Node<T> prev;
        private Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Node<T> trav = head;
        while(trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = null;
        size = 0;
    }

    private Node<T> traverse(int index) {
        Node<T> trav;
        if (index < size / 2) {
            trav = head;
            for (int i = 0; i != index; ++i)
                trav = trav.next;
        } else {
            trav = tail;
            for (int i = size - 1; i != index; --i)
                trav = trav.prev;
        }
        return trav;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Incorrect index " + index);
        } else {
            return traverse(index).data;
        }
    }

    public void set(int index, T element) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Incorrect index " + index);
        } else {
            traverse(index).data = element;
        }
    }

    public int indexOf(T element) {
        int index = 0;
        if(tail.data == element) {
            return size - 1;
        }
        Node<T> trav = head;
        while(trav != null) {
            if(trav.data == element)
                return index;
            trav = trav.next;
            index++;
        }
        return -1;
    }

    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

    public void add(T element) {
        if(isEmpty()) {
            head = tail = new Node<>(element, null, null);
        } else {
            tail.next = new Node<>(element, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void add(int index, T element) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Incorrect index " + index);
        } else if(isEmpty()) {
            head = tail = new Node<>(element, null, null);
            size++;
        } else if(index == 0) {
            head.prev = new Node<>(element, null, head);
            head = head.prev;
            size++;
        } else if(index == size) {
            tail.next = new Node<>(element, tail, null);
            tail = tail.next;
            size++;
        } else {
            Node<T> target = traverse(index);
            Node<T> newNode = new Node<>(element, target.prev, target);
            target.prev.next = newNode;
            target.prev = newNode;
            size++;
        }
    }

    public void remove(int index) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Incorrect index " + index);
        } else if( isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        } else if(index == 0) {
            head = head.next;
            size--;
            if(isEmpty())
                tail = null;
            else
                head.prev = null;
        } else if(index == size-1) {
            tail = tail.prev;
            size--;
            if(isEmpty())
                head = null;
            else
                tail.next = null;
        } else {
            Node<T> target = traverse(index);
            target.prev.next = target.next;
            target.next.prev = target.prev;
            target = null;
            size--;
        }
    }

    public void remove(T element) {
        int target = indexOf(element);
        remove(target);
    }

    public void reverseSelf() {
        Node<T> previous = null;
        Node<T> current = head;
        Node<T> next = null;
        while(current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        tail = head;
        head = previous;
    }

    public LinkedList<T> reverse() {
        LinkedList<T> reversed = new LinkedList<T>();
        Node<T> trav = tail;
        while(trav != null) {
            reversed.add(trav.data);
            trav = trav.prev;
        }
        return reversed;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                T target = get(index);
                index++;
                return target;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder().append("[");
        Node<T> trav = head;
        while(trav != null) {
            String gripe = trav.data.toString();
            if(trav.next != null)
                gripe += ", ";
            list.append(gripe);
            trav = trav.next;
        }
        list.append("]");
        return list.toString();
    }

}
