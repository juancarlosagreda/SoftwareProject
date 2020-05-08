
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminRequestData {
    int caregiverID;
    String firstname;
    String lastname;
    String email;
    String password;
    int gender;
    int available;
    float hourlyrate;
    String description;
    Date birthdate;
    int enrollmentstatus;
    String province;



    adminRequestData (int caregiverID, String firstname, String lastname, String email, int gender,
    int available, float hourlyrate, String description, Date birthdate, int enrollmentstatus, String province ) {
        this.caregiverID = caregiverID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.available = available;
        this.hourlyrate = hourlyrate;
        this.description = description;
        this.enrollmentstatus = enrollmentstatus;
        this.province = province;
    }

    adminRequestData (int caregiverID,  int enrollmentstatus) {
        this.caregiverID = caregiverID;
        this.enrollmentstatus = enrollmentstatus;
    }



    public static Vector<adminRequestData> getadminRequestData(Connection connection) {
            Vector<adminRequestData> vec = new Vector<adminRequestData>();
            String sql = "Select * FROM Caregiver WHERE enrollmentstatus =0";
            System.out.println("Get Requests list SQL: " + sql);
            try {
                Statement statement=connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while(result.next()) {
                    int caregiverID = Integer.parseInt(result.getString("Caregiver.caregiverID"));
                    adminRequestData request = new adminRequestData(
                        caregiverID,
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("email"),
                        Integer.parseInt(result.getString("gender")),
                        Integer.parseInt(result.getString("available")),
                        Float.parseFloat(result.getString("hourlyrate")),
                        result.getString("description"),
                        result.getDate("birthdate"),
                        Integer.parseInt(result.getString("enrollmentstatus")),
                        getprovince(connection, caregiverID)
                    );
                    vec.addElement(request);
                }
            } catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Error in get Requests List: " + sql + " Exception: " + e);
            }
            return vec;
        }

        public static String getprovince(Connection connection, int id) {
          String province =null;
           String sql = "SELECT provincename FROM Province WHERE provinceID IN (SELECT provinceID FROM CaregiverProvinces WHERE caregiverID = ?)";
            System.out.println("getprovince: " + sql);

            try {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                result.next();
                province = result.getString("provincename");

                result.close();
                statement.close();
            } catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Error in getprovince: " + sql + " Exception: " + e);
            }
            System.out.println(province);
            return province;
        }

        public static int updateCaregiver(Connection connection, adminRequestData request) {
                String sql ="UPDATE Caregiver SET enrollmentstatus = ? WHERE caregiverID = ?";
                System.out.println("updateCaregiver: " + sql);
                int n = 0;
                try {
                    PreparedStatement stmtUpdate= connection.prepareStatement(sql);
                    stmtUpdate.setInt(2, request.enrollmentstatus);

                    stmtUpdate.setInt(1, request.caregiverID);
                    n = stmtUpdate.executeUpdate();
                    stmtUpdate.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error in updateCaregiver: " + sql + " Exception: " + e);
                }
                return n;
            }












    }
