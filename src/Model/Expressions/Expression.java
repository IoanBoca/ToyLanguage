package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.Values.Value;

public interface Expression {
    Value evaluate(MyIDictionary<String,Value> table, MyIDictionary<Integer,Value> heap) throws ExpException;
    String toString();
}
