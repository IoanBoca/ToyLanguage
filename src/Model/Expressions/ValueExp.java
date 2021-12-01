package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.Values.Value;

public class ValueExp implements Expression{

    private final Value val;

    public ValueExp(Value v){
        val = v;
    }

    public String toString(){
        return this.val.toString();
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIDictionary<Integer,Value> heap) throws ExpException {
        return val;
    }
}
