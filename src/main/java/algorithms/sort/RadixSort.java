package algorithms.sort;

/**
 * 基数排序， 桶排序的扩展
 * 基本思路：将所有待比较数值统一为同样的数位长度，数位较短的数前面补零，
 * 然后从最低位开始，依次进行依次排序，这样从最低位排序一直到最高位排序完成以后，数列就变成了一个有序序列
 *
 */
import java.util.Arrays;
import java.util.stream.Stream;

public class RadixSort {

    // 主函数：执行基数排序
    public static void radixSort(int[] arr) {
        // 找到数组中最大的数，确定最大位数
        int max = Arrays.stream(arr).max().getAsInt();
        int exp = 1; // 表示位数对应的指数（个位是1，十位是10，百位是100）

        // 逐位（从个位到最高位）进行排序
        while (max / exp > 0) {
            countingSortByDigit(arr, exp);
            exp *= 10;
        }
    }

    // 按照指定的位数进行计数排序
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n]; // 存储每次按位排序后的结果
        int[] count = new int[10]; // 计数器（0-9）

        // 统计每个数字在该位上出现的次数
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // 将计数数组转化为位置数组，决定元素的最终位置
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 从右向左遍历原数组，按计数数组的值放入结果数组
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // 将排序结果复制回原数组
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void countingDigit(int[] arr, int exp) {
        int[] output = new int[arr.length];
        // 0-9 的数字出现的次数
        int[] count = new int[10];

        for (int i : arr) {
            int digit = (i / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void sort(int[] array) {
        int max = Arrays.stream(array).max().getAsInt();
        int exp = 1;
        while ((max / exp) > 0) {
            countingDigit(array, exp);
            exp *= 10;
        }
    }

    // 主程序，测试基数排序
    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        //int[] arr = {170, 45, 2};
        System.out.println("原数组: " + Arrays.toString(arr));

        //radixSort(arr);
        sort(arr);

        System.out.println("排序后: " + Arrays.toString(arr));
    }
}
