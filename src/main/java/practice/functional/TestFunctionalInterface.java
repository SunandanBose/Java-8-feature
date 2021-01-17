package practice.functional;

@FunctionalInterface
public interface TestFunctionalInterface {

    int sum(int a, int b);

    default void show(){
        System.out.println("Test Functional Interface");
    }

}
