package service;

import java.util.List;
import java.util.Optional;

import model.Person;

public interface IPersonService {

    long getNumberFemales(List<Person> people);

    Optional<Person> getOldestPerson(List<Person> people);

}
