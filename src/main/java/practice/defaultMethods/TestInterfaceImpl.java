package practice.defaultMethods;

public class TestInterfaceImpl implements TestInterface{
    @Override
    public int sum(int a, int b) {
        return a+b;
    }

    @Override
    public int difference(int a, int b) {
        return a-b;
    }
}
