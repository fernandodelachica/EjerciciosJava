package org.example;

import java.util.List;
import java.util.Optional;

public class FilterCSV {

    public static void showPerson(List<Person> people){
        people.stream().forEach(person -> {
            System.out.println(person);
        });
    }
    private static List<Person> filterAge(List<Person> people, int minAge, int maxAge){
        return people.stream()
                .filter(person -> minAge == -1 || maxAge == -1 || (person.getAge() >= minAge && person.getAge() <= maxAge))
                .toList();
    }

    private static List<Person> filterCity(List<Person> people, String town){
        return people.stream()
                .filter(person -> person.getTown() != null && person.getTown().equalsIgnoreCase(town))
                .toList();
    }

    public static void filterByAge(List<Person> people){
        int minAge = 29;
        int maxAge = 38;

        List<Person> between20and39List = filterAge(people, minAge, maxAge);

        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("Personas entre: " +minAge+ " y " +maxAge +" años");
        for (Person person : between20and39List){
            System.out.println(person);
        }
    }

    public static void filterByCity(List<Person> people) {
        String town = "Madrid";

        List<Person> filteredPeople = filterCity(people, town);

        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("Personas en " + town + ":");

        for (Person person : filteredPeople) {
            System.out.println(person);
        }
    }

    public static void filterByNameAndAge(List<Person> people){
        List<Person> filteredPeopleAge = filterAge(people, 20, 38);

        Optional<Person> nameStartWithM = filteredPeopleAge.stream()
                .filter(person -> person.getName() != null && person.getName().startsWith("M"))
                .findAny();

        nameStartWithM.ifPresent(person -> {
            System.out.println("------------------------------------");
            System.out.println("Persona que tiene entre 20 y 40 años y empieza por la Letra M: ");
            System.out.println(person);
        });
    }

    public static void filterByCityAndName(List<Person> people){

        List<Person> filteredPeopleCity = filterCity(people, "Logroño");

        Optional<Person> nameAndCity = filteredPeopleCity.stream()
                .filter(person -> person.getName() != null && person.getName().equalsIgnoreCase("Carlos"))
                .findAny();

        nameAndCity.ifPresent(person -> {
            System.out.println("------------------------------------");
            System.out.println("Persona que es de Logroño y se llama Carlos: ");
            System.out.println(person);
        });
    }


}
