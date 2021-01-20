package ru.geekbrains.JASD.lesson_03;

public class Deque {
    private int[] deque;
    private int head;
    private int tail;
    private int capacity;

    public Deque(int initial) {
        deque = new int[initial];
        head = 0;
        tail = -1;
        capacity = 0;
    }

    public boolean isEmpty() {
        return capacity == 0;
    }

    public boolean isFull() {
        return capacity == deque.length;
    }

    public int length() {
        return capacity;
    }

    public void insertFirst(int i) {
        if (isFull())
            throw new RuntimeException("Deque is full!");
        if (head == 0)
            head = deque.length;
        deque[--head] = i;
        capacity++;
    }

    public void insertLast(int i) {
        if (isFull())
            throw new RuntimeException("Deque is full!");
        if (tail == deque.length -1)
            tail = -1;
        deque[++tail] = i;
        capacity++;
    }

    public int removeFirst() {
        if (isEmpty()) throw new RuntimeException("Deque is empty");
        int temp = deque[head++];
        head %= deque.length;
        capacity--;
        return temp;
    }

    public int removeLast() {
        if (isEmpty()) throw new RuntimeException("Deque is empty");
        int temp = deque[tail--];
        if (tail == -1) tail = deque.length - 1;
        capacity--;
        return temp;
    }
}