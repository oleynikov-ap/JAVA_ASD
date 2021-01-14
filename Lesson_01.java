package ru.geekbrains.JASD.lesson_01;

import java.math.BigInteger;

public class Lesson_01 {
    public static void main(String[] args) {
        float[] arr1 = {3f, 4f, 1f, 2f, 5f};
        float[] arr2 = {13f, 15f, 10f, 2f, 5f};
        int k;

        System.out.println(intPow(2,5, 0));
        System.out.println(intPow(2,9, 0));

        System.out.println(min(arr1));
        System.out.println(min(arr2));

        System.out.println(average(arr1));
        System.out.println(average(arr2));
    }
//    1. Возведение в степень *используя чётность степени*
//   Сложность О(n).
//    от степени n мы переходим, если она чётна, к n/2, а иначе — к n-1.
//    Будет не более log n переходов
    public static int intPow(int a, int n, int k) {
        if (n == 0) {
            System.out.println("Количество умножений: " + k);
            return 1;
        }
        if (n % 2 == 1) {
            return intPow(a, n - 1, ++k) * a;
        } else {
            int b = intPow(a, n / 2, ++k);
            return b * b;
        }
    }
//    2. Поиск минимального элемента в массиве
//    Сложность О(n).
//    Чтобы найти минимальный элемент нужно пробежать по каждому элементу массива.
    public static int indexMin(float[] arr){
        int index = 0;
        int n = 0;
        for (int i = 1; i < arr.length; i++) {
            n++;
            if (arr[i] < arr[index]) index = i;
        }
        System.out.println("Размер массива: " + arr.length + ". Пройдено элементов: " + n);
        return index;
    }
    public static float min(float[] arr){
        return arr[indexMin(arr)];
    }
//    3. Нахождение среднего арифметического массива
//    Сложность О(n).
//    Чтобы найти среднее арифметическое нужно сложить каждый элемент массива, т.е. нужно пройти все n элементов
    public static float average (float[] arr){
        float summ = 0f;
        int n = 0;
        for (int i = 0; i < arr.length; i++) {
            n++;
            summ += arr[i];
        }
        System.out.println("Размер массива: " + arr.length + ". Пройдено элементов: " + n);
        return summ / arr.length;
    }
}
