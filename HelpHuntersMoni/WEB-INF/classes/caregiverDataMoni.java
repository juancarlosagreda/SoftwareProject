import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class caregiverDataMoni {
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
	Integer clientID;
	Integer employmentID;

    caregiverDataMoni (Integer caregiverID, String firstname, String lastname, String email, String password, Integer  gender, Integer  available, float  hourlyrate, String description, Date birthdate, Integer  enrollmentstatus) {
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
	
	caregiverDataMoni (String firstname, String lastname, Integer clientID, Integer employmentID) {
        this.firstname = firstname;
        this.lastname = lastname;
		this.clientID = clientID;
		this.employmentID = employmentID;
    }
	
	caregiverDataMoni (Integer caregiverID, String firstname, String lastname, String email, String password, Integer  available, float  hourlyrate, String description) {
		this.caregiverID = caregiverID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.available = available;
        this.hourlyrate = hourlyrate;
        this.description = description;
    }
	
	
	
   public static int insertCaregiver(Connection connection, caregiverDataMoni caregiver) {
        String sql ="INSERT INTO Caregiver (firstname, lastname, email, password, gender, hourlyrate, description, birthdate)"
            + "VALUES (?,?,?,?,?,?,?,?)";
        System.out.println("insertCaregiver: " + sql);
        int n = 0;
		try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, caregiver.firstname);
            ps.setString(2, caregiver.lastname);
            ps.setString(3, caregiver.email);
            ps.setString(4, caregiver.password);
            ps.setInt(5, caregiver.gender);
            ps.setFloat(6, caregiver.hourlyrate);
            ps.setString(7, caregiver.description);
            ps.setDate(8, caregiver.birthdate);
            n = ps.executeUpdate();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertCaregiver: " + sql + " Exception: " + e);
        }
        return n;
    }	
	
	
	public static caregiverDataMoni getCaregiver(Connection connection, int id) {
		String sql = "SELECT firstname, lastname, email, password, available, hourlyrate, description FROM Caregiver";
		sql += " WHERE caregiverID=?";
		System.out.println("getCaregiver: " + sql);
		caregiverDataMoni caregiver = null;;
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			if(result.next()) {
				caregiver = new caregiverDataMoni(
				
					null,
					result.getString("firstname"),
					result.getString("lastname"),
					result.getString("email"),
					result.getString("password"),
					null,
					result.getInt("available"),
					Float.parseFloat(result.getString("hourlyrate")),
					result.getString("description"),
					null,
					null
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


    /*public static int updateCaregiver(Connection connection, caregiverData caregiver) {
        String sql ="UPDATE Caregiver "
            + "SET firstname = ?, lastname = ?, email = ?, password = ?, available = ?, hourlyrate = ?, description = ?"
            + " WHERE caregiverID = ?";
        System.out.println("updateCaregiver: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1, caregiver.firstname);
            stmtUpdate.setString(2, caregiver.lastname);
            stmtUpdate.setString(3, caregiver.email);
            stmtUpdate.setString(4, caregiver.password);
			stmtUpdate.setInt(5, caregiver.available);
			stmtUpdate.setFloat(6, caregiver.hourlyrate);
			stmtUpdate.setString(7, caregiver.description);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateCaregiver: " + sql + " Exception: " + e);
        }
        return n;
    }*/
	
	public static Vector<caregiverDataMoni> getCaregiverJobs(Connection connection, int id) {
		Vector<caregiverDataMoni> vec = new Vector<caregiverDataMoni>();
		String sql = "SELECT Client.clientID, Client.firstname, Client.lastname, EmploymentRecord.caregiverID, EmploymentRecord.status, EmploymentRecord.employmentID FROM Client INNER JOIN EmploymentRecord ON Client.clientID = EmploymentRecord.clientID ";
		sql += "WHERE EmploymentRecord.caregiverID=? AND EmploymentRecord.status=1";
		System.out.println("getCaregiverJobs: " + sql);
		try {
			PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				caregiverDataMoni job = new caregiverDataMoni(
					
					result.getString("firstname"),
					result.getString("lastname"),
					result.getInt("clientID"),
					result.getInt("employmentID")
				);
				vec.addElement(job);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error in getCaregiverJobs: " + sql + " Exception: " + e);
		}
		return vec;
    }
	
	public static int updateCaregiver(Connection connection, caregiverDataMoni caregiver) {
        String sql ="UPDATE Caregiver "
            + "SET firstname = ?, lastname = ?, available = ?, email = ?, hourlyrate = ?, description = ?, password = ? "
            + " WHERE caregiverID = ?";
        System.out.println("updateCaregiver: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,caregiver.firstname);
			stmtUpdate.setString(2,caregiver.lastname);
            stmtUpdate.setInt(3,caregiver.available);
			stmtUpdate.setString(4,caregiver.email);
            stmtUpdate.setFloat(5,caregiver.hourlyrate);
            stmtUpdate.setString(6,caregiver.description);
			stmtUpdate.setString(7,caregiver.password);
			stmtUpdate.setInt(8,caregiver.caregiverID);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateCaregiver: " + sql + " Exception: " + e);
        }
        return n;
    }
	
	
}
