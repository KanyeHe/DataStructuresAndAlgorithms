package data.queue;

/**
 * @author Kanye
 */

import java.util.Scanner;

/**
 * 在 {@link QueueByArray} 中，根据数组模拟了队列，简单的实现后发现，只能使用一次，
 * 也就是当 front == maxSize -1 后，虽然队列中的数据已经全部出列，但是无法再向其中添加数据了
 * 所以现在需要模拟一个环形队列，以便于重复使用
 * 注意事项：
 * front 和 rear 的初始值都是 0
 * front == rear 时 队列为空
 * (rear + 1) % maxSize == front 时 队列为满
 * 队列中有效的数据个数 ： (rear + maxSize - front) % maxSize
 *
 * 有个缺点，实际有效数据是 maxSize - 1
 */
public class CircleQueueByArray {
    private final int maxSize;
    private final int[] queue;
    private int front;
    private int rear;

    public CircleQueueByArray(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
        front = rear = 0;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public void add(int value) {
        if (isFull()) {
            System.out.println("队列已满！！！！");
            return;
        }
        queue[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    public int delete() {
        if (isEmpty()) {
            System.out.println("队列是空的~~~");
            throw new RuntimeException("Empty");
        }
        int value = queue[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 队列中的有效个数
     * @return 队列中有效数
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("空列表~~~~~");
            return;
        }
        int point = front;
        do {
            System.out.println(queue[point++]);
        } while (point % maxSize != rear);
    }

    public static void main(String[] args) {
        CircleQueueByArray queue = new CircleQueueByArray(5);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("当前数据是：");
            queue.show();
            System.out.println("请选择如下操作：");
            System.out.println("输入a(add)添加数据；");
            System.out.println("输入d(delete)删除数据；");
            System.out.println("输入e(exit)退出；");
            String operate = scanner.next();
            switch (operate) {
                case "a" -> {
                    System.out.print("请输入需要插入的数据：");
                    int value = scanner.nextInt();
                    queue.add(value);
                }
                case "d" -> System.out.println("删除数: " + queue.delete());
                case "e" -> System.exit(0);
            }
        }
    }
}
