import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminWarningData {
  int warningID;
    int    noticeID;
    int employmentID;
    int caregiverID;
    int clientID;
    Date datecreated;
    String subject;
    String message;
    int hasbeenread;
    String reply;
    Date datereplied;




    adminWarningData (int noticeID, int employmentID, String subject, String message, Date datecreated,
                    String reply, Date datereplied) {
        this.noticeID = noticeID;
        this.employmentID = employmentID;
        this.subject = subject;
        this.message = message;
        this.datecreated = datecreated;
        this.reply=reply;
        this.datereplied = datereplied;

}

adminWarningData (int noticeID, String subject, String message, Date datecreated) {
    this.noticeID = noticeID;
    this.subject = subject;
    this.message = message;
    this.datecreated = datecreated;

}

adminWarningData (int warningID, int noticeID, String subject, String message, Date datecreated, int hasbeenread) {
  this.warningID = warningID;
    this.noticeID = noticeID;
    this.subject = subject;
    this.message = message;
    this.datecreated = datecreated;
    this.hasbeenread = hasbeenread;

}

adminWarningData ( int noticeID, String subject, String message, Date datecreated, int hasbeenread) {
    this.noticeID = noticeID;
    this.subject = subject;
    this.message = message;
    this.datecreated = datecreated;
    this.hasbeenread = hasbeenread;

}

adminWarningData (int noticeID, String subject, String message) {
    this.noticeID = noticeID;
    this.subject = subject;
    this.message = message;


}




public static Vector<adminWarningData> getadminWarningData(Connection connection) {
        Vector<adminWarningData> vec = new Vector<adminWarningData>();
        String sql = "Select * FROM WarningToCaregiver ";
        System.out.println("Get warningdata SQL: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                adminWarningData warning = new adminWarningData(
                Integer.parseInt(result.getString("warningID")),
                    Integer.parseInt(result.getString("noticeID")),
                    result.getString("subject"),
                    result.getString("message"),
                    result.getDate("datecreated"),
                    Integer.parseInt(result.getString("hasbeenread"))


                );
                vec.addElement(warning);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in get warnindata: " + sql + " Exception: " + e);
        }
        return vec;
    }







public static  int  createWarning(Connection connection, adminWarningData warning) {

    String sql ="INSERT INTO [WarningToCaregiver] (noticeID, subject, message) VALUES (?,?,?)";
    System.out.println("createWarning: " + sql);
      int n = 0;
    try {
        PreparedStatement stmtUpdate= connection.prepareStatement(sql);
        stmtUpdate.setInt(1,warning.noticeID);
        stmtUpdate.setString(2,warning.subject);
        stmtUpdate.setString(3,warning.message);

        n = stmtUpdate.executeUpdate();
        stmtUpdate.close();
    } catch(SQLException e) {
        e.printStackTrace();
        System.out.println("Error in createWarning: " + sql + " Exception: " + e);
    }
    return n;

}

}
