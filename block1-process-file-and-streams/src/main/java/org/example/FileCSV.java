package org.example;

import java.io.IOException;
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

        } catch (IOException e) {
            String errorMsg = "Error en el archivo: ";
            throw new IOException(errorMsg + pathFile);
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
            town = "Unknown";
        }
        int age = 0;
        if (lineData.length > 2) {
            try {
                String ageStr = lineData[2];
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                String errorMsg = "El dato de Edad no es compatible";
                throw new InvalidLineFormatException(errorMsg);
            }
        }
        return new Person(name, town, age);
    }
}
