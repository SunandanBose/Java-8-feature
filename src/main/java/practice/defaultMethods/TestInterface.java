package practice.defaultMethods;

public interface TestInterface {

    int sum(int a, int b);

    int difference(int a, int b);

    default int multiply(int a, int b){
        return a*b;
    }

    static int divide(int a, int b){
        return a/b;
    }
}
