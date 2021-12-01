package Model.Statements;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.Expressions.ExpException;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.io.IOException;

public class HeapAllocationStmt implements IStmt{

    private String var_name;
    private Expression exp;

    public HeapAllocationStmt(String var_name, Expression exp){
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, IOException {
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIDictionary<Integer,Value> heap = state.getHeap();

        if(!symTbl.keys().contains(var_name))
            throw new StmtException("Variable name does not exist in SymTable");

        Value var_value = symTbl.get(var_name);
        if(var_value instanceof RefValue == false)
            throw new StmtException("Variable is not a reference");

        Value expValue = exp.evaluate(symTbl, heap);
        Type expType = expValue.getType();
        Type referencedType = ((RefValue)var_value).getLocationType();

        if(expType.equals(referencedType) == false){
            throw new StmtException("Type of expression and the type of referenced are not equal");
        }

        int positionInHeap = ((MyHeap<Integer, Value>)heap).getFirstAvailablePosition();
        heap.put(positionInHeap, expValue);
        symTbl.put(var_name, new RefValue(positionInHeap, referencedType));

        return state;
    }

    @Override
    public String toString(){
        return "new(" + var_name + ", " + exp.toString() +")";
    }

}
