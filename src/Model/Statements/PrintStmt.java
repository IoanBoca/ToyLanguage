package Model.Statements;

import Model.Expressions.ExpException;
import Model.Expressions.Expression;
import Model.PrgState;

public class PrintStmt implements IStmt{

    private Expression exp;

    public PrintStmt(Expression exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {
        state.getOut().add(exp.evaluate((state.getSymTable()), state.getHeap()));
        return state;
    }

    public String toString(){   return "print(" + exp.toString() + ")"; }

}
