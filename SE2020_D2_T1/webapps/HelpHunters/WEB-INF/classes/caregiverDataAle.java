import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class caregiverDataAle {
	Integer caregiverID;
    String firstname;
    String lastname;
    String email;
    String password;
    Integer  gender;
    Integer   available;
    float  hourlyrate;
    String description;
    Date   birthdate;
    Integer     enrollmentstatus;
	String province;

    caregiverDataAle (Integer caregiverID, String firstname, String lastname, String email, String password, Integer  gender, Integer  available, float  hourlyrate, String description, Date birthdate, Integer  enrollmentstatus) {
		this.caregiverID = caregiverID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.available = available;
        this.hourlyrate = hourlyrate;
        this.description = description;
        this.birthdate = birthdate;
        this.enrollmentstatus = enrollmentstatus;
    }
	caregiverDataAle (String firstname, String lastname, int caregiverID, float hourlyrate, String description){
		this.firstname = firstname;
		this.lastname = lastname;
		this.caregiverID = caregiverID;
		this.hourlyrate = hourlyrate;
		this.description = description;
}

caregiverDataAle (String province, String firstname, String lastname, int caregiverID, float hourlyrate, String description){
		
		this.province = province;
		this.firstname = firstname;
		this.lastname = lastname;
		this.caregiverID = caregiverID;
		this.hourlyrate = hourlyrate;
		this.description = description;
}

	public static Vector<caregiverDataAle> getCaregiverProvinceList(Connection connection, String province) {
        Vector<caregiverDataAle> vec = new Vector<caregiverDataAle>();
        String sql = "Select Caregiver.firstname, Caregiver.lastname, Caregiver.caregiverID, Caregiver.hourlyrate, Caregiver.description  FROM Caregiver, CaregiverProvinces, Province ";
        sql += " WHERE Caregiver.caregiverID = CaregiverProvinces.caregiverID";
		sql += " AND CaregiverProvinces.provinceID = Province.provinceID  ";
		sql += " AND Caregiver.enrollmentstatus = 1"; 
		sql += " AND Caregiver.available = 1"; 
		sql += " AND Province.provincename = ?"; 
        System.out.println("getCargiverProvinceList: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, province);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                caregiverDataAle caregiver = new caregiverDataAle(
					province,
                    result.getString("firstname"),
                    result.getString("lastname"),
					Integer.parseInt(result.getString("caregiverID")),
					Float.parseFloat(result.getString("hourlyrate")),
					result.getString("description")
                );
                vec.addElement(caregiver);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCaregiverProvinceList: " + sql + " Exception: " + e);
        }
        return vec;
    }
	
	public static int viewCProfile(Connection connection, int caregiverID) {
        String sql ="INSERT INTO ProfileViews(caregiverID) VALUES (?)";
        System.out.println("viewCProfile: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1,caregiverID);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in viewCProfile: " + sql + " Exception: " + e);
        }
        return n;
    }
	
	public static caregiverDataAle getCaregiver(Connection connection, int id) {
        String sql = "Select firstname, lastname, caregiverID, hourlyrate, description FROM Caregiver";
        sql += " WHERE caregiverID=?";
        System.out.println("getCaregiver: " + sql);
        caregiverDataAle caregiver = null;;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
				caregiver = new caregiverDataAle(
					result.getString("firstname"),
					result.getString("lastname"),
					id,
					Float.parseFloat(result.getString("hourlyrate")),
                    result.getString("description")
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCaregiver: " + sql + " Exception: " + e); 
        }
        return caregiver;
    }
}
	