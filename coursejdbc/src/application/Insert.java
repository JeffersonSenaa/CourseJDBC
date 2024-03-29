package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Insert {
    public static void main(String[] args) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;
        try{
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Maria");
            st.setString(2, "maria@gmail.com");
            st.setDate(3, new Date(fmt.parse("30/03/2003").getTime()));
            st.setDouble(4, 5000.0);
            st.setInt(5, 3);

            int rowAffected = st.executeUpdate();

            if(rowAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! ID = " + id);
                }
            }
            else{
                System.out.println("Error!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}