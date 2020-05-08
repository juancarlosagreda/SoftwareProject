import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class reviewDataAle {
	int employmentID;
	String firstname;
	String lastname;
	int caregiverID;
    int numberofstars;
	String comment;
	Date ratedate;

    reviewDataAle (int employmentID, String firstname, String lastname, int numberofstars, String comment, Date ratedate ) {
		this.employmentID = employmentID;
		this.firstname = firstname;
		this.lastname = lastname;
        this.numberofstars = numberofstars;
        this.comment = comment;
        this.ratedate = ratedate;
    }
	reviewDataAle (int employmentID, String firstname, String lastname, int caregiverID) {
		this.employmentID = employmentID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.caregiverID = caregiverID;
	}
	reviewDataAle (int employmentID, int numberofstars, String comment) {
		this.employmentID = employmentID;
		this.numberofstars = numberofstars;
		this.comment = comment;
	}

	public static Vector<reviewDataAle> getMyReviews(Connection connection, int clientID) {
        Vector<reviewDataAle> vec = new Vector<reviewDataAle>();
        String sql = "SELECT Caregiver.firstname, Caregiver.lastname, EmploymentRecord.employmentID, ClientRating.numberofstars, ClientRating.comment, ClientRating.ratedate";
        sql += " FROM ClientRating, Caregiver, EmploymentRecord";
		sql += " WHERE Caregiver.caregiverID = EmploymentRecord.caregiverID AND EmploymentRecord.employmentID = ClientRating.employmentID"; 
		sql += " AND EmploymentRecord.clientID = ?"; 
        System.out.println("getMyReviews: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setInt(1, clientID);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                reviewDataAle review = new reviewDataAle(
					Integer.parseInt(result.getString("employmentID")),
					result.getString("firstname"),
					result.getString("lastname"),
					Integer.parseInt(result.getString("numberofstars")),
                    result.getString("comment"),
					result.getDate("ratedate")
                );
                vec.addElement(review);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getMyReviews: " + sql + " Exception: " + e);
        }
        return vec;
    }

public static Vector<reviewDataAle> getCaregiverReviews(Connection connection, int caregiverID) {
        Vector<reviewDataAle> vec = new Vector<reviewDataAle>();
        String sql = "SELECT Client.firstname, Client.lastname, EmploymentRecord.employmentID, CaregiverRating.numberofstars, CaregiverRating.comment, CaregiverRating.ratedate ";
        sql += "FROM CaregiverRating, Client, EmploymentRecord, Caregiver ";
		sql += "WHERE Client.clientID = EmploymentRecord.clientID AND EmploymentRecord.employmentID = CaregiverRating.employmentID "; 
		sql += "AND  Caregiver.caregiverID = EmploymentRecord.caregiverID ";
		sql += "AND Caregiver.caregiverID = ? "; 

        System.out.println("getCaregiverReviews: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setInt(1, caregiverID);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                reviewDataAle review = new reviewDataAle(
					Integer.parseInt(result.getString("employmentID")),
					result.getString("firstname"),
					result.getString("lastname"),
					Integer.parseInt(result.getString("numberofstars")),
                    result.getString("comment"),
					result.getDate("ratedate")
                );
                vec.addElement(review);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCaregiverReviews: " + sql + " Exception: " + e);
        }
        return vec;
    }

	public static Vector<reviewDataAle> getPendingReviews(Connection connection, int clientID) {
        Vector<reviewDataAle> vec = new Vector<reviewDataAle>();
        String sql = "SELECT Caregiver.firstname, Caregiver.lastname, Caregiver.caregiverID, EmploymentRecord.employmentID";
        sql += " FROM Caregiver INNER JOIN EmploymentRecord ON Caregiver.caregiverID = EmploymentRecord.caregiverID";
		sql += " WHERE EmploymentRecord.status=6 AND EmploymentRecord.clientID=? AND EmploymentRecord.employmentID NOT IN (SELECT employmentID FROM CaregiverRating)"; 
        System.out.println("getPendingReviews: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setInt(1, clientID);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                reviewDataAle review = new reviewDataAle(
					Integer.parseInt(result.getString("employmentID")),
					result.getString("firstname"),
					result.getString("lastname"),
					Integer.parseInt(result.getString("caregiverID"))
                );
                vec.addElement(review);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getPendingReviews: " + sql + " Exception: " + e);
        }
        return vec;
    }
	
	public static int insertReview(Connection connection, reviewDataAle review) {
        String sql ="INSERT INTO CaregiverRating  (employmentID, numberofstars , comment) VALUES (?,?,?)";
        System.out.println("insertReview: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1,review.employmentID);
            stmtUpdate.setInt(2,review.numberofstars);
            stmtUpdate.setString(3,review.comment);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertReview: " + sql + " Exception: " + e);
        }
        return n;
    }


}