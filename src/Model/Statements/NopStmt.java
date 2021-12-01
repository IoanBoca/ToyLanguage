package Model.Statements;

import Model.Expressions.ExpException;
import Model.PrgState;

public class NopStmt implements IStmt{

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {
        return state;
    }
}
