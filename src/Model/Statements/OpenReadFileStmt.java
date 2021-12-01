package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.Expressions.ExpException;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.*;

public class OpenReadFileStmt implements IStmt{

    private Expression var_name;

    public OpenReadFileStmt(Expression exp){
        this.var_name = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, IOException {
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<Integer,Value> heap = state.getHeap();

        Value variableValue = var_name.evaluate(symTable, heap);
        Value filePathValue = symTable.get(variableValue.toString());

        if (!(filePathValue.getType() instanceof StringType))
            throw new ExpException("Expression for openRFile must be a string");

        StringValue filePathStr = (StringValue) filePathValue;

        if(fileTable.keys().contains(filePathStr))
            throw new ExpException("There already exists a file named " + filePathStr.toString() + " in the FileTable");

        BufferedReader br = new BufferedReader(new FileReader(filePathStr.toString()));
        fileTable.put(filePathStr, br);


        return state;
    }

    @Override
    public String toString() {
        return "openRead (" + var_name + ")";
    }
}
