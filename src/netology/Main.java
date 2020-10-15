package netology;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {


        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.print("1. Minor count: ");
        Long minorCount = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(minorCount);

        System.out.println("2. List of recruits: ");
        List<String> recruitSecondNameList = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .filter(x -> x.getSex().equals(Sex.MALE))
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println("Count: " + recruitSecondNameList.size());
        //System.out.println(recruitSecondNameList); // to print all second names

        System.out.println("3. Potential employee: ");
        List<Person> potentialEmployeeList = persons.stream()
                .filter(x -> x.getSex().equals(Sex.MALE) && x.getAge() >= 18 && x.getAge() < 65 ||
                        x.getSex().equals(Sex.FEMALE) && x.getAge() >= 18 && x.getAge() < 60)
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .sorted((x1,x2) -> x1.getFamily().compareTo(x2.getFamily()))
                .collect(Collectors.toList());
        System.out.println("Count: " + potentialEmployeeList.size());
        // System.out.println(potentialEmployeeList); // to print potential employee list

    }
}
