package data.list;

import java.util.Scanner;

/**
 * 单向列表
 * 添加元素直接添加在最后面
 */
public class SingleLinkedList {

    protected final Node<String> head = new Node<>();

    public boolean isEmpty() {
        return head.getNext() == null;
    }

    //在尾部添加
    public void addTail(Node<String> node) {
        Node<String> tmp = head;
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
        }
        tmp.setNext(node);
    }

    /**
     * 从头部插入
     * @param node
     */
    public void addHead(Node<String> node) {
        node.setNext(head.getNext());
        head.setNext(node);
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("空列表");
            return;
        }
        Node<String> tmp = head.getNext();
        do {
            System.out.println(tmp);
            tmp = tmp.getNext();
        } while (tmp != null);
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("当前数据是：");
            list.show();
            System.out.println("请选择如下操作：");
            System.out.println("输入at(addTail)添加数据；");
            System.out.println("输入ah(addHead)添加数据；");
            System.out.println("输入e(exit)退出；");
            String operate = scanner.next();
            switch (operate) {
                case "at" -> {
                    System.out.print("请输入需要插入的数据：");
                    String value = scanner.next();
                    String[] split = value.split(",");
                    list.addTail(new Node<>(Integer.parseInt(split[0].trim()), split[1].trim()));
                }
                case "ah" -> {
                    System.out.print("请输入需要插入的数据：");
                    String value = scanner.next();
                    String[] split = value.split(",");
                    list.addHead(new Node<>(Integer.parseInt(split[0].trim()), split[1].trim()));
                }
                case "e" -> System.exit(0);
            }
        }
    }
}

