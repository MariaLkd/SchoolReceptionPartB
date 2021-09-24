package Dao;

import Entities.Course;
import Entities.Student;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDao extends GenericDao {

    private static final String ALLSTUDENTS = "SELECT * FROM students ORDER BY lastName asc";
    private static final String INSERT = "INSERT INTO students(firstName, lastName, dateOfBirth, tuitionFees) VALUES(?,?,?,?)";
    private static final String ADDCOURSE = "INSERT IGNORE INTO courses_have_students VALUES (?,?)";
    private static final String FINDBYMAXID = "SELECT * FROM students WHERE studentid = (SELECT max(studentid) FROM students)";
    private static final String MULTIPLECOURSES = "SELECT * FROM students JOIN courses_have_students as chs ON chs.studentId = Students.studentId GROUP BY chs.studentId HAVING count(chs.studentId)>1 ORDER BY students.lastName asc";
    private static final PerCourseDao PCD = new PerCourseDao();

    public void create(Student student) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement(INSERT);
            pstm.setString(1, student.getFirstName());
            pstm.setString(2, student.getLastName());
            pstm.setDate(3, Date.valueOf(student.getDateOfBirth()));
            pstm.setDouble(4, student.getTuitionFees());
            int done = pstm.executeUpdate();
            if (done == 1) {
                System.out.println("Student saved!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Student> findAll() {
        ArrayList<Student> students = new ArrayList();
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(ALLSTUDENTS);
            while (rs.next()) {
                int id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                LocalDate dob = rs.getDate(4).toLocalDate();
                int tuitionFees = rs.getInt(5);
                Student student = new Student(id, fname, lname, dob, tuitionFees);
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt, con);
        }
        return students;
    }

    public Student findByMaxId() {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Student student = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(FINDBYMAXID);
            rs.next();
            int studentId = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            LocalDate dob = rs.getDate(4).toLocalDate();
            int tuitionFees = rs.getInt(5);
            student = new Student(studentId, fname, lname, dob, tuitionFees);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt, con);
        }
        return student;
    }

    public void addCourseToStudent(Course course, Student student) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement(ADDCOURSE);
            pstm.setInt(1, course.getCourseId());
            pstm.setInt(2, student.getId());
            int check = pstm.executeUpdate();
            if (check == 1) {
                System.out.println("Student " + student.getFirstName() + " " + student.getLastName() + " is now enrolled in course " + course);
            } else {
                System.out.println("Student already enrolled in this course.");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, con);
        }
    }

//    public void checkIfEnrolled(Course course, Student student)
    public ArrayList<Student> moreThanOneCourses() {

        Connection con = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Student> multipleCourses = new ArrayList();
        try {
            pstm = con.prepareStatement(MULTIPLECOURSES);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                LocalDate dob = rs.getDate(4).toLocalDate();
                int tuitionFees = rs.getInt(5);
                Student student = new Student(studentId, fname, lname, dob, tuitionFees);
                multipleCourses.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return multipleCourses;
    }
}
