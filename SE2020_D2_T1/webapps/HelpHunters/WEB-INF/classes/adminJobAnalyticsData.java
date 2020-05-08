import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminJobAnalyticsData {
    int employmentID;
    int clientID;
    int caregiverID;
    int administratorID;
    Date startdate;
    Date enddate;
    int hoursperweek;
    int status;
    Date dateauthorized;
    String province;
    int hours;
    int jobs;


    adminJobAnalyticsData (int employmentID, int clientID, int caregiverID, int administratorID, Date startdate,
    Date enddate, int hoursperweek, int status, Date dateauthorized, String province ) {
        this.employmentID = employmentID;
        this.clientID = clientID;
        this.caregiverID = caregiverID;
        this.administratorID = administratorID;
        this.startdate = startdate;
        this.enddate = enddate;
        this.hoursperweek = hoursperweek;
        this.status = status;
        this.dateauthorized=dateauthorized;
        this.province = province;
  //  adminRequestData(int caregiverID, String provincename, int provinceID)
    //    this.caregiverID = caregiverID;
    //    this.provincename = provincename;
    //    this.provinceID = provinceID;

    }
 adminJobAnalyticsData(int employmentID,int caregiverID, int hoursperweek){
   this.employmentID=employmentID;
   this.caregiverID=caregiverID;
   this.hoursperweek=hoursperweek;
 }

 adminJobAnalyticsData(int jobs,  int status){
   this.jobs=jobs;
   this.status=status;
 }




  /*  public static Vector<adminJobAnalyticsData> getadminJobAnalyticsData(Connection connection) {
        Vector<adminJobAnalyticsData> vec = new Vector<adminJobAnalyticsData>();
        String sql = "Select COUNT(employmentID) AS Expr1, hoursperweek  FROM EmploymentRecord GROUP BY hoursperweek";
        //String sql = "Select Client.*, EmploymentRecord.status FROM Client LEFT JOIN EmploymentRecord ORDER BY Client.clientID DESC";
        System.out.println("Get hours SQL: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                adminJobAnalyticsData hoursweek = new adminJobAnalyticsData(
                    Integer.parseInt(result.getString("Expr1")),
                    Integer.parseInt(result.getString("hoursperweek"))
                );
                vec.addElement(hoursweek);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in get hours: " + sql + " Exception: " + e);
        }
        return vec;
    }*/

    public static Vector<adminJobAnalyticsData> gethoursperwork(Connection connection) {
        Vector<adminJobAnalyticsData> vec = new Vector<adminJobAnalyticsData>();
        String sql = "Select employmentID, hoursperweek, caregiverID  FROM EmploymentRecord ";
        //String sql = "Select Client.*, EmploymentRecord.status FROM Client LEFT JOIN EmploymentRecord ORDER BY Client.clientID DESC";
        System.out.println("Get hours SQL: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                adminJobAnalyticsData hoursweek = new adminJobAnalyticsData(
                    Integer.parseInt(result.getString("employmentID")),
                    Integer.parseInt(result.getString("caregiverID")),

                    Integer.parseInt(result.getString("hoursperweek"))
                );
                vec.addElement(hoursweek);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in get hours: " + sql + " Exception: " + e);
        }
        return vec;
    }


    public static Vector<adminJobAnalyticsData> statusofjobs(Connection connection) {
        Vector<adminJobAnalyticsData> vec = new Vector<adminJobAnalyticsData>();
        String sql = "Select count (employmentID) AS jobs, status  FROM EmploymentRecord GROUP BY status";
        //String sql = "Select Client.*, EmploymentRecord.status FROM Client LEFT JOIN EmploymentRecord ORDER BY Client.clientID DESC";
        System.out.println("Get status SQL: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                adminJobAnalyticsData jobstatus = new adminJobAnalyticsData(
                    Integer.parseInt(result.getString("jobs")),

                    Integer.parseInt(result.getString("status"))
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
