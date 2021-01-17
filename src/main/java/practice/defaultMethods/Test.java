package practice.defaultMethods;

public class Test {

    public static void main(String[] args) {
        TestInterfaceImpl ti = new TestInterfaceImpl();
        System.out.println(ti.sum(2,4));
        System.out.println(ti.multiply(2,4));
        System.out.println(TestInterface.divide(2,4));


        TestInheritance testInheritance = new TestInheritance();
        testInheritance.show();

    }
}
