package model;

import java.util.Date;

public class Person {

    enum Gender {
	MALE, FEMALE
    }

    private final String firstName;
    private final Gender gender;
    private final Date dateOfBirth;

    public Person(String firstName, Gender gender, Date dateOfBirth) {
	this.firstName = firstName;
	this.gender = gender;
	this.dateOfBirth = new Date(dateOfBirth.getTime());
    }

    public String getFirstName() {
	return firstName;
    }

    public Gender getGender() {
	return gender;
    }

    public Date getDateOfBirth() {
	return new Date(dateOfBirth.getTime());
    }

}
