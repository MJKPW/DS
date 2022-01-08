package LinkedList;

public class SingleLinkedList<T> {

    private int size = 0;
    private Node<T> head = null;

    private static class Node<T> {
        T data;
        private Node<T> next;
        public Node(T data, Node<T> next) {
            this.data = data;
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

    private Node<T> traverse(int index) {
        Node<T> trav = head;
        for(int i = 0; i != index; ++i)
            trav = trav.next;
        return trav;
    }

    public void add(T data) {
        if(isEmpty()) {
            head = new Node<T>(data, null);
        } else {
            Node<T>last = traverse(size-1);
            last.next = new Node<>(data, null);
        }
        size++;
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> curr = head;
        Node<T> next = null;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append("[");
        Node<T> trav = head;
        while(trav != null) {
            builder.append(trav.data);
            if(trav.next != null) {
                builder.append(", ");
            }
            trav = trav.next;
        }
        builder.append("]");
        return builder.toString();
    }
}
