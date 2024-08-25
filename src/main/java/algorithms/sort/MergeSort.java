package algorithms.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] tmp = new int[array.length];
        sort(array, tmp, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array, int[] tmp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(array, tmp, left, mid);
            sort(array, tmp, mid + 1, right);
            merge(array, tmp, left, mid, right);
        }
    }


    /**
     * 合并
     * @param array 原数组
     * @param tmp 临时数组
     * @param left 左边有序序列的起点索引
     * @param mid 中间索引
     * @param right 右边有序序列的最右边索引
     */
    public static void merge(int[] array, int[] tmp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;  //右边有序序列的起点索引
        int t = 0; // 临时数组的当前索引
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                tmp[t] = array[i];
                i++;
            } else {
                tmp[t] = array[j];
                j++;
            }
            t++;
        }
        while (i <= mid) {
            tmp[t] = array[i];
            i++;
            t++;
        }
        while (j <= right) {
            tmp[t] = array[j];
            j++;
            t++;
        }
        //将临时数组的数据拷贝到原数组
        t = 0;
        int tmpLeft = left;
        while (tmpLeft <= right) {
            array[tmpLeft] = tmp[t];
            t++;
            tmpLeft++;
        }
    }
}
