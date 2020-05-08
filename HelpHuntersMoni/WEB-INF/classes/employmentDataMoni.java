import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class employmentDataMoni {
	Integer employmentID;
	Integer status;
	Integer hires;
	
	employmentDataMoni (Integer status, Integer employmentID) {
		this.status = status;
        this.employmentID = employmentID;
    }
	
	employmentDataMoni (Integer hires) {
		this.hires = hires;
    }
	
	public static int updateEmploymentStatus(Connection connection, employmentDataMoni status) {
        String sql ="UPDATE EmploymentRecord "
            + "SET status = ? "
            + " WHERE employmentID = ?";
        System.out.println("updateEmploymentStatus: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1,status.status);
			stmtUpdate.setInt(2,status.employmentID);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateEmploymentStatus: " + sql + " Exception: " + e);
        }
        return n;
    }
	
	public static employmentDataMoni getHires(Connection connection, int clientid) { 
		String sql = "SELECT COUNT(employmentID) as hires";
		sql += " FROM EmploymentRecord WHERE clientID=?";

		System.out.println("getHires: " + sql);
		employmentDataMoni hires = null;;
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, clientid);
			ResultSet result = pstmt.executeQuery();
			if(result.next()) {
				hires = new employmentDataMoni(

					result.getInt("hires")

				);
			}
			result.close();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error in getHires: " + sql + " Exception: " + e);
		}
		return hires;
    } 
	
}