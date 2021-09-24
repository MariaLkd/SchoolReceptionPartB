package Dao;

import Entities.Assignment;
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

public class AssignmentDao extends GenericDao {

    private static final String ALL_ASSIGNMENTS = "SELECT * FROM assignments ORDER BY title asc";
    private static final String INSERT = "INSERT INTO assignments(title, description, subDateTime, courseId)VALUES (?,?,?,?)";

    public void create(Assignment assignment, Course course) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement(INSERT);
            pstm.setString(1, assignment.getTitle());
            pstm.setString(2, assignment.getDescription());
            pstm.setDate(3, Date.valueOf(assignment.getSubDateTime()));
            pstm.setInt(4, course.getCourseId());
            int done = pstm.executeUpdate();
            if(done==1){System.out.println("Assignment was saved to course "+course);}
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Assignment> findAll() {
        Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt = null;
        ArrayList<Assignment> assignments = new ArrayList();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(ALL_ASSIGNMENTS);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                LocalDate subDateTime = rs.getDate(4).toLocalDate();
                Assignment assignment = new Assignment(id, title, description, subDateTime);
                assignments.add(assignment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assignments;
    }

}
