package ru.geekbrains.JASD.lesson_04;

public class Node {
    Cat cat;
    Node next;
    Node previous;

    public Node(Cat c) {
        this.cat = c;
    }

    @Override
    public String toString() {
        return String.format("Node(c=%s)", cat);
    }
}