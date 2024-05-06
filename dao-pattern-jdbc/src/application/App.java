package application;

import db.DB;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class App {
    static final String user="";
    static final String password="";
    static final String dburl="";
    public static void main(String[] args) {
    /*
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(dburl, user, password);
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM department");

            while(rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
        */


        Department dep = new Department(1, "Books");

        Seller seller = new Seller(2, "Jeff", "Jeff@gmail.com", new Date(), 2000.00, dep);

        System.out.println(seller);
    }
}