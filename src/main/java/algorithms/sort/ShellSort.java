package algorithms.sort;

import java.util.Arrays;

/**
 * @author Kanye
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] shell = {8, 9, 1, 7 ,2, 3, 5, 4, 6, 0};
        //shellSortByChange(shell);
        shellSortByMove(shell);
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
     * 希尔排序 移动法， 性能较好
     * @param shell
     */
    private static void shellSortByMove(int[] shell) {

        for (int gap = shell.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < shell.length; i++) {
                int j = i;
                int temp = shell[j];
                if (shell[j] < shell[j - gap]) {
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
