package practice.defaultMethods;

public class TestInheritance implements TestInterface1, TestInterface2{
    @Override
    public void show() {
        System.out.println("Show Method");
    }

}
