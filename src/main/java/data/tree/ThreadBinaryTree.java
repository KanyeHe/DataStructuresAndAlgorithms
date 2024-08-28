package data.tree;

import data.common.Student;
import data.common.ThreadTreeNode;

/**
 * 线索化二叉树，为什么会出现这样的二叉树？
 * 因为 在有n个节点的二次链表中，会含有 n+1 (公式： 2n-(n-1) = n+1) 的空指针域，比较浪费空间
 * 利用空指针域存放改节点在某种遍历次序下的前驱节点（节点的前一个节点）和后继节点（节点的后一个节点）的指针 称为线索化
 * 根据相应遍历顺序，也可分为 前序线索化二叉树，中序线索化二叉树，后续线索化二叉树
 *
 * 注意前驱节点 和 后继节点的概念
 *
 * 线索化后需要注意：
 * 一个节点的左指针既有可能指向的是左子结点，也有可能指向前驱节点，有指针既有可能指向右子节点，也有可能是指向后继节点
 *
 * 此种数的作用？优点？
 */
public class ThreadBinaryTree {

    private final ThreadTreeNode<Student> root;
    private ThreadTreeNode<Student> pre;

    public ThreadBinaryTree(ThreadTreeNode<Student> root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 中序线索化二叉树
     */
    public void infixThreadedThree(ThreadTreeNode<Student> node) {
        if (node == null) {
            return;
        }
        //线索化左子树
        infixThreadedThree(node.getLeft());
        //处理线索化
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(ThreadTreeNode.NodePointerType.THREAD_NODE);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(ThreadTreeNode.NodePointerType.THREAD_NODE);
        }
        pre = node;
        //线索化右子树
        infixThreadedThree(node.getRight());
    }

    public static void main(String[] args) {
        ThreadTreeNode<Student> one = new ThreadTreeNode<>(new Student(1));
        ThreadTreeNode<Student> two = new ThreadTreeNode<>(new Student(2));
        ThreadTreeNode<Student> three = new ThreadTreeNode<>(new Student(3));
        ThreadTreeNode<Student> four = new ThreadTreeNode<>(new Student(4));
        ThreadTreeNode<Student> five = new ThreadTreeNode<>(new Student(5));
        ThreadTreeNode<Student> six = new ThreadTreeNode<>(new Student(6));
        ThreadTreeNode<Student> seven = new ThreadTreeNode<>(new Student(7));
        one.setLeft(two);
        two.setLeft(four);
        two.setRight(five);
        one.setRight(three);
        three.setLeft(six);
        three.setRight(seven);
        ThreadBinaryTree binaryTree = new ThreadBinaryTree(one);
        binaryTree.infixThreadedThree(one);

        //验证线索化是否正确
        System.out.println(five.getLeft());
        System.out.println(five.getRight());
    }
}
