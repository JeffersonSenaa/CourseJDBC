package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete {
    static final String user = "";
    static final String dburl = "";
    static final String password = "";

    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(dburl, user, password);
            st = conn.prepareStatement("DELETE FROM department "
            + "WHERE Id=?");
            st.setInt(1, 5);

            int rowAffected = st.executeUpdate();
            System.out.println("Done! Row affected: " + rowAffected);

        } catch(SQLException e){
            throw new DbIntegrityException(e.getMessage());
        }
        finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

}
