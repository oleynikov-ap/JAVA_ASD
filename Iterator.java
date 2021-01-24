package ru.geekbrains.JASD.lesson_04;

public class Iterator {
    private Node cur;
    private DoubleLinkedList ddl;

    public Iterator(DoubleLinkedList list) {
        this.ddl = list;
        this.cur = list.getHead();
    }

    public void reset() {
        cur = this.ddl.getHead();
    }

    public void atEnd() {
        cur = this.ddl.getTail();
    }

    public Node next() {
        if (!isTail()) {
            cur = cur.next;
        }
        return cur;
    }

    public Node prev() {
        if (!isHead()) {
            cur = cur.previous;
        }
        return cur;
    }

    public Node getCurrent() {
        return cur;
    }

    public boolean isTail() {
        return (cur == null || cur.next == null);
    }

    public boolean isHead() {
        return (cur == null || cur.previous == null);
    }

    public void insertBefore(Cat c) {
        Node node = new Node(c);
        if (cur == null) {
            cur = node;
            this.ddl.setTail(node);
            this.ddl.setHead(node);
        } else if (isHead()) {
            cur.previous = node;
            node.next = cur;
            this.ddl.setHead(node);
        } else {
            node.previous = cur.previous;
            node.next = cur;
            cur.previous.next = node;
            cur.previous = node;
        }
    }

    public void insertAfter(Cat c) {
        Node node = new Node(c);
        if (cur == null) {
            cur = node;
            this.ddl.setTail(node);
            this.ddl.setHead(node);
        } else if (isTail()) {
            cur.next = node;
            node.previous = cur;
            this.ddl.setTail(node);
        } else {
            node.next = cur.next;
            node.previous = cur;
            cur.next.previous = node;
            cur.next = node;
        }
    }

    public void deleteCurrent() {
        if (cur == null) return;
        if (cur.previous == null && cur.next == null) {
            cur = null;
            this.ddl.setHead(cur);
            this.ddl.setTail(cur);
        } else if (cur.previous == null) {
            cur = cur.next;
            cur.previous = null;
            this.ddl.setHead(cur);
        } else if (cur.next == null) {
            cur = cur.previous;
            cur.next = null;
            this.ddl.setTail(cur);
        } else {
            Node next = cur.next;
            Node prev = cur.previous;
            cur.next.previous = cur.previous;
            cur.previous.next = cur.next;
            if (cur.next == null) cur = cur.previous;
            else cur = cur.next;
        }
    }
}