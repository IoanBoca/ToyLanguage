package Model.Expressions;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.Values.RefValue;
import Model.Values.Value;

public class HeapReadingExp implements Expression{

    private Expression exp;

    public HeapReadingExp(Expression exp){
        this.exp = exp;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIDictionary<Integer, Value> heap) throws ExpException {
        if((exp.evaluate(table, heap) instanceof RefValue) == false)
            throw new ExpException("The expression is not evaluated to a RefValue");

        RefValue val = (RefValue) exp.evaluate(table, heap);
        int valueAddress = val.getHeapAddress();

        if(heap.keys().contains(valueAddress) == false)
            throw new ExpException("The given heap address is not associated with any value");

        return heap.get(valueAddress);
    }

    @Override
    public String toString(){
        return "(" + exp.toString() + ")";
    }
}
