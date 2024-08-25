package algorithms.sort;

import java.util.Arrays;

/**
 * 快速排序是对冒泡排序的改进，
 * 基本思想：
 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另一部分的所有数据要小，
 * 然后再按照此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此使得整个数据变成有序序列
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {-9, 78, 0 , 23, -56, 70};
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array, int left, int right) {
        int leftIndex = left;
        int rightIndex = right;
        int pivot = array[(leftIndex + rightIndex) / 2];
        int tmp;
        while (leftIndex < rightIndex) {
            //从左边找出 比 pivot 要大的数据
            while (array[leftIndex] < pivot) {
                leftIndex++;
            }
            //从右边找出 比 pivot 要小的数据
            while (array[rightIndex] > pivot) {
                rightIndex--;
            }
            // 如果 leftIndex >= rightIndex 说明 pivot 的左边的值都比 pivot 小，
            // 右边的值都比 pivot 大，直接退出循环
            if (leftIndex >= rightIndex) {
                break;
            }
            //否则交换左右两边的值
            tmp = array[leftIndex];
            array[leftIndex]  = array[rightIndex];
            array[rightIndex] = tmp;
            //如果交换完以后发现 array[leftIndex] == pivot，则 rightIndex--
            if (array[leftIndex] == pivot) {
                rightIndex--;
            }
            //如果交换完以后发现 array[rightIndex] == pivot，则 leftIndex++
            if (array[rightIndex] == pivot) {
                leftIndex++;
            }
        }
        if (leftIndex == rightIndex) {
            leftIndex++;
            rightIndex--;
        }
        if (left < rightIndex) {
            sort(array, left, rightIndex);
        }
        if (right > leftIndex) {
            sort(array, leftIndex, right);
        }
    }
}

