package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignment {

    private int assignmentId;
    private String title;
    private String description;
    private LocalDate subDateTime;
    private int oralMark;
    private int totalMark;
    private Course course;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    public Assignment(String title, String description, LocalDate subDateTime) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
    }

    public Assignment(int assignmentId, String title, String description, LocalDate subDateTime) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
    }

    public Assignment(String title, String description, LocalDate subDateTime, Course course) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDate subDateTime) {
        this.subDateTime = subDateTime;
    }

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Assignment title: " + title + ", Description: " + description + ", Submission Date: " + FORMAT.format(subDateTime);
    }

}
