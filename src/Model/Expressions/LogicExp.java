package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.util.Objects;

public class LogicExp implements Expression{

    private Expression exp1;
    private Expression exp2;
    private int operand;

    public LogicExp(Expression e1, Expression e2, int op){
        exp1 = e1;
        exp2 = e2;
        operand = op;
    }

    public String toString(){
        switch(operand) {
            case 1 :
                return this.exp1 + " || " + this.exp2;
            case 2:
                return this.exp1 + " && " + this.exp2;
        }
        return this.exp1 + " " + this.operand + " " + this.exp2;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIDictionary<Integer,Value> heap) throws ExpException {
        Value nr1, nr2;
        nr1 = exp1.evaluate(table, heap);
        if(Objects.equals(nr1.getType(), new BoolType())){
            nr2 = exp2.evaluate(table, heap);
            if(Objects.equals(nr2.getType(), new BoolType())){
                BoolValue val1 = (BoolValue) nr1;
                BoolValue val2 = (BoolValue) nr2;
                switch (operand){
                    case 1:
                        return new BoolValue(val1.getVal() || val2.getVal());
                    case 2:
                        return new BoolValue(val1.getVal() && val2.getVal());
                }
            }
            else
                throw new ExpException("Second operand is not a boolean");
        }
        else
            throw new ExpException("First operand is not a boolean");
        return null;
    }
}
