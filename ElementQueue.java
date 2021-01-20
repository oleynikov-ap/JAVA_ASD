package ru.geekbrains.JASD.lesson_03;

public class ElementQueue {
    public int value;
    public int prior;

    public ElementQueue(){};

    public ElementQueue(int value, int prior){
        this();
        this.value = value;
        this.prior = prior;
    }
}
