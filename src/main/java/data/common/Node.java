package data.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Node<T> {
    private int index;
    private T data;
    private Node<T> next;

    public Node(int index, T data) {
        this.index = index;
        this.data = data;
    }

    @Override
    public String toString() {
        return "index = " + index + ";data = " + data + ";next -> " + (next == null ? null : next.getIndex());
    }
}
