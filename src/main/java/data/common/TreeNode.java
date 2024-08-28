package data.common;

import lombok.Data;

@Data
public class TreeNode<T extends Id> {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            preOrder();
        }
        if (this.right != null) {
            preOrder();
        }
    }

    public T preOrderSearch(int id) {
        if (this.getData().getId() == id) {
            return this.getData();
        }
        T data = null;
        if (this.left != null) {
            data = preOrderSearch(id);
        }
        if (data != null) {
            return data;
        }
        if (this.right != null) {
            data = preOrderSearch(id);
        }
        return data;
    }

    public T infixOrderSearch(int id) {
        return null;
    }

    public T postOrderSearch(int id) {
        return null;
    }

    public T delete(int id) {
        return null;
    }

    public void infixOrder() {
        if (this.left != null) {
            infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            postOrder();
        }
        if (this.right != null) {
            postOrder();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "{" + "data = " + data + "}";
    }
}
