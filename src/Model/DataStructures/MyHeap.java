package Model.DataStructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyHeap<TKey,TValue> implements MyIDictionary<TKey,TValue>{

    private HashMap<TKey,TValue> heap;
    int firstAvailablePosition;

    public MyHeap() {
        heap = new HashMap<TKey, TValue>();
        firstAvailablePosition = 1;
    }

    private int setNextAvailable(){
        return this.firstAvailablePosition + 1;
    }

    public int getFirstAvailablePosition(){
        int positionCopy = this.firstAvailablePosition;
        this.firstAvailablePosition = setNextAvailable();
        return positionCopy;
    }

    @Override
    public TValue remove(TKey key) {
        return heap.remove(key);
    }

    @Override
    public TValue get(TKey key) {
        return heap.get(key);
    }

    @Override
    public TValue put(TKey key, TValue tValue) {
        return heap.put(key, tValue);
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public Set<TKey> keys() {
        return heap.keySet();
    }

    @Override
    public String toString(){
        return heap.toString();
    }

    @Override
    public void setContent(HashMap<TKey, TValue> newContent) {
        this.heap = newContent;
    }

    @Override
    public HashMap<TKey, TValue> getContent() {
        return heap;
    }
}
