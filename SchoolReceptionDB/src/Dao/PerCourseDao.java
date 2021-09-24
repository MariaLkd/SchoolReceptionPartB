package Dao;

import Entities.Assignment;
import Entities.Course;
import Entities.Student;
import Entities.Trainer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerCourseDao extends GenericDao {

    private static final String STUDENTS_PER_COURSE = "SELECT * FROM students JOIN courses_have_students as chs ON chs.studentId = Students.studentId WHERE chs.courseId=? ORDER BY students.lastName asc";
    private static final String TRAINERS_PER_COURSE = "SELECT * FROM trainers JOIN courses_have_trainers as cht ON cht.trainerId = Trainers.trainerId WHERE cht.courseId=? ORDER BY trainers.lastName asc";
    private static final String ASSIGNMENTS_PER_COURSE = "SELECT * FROM assignments WHERE assignments.courseId = ? ORDER BY title asc";

    public ArrayList<Student> studentsPerCourse(Course course) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Student> studentsPerCourse = new ArrayList();
        try {
            pstm = con.prepareStatement(STUDENTS_PER_COURSE);
            pstm.setInt(1, course.getCourseId());
            rs = pstm.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                LocalDate dob = rs.getDate(4).toLocalDate();
                double tuitionFees = rs.getDouble(5);
                Student student = new Student(studentId, fname, lname, dob, tuitionFees);
                studentsPerCourse.add(student);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm, con);
        }
        return studentsPerCourse;
    }

    public ArrayList<Trainer> trainersPerCourse(Course course) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Trainer> trainersPerCourse = new ArrayList();
        try {
            pstm = con.prepareStatement(TRAINERS_PER_COURSE);
            pstm.setInt(1, course.getCourseId());
            rs = pstm.executeQuery();
            while (rs.next()) {
                int trainerId = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String subject = rs.getString(4);
                Trainer trainer = new Trainer(trainerId, fname, lname, subject);
                trainersPerCourse.add(trainer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm, con);
        }
        return trainersPerCourse;
    }

    public ArrayList<Assignment> assignmentsPerCourse(Course course) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Assignment> assignmentsPerCourse = new ArrayList();
        try {
            pstm = con.prepareStatement(ASSIGNMENTS_PER_COURSE);
            pstm.setInt(1, course.getCourseId());
            rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString(2);
                String desc = rs.getString(3);
                LocalDate subDate = rs.getDate(4).toLocalDate();
                Assignment assignment = new Assignment(title, desc, subDate);
                assignmentsPerCourse.add(assignment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm, con);
        }
        return assignmentsPerCourse;
    }
}
