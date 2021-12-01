package Model.Statements;

import Model.DataStructures.MyIStack;
import Model.PrgState;

public class CompStmt implements IStmt{

    IStmt first;
    IStmt second;

    public CompStmt(IStmt f, IStmt s){
        first = f;
        second = s;
    }

    public String toString(){   return "(" + first.toString() + ";" + second.toString() + ")";  }

    @Override
    public PrgState execute(PrgState state) throws StmtException {

        MyIStack<IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return state;
    }
}
