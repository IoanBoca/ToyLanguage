package Model.DataStructures;

import java.util.List;
import java.util.ArrayList;

public class MyList<TElem> implements MyIList<TElem> {

    private final List<TElem> list;

    public MyList() { list = new ArrayList<TElem>(); }

    @Override
    public TElem remove(int pos) {  return list.remove(pos);   }

    @Override
    public TElem get(int pos) { return list.get(pos);   }

    @Override
    public boolean add(TElem elem) {   return list.add(elem);   }

    @Override
    public void add(int pos, TElem elem) {  list.add(pos, elem);     }

    @Override
    public boolean isEmpty() {  return list.isEmpty();  }

    public String toString() {  return this.list.toString();    }

    public List<TElem> getList() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }
}
