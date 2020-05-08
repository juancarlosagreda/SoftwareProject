
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminNoticeData {
    int noticeID;
    int clientID;
    String subject;
    String message;
    Date datecreated;
    String reply;
    Date datereplied;
    int replyhasbeenread;
    //A 1 means it is an incidence and a 0 a normal notice.
    int classification;

    adminNoticeData (int noticeID, int clientID, String subject, String message, Date datecreated,
    String reply, Date datereplied, /*int replyhasbeenread,*/ int classification) {
        this.noticeID = noticeID;
        this.clientID = clientID;
        this.subject = subject;
        this.message = message;
        this.datecreated = datecreated;
        this.reply = reply;
        this.datereplied = datereplied;
        //this.replyhasbeenread = replyhasbeenread;
        this.classification = classification;

    }
    public static Vector<adminNoticeData> getadminNoticeData(Connection connection) {
        Vector<adminNoticeData> vec = new Vector<adminNoticeData>();
        //String sql = "SELECT * FROM Notice WHERE NOT classification = 1";
        String sql = "SELECT * FROM Notice WHERE NOT classification = ?";
        System.out.println("getList: " + sql);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,1);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                //We get the employmentID to query for the clientID
                int employmentID = Integer.parseInt(result.getString("employmentID"));
                adminNoticeData order = new adminNoticeData(
                    Integer.parseInt(result.getString("noticeID")),
                    getClientID(connection, employmentID),
                    result.getString("subject"),
                    result.getString("message"),
                    result.getDate("datecreated"),
                    result.getString("reply"),
                    result.getDate("datereplied"),
                    Integer.parseInt(result.getString("classification"))
                );
                vec.addElement(order);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getList: " + sql + " Exception: " + e);
        }
        return vec;
    }

    public static adminNoticeData getadminNoticeInfo(Connection connection, int noticeID) {
        String sql = "SELECT * FROM Notice WHERE noticeID = ?";
        System.out.println("get: " + sql);
        adminNoticeData notice = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, noticeID);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
              int employmentID = Integer.parseInt(result.getString("employmentID"));

                notice = new adminNoticeData(
                    noticeID,
                    getClientInfo.getClientID(connection, employmentID),
                    result.getString("subject"),
                    result.getString("message"),
                    result.getDate("datecreated"),
                    result.getString("reply"),
                    result.getDate("datereplied"),
                    //Integer.parseInt(result.getString("replyhasbeenread")),
                    Integer.parseInt(result.getString("classification"))
                    );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getHeader: " + sql + " Exception: " + e);
        }
        return notice;
    }

    public static Vector<adminNoticeData> getadminNoticeDataByClient(Connection connection, int clientID) {
        Vector<adminNoticeData> vec = new Vector<adminNoticeData>();
        String sql = "SELECT * FROM Notice WHERE employmentID IN (SELECT employmentID FROM EmploymentRecord WHERE clientID = ?)";
        System.out.println("getList: " + sql);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clientID);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                adminNoticeData order = new adminNoticeData(
                    Integer.parseInt(result.getString("noticeID")),
                    clientID,
                    result.getString("subject"),
                    result.getString("message"),
                    result.getDate("datecreated"),
                    result.getString("reply"),
                    result.getDate("datereplied"),
                    //Integer.parseInt(result.getString("replyhasbeenread")),
                    Integer.parseInt(result.getString("classification"))
                );
                vec.addElement(order);
                //System.out.println(Integer.parseInt(result.getString("classification")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getList: " + sql + " Exception: " + e);
        }
        return vec;
    }

    public static int getClientID(Connection connection, int employmentID){
      int clientID = 0;
      String sql = "SELECT * FROM EmploymentRecord WHERE employmentID = ?";
      System.out.println("get: " + sql);
      try {
          PreparedStatement pstmt = connection.prepareStatement(sql);
          pstmt.setInt(1, employmentID);
          ResultSet result = pstmt.executeQuery();
          if(result.next()) {
             clientID = Integer.parseInt(result.getString("clientID"));
          }
          result.close();
          pstmt.close();
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in getHeader: " + sql + " Exception: " + e);
      }
      return clientID;
    }

    public static Vector<adminNoticeData> getadminNoticeDataByQuery(Connection connection, String query) {
        Vector<adminNoticeData> vec = new Vector<adminNoticeData>();
        //String sql = "SELECT * FROM Notice WHERE NOT classification = 1";
        String sql = "SELECT * FROM Notice WHERE subject LIKE ? ESCAPE '!'";
        System.out.println("getList: " + sql);
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1, "%" + query + "%");
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                //We get the employmentID to query for the clientID
                int employmentID = Integer.parseInt(result.getString("employmentID"));
                adminNoticeData order = new adminNoticeData(
                    Integer.parseInt(result.getString("noticeID")),
                    getClientID(connection, employmentID),
                    result.getString("subject"),
                    result.getString("message"),
                    result.getDate("datecreated"),
                    result.getString("reply"),
                    result.getDate("datereplied"),
                    //Integer.parseInt(result.getString("replyhasbeenread")),
                    Integer.parseInt(result.getString("classification"))
                );
                vec.addElement(order);
                //System.out.println(Integer.parseInt(result.getString("classification")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getList: " + sql + " Exception: " + e);
        }
        return vec;
    }

    public static int makeIncidence(Connection connection, int noticeID){
      int n = 0;
      String sql = "UPDATE Notice SET classification = ? WHERE noticeID = ?";
      System.out.println("get: " + sql);
      try {
          PreparedStatement pstmt = connection.prepareStatement(sql);
          pstmt.setInt(1, 1);
          pstmt.setInt(2, noticeID);
          n = pstmt.executeUpdate();
          pstmt.close();
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in makeIncidence: " + sql + " Exception: " + e);
      }
      return n;
    }

    public static int makeReply(Connection connection, int noticeID, String reply){
      int n = 0;
      String sql = "UPDATE Notice SET reply = ? WHERE noticeID = ?";
      System.out.println("get: " + sql);
      try {
          PreparedStatement pstmt = connection.prepareStatement(sql);
          pstmt.setString(1, reply);
          pstmt.setInt(2, noticeID);
          n = pstmt.executeUpdate();
          pstmt.close();
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Error in makeIncidence: " + sql + " Exception: " + e);
      }
      return n;
    }
}
