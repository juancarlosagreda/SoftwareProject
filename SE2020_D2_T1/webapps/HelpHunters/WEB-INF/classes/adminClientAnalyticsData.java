import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminClientAnalyticsData{
    int param;
    int count;
    int starCount;
    float hoursperweek;

    adminClientAnalyticsData(int param, int count){
      this.param = param;
      this.count = count;
    }


    public static Vector<adminClientAnalyticsData> getadminClientAnalyticsData(Connection connection, int clientID){
      Vector<adminClientAnalyticsData> vec = new Vector<adminClientAnalyticsData>();
      String sql = "SELECT AVG(numberofstars) AS stars FROM ClientRating WHERE employmentID IN ";
      sql += " (SELECT employmentID FROM EmploymentRecord WHERE clientID = ?)";
      System.out.println("Get client list SQL: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          while(result.next()) {
              adminClientAnalyticsData client = new adminClientAnalyticsData(
                  result.getInt("clientID"),
                  noticeCount(connection, result.getInt("clientID"))
              );
              vec.addElement(client);
          }
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in get Client List: " + sql + " Exception: " + e);
      }

      return vec;
    }

    public static int noticeCount(Connection connection, int clientID){
      int noticeCount = 0;
      String sql = "SELECT COUNT(noticeID) AS newCount FROM Notice WHERE employmentID";
      sql += " IN (SELECT employmentID FROM EmploymentRecord WHERE clientID = " + clientID +")";
      System.out.println("getStatus: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          result.next();
          noticeCount = Integer.parseInt(result.getString("newCount"));
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in getStatus: " + sql + " Exception: " + e);
      }
      return noticeCount;
    }

    public static Vector<adminClientAnalyticsData> getadminClientAnalyticsData2(Connection connection){
      Vector<adminClientAnalyticsData> vec = new Vector<adminClientAnalyticsData>();
      String sql = "Select clientID, numberofstars FROM ClientRating";
      System.out.println("Get client list SQL: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          while(result.next()) {
              adminClientAnalyticsData client = new adminClientAnalyticsData(
                  result.getInt("clientID"),
                  1
                  );
              vec.addElement(client);
          }
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in get Client List: " + sql + " Exception: " + e);
      }

      return vec;
    }

    public static float hourlyAverage(Connection connection, int clientID){
      float hourlyAverage = 0;
      String sql = "SELECT AVG(hoursperweek) AS hours FROM EmploymentRecord WHERE clientID = " + clientID;
      System.out.println("getStatus: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          result.next();
          hourlyAverage = Float.parseFloat(result.getString("hours"));
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in getStatus: " + sql + " Exception: " + e);
      }
      return hourlyAverage;
    }

    public static Vector<adminClientAnalyticsData> getClientHourlyRates(Connection connection){
      Vector<adminClientAnalyticsData> vec = new Vector<adminClientAnalyticsData>();
      String sql = "SELECT COUNT(clientID) AS myCount, hoursperweek FROM EmploymentRecord GROUP BY hoursperweek ";
      System.out.println("Get client list SQL: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          while(result.next()) {
              adminClientAnalyticsData client = new adminClientAnalyticsData(
                  result.getInt("myCount"),
                  result.getInt("hoursperweek")
              );
              vec.addElement(client);
          }
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in get Client List: " + sql + " Exception: " + e);
      }

      return vec;
    }

    public static Vector<adminClientAnalyticsData> getGenderCounts(Connection connection){
      Vector<adminClientAnalyticsData> vec = new Vector<adminClientAnalyticsData>();
      String sql = "SELECT COUNT(clientID) AS myCount, gender FROM Client GROUP BY gender";
      System.out.println("Get client list SQL: " + sql);
      try {
          Statement statement=connection.createStatement();
          ResultSet result = statement.executeQuery(sql);
          while(result.next()) {
              adminClientAnalyticsData client = new adminClientAnalyticsData(
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
