package algorithms;

/**
 * @Author Kanye
 * 这里是8皇后问题的解法
 * 使用的数据结构是 一个一维数组 int[8] 数组的下标（index）表示皇后所在行，对应的值表示皇后所在列
 * 方法是 递归
 * 8皇后问题的主要三个条件
 *  1. 任意两个皇后不能在同一行
 *  2. 任意两个皇后不能在同一列
 *  3. 任意两个皇后不能在对角线
 */
public class Queen8 {
    int max = 8;
    int[] queen8 = new int[max];
    public static void main(String[] args) {
        new Queen8().check(0);
    }

    /**
     * 放置第 n 个皇后
     * @param n
     */
    public void check(int n) {
        if (n == max) {
            show();
            return;
        }
        //依次将皇后放入每一行（i 行）并检测条件
        //只有8行，这里也能用 max 代替8， 但是感觉语义上有点不好理解，所以直接写8
        for (int i = 0; i < 8; i++) {
            queen8[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    /**
     * 判断条件
     * @param n 表示传入的第几个皇后，0开始。表示第一个
     * @return
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //queen8[i] == queen8[n] 表示在同一列
            //Math.abs(n - i) == Math.abs(queen8[n] - queen8[i]) 表示在对角线
            //这里因为传入的 n 是递增的，所以可以不考虑在同一行的问题
             if (queen8[i] == queen8[n] || Math.abs(n - i) == Math.abs(queen8[n] - queen8[i])) {
                return false;
            }
        }
        return true;
    }

    public void show() {
        for (int j : queen8) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
