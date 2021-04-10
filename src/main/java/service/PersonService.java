package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Person> getOldest(List<Person> people) {

	if (people == null || people.isEmpty()) {
	    return new ArrayList<>();
	}

	LocalDate oldestDate = people.stream()
		.max((person1, person2) -> person1.getDateOfBirth().compareTo(person2.getDateOfBirth())).get()
		.getDateOfBirth();

	return people.stream().filter(person -> person.getDateOfBirth().equals(oldestDate))
		.collect(Collectors.toList());
    }

}
