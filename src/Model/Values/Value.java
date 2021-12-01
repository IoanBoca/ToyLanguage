package Model.Values;

import Model.Types.Type;

public interface Value {

    int defaultIntValue = 0;
    boolean defaultBooleanValue = false;
    String defaultStringValues = "";
    int defaultHeapAddress = 0;

    Type getType();
    boolean equals(Object another);

}
