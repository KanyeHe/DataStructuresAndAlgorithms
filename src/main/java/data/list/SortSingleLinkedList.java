package data.list;

import data.common.Node;

import java.util.Scanner;

/**
 * 按照序号排序 有序链表
 * 插入的时候需要排序
 */
public class SortSingleLinkedList extends SingleLinkedList {

    /**
     * 1. 找到需要插入的位置 （遍历）
     * 2. @param node.next = tmp.next
     * 3. tmp.next = @param node
     * @param node 新节点
     */
    public void addSort(Node<String> node) {
        Node<String> tmp = head;
        while (tmp.getNext() != null) {
            if (tmp.getNext().getIndex() == node.getIndex()) {
                throw new RuntimeException("index 重复");
            }
            if (tmp.getNext().getIndex() > node.getIndex()) {
                break;
            }
            tmp = tmp.getNext();
        }
        node.setNext(tmp.getNext());
        tmp.setNext(node);
    }

    public void delete(int index) {
        Node<String> tmp = head;
        while (tmp.getNext() != null) {
            if (tmp.getNext().getIndex() == index) {
                break;
            }
            tmp = tmp.getNext();
        }
        //遍历完还是没有找到对应的节点，抛出异常
        if (tmp.getNext() == null) {
            throw new RuntimeException("没有找到节点！！！");
        }
        tmp.setNext(tmp.getNext().getNext());
    }

    public void update(Node<String> node) {
        Node<String> tmp = head;
        while(tmp.getNext() != null) {
            if (tmp.getNext().getIndex() == node.getIndex()) {
                break;
            }
            tmp = tmp.getNext();
        }
        //遍历完还是没有找到对应的节点，抛出异常
        if (tmp.getNext() == null) {
            throw new RuntimeException("没有找到节点！！！");
        }
        tmp.getNext().setData(node.getData());
    }

    /**
     * 除头结点后的 有效数据节点个数
     * @return 有效数据节点个数
     */
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        Node<String> tmp = head;
        int size = 0;
        while (tmp.getNext() != null) {
            size++;
            tmp = tmp.getNext();
        }
        return size;
    }

    /**
     * 思路：
     * 1. 获取有效节点个数
     * 2. 遍历链表到 (size - num) 处即可
     * @param num 倒数个数
     * @return 节点
     */
    public Node<String> backwardsNode(int num) {
        if (isEmpty()) {
            System.out.println("空列表~~~~");
            return null;
        }
        int size = size();
        if (num < 0 || num > size) {
            System.out.println("无效参数！！！！");
            return null;
        }
        Node<String> tmp = head.getNext();
        for (int i = 0; i < size - num; i ++) {
            tmp = tmp.getNext();
        }
        return tmp;
    }

    /**
     * 翻转单链表
     * 思路：
     * 1. 将遍历的每个节点 从头部 插入到新链表中
     * 注意点：
     * 1. 翻转当前链表，head 节点在赋值给 tmp后，需要将head next 节点置为null，否则死循环
     * 2. 一定需要一个 next 指针记录 tmp 的下一个节点，因为在头部插入的时候，tmp会和head置换next域，
     * 如果不记录，遍历就断了
     */
    public void reverse() {
        if (isEmpty() || size() == 1) {
            System.out.println("无需翻转~~~~~~");
            return;
        }
        Node<String> tmp = head.getNext();
        head.setNext(null);
        Node<String> next;
        while (tmp != null) {
            next = tmp.getNext();
            addHead(tmp);
            tmp = next;
        }
    }

    /**
     * 翻转链表得到一个新链表，思路差不多
     * 但是会影响原来的链表，如果想不影响原链表，节点需要 new 即遍历一个 new 一个
     * @return 新链表
     */
    public SortSingleLinkedList reversed() {
        if (isEmpty() || size() == 1) {
            System.out.println("无需翻转~~~~~~");
            return this;
        }
        SortSingleLinkedList reversed = new SortSingleLinkedList();
        Node<String> tmp = head.getNext();
        Node<String> next;
        while (tmp != null) {
            next = tmp.getNext();
            reversed.addHead(tmp);
            tmp = next;
        }
        return reversed;
    }


    public static void main(String[] args) {
        SortSingleLinkedList list = new SortSingleLinkedList();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("当前数据是：");
            list.show();
            System.out.println("请选择如下操作：");
            System.out.println("输入a(add)添加数据；");
            System.out.println("输入d(delete)删除数据；");
            System.out.println("输入p(update)更新数据；");
            System.out.println("输入s(size)有效个数；");
            System.out.println("输入b(backwards)显示倒数第几个；");
            System.out.println("输入r(reverse)翻转链表；");
            System.out.println("输入e(exit)退出；");
            String operate = scanner.next();
            switch (operate) {
                case "a" -> {
                    System.out.print("请输入需要插入的数据：");
                    String value = scanner.next();
                    String[] split = value.split(",");
                    try {
                        list.addSort(new Node<>(Integer.parseInt(split[0].trim()), split[1].trim()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "d" -> {
                    System.out.print("请输入需要删除的数据：");
                    int index = scanner.nextInt();
                    try {
                        list.delete(index);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "p" -> {
                    System.out.print("请选输入要更新的数据：");
                    String value = scanner.next();
                    String[] split = value.split(",");
                    try {
                        list.update(new Node<>(Integer.parseInt(split[0].trim()), split[1].trim()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "s" -> System.out.println("有效个数：" + list.size());
                case "b" -> {
                    System.out.print("请选输入要显示的倒数第个数：");
                    int num  = scanner.nextInt();
                    try {
                        System.out.println("倒数第 " + num + "节点是 " + list.backwardsNode(num));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "r" -> list.reverse();
                case "r2" -> {
                    SortSingleLinkedList reversed = list.reversed();
                    System.out.println("翻转后的新链表：");
                    reversed.show();
                }
                case "e" -> System.exit(0);
            }
        }
    }
}
