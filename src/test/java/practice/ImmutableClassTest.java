package practice;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ImmutableClassTest extends TestCase {

    private ImmutableClass immutableClass;

    @Test
    public void testShouldAddValueInList1(){
        List<Integer> inputList = new ArrayList<>();
        inputList.add(1);
        inputList.add(2);
        immutableClass = new ImmutableClass(1, inputList);
        List<Integer> outputList = immutableClass.getList2();
        outputList.add(3);
        assertTrue(immutableClass.getList2().contains(3));
    }

    @Test
    public void testShouldNotAddValueInList1(){
        List<Integer> inputList = new ArrayList<>();
        inputList.add(1);
        inputList.add(2);
        immutableClass = new ImmutableClass(1, inputList);
        List<Integer> outputList = immutableClass.getList1();
        outputList.add(3);
        assertFalse(immutableClass.getList1().contains(3));
    }

}