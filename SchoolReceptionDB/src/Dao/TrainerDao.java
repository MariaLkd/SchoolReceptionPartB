/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Course;
import Entities.Trainer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria
 */
public class TrainerDao extends GenericDao {

    private static final String ALL_TRAINERS = "SELECT * FROM trainers ORDER BY lastName asc";
    private static final String ADD_TRAINER = "INSERT IGNORE INTO courses_have_trainers(courseId, trainerId) VALUES (?,?)";
    private static final String INSERT = "INSERT INTO trainers(firstName, lastName, subject) VALUES (?,?,?)";
    private static final String TRAINER_COURSES = "SELECT * from courses JOIN courses_have_trainers as chs ON chs.courseId = courses.courseId WHERE trainerId = 1 ORDER BY courses.title, courses.stream, courses.type asc";
    private static final String FIND_BY_MAX_ID = "SELECT * FROM trainers WHERE trainerid = (SELECT max(trainerid) FROM trainers)";

    public ArrayList<Trainer> findAll() {
        ArrayList<Trainer> trainers = new ArrayList();
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(ALL_TRAINERS);
            while (rs.next()) {
                int id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String subject = rs.getString(4);
                Trainer trainer = new Trainer(id, fname, lname, subject);
                trainers.add(trainer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt, con);
        }
        return trainers;
    }

    public void create(Trainer trainer) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement(INSERT);
            pstm.setString(1, trainer.getFirstName());
            pstm.setString(2, trainer.getLastName());
            pstm.setString(3, trainer.getSubject());
            int done = pstm.executeUpdate();
            if (done == 1) {
                System.out.println("Trainer saved!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Trainer findByMaxId() {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Trainer trainer = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(FIND_BY_MAX_ID);
            rs.next();
            int trainerId = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            String subj = rs.getString(4);
            trainer = new Trainer(trainerId, fname, lname, subj);
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt, con);
        }
        return trainer;
    }

    public void addCourseToTrainer(Course course, Trainer trainer) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement(ADD_TRAINER);
            pstm.setInt(1, course.getCourseId());
            pstm.setInt(2, trainer.getId());
            int check = pstm.executeUpdate();
            if (check == 1) {
                System.out.println(trainer.getFirstName() + " " + trainer.getLastName() + " now teaches " + course.getTitle() + " " + course.getStream() + " " + course.getType());
            }else {
                System.out.println("Trainer already teaches this course");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, con);
        }
    }

    public ArrayList<Course> getCourses(Trainer trainer) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Course> trainerCourses = new ArrayList();
        try {
            pstm = con.prepareStatement(TRAINER_COURSES);
            pstm.setInt(1, trainer.getId());
            rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString(2);
                String stream = rs.getString(3);
                String type = rs.getString(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();
                Course course = new Course(title, stream, type, startDate, endDate);
                trainerCourses.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainerCourses;
    }
}
