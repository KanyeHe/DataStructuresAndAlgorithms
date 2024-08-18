package data.queue;

/**
 * @author Kanye
 */

/**
 * 队列 由 数组实现
 * 应用场景：先进先出； 字面含义，排队的场景
 * 实现思路：
 * 定义一个 数组， 数据长度则是最大容量
 * 队列有输入和输出，所以 由两个变量 front 和 rear 来控制队列的前端 和 后端
 * front 随着数据的输出而改变
 * rear 随着数据的输入而改变
 *
 * front == rear 时，队列为空
 * rear == maxSize - 1 时，队列为满
 */
public class QueueByArray {

    private final int[] queue;
    private final int maxSize;
    private int front;
    private int rear;

    public QueueByArray(int maxSize) {
        queue = new int[maxSize];
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public void add() {
        if (isFull()) {
            System.out.println("队列已经满了！");
            return;
        }
        rear++;
        queue[rear] = 1;
        System.out.println("数据 1 入列！");
    }

    public void delete() {
        if (isEmpty()) {
            System.out.println("队列是空的！");
            return;
        }
        front++;
        System.out.printf("数据 %d 出列！", queue[front]);
        System.out.println();
    }

    public static void main(String[] args) {
        QueueByArray queueByArray = new QueueByArray(5);
        queueByArray.delete();
        queueByArray.add();
        queueByArray.add();
        queueByArray.delete();
        queueByArray.delete();
        queueByArray.delete();
        queueByArray.add();
        queueByArray.add();
        queueByArray.add();
        queueByArray.delete();
        queueByArray.add();
        queueByArray.delete();
        queueByArray.delete();
        queueByArray.delete();
    }

}
