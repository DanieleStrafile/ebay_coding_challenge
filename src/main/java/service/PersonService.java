package service;

import java.util.List;
import java.util.Optional;

import model.Person;
import model.Person.Gender;

public class PersonService implements IPersonService {

    @Override
    public long getNumberFemales(List<Person> people) {
	if (people == null) {
	    return 0;
	}
	return people.stream().filter(person -> person.getGender() == Gender.FEMALE).count();
    }

    @Override
    public Optional<Person> getOldestPerson(List<Person> people) {
	if (people == null) {
	    return Optional.empty();
	}
	return people.stream().max((person1, person2) -> person1.getDateOfBirth().compareTo(person2.getDateOfBirth()));
    }

}
