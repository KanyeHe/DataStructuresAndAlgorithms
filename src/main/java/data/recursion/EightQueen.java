package data.recursion;

import lombok.Data;

/**
 * 8皇后问题
 * 问题描述，在一个 8 * 8 的棋盘上，放置8个皇后，使得任意皇后不能在同一行或者同一列 或者在对角线上
 * 一共有多少种
 * 思路：
 * 使用一个长度为8的一维数组来表示这个 8个皇后的位置，下标表示所在行，对应的值表示所在的列
 * 因为每一个皇后占用一个值，所以可以不考虑行的判断
 * 是否在同一列，判断数组里面的值是否有相等的数，有则表示在同一列
 * 判断是否在对角线上， 所在行的差的绝对值 == 所在列的差的绝对值 则表示在同一列上
 */
public class EightQueen {
    private final int MAX = 4;
    private final int[] map = new int[MAX];

    private int count;


    public void show() {
        for (int i : map) {
            System.out.printf("%d\t", i);
        }
        System.out.println();
        count++;
    }

    private int getCount() {
        return count;
    }

    /**
     * 放置第 n 个皇后
     * @param n
     */
    public void solution(int n) {
        //如果放置的皇后数 n == max，说明已经放置完了
        if (MAX == n) {
            show();
            return;
        }
        //将放置的皇后 在没一列上去尝试放置，然后去判断是否与之前的有冲突
        for (int i = 0; i < MAX; i++) {
            map[n] = i;
            //如果没有冲突，则放置 n + 1 个
            if (judge(n)) {
                solution(n + 1);
            }
            //如果有冲突， 因为是在 for 循环里面，所以 i会 + 1，相当于是尝试放置在下一列
        }
    }

    /**
     * 判断放置的第n个皇后与已经存在的皇后是否冲突
     * @param n
     * @return
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i ++) {
            //不在同一列
            if (map[i] == map[n]) {
                return false;
            }
            //不在对角线
            if (Math.abs(n - i) == Math.abs(map[n] - map[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        EightQueen queen = new EightQueen();
        //因为数组下标是从0开始，所以这里的 0 表示第一个皇后
        queen.solution(0);
        System.out.println("放置的方法一共有" + queen.getCount() + "种");
    }

}
