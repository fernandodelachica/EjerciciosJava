package org.example;


import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String pathFile = "src/main/resources/Archivo.csv";
        FileCSV fc = new FileCSV();
        FilterCSV fl = new FilterCSV();

        try {
            List<Person> people = fc.readCSV(pathFile);
            System.out.println("Esta es la lista con todas las personas en el archivo csv:");
            //Muestra toda la lista de personas
            fl.showPerson(people);
            //Filtro que muestra a las personas con la edad: 29
            fl.filterByAge(people);
            //Filtro que muestra a las personas en la ciudad: Madrid
            fl.filterByCity(people);
            //Filtro que muestra a la persona que tiene entre 20 y 40 años y empieza por la Letra M
            fl.filterByNameAndAge(people);

        } catch (IOException | InvalidLineFormatException e) {
            String errorMsg = "Error: ";
            System.out.println(errorMsg + e.getMessage());
        }
    }
}