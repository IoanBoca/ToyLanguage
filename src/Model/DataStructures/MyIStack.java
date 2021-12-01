package Model.DataStructures;

import java.util.List;

public interface MyIStack<TElem> {

    TElem pop();
    void push(TElem elem);
    boolean isEmpty();
    String toString();
    List<TElem> getReversed();
}
