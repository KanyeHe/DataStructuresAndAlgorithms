package algorithms.sort;

import java.util.Arrays;

/**
 * @author Kanye
 * 插入排序 基本思路是将待排序的数据看成一个有序列表和无序列表，然后挨个从无序列表中选择数据插入到有序列表中
 * 假设数据 [17, 3, 25, 14, 20, 9]，将 第一个数17，看成一个有序列表，其余数据为一个无序列表
 * 第一次插入 ，选择 无序列中第一个 3 和 有序列比较得到
 * [3, 17, 25, 14, 20, 9]， 此时，有序列表变成了 [3, 17]， 以此类推
 * 第二次插入
 * [3, 17, 25, 14, 20, 9]
 * 第三次插入
 * [3, 14, 17, 25, 20, 9]
 * 第四次插入
 * [3, 14, 17, 20, 25, 9]
 * 第五次插入
 * [3, 9, 14, 17, 20, 25]
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] insert = {17, 3, 25, 14, 20, 9};

        for (int i = 1; i < insert.length; i++) {
            int waitInsert = insert[i];
            int waitIndex = i - 1;
            while (waitIndex >= 0 && waitInsert < insert[waitIndex]) {
                insert[waitIndex + 1] = insert[waitIndex];
                waitIndex--;
            }
            //当while结束后，就能知道待插入的位置是 waitIndex + 1 的地方
            insert[waitIndex + 1] = waitInsert;
        }
        System.out.println(Arrays.toString(insert));
    }
}
