package ru.geekbrains.JASD.lesson_04;

public class DoubleLinkedList {

    private Node head;
    private Node tail;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public void pushHead(Cat c) {
        Node node = new Node(c);
        if (!isEmpty()) {
            head.previous = node;
            node.next = head;
        } else {
            tail = node;
        }
        head = node;
    }

    public void pushTail(Cat c) {
        Node node = new Node(c);
        if (!isEmpty()) {
            tail.next = node;
            node.previous = tail;
        } else {
            head = node;
        }
        tail = node;
    }

    public Cat popHead() {
        if (isEmpty()) return null;
        Cat temp = head.cat;
        head = head.next;
        head.previous = null;
        return temp;
    }

    public Cat popTail() {
        if (isEmpty()) return null;
        Cat temp = tail.cat;
        tail = tail.previous;
        tail.next = null;
        return temp;
    }

    public boolean contains(Cat c) {
        if (isEmpty()) return false;
        Iterator itr = new Iterator(this);
        Node cur = head;
        while (cur != null) {
            if (cur.cat.equals(c)) return true;
            cur = cur.next;
        }
        return false;
    }

    public boolean delete(Cat cat) {
        if (isEmpty()) return false;
        Iterator itr = new Iterator(this);
        Node node = head;
        while (node != null) {
            if (node.cat.equals(cat)) {
                itr.deleteCurrent();
                return true;
            } else {
                node = itr.next();
            }
        }
        return false;
    }
}