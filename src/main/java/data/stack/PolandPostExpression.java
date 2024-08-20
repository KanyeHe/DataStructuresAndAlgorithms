package data.stack;

import data.stack.enums.Operator;

import java.util.Optional;
import java.util.Stack;

/**
 * 后缀表达式，又名逆波兰表达式
 * 给定一个 中缀表达式，输出为 后缀表达式，题外话：也存在前缀表达式（波兰表达式）
 * 例子 ： 输入 (3+4)*5-6  -> 输出 34+5*6-
 * 思路：
 * 1. 创建一个栈用于存放操作符
 * 2. 遍历输入的字符串，如果是数字则直接输出，如果是遇到"("则推入栈中，如果是")" 则从栈中弹出操作符并输出，直到遇到"("并将其弹出抛弃
 * 3. 如果是遇到运算操作符，如：+ - * / 等
 *      3.1 如果栈不为空，并且 栈顶运算符优先级 >= 当前运算符优先级，则不断的弹出运算符，直到不符合 3.1 条件为止
 *      3.2 如果不符合 3.1 条件，则直接将运算符压入栈中
 * 4. 弹出栈中剩下的运算符
 *
 * 关键点总结：
 *      1. 栈用于处理操作符及括号，确保操作符按正确的优先级顺序输出
 *      2. 操作数直接输出，不需要额外处理
 */

public class PolandPostExpression {

    private final static char LEFT_BRACKET = '(';
    private final static char RIGHT_BRACKET = ')';

    private final Stack<Character> operatorStack;

    public PolandPostExpression() {
        this.operatorStack = new Stack<>();
    }

    public String toPostPolandExpression(String expression) {
        StringBuilder result = new StringBuilder();
        StringBuilder numKeeper = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char curChar = expression.charAt(i);
            if (Character.isDigit(curChar) ) {
                numKeeper.append(curChar);
                if ((i + 1) < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
                    continue;
                } else {
                    result.append(numKeeper).append(" ");
                    numKeeper.setLength(0);
                }
                continue;
            }
            if (curChar == LEFT_BRACKET) {
                operatorStack.push(LEFT_BRACKET);
                continue;
            }
            if (curChar == RIGHT_BRACKET) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals(LEFT_BRACKET)) {
                    result.append(operatorStack.pop()).append(" ");
                }
                //弹出 "(" 直接丢弃
                operatorStack.pop();
                continue;
            }
            Optional<Operator> operator = Operator.operator(curChar);
            if (operator.isPresent()) {
                // 这个地方一定要注意，要保证 peek() 返回的也是运算符，不能是 ( 左括号
                while (!operatorStack.isEmpty()
                        && Operator.operator(operatorStack.peek()).isPresent()
                        && Operator.operator(operatorStack.peek()).get().getPriority() >= operator.get().getPriority()) {
                    result.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(curChar);
            }
        }
        while (!operatorStack.isEmpty()) {
            result.append(operatorStack.pop()).append(" ");
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        PolandPostExpression expression = new PolandPostExpression();
        System.out.println(expression.toPostPolandExpression("1+((2+3)*4)-5"));
    }

}
