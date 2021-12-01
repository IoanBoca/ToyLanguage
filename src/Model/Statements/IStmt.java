package Model.Statements;

import Model.Expressions.ExpException;
import Model.PrgState;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt {

    PrgState execute(PrgState state) throws StmtException, ExpException, IOException;

}
