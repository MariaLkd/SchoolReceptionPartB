package Dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Lykoudi
 */
public class SyntheticDataDao {

    private static boolean alreadyAdded = false;
    private static final GenericDao GD = new GenericDao();
    private static final StudentDao SD = new StudentDao();
    private static final CourseDao CD = new CourseDao();
    private static final AssignmentDao AD = new AssignmentDao();
    private static final TrainerDao TD = new TrainerDao();

    public static void add() throws FileNotFoundException {
        if (alreadyAdded == false) {
            String ADDDATA = new Scanner(new File("syntheticData.sql")).useDelimiter("\\Z").next();
            Connection con = null;
            PreparedStatement pstm = null;
            if (SD.findAll().isEmpty() & CD.findAll().isEmpty() & AD.findAll().isEmpty() & TD.findAll().isEmpty()) {
                con = GD.getConnection();
                try {
                    pstm = con.prepareStatement(ADDDATA);
                    pstm.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(SyntheticDataDao.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    GD.closeConnections(pstm, con);
                }
                System.out.println("Synthetic Data added successfully");
                alreadyAdded = true;
            } else {
                System.out.println("Synthetic Data can't be added when user data have been entered");
            }
        } else {
            System.out.println("Synthetic Data already added");
        }
    }

}
