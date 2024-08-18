package data.list;

import java.util.Scanner;

/**
 * 双向链表
 * 遍历方式和 单向列表差不多，只是既可以向后 也可以向前遍历
 *
 */
public class DoubleWardsLinkedList {

    private final DNode<String> head = new DNode<>();

    public boolean isEmpty() {
        return head.getNext() == null;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("空列表");
            return;
        }
        DNode<String> tmp = head.getNext();
        do {
            System.out.println(tmp);
            tmp = tmp.getNext();
        } while (tmp != null);
    }

    /**
     * 添加在链表尾部
     * @param node
     */
    public void addTail(DNode<String> node) {
        DNode<String> tmp = head;
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
        }
        node.setNext(tmp.getNext());
        node.setPrevious(tmp);
        tmp.setNext(node);
    }

    /**
     * 添加在头部
     * @param node
     */
    public void addHead(DNode<String> node) {
        node.setNext(head.getNext());
        node.setPrevious(head);
        //如果不是空列表，要将 head的下一个元素的前指针指向插入的节点
        if (!isEmpty()) {
            head.getNext().setPrevious(node);
        }
        head.setNext(node);
    }

    /**
     * 有序插入
     * @param node
     */
    public void addSort(DNode<String> node) {
        DNode<String> tmp = head;
        while (tmp.getNext() != null) {
            if (tmp.getNext().getIndex() == node.getIndex()) {
                throw new RuntimeException("重复元素!!!!!!!!");
            }
            if (tmp.getNext().getIndex() > node.getIndex()) {
                break;
            }
            tmp = tmp.getNext();
        }
        node.setNext(tmp.getNext());
        node.setPrevious(tmp);
        tmp.getNext().setPrevious(node);
        tmp.setNext(node);
    }

    public void update(Node<String> node) {
        DNode<String> tmp = head;
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

    public void delete(int index) {
        DNode<String> tmp = head;
        while(tmp.getNext() != null) {
            if (tmp.getNext().getIndex() == index) {
                break;
            }
            tmp = tmp.getNext();
        }
        tmp.setNext(tmp.getNext().getNext());
        //如果 tmp.getNext() 是最后一个元素，不判空就是存在 NPE
        if (tmp.getNext() != null) {
            tmp.getNext().setPrevious(tmp);
        }
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        DNode<String> tmp = head;
        int size = 0;
        while (tmp.getNext() != null) {
            size++;
            tmp = tmp.getNext();
        }
        return size;
    }

    public static void main(String[] args) {
        DoubleWardsLinkedList list = new DoubleWardsLinkedList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("当前数据：");
            list.show();
            System.out.println("请选择如下操作：");
            System.out.println("输入at(add)尾部添加数据；");
            System.out.println("输入ah(add)头部添加数据；");
            System.out.println("输入d(delete)删除数据；");
            System.out.println("输入p(update)更新数据；");
            System.out.println("输入s(size)有效个数；");
            System.out.println("输入e(exit)退出；");
            String operate = scanner.next();
            switch (operate) {
                case "at" -> {
                    System.out.print("请输入需要插入的数据：");
                    String value = scanner.next();
                    String[] split = value.split(",");
                    try {
                        list.addTail(new DNode<>(Integer.parseInt(split[0].trim()), split[1].trim()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "ah" -> {
                    System.out.print("请输入需要插入的数据：");
                    String value = scanner.next();
                    String[] split = value.split(",");
                    try {
                        list.addHead(new DNode<>(Integer.parseInt(split[0].trim()), split[1].trim()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "a" -> {
                    System.out.print("请输入需要插入的数据：");
                    String value = scanner.next();
                    String[] split = value.split(",");
                    try {
                        list.addSort(new DNode<>(Integer.parseInt(split[0].trim()), split[1].trim()));
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
                case "s" -> {
                    System.out.println("有效数据个数：" + list.size());
                }
                case "e" -> System.exit(0);
            }
        }
    }
}
