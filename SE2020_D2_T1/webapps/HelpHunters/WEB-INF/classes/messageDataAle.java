import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class messageDataAle {
	int clientID;
	int caregiverID;
	String subject;
	String message;
    Date datecreated;
	String reply;
	Date datereplied;
	String firstname;
	String lastname;
	

    messageDataAle (int clientID, int caregiverID, String subject, String message, Date datecreated, Date datereplied ) {
		this.clientID = clientID;
		this.caregiverID = caregiverID;
		this.subject = subject;
        this.message = message;
        this.datecreated = datecreated;
        this.datereplied = datereplied;
    }
	
	 messageDataAle (int clientID, int caregiverID, String subject, String message) {
		this.clientID = clientID;
		this.caregiverID = caregiverID;
		this.subject = subject;
        this.message = message;
	}
	
	messageDataAle (String firstname, String lastname, String subject, String message, String reply, Date datecreated) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.subject = subject;
        this.message = message;
		this.reply = reply;
		this.datecreated = datecreated;
	}
	
	messageDataAle (String firstname, String lastname, int caregiverID) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.caregiverID = caregiverID;
	}

	public static int sendMessage(Connection connection, messageDataAle message) {
        String sql ="INSERT INTO Message  (clientID, caregiverID, subject, message, reply) VALUES (?,?,?,?,?)";
        System.out.println("sendMessage: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1,message.clientID);
            stmtUpdate.setInt(2,message.caregiverID);
            stmtUpdate.setString(3,message.subject);
			stmtUpdate.setString(4,message.message);
			stmtUpdate.setString(5," ");
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in sendMessage: " + sql + " Exception: " + e);
        }
        return n;
    }
	
	public static Vector<messageDataAle> getMessages(Connection connection, int clientID) {
        Vector<messageDataAle> vec = new Vector<messageDataAle>();
        String sql = "SELECT Caregiver.firstname, Caregiver.lastname, Message.subject, Message.message, Message.reply, Message.datecreated ";
        sql += "FROM Caregiver, Message ";
		sql += "WHERE Caregiver.caregiverID = Message.caregiverID ";
		sql += "AND Message.clientID = ? ORDER BY Message.datecreated DESC";
	
        System.out.println("getMessages: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setInt(1, clientID);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                messageDataAle message = new messageDataAle(
					result.getString("firstname"),
					result.getString("lastname"),
                    result.getString("subject"),
					result.getString("message"),
					result.getString("reply"),
					result.getDate("datecreated")
                );
                vec.addElement(message);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getMessages: " + sql + " Exception: " + e);
        }
        return vec;
    }

}