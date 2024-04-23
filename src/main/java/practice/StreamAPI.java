package practice;

import beans.Car;
import beans.Person;
import beans.PersonDTO;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import mockdata.MockData;
import org.assertj.core.api.AbstractBigDecimalAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class StreamAPI {

    public static void main(String[] args) throws Exception {
        StreamAPI streamAPI = new StreamAPI();
        streamAPI.imperativeApproach();
        streamAPI.declarativeApproachUsingStreams();
        streamAPI.rangeIteratingLists();
        streamAPI.intStreamIterate();
        streamAPI.min();
        streamAPI.max();
        streamAPI.distinct();
        streamAPI.distinctWithSet();
        streamAPI.ourFirstMapping();
        streamAPI.averageCarPrice();
        streamAPI.findAny();
        streamAPI.findFirst();
        streamAPI.groupingAndCounting();
        streamAPI.withoutFlatMap();
        streamAPI.reduce();
        streamAPI.withFlatMap();
        streamAPI.joiningStringsWithStream();
        streamAPI.understandingCollect();
        streamAPI.intermediateAndTerminalOperations();
    }


    public void imperativeApproach() throws IOException {
        List<Person> people = MockData.getPeople();

        List<Person> youngPeople = Lists.newArrayList();
        int limit = 10;
        for (Person person : people) {
            if (limit <= 0) return;
            if (person.getAge() >= 18) {
                System.out.println(person);
                youngPeople.add(person);
            }
            limit--;
        }
    }

    public void declarativeApproachUsingStreams() throws Exception {
        ImmutableList<Person> people = MockData.getPeople();
        List<Person> youngPeople = people.stream().filter(
                person -> person.getAge() >= 18
        ).limit(10).collect(Collectors.toList());
        youngPeople.forEach(System.out::println);
    }

    public void rangeIteratingLists() throws Exception {
        ImmutableList<Person> people = MockData.getPeople();
        IntStream.range(0, people.size())
                .forEach(index -> {
                    Person person = people.get(index);
                    System.out.println(person);
                });
    }

    public void intStreamIterate() {
        //seed is where you begin
        IntStream.iterate(1, operand -> operand + 1)
                .filter(num -> num % 2 == 0)
                .limit(20)
                .forEach(System.out::println);
    }

    public void min() {
        final List<Integer> numbers = ImmutableList.of(1, 2, 3, 100, 23, 93, 99);
        Integer min = numbers.stream().min(Comparator.naturalOrder()).get();
        assertThat(min).isEqualTo(1);
    }

    public void max() {
        final List<Integer> numbers = ImmutableList.of(1, 2, 3, 100, 23, 93, 99);
        Integer max = numbers.stream().max(Comparator.naturalOrder()).get();
        assertThat(max).isEqualTo(100);
        System.out.println(max);
    }

    public void distinct() {
        final List<Integer> numbers = ImmutableList.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9);

        List<Integer> distinctNumbers = numbers.stream().distinct().collect(Collectors.toList());

        assertThat(distinctNumbers).hasSize(9);

        System.out.println(distinctNumbers);

    }

    public void distinctWithSet() {
        final List<Integer> numbers = ImmutableList.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9);

        Set<Integer> distinctNumbers = numbers.stream().collect(Collectors.toSet());

        assertThat(distinctNumbers).hasSize(9);

        System.out.println(distinctNumbers);
    }

    public void ourFirstMapping() throws Exception {
        // transform from one data type to another
        List<Person> people = MockData.getPeople();

        List<PersonDTO> dtos = people.stream().map(person -> {
            PersonDTO personDTO = new PersonDTO(person.getId(), person.getFirstName(), person.getAge());
            return personDTO;
        }).collect(Collectors.toList());

        // cleaner way
        dtos = people.stream().map(PersonDTO::map).collect(Collectors.toList());

        assertThat(dtos).hasSize(1000);
        System.out.println(dtos.size());

    }

    public void averageCarPrice() throws Exception {
        // calculate average of car prices
        double average = MockData.getCars().stream().mapToDouble(Car::getPrice).average().orElse(0);
        System.out.println(MockData.getCars().stream().mapToInt(Car::getId).average().orElse(0));
        System.out.println(average);
    }


    //findany = The behavior of this operation is explicitly nondeterministic; it is
    // free to select any element in the stream.
    //findFirst = The behavior of this operation is explicitly deterministic;
    // Returns an Optional describing the first element of this stream, or an empty
    public void findAny() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int any = Arrays.stream(numbers).filter(number -> number < 10).findAny().get();
        System.out.println(any);
    }

    public void findFirst() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int first = Arrays.stream(numbers).filter(number -> number < 10).findFirst().get();
        System.out.println(first);
    }

    public void groupingAndCounting() {
        ArrayList<String> names = Lists
                .newArrayList(
                        "John",
                        "John",
                        "Mariam",
                        "Alex",
                        "Mohammado",
                        "Mohammado",
                        "Vincent",
                        "Alex",
                        "Alex"
                );

        Map<String, Long> counting = names.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        counting.forEach((name, count) -> System.out.println(name + " > " + count));
    }

    public void reduce() {
        Integer[] integers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};

        int sum = Arrays.stream(integers).reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = Arrays.stream(integers).reduce(0, Integer::sum);
        System.out.println(sum2);
    }

    public void withoutFlatMap() {
        List<String> listA = com.google.common.collect.Lists.newArrayList("A", "B", "C", "D", "E");
        List<String> listB = com.google.common.collect.Lists.newArrayList("A1", "B1", "C1", "D1", "E1");
        List<String> listC = com.google.common.collect.Lists.newArrayList("A2", "B2", "C2", "D2", "E2");

        List<String> result = new ArrayList<>();
        Stream.of(listA, listB, listC).forEach(result::addAll);
        System.out.println(result);
    }

    private static final List<ArrayList<String>> arrayListOfNames = com.google.common.collect.Lists.newArrayList(
            com.google.common.collect.Lists.newArrayList("Mariam", "Alex", "Ismail"),
            com.google.common.collect.Lists.newArrayList("John", "Alesha", "Andre"),
            com.google.common.collect.Lists.newArrayList("Susy", "Ali")
    );

    public void withFlatMap() {
//   [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]

        List<String> names = arrayListOfNames.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(names);

    }

    public void joiningStringsWithStream() {
        List<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");

        String join = names.stream().collect(Collectors.joining(" | "));

        System.out.println(join);
    }

    public void understandingCollect() throws Exception {
        List<String> emails = MockData.getPeople()
                .stream()
                .map(Person::getEmail)
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);
//        .collect(Collectors.toList());

        emails.forEach(System.out::println);
    }

    public void intermediateAndTerminalOperations() throws Exception {
        System.out.println(
                MockData.getCars()
                        .stream()
                        .filter(car -> {
                            System.out.println("filter car " + car);
                            return car.getPrice() < 10000;
                        })
                        .map(car -> {
                            System.out.println("mapping car " + car);
                            return car.getPrice();
                        })
                        .map(price -> {
                            System.out.println("mapping price " + price);
                            return price + (price * .14);
                        })
                        .collect(Collectors.toList())
        );
    }
}
