package practice;

import java.util.ArrayList;
import java.util.List;

public class UnmodifiableArrayList<T> extends ArrayList<T> {

    private final List<T> list;

    public UnmodifiableArrayList(List<T> list) {
        this.list = new ArrayList<T>(list);
    }

    public List<T> getList() {
        return new ArrayList<T>(list);
    }

    public boolean add(T value) {
        return false;
    }

    public boolean addAll(List<T> addList) {
        return false;
    }

    public T remove(int index) {
        return null;
    }
}

class TestUnmodifiableClass{

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        UnmodifiableArrayList unmodifiablelist = new UnmodifiableArrayList(list);
        System.out.println("Before Modification");
        System.out.println(list);
        System.out.println(unmodifiablelist.getList());
        System.out.println("After Modification");
        list.add(3);
        unmodifiablelist.add(4);
        System.out.println(list);
        System.out.println(unmodifiablelist.getList());
    }

}
