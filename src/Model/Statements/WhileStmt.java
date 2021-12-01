package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.Expressions.ExpException;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.io.IOException;

public class WhileStmt implements IStmt{

    private final Expression conditionalExpression;
    private final IStmt statement;

    public WhileStmt(Expression conditionalExpression, IStmt statement) {
        this.conditionalExpression = conditionalExpression;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState crtState) throws StmtException, ExpException, IOException {
        MyIStack<IStmt> stack = crtState.getExeStack();
        MyIDictionary<String, Value> symbolTable = crtState.getSymTable();
        MyIDictionary<Integer, Value> heap = crtState.getHeap();

        Value conditionalExpressionValue = this.conditionalExpression.evaluate(symbolTable, heap);
        if (conditionalExpressionValue instanceof BoolValue == false) {
            throw new StmtException("Conditional expression is not boolean");
        }

        if (((BoolValue)conditionalExpressionValue).getVal() == true) {
            stack.push(this);
            stack.push(this.statement);
        }

        return crtState;
    }

    public String toString() {
        String representation = "";
        // this indentation doesn't work past 1 level - I'm going to need sth like an indentationLevel when creating the statement
        representation += ("while ("+ this.conditionalExpression.toString() + ") {\n\t");
        representation += (this.statement.toString() + "}\n");
        return representation;
    }
}
