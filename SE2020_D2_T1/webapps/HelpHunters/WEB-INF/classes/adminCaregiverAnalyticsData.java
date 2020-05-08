import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminCaregiverAnalyticsData{
    int caregiverID;
    int hourlyrate;
    int starCount;
    int status;
    int gender;

    adminCaregiverAnalyticsData(int caregiverID, int hourlyrate, int gender){
      this.caregiverID = caregiverID;
      this.hourlyrate = hourlyrate;
      this.gender = gender;
    }

    adminCaregiverAnalyticsData(int caregiverID, int hourlyrate){
      this.caregiverID = caregiverID;
      this.hourlyrate = hourlyrate;
    }

    public static Vector<adminCaregiverAnalyticsData> getadminCaregiverAnalyticsData(Connection connection){
      Vector<adminCaregiverAnalyticsData> vec = new Vector<adminCaregiverAnalyticsData>();
      String sql = "Select caregiverID, numerofstars AS numberofstars FROM CaregiverRating";
      System.out.println("Get client list SQL: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          while(result.next()) {
              adminCaregiverAnalyticsData client = new adminCaregiverAnalyticsData(
                  result.getInt("caregiverID"),
                  getHourlyRate(connection, result.getInt("caregiverID")),
                  result.getInt("numberofstars")
              );
              vec.addElement(client);
          }
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in get Client List: " + sql + " Exception: " + e);
      }

      return vec;
    }

    public static int getHourlyRate(Connection connection, int caregiverID){
      int getHourlyRate = 0;
      String sql = "SELECT AVG(hourlyrate) FROM Caregiver WHERE caregiverID = " + caregiverID;
      System.out.println("getStatus: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          result.next();
          getHourlyRate = Integer.parseInt(result.getString("hourlyrate"));
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in getStatus: " + sql + " Exception: " + e);
      }
      return getHourlyRate;
    }

    public static Vector<adminCaregiverAnalyticsData> getCaregiverStatus(Connection connection){
      Vector<adminCaregiverAnalyticsData> vec = new Vector<adminCaregiverAnalyticsData>();
      String sql = "Select COUNT(caregiverID) AS myCount, status FROM CaregiverRating ORDER BY status";
      System.out.println("Get client list SQL: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          while(result.next()) {
              adminCaregiverAnalyticsData client = new adminCaregiverAnalyticsData(
                  result.getInt("myCount"),
                  result.getInt("status")
              );
              System.out.println("There are " + result.getInt("myCount") + " for this status " + result.getInt("status"));
              vec.addElement(client);
          }
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in get Client List: " + sql + " Exception: " + e);
      }
      return vec;
    }

    public static Vector<adminCaregiverAnalyticsData> getHourlyRates(Connection connection){
      Vector<adminCaregiverAnalyticsData> vec = new Vector<adminCaregiverAnalyticsData>();
      String sql = "SELECT COUNT(caregiverID) AS myCount, hourlyrate FROM Caregiver GROUP BY hourlyrate";
      System.out.println("Get client list SQL: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          while(result.next()) {
              adminCaregiverAnalyticsData client = new adminCaregiverAnalyticsData(
                  result.getInt("myCount"),
                  result.getInt("hourlyrate")
              );
              vec.addElement(client);
          }
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in get Client List: " + sql + " Exception: " + e);
      }
      return vec;
    }

    public static Vector<adminCaregiverAnalyticsData> getGenderCounts(Connection connection){
      Vector<adminCaregiverAnalyticsData> vec = new Vector<adminCaregiverAnalyticsData>();
      String sql = "SELECT COUNT(caregiverID) AS myCount, gender FROM Caregiver GROUP BY gender";
      System.out.println("Get client list SQL: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          while(result.next()) {
              adminCaregiverAnalyticsData client = new adminCaregiverAnalyticsData(
                  result.getInt("myCount"),
                  result.getInt("gender")
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
