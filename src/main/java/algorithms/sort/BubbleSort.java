package algorithms.sort;

import java.util.Arrays;

/**
 * @author Kanye
 * 冒泡排序，是相邻两个数进行比较
 * 假设 [4, 3, 2, 1]
 * 第一轮比较
 * [3, 4, 2, 1]   （下标）index = 0 和 index = 1 比较，得到此行数据
 * [3, 2, 4, 1]   index = 1 和 index = 2 比较，得到此行数据
 * [3, 2, 1, 4]   index = 2 和 index = 3 比较，得到此行数据
 * 第2轮因为 4 已确认，所以比较就是 前3个
 * [2, 3, 1, 4]
 * [2, 1, 3, 4]
 * 第3轮因为 3、4 已确认，所以比较就是 前2个
 * [1, 2, 3, 4]
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] bubble = {17, 38, 27, 6, 14, 5};

        for (int i = 0; i < bubble.length - 1; i++) {
            for (int j = 0; j < bubble.length - i - 1; j++) {
                if (bubble[j] > bubble[j + 1]) {
                    int temp = bubble[j];
                    bubble[j] = bubble[j + 1];
                    bubble[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(bubble));
    }
}
