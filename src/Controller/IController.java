package Controller;

import Model.Expressions.ExpException;
import Model.PrgState;
import Model.Statements.StmtException;
import Repository.IRepository;
import Repository.RepoException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IController {

    IRepository getRepo();
    PrgState oneStep(PrgState state) throws CtrlException, StmtException, ExpException, IOException;
    void allSteps() throws CtrlException, StmtException, ExpException, RepoException, IOException;
}
