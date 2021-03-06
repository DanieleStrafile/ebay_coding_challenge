package service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	List<Person> oldestPeople = service.getOldest(null);

	assertTrue(oldestPeople.isEmpty());

    }

    @Test
    public void when_no_people_then_no_oldest_person() {

	IPersonService service = new PersonService();

	List<Person> oldestPeople = service.getOldest(new ArrayList<>());

	assertTrue(oldestPeople.isEmpty());

    }

    @Test
    public void when_one_oldest_then_return_oldest() {

	List<Person> people = Arrays.asList(new Person("Miranda", Gender.FEMALE, LocalDate.of(1980, 12, 1)),
		new Person("Anita", Gender.FEMALE, LocalDate.of(1980, 12, 2)),
		new Person("Clark", Gender.MALE, LocalDate.of(1980, 12, 3)));
	IPersonService service = new PersonService();
	Person expected = new Person("Miranda", Gender.FEMALE, LocalDate.of(1980, 12, 1));

	List<Person> oldestPeople = service.getOldest(people);

	assertEquals(expected, oldestPeople.get(0));

    }

    @Test
    public void when_several_oldest_then_return_all_oldest() {

	List<Person> people = Arrays.asList(new Person("Miranda", Gender.FEMALE, LocalDate.of(1980, 12, 1)),
		new Person("Matthew", Gender.MALE, LocalDate.of(1980, 12, 1)),
		new Person("Anita", Gender.FEMALE, LocalDate.of(1980, 12, 2)),
		new Person("Clark", Gender.MALE, LocalDate.of(1980, 12, 3)));
	IPersonService service = new PersonService();
	List<Person> expected = Arrays.asList(new Person("Matthew", Gender.MALE, LocalDate.of(1980, 12, 1)),
		new Person("Miranda", Gender.FEMALE, LocalDate.of(1980, 12, 1)));

	List<Person> oldestPeople = service.getOldest(people);
	oldestPeople.sort((person1, person2) -> person1.getFirstName().compareTo(person2.getFirstName()));

	assertEquals(expected, oldestPeople);

    }

    @Test
    public void when_read_address_book_file_then_get_people() throws IOException {

	InputStream inputFS = getClass().getClassLoader().getResourceAsStream("address-book.csv");

	try (BufferedReader br = new BufferedReader(new InputStreamReader(inputFS))) {

	    IPersonService service = new PersonService();
	    List<Person> people = service.parseCSVFile(br);
	    people.sort((person1, person2) -> person1.getFirstName().compareTo(person2.getFirstName()));

	    List<Person> expected = Arrays.asList(new Person("Jessica", Gender.FEMALE, LocalDate.of(1990, 11, 5)),
		    new Person("Jon", Gender.MALE, LocalDate.of(1985, 3, 9)),
		    new Person("Michael", Gender.MALE, LocalDate.of(1973, 4, 20)),
		    new Person("Paul", Gender.MALE, LocalDate.of(1985, 7, 20)),
		    new Person("Sarah", Gender.FEMALE, LocalDate.of(1980, 8, 17)));

	    assertThat(people, is(expected));

	}

    }

}
