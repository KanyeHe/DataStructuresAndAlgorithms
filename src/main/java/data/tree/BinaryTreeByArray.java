package data.tree;

/**
 * 顺序存储二叉树 用数组实现
 * 其存储方式可以和数组互换（映射）
 * 特点：
 * 1. 必须满足完全二叉树
 * 2. 与数组映射后第n个元素的左子结点 数组下标为 2n + 1
 * 3. ~~第n个元素的右子节点下标 为 2n + 2
 * 4. ~~第n个元素的父节点下标为 (n - 1)/2
 * 注意，n表示二叉树中的第几个元素，0开始
 */
public class BinaryTreeByArray {

    private final int[] array;

    public BinaryTreeByArray(int[] array) {
        this.array = array;
    }

    public boolean isEmpty() {
        return array == null || array.length == 0;
    }

    public int maxIndex() {
        return array.length - 1;
    }

    private void preOrder(int index) {
        if (isEmpty()) {
            System.out.println("Empty ~~~~");
        }
        System.out.println(array[index]);
        int leftIndex = (index << 1) + 1;
        if (leftIndex <= maxIndex()) {
            preOrder(leftIndex);
        }
        int rightIndex = (index << 1) + 2;
        if (rightIndex <= maxIndex()) {
            preOrder(rightIndex);
        }
    }

    public void preOrder() {
        preOrder(0);
    }

    private void infixOrder(int index) {
        if (isEmpty()) {
            System.out.println("Empty ! ");
        }
        int leftIndex = (index << 1) + 1;
        if (leftIndex <= maxIndex()) {
            infixOrder(leftIndex);
        }
        System.out.println(array[index]);
        int rightIndex = (index << 1) + 2;
        if (rightIndex <= maxIndex()) {
            infixOrder(rightIndex);
        }
    }

    public void infixOrder() {
        infixOrder(0);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        BinaryTreeByArray binaryTreeByArray = new BinaryTreeByArray(array);
        binaryTreeByArray.preOrder();
        System.out.println("中序遍历");
        binaryTreeByArray.infixOrder();
    }
}
