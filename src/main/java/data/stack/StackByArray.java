package data.stack;

import java.util.Scanner;

public class StackByArray {

    private final int maxSize;
    private final int[] stack;
    private int topIndex;

    public StackByArray(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        topIndex = -1;
    }

    public boolean isEmpty() {
        return topIndex == -1;
    }

    public boolean isFully() {
        return topIndex == maxSize - 1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        if (isFully()) {
            System.out.println("栈已满~~~~~~~~");
            return;
        }
        stack[++topIndex] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空！！！！");
        }
        return stack[topIndex--];
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("空栈~~~");
        }
        for (int i = topIndex; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    public static void main(String[] args) {
        StackByArray stack = new StackByArray(4);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("当前栈中数据");
            stack.show();
            System.out.println("输入p(push)入栈");
            System.out.println("输入pp(pop)出栈");
            System.out.println("输入e(exit)退出");
            String operate = scanner.next();
            switch (operate) {
                case "p" -> {
                    System.out.print("输入数据：");
                    int value = scanner.nextInt();
                    stack.push(value);
                }
                case "pp" -> {
                    try {
                        int value = stack.pop();
                        System.out.println("出栈数据：" + value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "e" -> {
                    System.exit(0);
                }
            }
        }
    }

}
