package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.Expressions.ExpException;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Objects;

public class ReadFileStmt implements IStmt{

    private Expression exp;
    private String var_name;

    public ReadFileStmt(Expression exp, String vn){
        this.exp = exp;
        var_name = vn;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<Integer,Value> heap = state.getHeap();

        if(!symTbl.keys().contains(var_name))
            throw new StmtException("Variable is not declared");
        if(!Objects.equals(symTbl.get(var_name).getType(), new IntType()))
            throw new StmtException("Variable is not an IntType");

        StringValue fileVariable;
        StringValue filePath;
        try{
            fileVariable = (StringValue) exp.evaluate(symTbl, heap);
            filePath = (StringValue) symTbl.get(fileVariable.toString());
        }
        catch (ExpException e) {
            throw new StmtException("Error FileName not StringValue");
        }

        if(!fileTable.keys().contains(filePath))
            throw new StmtException("No such FileName in FileTable");

        BufferedReader br = fileTable.get(filePath);
        String line = br.readLine();
        if (line == null)
            symTbl.put(var_name, new IntValue());
        else{
            int varc = Integer.parseInt(line);
            symTbl.put(var_name, new IntValue(varc));
        }

        return state;
    }

    @Override
    public String toString() { return "readFile (" + var_name + ")";}
}
