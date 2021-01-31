package ru.geekbrains.JASD.lesson_05;

import org.omg.CORBA.MARSHAL;

public class Main {

    private static void iterPrint(int i) {
        while (i >= 0) {
            System.out.print(i + " ");
            i--;
        }
        System.out.print(i + " ");
    }

    private static void recPrint(int i) {
        if (i >= 0) {
            System.out.print(i + " ");
            recPrint(--i);
        }
        System.out.print(i + " ");
    }

    private static int op = 0;
    private static void put(int from, int to) {
        System.out.printf("%d -> %d | ", from, to);
        if ((++op % 5) == 0)System.out.println();
    }

    private static void hanoi(int from, int to, int n) {
        int temp = (from ^ to) & 0x3;
        if (n == 1) {
            put(from, to);
        } else {
            hanoi(from, temp, n - 1);
            put(from, to);
            hanoi(temp, to, n - 1);
        }
    }

    private static int[][] moves = {
        {-2, 1}, {-1, 2}, {1, 2}, {2, 1},
        {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };

	private static boolean knight(int[][] desk, int x, int y, int move) {
        desk[y][x] = move;
        if (move > desk.length * desk[0].length - 1) return true;

        int nextX;
        int nextY;
        for (int i = 0; i < moves.length - 1; i++) {
            nextX = x + moves[i][0];
            nextY = y + moves[i][1];
            if (isPossible(desk, nextX, nextY) && knight(desk, nextX, nextY, move + 1))
                return true;
        }
        desk[y][x] = 0;
        return false;
    }

    private static boolean isPossible(int[][] desk, int x, int y) {
        return x >= 0 && x < desk[0].length &&
                y >= 0 && y < desk.length &&
                desk[y][x] == 0;
    }

    private static int[][] movesKing = {{0, 1}, {1, 0}};
    // задача о шахматном короле
    private static boolean king(int[][] desk, int x, int y) {
        desk[y][x] = 2;
        if ((y == desk.length - 1) && (x == desk[0].length - 1)) return true;
        int nextX;
        int nextY;
        for (int i = 0; i < movesKing.length; i++) {
            nextX = x + movesKing[i][0];
            nextY = y + movesKing[i][1];
            if (isPossible(desk, nextX, nextY) && king(desk, nextX, nextY))
                return true;
        }
        desk[y][x] = 0;
        return false;
    }

    private static long pow(int a, int p){
        if (p == 1) return a;
        return a * pow(a, --p);
    }
	public static void main(String[] args) {
        iterPrint(5);
        System.out.println();
        recPrint(5);
        System.out.println();
        hanoi(1, 3, 8);
        System.out.println();
        System.out.println(op); // 2^n
        int[][] desk = new int[8][8];
        knight(desk, 0, 1, 1);
        printDesk(desk);

        System.out.println("2^5 = " + pow(2,5));
        System.out.println("3^7 = " + pow(3,7));

        int matrix[][] = {{0, 0, 0, 0, 1, 0, 0, 1},
                {0,0,1,0,0,0,0,1},
                {1,0,1,0,0,0,0,1},
                {0,1,0,0,1,0,0,1},
                {0,1,0,0,1,0,1,0},
                {0,1,0,0,1,0,0,1},
                {0,1,0,0,1,0,0,1},
                {0,1,0,0,1,0,0,0}};
        System.out.println(king(matrix, 0, 0));
        printDesk(matrix);
    }

    private static void printDesk(int[][] desk) {
        for (int y = 0; y < desk.length; y++) {
            for (int x = 0; x < desk[y].length; x++) {
                System.out.printf("%3d", desk[y][x]);
            }
            System.out.println();
        }
    }
}
