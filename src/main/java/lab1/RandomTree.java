package lab1;

import java.util.Vector;
import java.util.Stack;
import java.util.Random;

class RandomTree {
    public Vector<RandomTree> children;
    static Random random = new Random();

    public RandomTree() {
        children = new Vector<RandomTree>();

        while (true) {
            boolean addChild = random.nextBoolean();
            if (addChild) {
                children.add(new RandomTree());
            } else {
                break;
            }
        }
    }

    int recursivePrint(int number, Stack<Boolean> ancestorHasMoreChildren) {
        if (ancestorHasMoreChildren.size() > 0) {
            for (int i = 0; i < ancestorHasMoreChildren.size() - 1; ++i) {
                if (ancestorHasMoreChildren.get(i)) {
                    System.out.print("│  ");
                } else {
                    System.out.print("   ");
                }
            }
            if (ancestorHasMoreChildren.lastElement()) {
                System.out.print("├──");
            } else {
                System.out.print("└──");
            }
        }

        System.out.println("node" + number);

        for (int i = 0; i < children.size(); ++i) {
            ++number;
            if (i == children.size() - 1) {
                ancestorHasMoreChildren.push(false);
            } else {
                ancestorHasMoreChildren.push(true);
            }
            number = children.get(i).recursivePrint(number, ancestorHasMoreChildren);
            ancestorHasMoreChildren.pop();

        }

        return number;
    }

    public void print() {
        recursivePrint(0, new Stack<Boolean>());
    }
}