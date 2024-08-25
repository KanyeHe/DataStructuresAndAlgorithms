package algorithms.sort;

import java.util.Arrays;

/**
 * @author Kanye
 * 基本思路：
 * 希尔排序是把记录按下标的一定量分组，对每组使用直接插入排序算法排序
 * 随着增量逐渐减少，每组包含关键词越来越多，当增量减至1，真个文件恰被分成一组，算法便终止
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] shell = {8, 9, 1, 7 ,2, 3, 5, 4, 6, 0};
        //shellSortByChange(shell);
        shellSortByMove(shell);
        //shellSortByChange2(shell);
        System.out.println(Arrays.toString(shell));
    }

    /**
     * 希尔排序 交换法， 性能差
     * 当 gap = 1 时，相当于做了一次冒泡排序
     * @param shell
     */
    private static void shellSortByChange(int[] shell) {
        int temp;
        for (int gap = shell.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < shell.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (shell[j] > shell[j + gap]) {
                        temp = shell[j];
                        shell[j] = shell[j + gap];
                        shell[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 主要就是 对分组后的数据 做插入排序，所以首先要理解插入排序
     * @param array
     */
    public static void shellSortByChange2(int[] array) {
        int tmp;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i - gap;
                while (j >= 0 && array[j] > array[j + gap]) {
                    tmp = array[j];
                    array[j] = array[j + gap];
                    array[j + gap] = tmp;
                    j -= gap;
                }
            }
        }
    }

    /**
     * 希尔排序 移动法， 性能较好
     * @param shell
     */
    private static void shellSortByMove(int[] shell) {

        for (int gap = shell.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < shell.length; i++) {
                int j = i;
                int temp = shell[j];
                if (temp < shell[j - gap]) {
                    while (j - gap >= 0 && temp < shell[j - gap]) {
                        shell[j] = shell[j - gap];
                        j -= gap;
                    }
                    shell[j] = temp;
                }
            }
        }
    }
}
