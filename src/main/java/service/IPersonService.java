package service;

import java.util.List;

import model.Person;

public interface IPersonService {

    long getNumberFemales(List<Person> people);

    List<Person> getOldest(List<Person> people);

}
