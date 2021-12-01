package Model.Values;

import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;

import java.util.Objects;

public class BoolValue implements Value{

    boolean val;
    public BoolValue() { val = defaultBooleanValue; }
    public BoolValue(boolean v) {   val = v;  }

    public boolean getVal() {  return val; }
    public String toString() { return Boolean.toString(val);   }
    public Type getType() { return new BoolType();   }

    @Override
    public boolean equals(Object another){
        if((another instanceof BoolType) && (Objects.equals(another.toString(), String.valueOf(val))))
            return true;
        else
            return false;
    }
}
