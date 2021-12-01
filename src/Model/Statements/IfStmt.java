package Model.Statements;

import Model.Expressions.ExpException;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.util.Objects;

public class IfStmt implements IStmt{

    Expression exp;
    IStmt thenStmt;
    IStmt elseStmt;

    public IfStmt(Expression exp, IStmt t, IStmt e){
        this.exp = exp;
        thenStmt = t;
        elseStmt = e;
    }

    public String toString(){
        return "(IF(" + exp.toString() + ") THEN(" + thenStmt.toString() +")ELSE(" + elseStmt.toString()+"))";
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {
        Value cond = exp.evaluate(state.getSymTable(), state.getHeap());
        if (!Objects.equals(cond.getType(), new BoolType())){
            throw new ExpException("Conditional expression is not a boolaen");
        }
        else{
            BoolValue value = (BoolValue)cond;
            if(value.getVal())
                state.getExeStack().push(this.thenStmt);
            else
                state.getExeStack().push(this.elseStmt);
        }
        return state;
    }
}
