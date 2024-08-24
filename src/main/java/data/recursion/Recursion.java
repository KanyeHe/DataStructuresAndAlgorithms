package data.recursion;

/**
 * 递归需要遵循的原则
 * 1. 执行一个方法的时候就创建一个新的受保护的独立空间
 * 2. 方法的局部变了是独立的，不会相互影响。(如果使用使用了引用变量就会共享改引用变量)
 * 3. 递归必须向退出递归的条件逼近，否则无限递归
 * 4. 当一个方法执行完毕，或者遇到return的时候就会返回，遵守谁调用就返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕 ？？？？
 */
public class Recursion {

    public static void main(String[] args) {
        System.out.println(factorial(4));
    }

    /**
     * 阶乘
     * 这是一个计算的问题：
     * 可以这么理解
     * 当 n == 1时， factorial(1) 返回的结果是 1 所以 factorial(1) = 1
     * 当 n == 2时， factorial(2) = factorial(1) * 2， 带入 factorial(1) = 1， 所以 factorial(2) = 1 * 2
     * 当 n == 3时， factorial(3) = factorial(2) * 3， 带入 factorial(2) = 1 * 2， 所以 factorial(3) = 1 * 2 * 3
     * 以此类推，
     * 当 n == 4时， factorial(4) = 1 * 2 * 3 * 4
     * ......
     * ......
     * 以后不要想着执行的步骤了，反过来看，从出口开始，直接带入计算就清楚了
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }
}
