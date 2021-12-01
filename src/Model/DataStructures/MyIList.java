package Model.DataStructures;

public interface MyIList<TElem> {

    TElem remove(int pos);
    TElem get(int pos);
    boolean add(TElem elem);
    void add(int pos, TElem elem);
    boolean isEmpty();
    String toString();
    int size();

}
