package org.example;


import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String pathFile = "/src/main/resources/Archivo.csv";
        FileCSV fc = new FileCSV();

        try {
            List<Person> people = fc.readCSV(pathFile);

            for (Person person : people){
                System.out.println(
                        "Name: " + (person.getName())+
                                ". Town: " + (person.getTown() != null ? person.getTown() : "Unknown")+
                                ". Age: " + (person.getAge() != 0 ? person.getAge() : "Unknown")
                );
            }
        } catch (IOException | InvalidLineFormatException e) {
            String errorMsg = "Error: ";
            System.out.println(errorMsg + e.getMessage());
        }
    }
}