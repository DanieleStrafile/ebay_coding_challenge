package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import model.Person;
import model.Person.Gender;

public class PersonServiceTest {

    @Test
    public void when_no_females_then_zero_females() {
	List<Person> people = Arrays.asList(new Person("John", Gender.MALE, LocalDate.ofEpochDay(1l)),
		new Person("Peter", Gender.MALE, LocalDate.ofEpochDay(2l)),
		new Person("Clark", Gender.MALE, LocalDate.ofEpochDay(3l)));
	IPersonService service = new PersonService();

	long expected = service.getNumberFemales(people);

	assertEquals(expected, 0);
    }

    @Test
    public void when_two_females_then_two_females() {
	List<Person> people = Arrays.asList(new Person("Miranda", Gender.FEMALE, LocalDate.ofEpochDay(1l)),
		new Person("Anita", Gender.FEMALE, LocalDate.ofEpochDay(2l)),
		new Person("Clark", Gender.MALE, LocalDate.ofEpochDay(3l)));
	IPersonService service = new PersonService();

	long expected = service.getNumberFemales(people);

	assertEquals(expected, 2);
    }

    @Test
    public void when_no_people_then_zero_females() {
	IPersonService service = new PersonService();

	long expected = service.getNumberFemales(new ArrayList<>());

	assertEquals(expected, 0);
    }

    @Test
    public void when_null_people_then_zero_females() {
	IPersonService service = new PersonService();

	long expected = service.getNumberFemales(null);

	assertEquals(expected, 0);
    }

    @Test
    public void when_null_people_then_no_oldest_person() {

	IPersonService service = new PersonService();

	Optional<Person> person = service.getOldestPerson(null);

	assertTrue(person.isEmpty());

    }

    @Test
    public void when_no_people_then_no_oldest_person() {

	IPersonService service = new PersonService();

	Optional<Person> person = service.getOldestPerson(new ArrayList<>());

	assertTrue(person.isEmpty());

    }

    @Test
    public void when_people_then_return_oldest() {

	List<Person> people = Arrays.asList(new Person("Miranda", Gender.FEMALE, LocalDate.ofEpochDay(1l)),
		new Person("Anita", Gender.FEMALE, LocalDate.ofEpochDay(3l)),
		new Person("Clark", Gender.MALE, LocalDate.ofEpochDay(2l)));
	IPersonService service = new PersonService();
	Person expected = new Person("Anita", Gender.FEMALE, LocalDate.ofEpochDay(3l));

	Optional<Person> person = service.getOldestPerson(people);

	assertEquals(expected, person.get());

    }

}
