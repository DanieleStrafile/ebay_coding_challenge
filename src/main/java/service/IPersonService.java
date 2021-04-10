package service;

import java.io.BufferedReader;
import java.util.List;

import model.Person;

public interface IPersonService {

    long getNumberFemales(List<Person> people);

    List<Person> getOldest(List<Person> people);

    List<Person> parseCSVFile(BufferedReader bufferedReader);

}
