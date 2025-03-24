package data.tree;

import data.common.Id;
import data.common.Student;
import data.common.TreeNode;

public class SortBinaryTree<T extends Id> {

    private SortTreeNode<T> root;


    public void add(TreeNode<T> node) {
        if (root == null) {
            root = (SortTreeNode<T>) node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("空列表~~~~");
            return;
        }
        root.infixOrder();
    }

    public SortTreeNode<T> search(int id) {
        if (root == null) {
            return null;
        }
        return root.search(id);
    }

    public SortTreeNode<T> searchParent(int id) {
        if (root == null) {
            return null;
        }
        return root.searchParent(id);
    }

    /**
     * 删除
     * 基本思路：
     * 1.删除叶子节点
     *  1.1 先找到需要删除的节点 targetNode
     *  1.2 确定 targetNode的父节点 parentNode
     *  1.3 确定targetNode 是parentNode的左节点还是右节点，然后去删除
     * 2. 删除只有一个子节点的节点
     *  2.1 先找到需要删除的节点 targetNode
     *  2.2 确定 targetNode的父节点 parentNode
     *  2.3 如果targetNode的子节点是左子结点
     *      2.3.1 如果 targetNode 是 parentNode 的左子结点 则 parentNode.left = targetNode.left
     *      2.3.2 如果 targetNode 是 parentNode 的右子结点 则 parentNode.right = targetNode.left
     *  2.4 如果targetNode的子节点是右子结点
     *      2.3.1 如果 targetNode 是 parentNode 的左子结点 则 parentNode.left = targetNode.right
     *      2.3.2 如果 targetNode 是 parentNode 的右子结点 则 parentNode.right = targetNode.right
     * 3. 删除存在两个子节点的节点
     *  3.1 先找到需要删除的节点 targetNode
     *  3.2 确定 targetNode的父节点 parentNode
     *  3.3 从targetNode的右子树找到最小的节点，然后存到临时变量中 tmp
     *  3.4 删除最小节点，将targetNode.data = tmp.data
     * @param id
     * @return
     */
    public T delete(int id) {
        if (root == null) {
            System.out.println("空列表~~~~");
            return null;
        }
        SortTreeNode<T> targetNode = search(id);
        if (targetNode == null) {
            System.out.println("找不到节点~~~~~~");
            return null;
        }
        if (targetNode.getData().getId() == root.getData().getId()) {
            T data = root.getData();
            this.root = null;
            return data;
        }
        SortTreeNode<T> parentNode = searchParent(id);
        if (parentNode == null) { // 待删除前节点存在，且父节点为空，说明待删除节点是根节点
            System.out.println("不可能，绝对不可能");
            return null;
        }
        if (targetNode.getLeft() == null && targetNode.getRight() == null) {
            if (targetNode.getData().getId() == parentNode.getLeft().getData().getId()) {
                parentNode.setLeft(null);
            }
            if (targetNode.getData().getId() == parentNode.getRight().getData().getId()) {
                parentNode.setRight(null);
            }
        }
        if (targetNode.getLeft() == null && targetNode.getRight() != null) {
            if (parentNode.getLeft().getData().getId() == targetNode.getData().getId()) {
                parentNode.setLeft(targetNode.getRight());
            } else {
                parentNode.setRight(targetNode.getRight());
            }
        }
        if (targetNode.getLeft() != null && targetNode.getRight() == null) {
            if (parentNode.getLeft().getData().getId() == targetNode.getData().getId()) {
                parentNode.setLeft(targetNode.getLeft());
            } else {
                parentNode.setRight(targetNode.getLeft());
            }
        }
        if (targetNode.getLeft() != null && targetNode.getRight() != null) {
            SortTreeNode<T> min = ((SortTreeNode<T>) targetNode.getRight()).searchMinNode();
            targetNode.setData(min.getData());
            min = null;
        }
        return targetNode.getData();
    }

    public static void main(String[] args) {
        SortBinaryTree<Student> tree = new SortBinaryTree<>();
        int[] arr = {7, 3, 10, 10, 12, 5, 1, 9};
        for (int i : arr) {
            tree.add(new SortTreeNode<>(new Student(i)));
        }
        tree.infixOrder();
        tree.delete(7);
        tree.infixOrder();
    }

    public static class SortTreeNode<T extends Id> extends TreeNode<T> {

        public SortTreeNode(T data) {
            this.data = data;
        }

        /**
         * 添加节点
         * @param node
         */
        public void add(TreeNode<T> node) {
            if (node == null || node.getData() == null) {
                System.out.println("数据不能为NULL !!!");
                return;
            }
            //判断值，如果值小于当前节点，放在左节点或者左子树
            if (node.getData().getId() < this.data.getId()) {
                if (this.left == null) {
                    this.setLeft(node);
                } else {
                    // 这里因为是继承的原因，所以需要强转一下， TreeNode 没有add()方法
                    SortTreeNode<T> sNode = (SortTreeNode<T>) this.left;
                    sNode.add(node);
                }
            } else if (node.getData().getId() > this.data.getId()) { //放置有节点或者右子树
                if (this.right == null) {
                    this.setRight(node);
                } else {
                    SortTreeNode<T> sNode = (SortTreeNode<T>) this.right;
                    sNode.add(node);
                }
            } else { //最后只有等于的情况，这里就不允许插入相同的值，所以等于的情况就提示一下，并且不做任何处理
                System.out.println("不能插入相同的值， 主键冲突");
            }
        }

        public SortTreeNode<T> search(int id) {
            if (this.getData().getId() == id) {
                return this;
            }
            // 如果当前节点的值大于id，说明在左子树中，则需要去左子树查找
            if (this.getData().getId() > id) {
                if (this.left == null) {
                    return null;
                }
                return ((SortTreeNode<T>)this.left).search(id);
            } else {
                if (this.right == null) {
                    return null;
                }
                return ((SortTreeNode<T>)this.right).search(id);
            }
        }

        public SortTreeNode<T> searchMinNode() {
            SortTreeNode<T> min = this;
            while (this.left != null) {
                min = (SortTreeNode<T>) this.getLeft();
            }
            return min;
        }

        /**
         * 查找父节点
         * @param id
         * @return
         */
        public SortTreeNode<T> searchParent(int id) {
            if ((this.left != null && this.left.getData().getId() == id)
                    || (this.right != null && this.right.getData().getId() == id)) {
                return this;
            }
            //在左子树
            if (this.getData().getId() > id && this.left != null) {
                return ((SortTreeNode<T>)this.left).searchParent(id);
            }
            if (this.getData().getId() < id && this.right != null) {
                return ((SortTreeNode<T>)this.right).searchParent(id);
            }
            //没有父节点
            return null;
        }
    }
}
