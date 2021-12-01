package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.Expressions.ExpException;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStmt implements IStmt{

    private Expression var_name;

    public CloseReadFileStmt(Expression exp){
        this.var_name = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, IOException {
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<Integer,Value> heap = state.getHeap();

        Value fileVariable = var_name.evaluate(symTable, heap);
        Value filePathValue = symTable.get(fileVariable.toString());

        if (!(filePathValue.getType() instanceof StringType))
            throw new ExpException("Expression for closeRFile must be a string");

        StringValue filePathStr = (StringValue) filePathValue;

        if(!fileTable.keys().contains(filePathStr))
            throw new ExpException("There doesn't exist a file named " + filePathValue.toString() + " in the FileTable");

        BufferedReader bf = fileTable.get(filePathStr);
        bf.close();
        fileTable.remove(filePathStr);

        return state;
    }

    @Override
    public String toString() {
        return "closeRead (" + var_name + ")";
    }
}
