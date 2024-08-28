package data.common;

import lombok.Data;

@Data
public class ThreadTreeNode<T extends Id> {
    private T data;
    private ThreadTreeNode<T> left;
    private ThreadTreeNode<T> right;
    private NodePointerType leftType;
    private NodePointerType rightType;

    public ThreadTreeNode(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" + "data = " + data + "}";
    }

    public enum NodePointerType {
        /**
         * 表示节点的左或者右指针指向了左子结点或者右子节点
         */
        CHILD_NODE,
        /**
         * 表示节点的左或者右指针指向了前驱或者后继节点
         */
        THREAD_NODE
    }
}
