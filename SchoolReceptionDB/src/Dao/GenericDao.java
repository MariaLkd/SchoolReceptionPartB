package Dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericDao {

    private final String URL = "jdbc:mysql://localhost/school?allowMultiQueries=true";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private static final String CREATE_DB = "CREATE SCHEMA IF NOT EXISTS school;";

    public void createDB() throws FileNotFoundException {
        PreparedStatement pstm = null;
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost?allowMultiQueries=true",USER,PASSWORD);
            pstm = con.prepareStatement(CREATE_DB);
            pstm.execute();
            createTables();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{closeConnections(pstm, con);}
    }
    
    protected void createTables() throws FileNotFoundException{
        String CREATETABLES = new Scanner(new File("createTables.sql")).useDelimiter("\\Z").next();
        Connection con = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement(CREATETABLES);
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, con);
        }
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    protected void closeConnections(ResultSet rs, Statement stmt, Connection con) {
        try {
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void closeConnections(PreparedStatement pstm, Connection con) {
        try {
            pstm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
