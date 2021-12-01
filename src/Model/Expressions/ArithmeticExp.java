package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.Types.IntType;
import Model.Values.IntValue;
import Model.Values.Value;

import java.util.Objects;

public class ArithmeticExp implements Expression{

    private Expression exp1;
    private Expression exp2;
    private int operand;

    public ArithmeticExp(Expression e1, Expression e2, int op){
        exp1 = e1;
        exp2 = e2;
        operand = op;
    }

    public String toString(){
        if(this.operand==43)
            return this.exp1 + " + "  + this.exp2 +"; ";
        else if(this.operand==45)
            return this.exp1 + " - "  + this.exp2 +"; ";
        else if(this.operand==42)
            return this.exp1 + " * "  + this.exp2 +"; ";
        else
            return this.exp1 + " / "  + this.exp2 +"; ";
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIDictionary<Integer, Value> heap) throws ExpException {
        Value val1, val2;
        val1 = exp1.evaluate(table, heap);
        if(Objects.equals(val1.getType(), new IntType())){
            val2 = exp2.evaluate(table, heap);
            if(Objects.equals(val2.getType(), new IntType())){
                int nr1 = ((IntValue) val1).getVal();
                int nr2 = ((IntValue) val2).getVal();
                switch (operand){
                    case 43:
                        // operand +
                        return new IntValue(nr1 + nr2);
                    case 45:
                        // operand -
                        return new IntValue(nr1 - nr2);
                    case 42:
                        // operand *
                        return new IntValue(nr1 * nr2);
                    case 47:
                        // operand /
                        if(nr2 == 0)
                            throw new ExpException("Division by 0");
                        return new IntValue(nr1 / nr2);
                }
            }
            else
                throw new ExpException("Second operand is not an integer");
        }
        else
            throw new ExpException("First operand is not an integer");
        return null;
    }
}
