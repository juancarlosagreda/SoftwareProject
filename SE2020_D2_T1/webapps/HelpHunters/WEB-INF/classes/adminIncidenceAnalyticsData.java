import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminIncidenceAnalyticsData {
    int caregiverID;
    String firstname;
    String lastname;
    String email;
    String password;
    int gender;
    int available;
    float hourlyrate;
    String description;
    Date birthdate;
    int enrollmentstatus;
    String province;
    int notices;
    int classification;


    adminIncidenceAnalyticsData (int caregiverID, String firstname, String lastname, String email, int gender,
    int available, float hourlyrate, String description, Date birthdate, int enrollmentstatus, String province ) {
        this.caregiverID = caregiverID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.available = available;
        this.hourlyrate = hourlyrate;
        this.description = description;
        this.birthdate=birthdate;

        this.enrollmentstatus = enrollmentstatus;
        this.province = province;
  //  adminRequestData(int caregiverID, String provincename, int provinceID)
    //    this.caregiverID = caregiverID;
    //    this.provincename = provincename;
    //    this.provinceID = provinceID;


    }

adminIncidenceAnalyticsData(int notices, int classification){
  this.notices=notices;
  this.classification=classification;
}

    public static Vector<adminIncidenceAnalyticsData> percentageofincidences(Connection connection) {
        Vector<adminIncidenceAnalyticsData> vec = new Vector<adminIncidenceAnalyticsData>();
        String sql = "Select count (noticeID) AS notices, classification  FROM Notice GROUP BY classification";
        //String sql = "Select Client.*, EmploymentRecord.status FROM Client LEFT JOIN EmploymentRecord ORDER BY Client.clientID DESC";
        System.out.println("Get status SQL: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                adminIncidenceAnalyticsData jobstatus = new adminIncidenceAnalyticsData(
                    Integer.parseInt(result.getString("notices")),
                    Integer.parseInt(result.getString("classification"))
                );
                vec.addElement(jobstatus);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in get status: " + sql + " Exception: " + e);
        }
        return vec;
    }








  }
