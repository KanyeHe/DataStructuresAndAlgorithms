package data.stack;


import data.stack.enums.Operator;
import java.util.Stack;

/**
 * 综合计算器
 * 比如输入  2*4*5-8+5*4/4 可以直接得出结果 这里用 / 表示除(÷)
 * 思路
 * 1. 使用两个栈，一个数栈用于存储数字，一个符号栈用于存储符号
 * 2. 遍历 输入的 表达式字符串
 * 3. 如果得到是数字，直接入数栈
 * 4. 如果得到是符号，则分情况
 *      4.1 如果符号栈为空，则直接入符号栈
 *      4.2 如果符号栈不为空，则将当前符号与栈顶符号做比较
 *          4.2.1 如果当前符号优先级 <= 栈顶符号优先级， 则从数栈中pop两个数，然后再pop出栈顶符号进行运算，
 *          将运算的结果存入符号栈，最后将当前符号存入符号栈
 *          4.2.2 如果当前符号优先级 > 栈顶符号优先级，则将当前符号入符号栈
 * 5. 当表达式遍历完毕，就顺序的从数栈和符号栈中pop出相应的数和符号进行运算，并将运算结果存入数栈
 * 6. 最后数栈中剩下的一个数就是运算结果
 *
 */
public class CalculatorByStack {

    private final Stack<Integer> numsStack;
    private final Stack<Operator> operatorStack;

    public CalculatorByStack() {
        this.numsStack = new Stack<>();
        this.operatorStack = new Stack<>();
    }

    public Integer operate(String expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                //这个地方需要做处理，否则如果处理 两位数就会存在问题
                //numsStack.push(Integer.parseInt(c + ""));
                //todo 改进后的方案 思路：先用一个 sb 保存当前的数字，再看看后一位是否是数字，如果是拼接在一起并继续遍历下一个元素
                //todo 如果不是,则将 sb 中拼接的数字存入 数栈
                sb.append(c);
                if ((i + 1 < expression.length()) && Character.isDigit(expression.charAt(i + 1))) {
                    continue;
                } else {
                    numsStack.push(Integer.parseInt(sb.toString()));
                    //入栈后清空 sb
                    sb.setLength(0);
                }
            }
            if (Operator.operator(c).isPresent()) {
                Operator current = Operator.operator(c).get();
                if (operatorStack.isEmpty()) {
                    operatorStack.push(current);
                } else {
                    Operator peek = operatorStack.peek();
                    if (current.getPriority() <= peek.getPriority()) {
                        Integer right = numsStack.pop();
                        Integer left = numsStack.pop();
                        Operator operator = operatorStack.pop();
                        Integer result = operator.getOperate().apply(left, right);
                        numsStack.push(result);
                        operatorStack.push(current);
                    } else {
                        operatorStack.push(current);
                    }
                }

            }
        }
        while (!operatorStack.isEmpty()) {
            Integer right = numsStack.pop();
            Integer left = numsStack.pop();
            Operator operator = operatorStack.pop();
            Integer result = operator.getOperate().apply(left, right);
            numsStack.push(result);
        }
        return numsStack.pop();
    }

    public static void main(String[] args) {
        CalculatorByStack calculator = new CalculatorByStack();
        System.out.println(calculator.operate("2*5-80+110-25/5"));
    }

}
