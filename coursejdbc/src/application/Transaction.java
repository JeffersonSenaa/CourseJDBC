package application;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Transaction {
    static final String user = "";
    static final String dburl = "";
    static final String password = "";

    public static void main(String[] args){
        Connection conn = null;
        Statement st = null;
        try{
            conn = DriverManager.getConnection(dburl, user, password);
            conn.setAutoCommit(false);
            st = conn.createStatement();

            int rowAffected = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 2 ");

            int rowAffected2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 3 ");

            conn.commit();

            System.out.println("Done! Row affected: " + rowAffected);
            System.out.println("Done! Row affected: " + rowAffected2);
        } catch(SQLException e){
            try{
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());

            } catch (SQLException e1){
                throw new DbException("Error rollback! Caused by: " + e1.getMessage());
            }
        }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

}
