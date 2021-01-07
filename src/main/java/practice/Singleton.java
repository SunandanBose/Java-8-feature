package practice;

import java.io.*;

// We can create a object in java using new, Serialization and Deserialization, clone and Reflection
public class Singleton implements Serializable {

    private static Singleton singleton = new Singleton();

    private Singleton() {
        //Stop creation of a object using reflection
        if (singleton != null) {
            throw new IllegalStateException("Singleton already constructed");
        }
    }

    public static Singleton getInstance() {
        return singleton;
    }

    //Stop creation of a new object using clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clone not supported");
    }

    // stop creation using Serialization and Deserialization
    protected Object readResolve() {
        return singleton;
    }

}

class TestSingleton{

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();

        //Reflection
        try {
            Singleton.class.newInstance();
        } catch (IllegalStateException e){
            System.out.println("Cannot create via Reflection");
        } catch (IllegalAccessException e) {
            System.out.println("Cannot create via Reflection");
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        // Clone
        try {
            singleton.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cannot create via Clone");
        }

        //Serialization and Deserialization

        // Serialization
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"))) {
            out.writeObject(singleton);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 5. Using Deserialization
        Singleton singleton1 = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"))) {
            singleton1 = (Singleton) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(singleton.equals(singleton1)){
            System.out.println("Unable to create via Serialization and Deserialization");
        }
    }
}
