package Model.Values;

import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.Type;

import java.sql.Ref;
import java.util.Objects;

public class RefValue implements Value{
    private int heapAddress;
    private Type locationType;

    public RefValue(int address, Type locationType){
        this.heapAddress = address;
        this.locationType = locationType;
    }

    public RefValue(Type locationType){
        this.heapAddress = RefValue.defaultHeapAddress;
        this.locationType = locationType;
    }

    public int getHeapAddress() {  return heapAddress; }
    public Type getType() { return new RefType(locationType);   }
    public Type getLocationType() { return locationType;    }

    @Override
    public boolean equals(Object another){
        if((another instanceof RefValue) && (((RefValue) another).getHeapAddress() == this.heapAddress))
            return true;
        else
            return false;
    }

    public String toString() {
        String representation = "";
        representation += ("(" + Integer.toHexString(heapAddress) + ", " + this.locationType.toString() + ")");
        return representation;
    }
}
