import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminLoginData {
    String username;
    String password;

    adminLoginData (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static int getAdminAccess(Connection connection, String username, String password) {
        String sql = "SELECT COUNT(*) FROM Administrator WHERE email = ? AND password = ?;";
        System.out.println("update: " + sql);
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
        return n;
    }
}
