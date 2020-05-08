
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminClientData {
    int clientID;
    String firstname;
    String lastname;
    String email;
    String password;
    int gender;
    int status;
    int inactive;

    adminClientData (int clientID, String firstname,String lastname, String email, int gender, int inactive) {
        this.clientID = clientID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.inactive = inactive;
    }
    public static Vector<adminClientData> getadminClientData(Connection connection) {
        Vector<adminClientData> vec = new Vector<adminClientData>();
        String sql = "Select * FROM Client WHERE inactive = ?";
        //String sql = "Select Client.*, EmploymentRecord.status FROM Client LEFT JOIN EmploymentRecord ORDER BY Client.clientID DESC";
        System.out.println("Get client list SQL: " + sql);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 0);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int clientID = Integer.parseInt(result.getString("clientID"));
                adminClientData client = new adminClientData(
                    clientID,
                    result.getString("firstname"),
                    result.getString("lastname"),
                    result.getString("email"),
                    Integer.parseInt(result.getString("gender")),
                    Integer.parseInt(result.getString("inactive"))
                    //getStatus(connection, clientID)
                    // Integer.parseInt(result.getString("EmploymentRecord.status"))
                );
                System.out.println("id " + clientID);
                vec.addElement(client);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in get Client List: " + sql + " Exception: " + e);
        }
        return vec;
    }

    public static adminClientData getadminClient(Connection connection, int clientID) {
        adminClientData clientData = null;
        //String sql = "Select Client.*, EmploymentRecord.status FROM Client LEFT JOIN EmploymentRecord";
        String sql = "SELECT * FROM Client";
        sql +=  " WHERE clientID = ?";
        System.out.println("get Client: " + sql);
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, clientID);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                 clientData = new adminClientData(
                    clientID,
                    result.getString("Client.firstname"),
                    result.getString("Client.lastname"),
                    result.getString("Client.email"),
                    Integer.parseInt(result.getString("Client.gender")),
                    Integer.parseInt(result.getString("Client.inactive"))
                    //getStatus(connection, clientID)
                    // Integer.parseInt(result.getString("EmploymentRecord.status"))
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getHeader: " + sql + " Exception: " + e);
        }
        return clientData;
    }

    public static int deleteClient(Connection connection, int clientID) {
        //String sql ="DELETE * FROM Client, Message WHERE clientID = ?";
        String sql = "UPDATE [Client] SET inactive = 1 WHERE clientID = ?";
        System.out.println("update: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1, clientID);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in update: " + sql + " Exception: " + e);
        }
        return n;
    }

    public static int getStatus(Connection connection, int clientID) {
        int stat = 0;
        String sql = "Select status, startdate FROM EmploymentRecord ORDER BY startdate DESC";
        System.out.println("getStatus: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            result.next();
            stat = Integer.parseInt(result.getString("status"));
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getStatus: " + sql + " Exception: " + e);
        }
        return stat;
    }

    public static Vector<adminClientData> getadminClientDataByGender(Connection connection, int gender) {
        Vector<adminClientData> vec = new Vector<adminClientData>();
        String sql = "Select * FROM Client WHERE inactive = ? AND gender = ?";
        System.out.println("Get client list SQL: " + sql);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 0);
            statement.setInt(2, gender);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int clientID = Integer.parseInt(result.getString("clientID"));
                adminClientData client = new adminClientData(
                    clientID,
                    result.getString("firstname"),
                    result.getString("lastname"),
                    result.getString("email"),
                    Integer.parseInt(result.getString("gender")),
                    Integer.parseInt(result.getString("inactive"))
                );
                vec.addElement(client);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in get Client List: " + sql + " Exception: " + e);
        }
        return vec;
    }

}
