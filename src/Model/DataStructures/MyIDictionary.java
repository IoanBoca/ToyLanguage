package Model.DataStructures;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public interface MyIDictionary<TKey,TValue> {

    TValue remove(TKey key);
    TValue get(TKey key);
    TValue put(TKey key, TValue value);
    boolean isEmpty();
    Set<TKey> keys();
    String toString();
    void setContent(HashMap<TKey,TValue> newContent);
    HashMap<TKey,TValue> getContent();
}
