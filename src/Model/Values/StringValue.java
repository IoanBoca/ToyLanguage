package Model.Values;

import Model.Types.BoolType;
import Model.Types.StringType;
import Model.Types.Type;

import java.util.Objects;

public class StringValue implements Value{

    String val;
    public StringValue() {  val = defaultStringValues;   }
    public StringValue(String v) {  val = v;    }

    public String getVal() {    return val;    }
    @Override
    public Type getType() {
        return new StringType();
    }
    @Override
    public boolean equals(Object another){
        if((another instanceof StringType) && (Objects.equals(another.toString(), val)))
            return true;
        else
            return false;
    }

    public String toString() { return val;   }

}
