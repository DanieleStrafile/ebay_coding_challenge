package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import model.Person;
import service.IPersonService;
import service.PersonService;

public class Main {

    public static void main(String[] args) {

	InputStream inputFS = Main.class.getClassLoader().getResourceAsStream("address-book.csv");

	try (BufferedReader br = new BufferedReader(new InputStreamReader(inputFS))) {

	    IPersonService service = new PersonService();
	    List<Person> people = service.parseCSVFile(br);
	    System.out.println("Number of females inside address book: " + service.getNumberFemales(people));

	    System.out.println("Oldest person inside address book: " + service.getOldest(people));

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}