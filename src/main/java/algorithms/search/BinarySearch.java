package algorithms.search;

/**
 * 二分查找，对查找的序列必须是有序的
 * 思路：
 */
public class BinarySearch {

    public static void searchByRecursion(int[] array, int left, int right, int value) {
        int midIndex = (left + right) / 2;
        //正常情况是走这里退出递归
        if (array[midIndex] == value) {
            System.out.println("index = " + midIndex);
            int tmp = midIndex + 1;
            while(tmp <= right && array[tmp] == value) {
                System.out.println("index = " + tmp);
                tmp++;
            }
            tmp = midIndex - 1;
            while(tmp >= left && array[tmp] == value) {
                System.out.println("index = " + tmp);
                tmp--;
            }
            return;
        }
        //异常情况，就是没有找到数据的时候，是走这里结束递归
        if (left > right) {
            System.out.println("没有找到数据");
            return;
        }
        if (array[midIndex] < value) {
            searchByRecursion(array, midIndex + 1, right, value);
        }
        if (array[midIndex] > value) {
            searchByRecursion(array, 0, midIndex - 1, value);
        }
    }

    public static void main(String[] args) {
        int[] array = {0, 3, 4, 6, 7, 7, 7, 8, 8, 19, 28, 37};
        searchByRecursion(array, 0, array.length - 1, 0);
    }
}
