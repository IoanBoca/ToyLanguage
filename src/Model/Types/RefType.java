package Model.Types;
import Model.Values.RefValue;
import Model.Values.Value;

import java.sql.Ref;

public class RefType implements Type{
    private Type innerType;

    public RefType(Type innerType) {   this.innerType=innerType;   }

    public Type getInner() {   return innerType;   }

    public boolean equals(Object another){
        if (another instanceof RefType) {
            RefType anotherRef = (RefType) another;
            return innerType.equals(anotherRef.getInner());
        }
        else
            return false;
    }

    public String toString() {  return "Ref(" + innerType.toString() + ")";    }

    public Value defaultValue() {   return new RefValue(0,innerType);   }
}