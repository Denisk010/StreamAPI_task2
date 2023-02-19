import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Comparator;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //task 1. Find all peoples less than 18
        Stream<Person> streamPers18 = persons.stream()
                .filter(age -> age.getAge() < 18);
        System.out.println("Task 1: " + streamPers18.count());
        //task 2. Find peoples=MAN age 18..27
        List<String> listPers = persons.stream()
                .filter(x -> x.getAge() > 18 & x.getAge() < 27 & x.getSex() == Sex.MAN)
                .map(Person::toString)
                .collect(Collectors.toList());
        System.out.println("Task 2: " + listPers);
        //task 3. Find High education Woman 18-60 and Man 18-65
        List<String> allWork = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() >= 18 & x.getAge() <= 65 & x.getSex() == Sex.MAN | x.getAge() >= 18 & x.getAge() <= 60 & x.getSex() == Sex.WOMAN)
                .sorted(Comparator.comparing((Person p) -> p.getSex()) //Сортировка сначала MAN по фамилии потом Woman
                        .thenComparing(p -> p.getFamily()))
                .map(Person::toString)
                .collect(Collectors.toList());
        System.out.println("Task 3: " + allWork);
    }
}