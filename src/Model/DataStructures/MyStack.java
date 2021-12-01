package Model.DataStructures;

import java.util.*;

public class MyStack<TElem> implements MyIStack<TElem> {

    private final Stack<TElem> stack;

    public MyStack(){  stack = new Stack<TElem>();  }

    @Override
    public TElem pop() {    return stack.pop(); }

    @Override
    public void push(TElem elem) {  stack.push(elem);   }

    @Override
    public boolean isEmpty() {  return stack.isEmpty(); }


    @Override
    public List<TElem> getReversed() {
        List<TElem> list = Arrays.asList((TElem[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }

    public String toString() {  return getReversed().toString();    }

}
