package data.list;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DNode<T> {
    private int index;
    private T data;
    private DNode<T> next;
    private DNode<T> previous;

    public DNode(int index, T data) {
        this.index = index;
        this.data = data;
    }

    @Override
    public String toString() {
        return "index = " + index + ";data = " + data
                + ";next -> " + (next == null ? null : next.getIndex())
                + ";previous -> " + (previous == null ? null : previous.getIndex());
    }
}
