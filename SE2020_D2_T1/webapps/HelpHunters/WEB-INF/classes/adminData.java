
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminData {
    int    administratorID;
    String firstname;
    String lastname;
    String email;
    String password;

    adminData (int administratorID, String firstname, String lastname, String email, String password) {
        this.administratorID = administratorID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public static adminData getadminData(Connection connection, String email, String password) {
        adminData adminData = null;
        String sql = "Select * FROM Administrator WHERE email = ? AND password = ?";
        System.out.println("getList: " + sql);
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                 adminData = new adminData(
                    Integer.parseInt(result.getString("administratorID")),
                    result.getString("firstname"),
                    result.getString("lastname"),
                    email,
                    result.getString("password")
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getHeader: " + sql + " Exception: " + e);
        }
        return adminData;
    }

    public static int updatePassword(Connection connection, adminData info) {
        String sql ="UPDATE  Administrator"
            + " SET password = ?"
            + " WHERE administratorID = ?";
        System.out.println("This is the query: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,info.password);
            stmtUpdate.setInt(2,info.administratorID);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in update: " + sql + " Exception: " + e);
        }
        return n;
    }
    public static int getAdminAccess(Connection connection, String username, String password) {
        String sql = "SELECT COUNT(email) FROM Administrator WHERE email = ? AND password = ?;";
        System.out.println("Query performed: " + sql);
        int n = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet result = stm.executeQuery();
            if(result.next()){
              n = result.getInt(1);
            } else {
              n = 0;
            }
            stm.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in query: " + sql + " Exception: " + e);
        }
        System.out.println(n);
        return n;
    }

    public static int updateSettingsPW(Connection connection, int administratorID, String firstname, String lastname,
                                        String email, String password) {
        String sql = "UPDATE Administrator";
        sql += " SET firstname = ?, lastname = ?, email = ?, password =?";
        sql += " WHERE administratorID = ?";
        System.out.println("Query performed: " + sql);
        int n = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, firstname);
            stm.setString(2, lastname);
            stm.setString(3, email);
            stm.setString(4, password);
            stm.setInt(5, administratorID);
            //We set the output
            n = stm.executeUpdate();
            stm.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in query: " + sql + " Exception: " + e);
        }
        System.out.println(n);
        return n;
    }

    public static int updateSettings(Connection connection, int administratorID, String firstname, String lastname,
                                        String email) {
        String sql = "UPDATE Administrator";
        sql += " SET firstname = ?, lastname = ?, email = ?";
        sql += " WHERE administratorID = ?";
        System.out.println("Query performed: " + sql);
        int n = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, firstname);
            stm.setString(2, lastname);
            stm.setString(3, email);
            stm.setInt(4, administratorID);
            //We set the output
            n = stm.executeUpdate();
            stm.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in query: " + sql + " Exception: " + e);
        }
        System.out.println(n);
        return n;
    }


}
