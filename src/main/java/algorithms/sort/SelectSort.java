package algorithms.sort;

import java.util.Arrays;

/**
 * @author Kanye
 * 选择排序， 是两两比较选择一个最小（或者最大）的数放在前面
 * 即：假设最前面的数是最小数，然后依次和后面的数进行比较，拿到最小值，然后和假设的最小值进行交换
 * 假设 [4, 3, 2, 1]
 * [1, 3, 2, 4] 找到最小的数是 1 ，所以和 4 交换位置 得到此行
 * [1, 2, 3, 4] 找到最小的数是 2 ，所以和 3 交换位置 得到此行
 * [1, 2, 3, 4] 找到最小的数是 3 ，因为 本来 3 < 4， 所以没有变化 得到此行
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] select = {101, 34, 119, 1};
        for (int i = 0; i < select.length - 1; i++) {
            int minIndex = i;
            int min = select[i];
            for (int j = i + 1; j < select.length; j++) {
                if (min > select[j]) {
                    minIndex = j;
                    min = select[j];
                }
            }
            select[minIndex] = select[i];
            select[i] = min;
        }
        System.out.println(Arrays.toString(select));
    }
}
