package ru.geekbrains.JASD.lesson_06.home;

import java.util.ArrayList;
import java.util.Objects;

public class GardenHome {
    private static class Cat implements Comparable {
        int age;
        String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cat cat = (Cat) o;
            return age == cat.age &&
                    Objects.equals(name, cat.name);
        }

        public Cat(int age) {
            this.age = age;
        }

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
//        public String toString() {
//            return String.format("C(%s, %d)", name, age);
//        }
        public String toString() {
            return " " + this.age + " ";
        }

        @Override
        public int compareTo(Object o) {
            Cat c = (Cat) o;
            return this.age - c.age;
        }
    }
    private static class Tree{
        private static class TreeNode implements Comparable {
            private Cat c;
            public TreeNode left;
            public TreeNode right;

            public TreeNode(Cat c) {
                this.c = c;
            }

            @Override
//            public String toString() {
//                return String.format("TN(%s)", c);
//            }
            public String toString() {return this.c.toString();}

            @Override
            public int compareTo(Object o) {
                if (!(o instanceof Cat))
                    throw new ClassCastException("Not a cat!");
                return ((Cat) c).age - ((Cat) o).age;
            }
        }
        TreeNode root;

        public void insert(Cat c) {
            TreeNode node = new TreeNode(c);
            if (root == null) {
                root = node;
            } else {
                TreeNode current = root;
                TreeNode parent;
                int delta;
                while (true) {
                    parent = current;
                    delta = c.compareTo(current.c);
                    if (delta < 0) {
                        current = current.left;
                        if (current == null) {
                            parent.left = node;
                            return;
                        }
                    } else if (delta > 0) {
                        current = current.right;
                        if (current == null) {
                            parent.right = node;
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
        public Cat find(int age) {
            TreeNode current = root;
            while (current.c.age != age) {
                current = (age < current.c.age) ? current.left : current.right;
                if (current == null) return null;
            }
            return current.c;
        }
        public void preOrderTraverse(TreeNode currentNode) {
            if (currentNode != null) {
                System.out.print(currentNode);
                System.out.print("[");
                preOrderTraverse(currentNode.left);
                System.out.print(",");
                preOrderTraverse(currentNode.right);
                System.out.print("]");
            }
        }
        // LeftRootRight
        public void preOrderTraverseLRR(TreeNode currentNode) {
            if (currentNode != null) {
                preOrderTraverseLRR(currentNode.left);
                System.out.print(currentNode + ",");
                preOrderTraverseLRR(currentNode.right);
            }
        }
        public boolean isSheet(TreeNode currentNode){
            return currentNode.right == null && currentNode.left == null;
        }

        //Если дерево большое, то будет очень большая вложенность, и будет долго выполняться,
		//т.к. будет использовать много ресурсов.
		//требует доработки
		public boolean balance(TreeNode currentNode){
            if (currentNode != null) {
                if (isSheet(currentNode)) return true;
                if (currentNode.right != null && currentNode.left == null){
                    if (isSheet(currentNode.right)) return true;
                    else return false;
                }
                else if (currentNode.right == null && currentNode.left != null) {
                    if(isSheet(currentNode.left)) return true;
                    else return false;
                }
                else return balance(currentNode.left) && balance(currentNode.right);
            }
            else return true;
        }

        public void displayTreeLRR() {
            preOrderTraverseLRR(root);
        }

        // RightLeftRoot
        public void displayTree() {
            preOrderTraverse(root);
        }
        public Cat delete(int age)  {
            TreeNode current = root;
            TreeNode parent = root;
            boolean isLeftChild = true;
            while (current.c.age != age) {
                parent = current;
                if (age < current.c.age) {
                    current = current.left;
                    isLeftChild = true;
                } else  {
                    current = current.right;
                    isLeftChild = false;
                }
                if (current == null) {
                    return null;
                }
            }
            //leaf
            if (current.left == null && current.right == null) {
                if (current == root) {
                    root = null;
                } else if (isLeftChild) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // one ancestor
            else if (current.right == null) {
                if (isLeftChild)
                    parent.left = current.left;
                else
                    parent.right = current.left;
            }
            else if (current.left == null) {
                if (isLeftChild)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
            // two ancestors
            else {
                TreeNode successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
                successor.left = current.left;
            }
            return current.c;
        }

        private TreeNode getSuccessor(TreeNode node) {
            TreeNode current = node.right;
            TreeNode parent = node;
            TreeNode successor = node;
            while (current != null) {
                parent = successor;
                successor = current;
                current = current.left;
            }

            if (successor != node.right) {
                parent.left = successor.right;
                successor.right = node.right;
            }
            return successor;
        }

    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(new Cat(4));
        tree.insert(new Cat(2));
        tree.insert(new Cat(1));
        tree.insert(new Cat(3));
        tree.insert(new Cat(5));
        tree.insert(new Cat(6));
        tree.insert(new Cat(7));
        if (tree.balance(tree.root)) System.out.println("дерево сбалансировано");
        else System.out.println("дерево не сбалансировано");
        tree.displayTree();
        System.out.println();

        tree = new Tree();
        tree.insert(new Cat(4));
        tree.insert(new Cat(2));
        tree.insert(new Cat(1));
        tree.insert(new Cat(3));
        tree.insert(new Cat(6));
        tree.insert(new Cat(5));
        tree.insert(new Cat(7));
        if (tree.balance(tree.root)) System.out.println("дерево сбалансировано");
        else System.out.println("дерево не сбалансировано");
        tree.displayTree();
        System.out.println();

        ArrayList<Tree> garden = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            garden.add(new Tree());
            for (int j = 0; j < 100; j++) {
                garden.get(i).insert(new Cat((int) (Math.random() * 200 - 100)));
            }
            System.out.println(i + 1 + "-е дерево");
            garden.get(i).displayTree();
            System.out.println();
            garden.get(i).displayTreeLRR();
            System.out.println();
        }
    }
}
