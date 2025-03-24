package algorithms.sort;


import java.util.Arrays;

/**
 * 堆排序
 * 基本思路
 * 1. 将待排序序列构造成一个大顶堆， 整个队列的最大值就是堆顶的根节点
 *
 * 2. 将根节点与末尾元素进行交换，此时末尾元素是最大值
 * 3. 然后将剩余的n - 1个元素重复 上述 1、2步骤，知道只有一个元素的大顶堆，结束
 */
public class HeapSort {

    public static void main(String[] args) {

        int[] array = {4, 6, 8, 5, 9};
        //将无序队列构建成一个堆，这里是大顶堆，因为是升序排序，如果是降序则构建为小顶堆
        //由下往上，第一个非叶子节点的 索引 就是 （array.length / 2 - 1）
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        int tmp = 0;
        //交换最大值，然后再调整堆，因为将堆顶元素放在最后 所以 i 初始 是 array.length - 1
        // 因为剩下最后一个元素的时候，没必要再调整，所以 i > 0, 不需要 i >= 0
        for (int i = array.length - 1; i > 0; i--) {
            tmp = array[i];
            array[i] = array[0];
            array[0] = tmp;
            adjustHeap(array, 0, i);
        }

        System.out.println(Arrays.toString(array));
    }

    public static void adjustHeap(int[] array, int i, int length) {
        int tmp = array[i];
        for (int index = i * 2 + 1; index < length; index = index * 2 + 1) {
            if (index + 1 < length && array[index] < array[index + 1]) {
                index++;
            }
            if (array[index] > tmp) {
                array[i] = array[index];
                i = index;
            } else {
                break;
            }
        }
        array[i] = tmp;
    }
}
