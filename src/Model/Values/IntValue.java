package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

import java.util.Objects;

public class IntValue implements Value{

    int val;
    public IntValue() { val = defaultIntValue; }
    public IntValue(int v) {   val=v;  }

    public int getVal() {  return val; }
    public String toString() { return Integer.toString(val);   }
    public Type getType() { return new IntType();   }

    @Override
    public boolean equals(Object another){
        if((another instanceof IntType) && (Objects.equals(another.toString(), String.valueOf(val))))
            return true;
        else
            return false;
    }
}
