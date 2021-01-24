package ru.geekbrains.JASD.lesson_04;

public class Main_04 {
    public static void main(String[] args) {
        Cat cat1 = new Cat(1, "Barsik");
        Cat cat2 = new Cat(2, "Murzik");
        Cat cat3 = new Cat(3, "Vajska");
        Cat cat4 = new Cat(4, "Ryzhik");
        Cat cat5 = new Cat(5, "Romik");
        Cat cat6 = new Cat(6, "Homik");
        Cat cat7 = new Cat(7, "Something...");
        DoubleLinkedList ddl = new DoubleLinkedList();
        ddl.pushHead(cat2);
        ddl.pushHead(cat1);
        ddl.pushTail(cat4);
        ddl.pushTail(cat7);
        Iterator itr = new Iterator(ddl);
        for (int i = 0; i < 4; i++) {
            System.out.println(itr.getCurrent());
            itr.next();
        }
        System.out.println("Проверка");
        System.out.println(cat2 + " в очереди? " + ddl.contains(cat2));
        System.out.println(cat5 + " в очереди? " + ddl.contains(cat5));
        System.out.println("Добавление");
        itr.reset();
        itr.insertBefore(cat3);
        itr.next();
        itr.next();
        itr.insertAfter(cat6);
        itr.reset();
        for (int i = 0; i < 6; i++) {
            System.out.println(itr.getCurrent());
            itr.next();
        }
        System.out.println("Удаление");
        ddl.delete(cat3);
        itr.reset();
        for (int i = 0; i < 5; i++) {
            System.out.println(itr.next());
        }
    }
}