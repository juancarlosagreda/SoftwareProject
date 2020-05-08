import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class provinceDataMoni {
	Integer caregiverID;
	Integer provinceID;
	Integer count;
	
	provinceDataMoni (Integer caregiverID, Integer provinceID) {
		this.caregiverID = caregiverID;
        this.provinceID = provinceID;
    }
	
	provinceDataMoni (Integer count) {
        this.count = count;
    }
	
		
    public static int insertCaregiverProvince(Connection connection, provinceDataMoni caregiverProvince) {
         String sql ="INSERT INTO CaregiverProvinces (caregiverID, provinceID)"
            + "VALUES (?,?)";
        System.out.println("insertCaregiverProvince: " + sql);
        int n = 0;
		try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setInt(1, caregiverProvince.caregiverID);
            ps.setInt(2, caregiverProvince.provinceID);
			
            n = ps.executeUpdate();
            ps.close();
			
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertCaregiverProvincer: " + sql + " Exception: " + e);
        }
        return n;
    }
	
	public static int updateCaregiverProvince(Connection connection, provinceDataMoni province) {
        String sql ="UPDATE CaregiverProvinces "
            + "SET provinceID = ? "
            + " WHERE caregiverID = ?";
        System.out.println("updateCaregiverProvince: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1,province.provinceID);
			stmtUpdate.setInt(2,province.caregiverID);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateCaregiverProvince: " + sql + " Exception: " + e);
        }
        return n;
    }
	
	public static provinceDataMoni countCaregiverInProvince(Connection connection, int id) {
		String sql = "SELECT COUNT(caregiverID) as count FROM caregiverProvinces";
		sql += " WHERE caregiverID=?";
		System.out.println("countCaregiverInProvince: " + sql);
		provinceDataMoni CaregiverInProvince = null;;
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			if(result.next()) {
				CaregiverInProvince = new provinceDataMoni(

					result.getInt("count")

				);
			}
			result.close();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error in countCaregiverInProvince: " + sql + " Exception: " + e);
		}
		return CaregiverInProvince;
    }


}