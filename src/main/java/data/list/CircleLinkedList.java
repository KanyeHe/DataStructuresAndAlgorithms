package data.list;

import data.common.Node;

import java.util.Scanner;

/**
 * 环形链表
 */
public class CircleLinkedList {

    private final Node<String> head = new Node<>();
    //感觉这个东西要不要都没有什么影响，有的话只能说方便从 理论意义上的尾部 插入
    private final Node<String> tail = new Node<>();

    public boolean isEmpty() {
        return head.getNext() == null;
    }

    public void add(Node<String> node) {
        if (isEmpty()) {
            head.setNext(node);
            node.setNext(node);
        } else {
            node.setNext(head.getNext());
            tail.getNext().setNext(node);
        }
        tail.setNext(node);
    }

    /**
     * 根据输入的 num ，从第一个开始数，数到num的节点出列，然后从出列节点的下一个节点从新开始，以此类推直到剩最后一个节点
     * @param num 第几个节点出列
     */
    public void joseph(int num) {
        while (true) {
            if (isEmpty()) {
                System.out.println("出列结束~~~~~~~");
                break;
            }
            if (head.getNext() == tail.getNext()) {
                System.out.println(head.getNext());
                break;
            }
            for (int i = 1; i < num; i++) {
                head.setNext(head.getNext().getNext());
                tail.setNext(tail.getNext().getNext());
            }
            System.out.println(head.getNext());
            tail.getNext().setNext(head.getNext().getNext());
            head.setNext(head.getNext().getNext());

        }
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("空列表~~~~");
            return;
        }
        Node<String> tmp = head;
        do {
            System.out.println(tmp.getNext());
            tmp = tmp.getNext();
        } while (tmp.getNext() != head.getNext());
    }

    public static void main(String[] args) {
        CircleLinkedList list = new CircleLinkedList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("当前数据：");
            list.show();
            System.out.println("请选择如下操作：");
            System.out.println("输入at(add)尾部添加数据；");
            System.out.println("输入d(delete)删除数据；");
            System.out.println("输入p(update)更新数据；");
            System.out.println("输入j(joseph)约瑟夫环；");
            System.out.println("输入e(exit)退出；");
            String operate = scanner.next();
            switch (operate) {
                case "a" -> {
                    System.out.print("请输入需要插入的数据：");
                    String value = scanner.next();
                    String[] split = value.split(",");
                    try {
                        list.add(new Node<>(Integer.parseInt(split[0].trim()), split[1].trim()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "j" -> {
                    System.out.print("请输入报数：");
                    int num = scanner.nextInt();
                    list.joseph(num);
                }
                case "e" -> System.exit(0);
            }
        }
    }

}
