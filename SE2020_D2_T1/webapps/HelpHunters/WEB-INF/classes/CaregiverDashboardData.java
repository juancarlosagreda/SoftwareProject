import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class CaregiverDashboardData {
    
    /*
    Int employmentID;
    Int numberofstars;
    String comment;
    Date ratedate;
    
    Int caregiverID;
    Date viewdate;
    
    
    //Constructor para getRatingList
    caregiverDashboardData (Int employmentID, Date ratedate){
        this.employmentID = employmentID;
        this.ratedate = ratedate;
    }
    
    //Constructor para getRating
    caregiverDashboardData (Int employmentID, Int numberofstars, String comment, Date ratedate){
        this.employmentID = employmentID;
        this.numberofstars = numberofstars;
        this.comment = comment;
        this.ratedate = ratedate;
    }
    
    //Constructor para getProfileView
    caregiverDashboardData (Int caregiverID, Date viewdate){
        this.caregiverID = caregiverID;
        this.viewdate = viewdate;
    }*/
    
    String name;
    String email;
    String gender;
    String inactive;
    
    CaregiverDashboardData (String name, String email, String gender, String inactive){
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.inactive = inactive;
    }
    
    public static CaregiverDashboardData getClientInfo(Connection connection, String clientID){
        
        
        String sql = "";
        
        sql += "SELECT CONCAT(firstname, ' ', lastname) AS name, email, gender, inactive FROM Client WHERE clientID=?;";
        
        System.out.println("getClientInfo: " + sql + clientID);
        
        CaregiverDashboardData x = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, clientID);
            
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){             
                x = new CaregiverDashboardData(
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("gender"),
                    result.getString("inactive")
                );   
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getNumberOfReviewsOfCaregiver: " + sql + " Exception: " + e);
        }
        
        return x;
    }
    
    public static int getNumberOfReviewsOfCaregiver(Connection connection, String caregiverID){
        
        int numReviews=0;
        
        String sql = "";
        
        sql += "SELECT COUNT(*) AS result FROM EmploymentRecord";
        sql += " JOIN CaregiverRating ON CaregiverRating.clientID = EmploymentRecord.clientID WHERE";
        sql += " EmploymentRecord.caregiverID = ?;";
        
        System.out.println("getNumberOfReviewsOfCaregiver: " + sql + caregiverID);
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, caregiverID);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                                
                int x = Integer.parseInt(result.getString("result"));
                
                numReviews = x;
                System.out.println(x);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getNumberOfReviewsOfCaregiver: " + sql + " Exception: " + e);
        }
        return numReviews;
    }
    
    
    public static int getNumberOfProfileViewsOfCaregiver(Connection connection, String caregiverID){
        
        int numProfileViews=0;
        
        String sql = "";
        
        sql += "SELECT COUNT(*) AS result FROM ProfileViews WHERE";
        sql += " ProfileViews.caregiverID = ? AND month(VIEWDATE)=month(CURRENT_DATE);";
        
        System.out.println("getNumberOfProfileViewsOfCaregiver: " + sql + caregiverID);
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, caregiverID);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                        
                int x = Integer.parseInt(result.getString("result"));
                
                numProfileViews = x;
                System.out.println(x);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getNumberOfProfileViewsOfCaregiver: " + sql + " Exception: " + e);
        }
        return numProfileViews;
    }
    
    public static String getNameOfCaregiver(Connection connection, String caregiverID){
        
        String nameOfCaregiver="";
        
        String sql = "";
        
        sql += "SELECT firstname FROM Caregiver WHERE";
        sql += " Caregiver.caregiverID = ?;";
        
        System.out.println("getNameOfCaregiver: " + sql + caregiverID);
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, caregiverID);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                        
                String x = result.getString("firstname");
                
                nameOfCaregiver = x;
                System.out.println(x);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getNameOfCaregiver: " + sql + " Exception: " + e);
        }
        return nameOfCaregiver;
    }
    
    /*
    public static Vector<caregiverDashboardData> getRatingList(Connection connection){
        Vector<caregiverDashboardData> = new Vector<caregiverDashboardData>();
        
        String sql = "SELECT employmentID, numberofstars, comment, ratedate FROM CaregiverRating";
        
        System.out.println("getRatingList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                caregiverDashboardData rating = new caregiverDashboardData(
                    result.getInteger("employmentID"),
					result.getInteger("numberofstars"),
                    result.getString("comment"),
                    result.getDate("ratedate")
                );
                
                vec.addElement(rating);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getRatingList: " + sql + " Exception: " + e);
        }
        return vec;
    }
        
    
    public static caregiverDashboardData getRating(Connection connection, int employmentID){
        
        String sql = "SELECT employmentID, numberofstars, comment, ratedate FROM CaregiverRating WHERE employmentID=?";
        
        System.out.println("getRating: " + sql);
        
        caregiverDashboardData rating = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, employmentID);
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                rating = new caregiverDashboardData(
                    result.getInteger"employmentID"),
                    result.getInteger("numberofstars"),
                    result.getString("comment"),
                    result.getDate("ratedate")
                );  
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getRating: " + sql + " Exception: " + e);
        }
        
        return client;
        
    }
    
    public static Vector<caregiverDashboardData>getProfileView(Connection connection){
        Vector<caregiverDashboardData> = new Vector<caregiverDashboardData>();
        
        String sql = "SELECT caregiverID, viewdate FROM ProfileViews";
        
        System.out.println("getProfileView: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                caregiverDashboardData views = new caregiverDashboardData(
                    result.getInteger("caregiverID"),
                    result.getDate("ratedate")
                );
                
                vec.addElement(views);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProfileView: " + sql + " Exception: " + e);
        }
        return vec;
    }
    */

}

