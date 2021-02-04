package ru.geekbrains.JASD.lesson_07.home;

import ru.geekbrains.jads.lessonc.online.Queue;
import ru.geekbrains.jads.lessonc.online.Stack;

public class Manor {

    private static class Graph {
        private class Vertex {
            char label;
            boolean wasVisited;
            int moveFrom; //откуда пришел

            public Vertex(char label) {
                this.label = label;
                this.wasVisited = false;
                this.moveFrom = -1;
            }

            @Override
            public String toString() {
                return String.format("V=%c", label);
            }
        }

        private final int MAX_VERTICES = 16;
        private Vertex[] vertexList;
        private int[][] adjacencyMatrix;
        private int currentSize;

        public Graph() {
            vertexList = new Vertex[MAX_VERTICES];
            adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
            currentSize = 0;
        }
        public void addVertex(char label) {
            vertexList[currentSize++] = new Vertex(label);
        }
        public void addEdge(int start, int end) {
            adjacencyMatrix[start][end] = 1; // change 1 to weight for weight
            adjacencyMatrix[end][start] = 1; // delete this for direction
        }
        public void displayVertex(int v) {
            System.out.print(vertexList[v] + " ");
        }
        private int getUnvisitedVertex(int current) {
            for (int i = 0; i < currentSize; i++) {
                if (adjacencyMatrix[current][i] == 1 &&
                        !vertexList[i].wasVisited) {
                    return i;
                }
            }
            return -1;
        }
        public void depthTraverse() {
            Stack stack = new Stack(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            displayVertex(0);
            stack.push(0);
            while (!stack.isEmpty()) {
                int v = getUnvisitedVertex(stack.peek());
                if (v == -1) {
                    stack.pop();
                } else {
                    vertexList[v].wasVisited = true;
                    displayVertex(v);
                    stack.push(v);
                }
            }
            resetFlags();
        }
        public void widthTraverse() {
            Queue queue = new Queue(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            queue.insert(0);
            while (!queue.isEmpty()) {
                int current = queue.remove();
                displayVertex(current);
                int next;
                while ((next = getUnvisitedVertex(current)) != -1) {
                    vertexList[next].wasVisited = true;
                    queue.insert(next);
                }
            }
            resetFlags();
        }
        public int getIndex(char label){
            for (int i = 0; i < vertexList.length; i++) {
                if (vertexList[i].label == label) return i;
            }
            return -1;
        }

        public void findWay(char target) {
            Queue queue = new Queue(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            queue.insert(0);
            while (!queue.isEmpty()) {
                int current = queue.remove();
                int next;
                while ((next = getUnvisitedVertex(current)) != -1) {
                    vertexList[next].wasVisited = true;
                    vertexList[next].moveFrom = current;
                    if (vertexList[next].label == target) return;
                    queue.insert(next);
                }
            }
            resetFlags();
        }
        private void resetFlags() {
            for (int i = 0; i < currentSize; i++) {
                vertexList[i].wasVisited = false;
                vertexList[i].moveFrom = -1;
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex('a');
        g.addVertex('b');
        g.addVertex('c');
        g.addVertex('d');
        g.addVertex('e');
        g.addVertex('f');
        g.addVertex('g');
        g.addVertex('h');
        g.addVertex('i');
        g.addVertex('j');
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 6);
        g.addEdge(1, 4);
        g.addEdge(4, 2);
        g.addEdge(4, 8);
        g.addEdge(2, 8);
        g.addEdge(8, 5);
        g.addEdge(8, 9);
        g.addEdge(5, 9);
        g.addEdge(5, 3);
        g.addEdge(7, 9);
        g.addEdge(3, 7);
        g.addEdge(3, 6);
        g.depthTraverse();
        System.out.println();
        g.widthTraverse();
        System.out.println();

        char findChar = 'h';
        g.findWay(findChar);
        int index = g.getIndex(findChar);
        if (g.vertexList[index].wasVisited){
            Stack stack = new Stack(g.MAX_VERTICES);
            stack.push(index);
            while (index != 0){
                index = g.vertexList[index].moveFrom;
                stack.push(index);
            }
            while (!stack.isEmpty()) {
                g.displayVertex(stack.pop());
            }
            g.resetFlags();
        }
    }
}
