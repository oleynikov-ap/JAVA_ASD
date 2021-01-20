package ru.geekbrains.jads.lessonc.online;

import ru.geekbrains.JASD.lesson_03.ElementQueue;
import ru.geekbrains.JASD.lesson_03.PriorQueue;

public class Main {
    private static class Stack {
        private int[] stack;
        private int head;

        public Stack(int size) {
            this.stack = new int[size];
            this.head = -1;
        }

        public boolean isEmpty() {
            return head == -1;
        }

        public boolean isFull() {
            return head == stack.length - 1;
        }

        public boolean push(int i) {
            if (isFull()) return false;
            stack[++head] = i;
            return true;
        }

        public int pop() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head--];
        }

        public int peek() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head];
        }

    }

    private static int checkBrackets(String input) {
        int size = input.length();
        Stack st = new Stack(size);
        for (int i = 0; i < size; i++) {
            char ch = input.charAt(i);
            if (ch == '[' || ch == '(' || ch == '<' || ch == '{') {
                st.push(ch);
            } else if (ch == ']' || ch == ')' || ch == '>' || ch == '}') {
                if (st.isEmpty())
                    return i;

                char op = (char) st.pop();
                if (!((op == '[' && ch == ']') ||
                        (op == '{' && ch == '}') ||
                        (op == '(' && ch == ')') ||
                        (op == '<' && ch == '>'))) {
                    return i;
                }
            }
        }
        if (!st.isEmpty()) {
            return size;
        }
        return -1;
    }
    private static String reverseString(String input) {
        int size = input.length();
        Stack st = new Stack(size);
        for (int i = 0; i < size; i++) {
            char ch = input.charAt(i);
            st.push(ch);
            }
        char[] ch = new char[size];
        int i = 0;
        while (!st.isEmpty()) {
            ch[i++] = (char) st.pop();
        }
        return String.valueOf(ch);
    }

    private static class Queue {
        private int[] queue;
        private int head;
        private int tail;
        private int capacity;

        public Queue(int initial) {
            queue = new int[initial];
            head = 0;
            tail = -1;
            capacity = 0;
        }

        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == queue.length;
        }

        public int length() {
            return capacity;
        }

        public void insert(int i) {
            if (isFull())
                throw new RuntimeException("Queue is full!");
            if (tail == queue.length -1)
                tail = -1;
            queue[++tail] = i;
            capacity++;
        }

        public int remove() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            int temp = queue[head++];
            head %= queue.length; //if (head == queue.length) head = 0;
            capacity--;
            return temp;
        }

    }

    public static void main(String[] args) {
        System.out.println(checkBrackets("<> () [] {} {[() <>]}"));
        System.out.println(reverseString("<> () [] {} {[() <>]}"));


        PriorQueue pqueue = new PriorQueue(7);
        for (int i = 0; i < 7; i++) {
            pqueue.PrQueue[i] = new ElementQueue(i, i);
        }

        System.out.println(pqueue.toString());
        pqueue.insert(new ElementQueue(9, 0));
        pqueue.insert(new ElementQueue(10, 8));
        pqueue.insert(new ElementQueue(5, 1));
        System.out.println(pqueue.toString());
        ElementQueue temp = new ElementQueue();
        for (int i = 0; i < 4; i++) {
            temp = pqueue.remove();
            System.out.println("[" + temp.value + "," + temp.prior + "]");
        }
        System.out.println(pqueue.toString());
    }
}
