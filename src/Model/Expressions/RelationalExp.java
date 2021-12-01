package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationalExp implements Expression{
    private Expression exp1;
    private Expression exp2;
    private String operator;

    public RelationalExp(Expression e1, Expression e2, String o) {
        this.exp1 = e1;
        this.exp2 = e2;
        this.operator = o;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIDictionary<Integer,Value> heap) throws ExpException {
        Value value1 = exp1.evaluate(symTable, heap);
        Value value2 = exp2.evaluate(symTable, heap);

        if(!value1.getType().equals(new IntType())) {
            throw new ExpException("First operand is not an integer.");
        }

        if(!value2.getType().equals(new IntType())) {
            throw new ExpException("Second operand is not an integer.");
        }

        int intValue1 = ((IntValue)value1).getVal();
        int intValue2 = ((IntValue)value2).getVal();

        return switch (operator) {
            case "<" -> new BoolValue(intValue1 < intValue2);
            case "<=" -> new BoolValue(intValue1 <= intValue2);
            case "==" -> new BoolValue(intValue1 == intValue2);
            case "!=" -> new BoolValue(intValue1 != intValue2);
            case ">" -> new BoolValue(intValue1 > intValue2);
            case ">=" -> new BoolValue(intValue1 >= intValue2);
            default -> null;
        };


    }

    @Override
    public String toString() {
        return exp1.toString() + " " + operator + " " + exp2.toString();
    }
}
