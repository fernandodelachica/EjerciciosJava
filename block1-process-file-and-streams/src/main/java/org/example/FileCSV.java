package org.example;

import java.io.IOException;
import java.io.InvalidClassException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileCSV {
    public List<Person> readCSV(String pathFile) throws IOException, InvalidLineFormatException {
        List<Person> people = new ArrayList<>();

        Path file = Paths.get(pathFile);

        try{
            List<String> lines = Files.readAllLines(file);

            int counter = 0;
            for(String line : lines){
                counter ++;

                try{
                    Person person = personLine(line);
                    people.add(person);
                } catch (InvalidLineFormatException e) {
                    String errorMsg = "Error en la línea " + counter + ". Por " + e.getMessage();
                    throw new InvalidLineFormatException(errorMsg);
                }

            }

        } catch (InvalidClassException e) {
            String errorMsg = "Error en el archivo: ";
            throw new InvalidLineFormatException(errorMsg + pathFile);
        }
        return people;
    }

    private Person personLine(String line) throws InvalidLineFormatException {
        String[] lineData = line.split(":");

        if (lineData.length == 0 || lineData.length > 3) {
            String errorMsg = "Línea inválida";
            throw new InvalidLineFormatException(errorMsg);
        }
        String name = lineData[0];
        String town;
        if (lineData.length > 1) {
            town = lineData[1];
        } else {
            town = "";
        }
        int age;
        if (lineData.length > 2) {
            age = parseAge(lineData[2]);
        } else {
            age = 0;
        }
        return new Person(name, town, age);
    }

    private int parseAge(String ageStr) throws InvalidLineFormatException {
        try {
            int age = Integer.parseInt(ageStr);

            if (age == 0){
                return -1;
            }
            return age;
        }catch (NumberFormatException e){
            String errorMsg = "Edad incorrecta";
            throw new InvalidLineFormatException(errorMsg);
        }
    }
}
