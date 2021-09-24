package Dao;

import Entities.Assignment;
import Entities.Course;
import Entities.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerStudentDao extends GenericDao {

    private static final String ASSIGNMENTS_PER_STUDENT = "SELECT * FROM assignments JOIN courses_have_students as chs ON chs.courseId = assignments.courseId WHERE chs.studentId = ? ORDER BY assignments.courseId, assignments.title asc";
    private static final CourseDao CD = new CourseDao();

    public ArrayList<Assignment> assignmentsPerStudentPerCourse(Student student) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Course course = null;
        ArrayList<Assignment> assignmentsPerStudent = new ArrayList();
        try {
            pstm = con.prepareStatement(ASSIGNMENTS_PER_STUDENT);
            pstm.setInt(1, student.getId());
            rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString(2);
                String desc = rs.getString(3);
                LocalDate subDate = rs.getDate(4).toLocalDate();
                int courseId = rs.getInt(7);
                course = CD.findById(courseId);
                Assignment assignment = new Assignment(title, desc, subDate, course);
                assignmentsPerStudent.add(assignment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PerStudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm, con);
        }    
        return assignmentsPerStudent;
    }
}
