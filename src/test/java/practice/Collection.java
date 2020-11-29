package practice;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Collection<T, E> {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

        List<String> listString = Lists.newArrayList("A", "B", "C", "D", "E");

        Mapper<Integer, String> mapper = a -> String.valueOf(a) + "test";
        Reducer<String> reduce = (a, b) -> a + b;

        System.out.println(new Collection<Integer, String>().map(list, mapper));

        System.out.println(new Collection<String, String>().reduce(listString, "", reduce));


    }

    private E reduce(List<E> list, E defaultValue, Reducer<E> reduce) {
        E result = defaultValue;
        for (int i = 0; i < list.size(); i++) {
            result = reduce.reduce(result, list.get(i));
        }

        return result;
    }

    public List<E> map(List<T> list, Mapper<T, E> func) {
        List<E> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(func.map(list.get(i)));
        }
        return result;
    }


}

interface Mapper<T, E> {
    public E map(T a);
}

interface Reducer<T> {
    public T reduce(T a, T b);
}
