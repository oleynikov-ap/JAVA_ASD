package ru.geekbrains.JASD.lesson_03;

public class PriorQueue {

    public static ElementQueue[] PrQueue;
    public static int size;

    public PriorQueue(int size) {
        this.size = size;
        this.PrQueue = new ElementQueue[size];
    }

    public PriorQueue(ElementQueue[] PrQueue) {
        this(PrQueue.length - 1);
        this.PrQueue = PrQueue;
    }

    public static void insert(ElementQueue ElQ){
        if (size >= PrQueue.length) {
            ElementQueue[] temp = PrQueue;
            PrQueue = new ElementQueue[size * 2 + 1];
            System.arraycopy(temp, 0, PrQueue, 0, size);
        }
        PrQueue[size++] = ElQ;
    }

    public ElementQueue remove() {
        int index = 0;
        for (int i = 1; i < size; i++) {
            if (PrQueue[index].prior > PrQueue[i].prior) index = i;
        }
        ElementQueue temp = PrQueue[index];
        System.arraycopy(PrQueue, index + 1, PrQueue, index, size - index);
        size--;
        return temp;
    }
    @Override
    public String toString() {
        if (PrQueue == null)
            return "null";
        if (size == 0)
            return "[]";

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < size ; i++) {
            b.append("[").append(PrQueue[i].value).append(",").append(PrQueue[i].prior).append("]");
            if (i != size -1 ) b.append(";");
        }
        return b.toString();
    }

}
