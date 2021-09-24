package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private double tuitionFees;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    public Student(String firstName, String lastName, LocalDate dateOfBirth, double tuitionFees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
    }

    public Student(int id, String firstName, String lastName, LocalDate dateOfBirth, double tuitionFees) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(double tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return lastName + " " + firstName + ", Date Of Birth: "+FORMAT.format(dateOfBirth)+", Tuition Fees: "+tuitionFees;
    }

}
