package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.Expressions.ExpException;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.io.IOException;

public class HeapWritingStmt implements IStmt{

    String var_name;
    Expression exp;

    public HeapWritingStmt(String var_name, Expression exp){
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, IOException {
        MyIDictionary<String, Value> table = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();

        if(table.keys().contains(var_name) == false)
            throw new ExpException("Variable is not defined in SymTable");

        Value val = table.get(var_name);
        if(val instanceof RefValue == false)
            throw new ExpException("Variable type is not RefType");

        int positionInHeap = ((RefValue)val).getHeapAddress();
        if(heap.keys().contains(positionInHeap) == false)
            throw new ExpException("Undefined variable at this position in heap");

        Value expVal = exp.evaluate(table, heap);
        Type expType = expVal.getType();
        Value referencedVal = heap.get(positionInHeap);
        Type referencedType = referencedVal.getType();

        if(expType.equals(referencedType) == false)
            throw new ExpException("The reference type is not equal to the type of the expression");

        heap.put(positionInHeap, expVal);

        return state;
    }

    @Override
    public String toString(){
        return "new(" + var_name + ", " + exp.toString() +")";
    }
}
