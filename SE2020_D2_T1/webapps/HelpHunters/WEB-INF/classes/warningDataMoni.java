import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class warningDataMoni {
	String subject;
	String message;
	Date   datecreated;
	Integer hasbeenread;
	Integer caregiverID;
	Integer noticeID;
		
    warningDataMoni (String subject, String message, Date datecreated, Integer noticeID, Integer hasbeenread) {
        this.subject = subject;
		this.message = message;
		this.datecreated = datecreated;
		this.noticeID = noticeID;
		this.hasbeenread = hasbeenread;
    }
	
	warningDataMoni (Integer hasbeenread, Integer noticeID) {
        this.hasbeenread = hasbeenread;
		this.noticeID = noticeID;
    }
	
	public static Vector<warningDataMoni> getCaregiverWarnings(Connection connection, int id) {
		Vector<warningDataMoni> vec = new Vector<warningDataMoni>();
		String sql = "SELECT WarningToCaregiver.subject, WarningToCaregiver.message, WarningToCaregiver.datecreated, EmploymentRecord.caregiverID, WarningToCaregiver.noticeID, WarningToCaregiver.hasbeenread";
		sql += " FROM (EmploymentRecord INNER JOIN Notice ON EmploymentRecord.employmentID = Notice.employmentID) INNER JOIN WarningToCaregiver ON Notice.noticeID = WarningToCaregiver.noticeID";
		sql += " WHERE (((EmploymentRecord.caregiverID)=?))";

		System.out.println("getCaregiverWarnings: " + sql);
		try {
			PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				warningDataMoni warning = new warningDataMoni(
					result.getString("subject"),
					result.getString("message"),
					result.getDate("datecreated"),
					result.getInt("noticeID"),
					result.getInt("hasbeenread")
				);
				vec.addElement(warning);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error in getCaregiverWarnings: " + sql + " Exception: " + e);
		}
		return vec;
    }
	
	public static int updateSeen(Connection connection, warningDataMoni seen) {
        String sql ="UPDATE WarningToCaregiver  "
            + "SET hasbeenread = ?"
            + " WHERE noticeID = ?";
        System.out.println("updateSeen: " + sql);
        int n = 0;
        try {
			
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1,seen.hasbeenread);
			stmtUpdate.setInt(2,seen.noticeID);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateSeen: " + sql + " Exception: " + e);
        }
        return n;
    }
}