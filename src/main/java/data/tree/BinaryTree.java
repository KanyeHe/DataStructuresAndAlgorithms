package data.tree;

import data.common.Student;
import data.common.TreeNode;

/**
 * 为什么要使用 树这种 数据结构？ 树的好处
 *
 */
public class BinaryTree {

    private TreeNode<Student> root;

    public boolean isEmpty() {
        return root == null;
    }

    public void preOrder() {
        if (isEmpty()) {
            System.out.println("Empty Tree ~~~ ");
        }
        root.preOrder();
    }

    public void infixOrder() {
        if (isEmpty()) {
            System.out.println("Empty Tree ~~~ ");
        }
        root.infixOrder();
    }

    public void postOrder() {
        if (isEmpty()) {
            System.out.println("Empty Tree ~~~ ");
        }
        root.postOrder();
    }

    public <T> void add(T data) {

    }

}
