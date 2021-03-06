package model;

import java.time.LocalDate;

public class Person {

    public enum Gender {
	MALE, FEMALE
    }

    private final String firstName;
    private final Gender gender;
    private final LocalDate dateOfBirth;

    public Person(String firstName, Gender gender, LocalDate dateOfBirth) {
	this.firstName = firstName;
	this.gender = gender;
	this.dateOfBirth = LocalDate.of(dateOfBirth.getYear(), dateOfBirth.getMonthValue(),
		dateOfBirth.getDayOfMonth());
    }

    public String getFirstName() {
	return firstName;
    }

    public Gender getGender() {
	return gender;
    }

    public LocalDate getDateOfBirth() {
	return LocalDate.of(dateOfBirth.getYear(), dateOfBirth.getMonthValue(), dateOfBirth.getDayOfMonth());
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((gender == null) ? 0 : gender.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Person other = (Person) obj;
	if (dateOfBirth == null) {
	    if (other.dateOfBirth != null)
		return false;
	} else if (!dateOfBirth.equals(other.dateOfBirth))
	    return false;
	if (firstName == null) {
	    if (other.firstName != null)
		return false;
	} else if (!firstName.equals(other.firstName))
	    return false;
	if (gender != other.gender)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Person [firstName=" + firstName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + "]";
    }

}
