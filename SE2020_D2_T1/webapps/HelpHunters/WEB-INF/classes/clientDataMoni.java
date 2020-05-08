import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class clientDataMoni {
    int clientID;
	String firstname;
    String lastname;
	int hires;
	int    caregiverID;
	int    numberofstars;
    String comment;
	Date   ratedate;
	int    employmentID;
	
	
	clientDataMoni (int clientID, String firstname, String lastname, int hires, int numberofstars, String comment, Date ratedate) {
		this.clientID = clientID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.hires = hires;
		this.numberofstars = numberofstars;
		this.comment = comment;
		this.ratedate = ratedate;
		}
		
	clientDataMoni (String firstname, String lastname) {

		this.firstname = firstname;
		this.lastname = lastname;
		}
		
				
	clientDataMoni (int employmentID, String firstname, String lastname, int numberofstars, String comment, Date ratedate) {
		this.employmentID = employmentID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.numberofstars = numberofstars;
		this.comment = comment;
		this.ratedate = ratedate;
		}
		
		
		
	public static clientDataMoni getClient(Connection connection, int clientid) { 
		String sql = "SELECT Client.firstname, Client.lastname";
		sql += " FROM Client WHERE Client.clientID=?";

		System.out.println("getClient: " + sql);
		clientDataMoni client = null;;
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, clientid);
			ResultSet result = pstmt.executeQuery();
			if(result.next()) {
				client = new clientDataMoni(

					result.getString("firstname"),
					result.getString("lastname")

				);
			}
			result.close();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error in getClient: " + sql + " Exception: " + e);
		}
		return client;
    } 
	
	public static Vector<clientDataMoni> getClientReviews(Connection connection, int clientid) {
		Vector<clientDataMoni> vec = new Vector<clientDataMoni>();
        String sql = "SELECT Caregiver.firstname, Caregiver.lastname, EmploymentRecord.employmentID, ClientRating.numberofstars, ClientRating.comment, ClientRating.ratedate";
        sql += " FROM ClientRating, Caregiver, EmploymentRecord";
		sql += " WHERE Caregiver.caregiverID = EmploymentRecord.caregiverID AND EmploymentRecord.employmentID = ClientRating.employmentID"; 
		sql += " AND EmploymentRecord.clientID = ?"; 
		System.out.println("getClientReviews: " + sql);
		try {
			PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, clientid);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				clientDataMoni review = new clientDataMoni(
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
			System.out.println("Error in getClientReviews: " + sql + " Exception: " + e);
		}
		return vec;
    }
	
	
}