package Dao;

import Entities.Course;
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

public class CourseDao extends GenericDao {

    private static final String ALL_COURSES = "SELECT * FROM courses ORDER BY title, stream, type asc";
    private static final String INSERT = "INSERT INTO courses(title, stream, type, start_date, end_date) VALUES (?,?,?,?,?)";
    private static final String FINDBYID = "SELECT * FROM courses WHERE courseId = ?";

    public void create(Course course) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement(INSERT);
            pstm.setString(1, course.getTitle());
            pstm.setString(2, course.getStream());
            pstm.setString(3, course.getType());
            pstm.setDate(4, Date.valueOf(course.getStart_date()));
            pstm.setDate(5, Date.valueOf(course.getEnd_date()));
            int done = pstm.executeUpdate();
            if (done == 1) {
                System.out.println("Course saved!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Course findById(int id) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Course course = null;
        try {
            pstm = con.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int courseId = rs.getInt(1);
            String title = rs.getString(2);
            String stream = rs.getString(3);
            String type = rs.getString(4);
            LocalDate startDate = rs.getDate(5).toLocalDate();
            LocalDate endDate = rs.getDate(6).toLocalDate();
            course = new Course(courseId, title, stream, type, startDate, endDate);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm, con);
        }
        return course;
    }

    public ArrayList<Course> findAll() {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Course> courses = new ArrayList();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(ALL_COURSES);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String stream = rs.getString(3);
                String type = rs.getString(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();
                Course course = new Course(id, title, stream, type, startDate, endDate);
                courses.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt, con);
        }
        return courses;
    }
}
