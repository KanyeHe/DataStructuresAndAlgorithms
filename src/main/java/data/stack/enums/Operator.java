package data.stack.enums;

import lombok.Getter;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

@Getter
public enum Operator {
    ADD('+', 10, Integer::sum,"加法"),
    SUB('-', 10, (a, b) -> a - b, "减法"),
    MUL('*', 20, (a, b) -> a * b, "乘法"),
    DIV('/', 20, (a, b) -> a / b, "除法"),
    ;
    private final char operator;
    private final int priority;
    private final String desc;

    private final BiFunction<Integer, Integer, Integer> operate;

    Operator(char operator, int priority, BiFunction<Integer, Integer, Integer> operate, String desc) {
        this.operator = operator;
        this.priority = priority;
        this.operate = operate;
        this.desc = desc;
    }
    public static Optional<Operator> operator(char c) {
        return Stream.of(values()).filter(x -> x.getOperator() == c).findAny();
    }
}
