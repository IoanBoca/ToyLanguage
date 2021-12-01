package Model.DataStructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<TKey,TValue> implements MyIDictionary<TKey,TValue> {

    private HashMap<TKey,TValue> dictionary;

    public MyDictionary(){
        dictionary = new HashMap<TKey, TValue>();
    }


    @Override
    public TValue remove(TKey key) {
        return dictionary.remove(key);
    }

    @Override
    public TValue get(TKey key) {
        return dictionary.get(key);
    }

    @Override
    public TValue put(TKey key, TValue value) {
        return dictionary.put(key,value);
    }

    @Override
    public boolean isEmpty(){
        return dictionary.isEmpty();
    }

    @Override
    public Set<TKey> keys() {
        return dictionary.keySet();
    }

    @Override
    public String toString() {
        return this.dictionary.toString();
    }

    @Override
    public void setContent(HashMap<TKey, TValue> newContent) {
        this.dictionary = newContent;
    }

    @Override
    public HashMap<TKey, TValue> getContent() {
        return dictionary;
    }

}
