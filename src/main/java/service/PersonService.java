package service;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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
		.min((person1, person2) -> person1.getDateOfBirth().compareTo(person2.getDateOfBirth())).get()
		.getDateOfBirth();

	return people.stream().filter(person -> person.getDateOfBirth().equals(oldestDate))
		.collect(Collectors.toList());
    }

    @Override
    public List<Person> parseCSVFile(BufferedReader bufferedReader) {

	Function<String, Person> mapToPerson = (line) -> {

	    String[] attributes = line.split(",");

	    String firstName = attributes[0].trim();
	    Gender gender = attributes[1].trim().equals("Male") ? Gender.MALE : Gender.FEMALE;
	    LocalDate dateOfBirth = LocalDate.parse(attributes[2].trim());

	    return new Person(firstName, gender, dateOfBirth);
	};

	return bufferedReader.lines().map(mapToPerson).collect(Collectors.toList());

    }

}
