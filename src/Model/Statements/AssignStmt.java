package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.Expressions.ExpException;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStmt implements IStmt{

    private String id;
    private Expression exp;

    public AssignStmt(String id, Expression exp){
        this.id = id;
        this.exp = exp;
    }

    public String toString(){
        return this.id + "=" + this.exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        MyIDictionary<Integer,Value> heap = state.getHeap();

        if (symTbl.keys().contains(id)) {
            Value val = exp.evaluate(symTbl, heap);
            Type typeId = (symTbl.get(id)).getType();
            if (val.getType().equals(typeId))
                symTbl.put(id, val);
            else
                throw new ExpException("Declared type of variable " + id + " and type of the assigned expression do not match");
        }
        else
            throw new ExpException("The used variable " +id + " was not declared before");
            return state;
        }
}
