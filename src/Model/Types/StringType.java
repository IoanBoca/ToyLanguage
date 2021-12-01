package Model.Types;

import Model.Values.StringValue;
import Model.Values.Value;

public class StringType implements Type{

    @Override
    public boolean equals(Object another){
        if(another instanceof StringType)
            return true;
        else
            return false;
    }

    @Override
    public Value defaultValue() {
        return new StringValue();
    }

    public String toString(){   return "string";   }

}
