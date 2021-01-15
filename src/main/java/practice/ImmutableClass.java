package practice;

import java.util.ArrayList;
import java.util.List;

//class as final so it can’t be extended.
public final class ImmutableClass {

    //mutable fields final so that its value can be assigned only once.
    private final Integer field1;

    private final List<Integer> list1;

    //all the fields via a constructor performing deep copy.
    public ImmutableClass(Integer field1, List<Integer> list1) {
        this.field1 = field1;
        this.list1 = list1;
    }

    //No provide setter methods for variables.

    public Integer getField1() {
        return field1;
    }

    //clonning of objects in getter method so that you cannot modify the actual object reference.
    protected List<Integer> getList1(){
        return new ArrayList<>(list1);
    }

    public List<Integer> getList2(){
        return list1;
    }


}
