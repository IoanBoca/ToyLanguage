package Model.Statements;

import Model.DataStructures.MyDictionary;
import Model.Expressions.ExpException;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.*;

import java.util.Objects;

public class VarDeclStmt implements IStmt{

    private final String id;
    private final Type type;

    public VarDeclStmt(String i, Type t){
        id = i;
        type = t;
    }

    public String toString(){
        return this.type + " " + this.id;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {
        if(state.getSymTable().keys().contains(id))
            throw new StmtException("Variable is already declared");
        else{
            if(Objects.equals(type.toString(), "int"))
                state.getSymTable().put(id, new IntValue());
            else if(Objects.equals(type.toString(), "bool"))
                state.getSymTable().put(id, new BoolValue());
            else if(Objects.equals(type.toString(), "string"))
                state.getSymTable().put(id, new StringValue());
            else if(type instanceof RefType)
                state.getSymTable().put(id, type.defaultValue());
        }
        return state;
    }
}
