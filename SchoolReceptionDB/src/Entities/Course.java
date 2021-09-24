package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Course {
    
    private int courseId;
    private String title;
    private String stream;
    private String type;
    private LocalDate start_date;
    private LocalDate end_date;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    
    public Course(String title, String stream, String type, LocalDate start_date, LocalDate end_date){
        this.title=title;
        this.stream=stream;
        this.type=type;
        this.start_date=start_date;
        this.end_date=end_date;
    }

    public Course(int courseId, String title, String stream, String type, LocalDate start_date, LocalDate end_date) {
        this.courseId = courseId;
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return title + " " + stream + " " + type + ", Start date: " + FORMAT.format(start_date) + ", End date: " + FORMAT.format(end_date);
    }
    
}
