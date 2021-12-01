package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.Values.Value;

public class VariableExp implements Expression{

    String var;

    public VariableExp(String v){
        var = v;
    }

    public String toString(){
        return this.var;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIDictionary<Integer,Value> heap) throws ExpException {
        if(table.keys().contains(var))
            return table.get(var);
        else
            throw new ExpException("Variable " + var + " is not defined");
    }
}
