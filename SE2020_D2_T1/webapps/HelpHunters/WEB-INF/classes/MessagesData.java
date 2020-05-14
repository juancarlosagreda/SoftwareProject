import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//Para las fechas 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessagesData {
    String clientID;
    String caregiverID;
    String subject;
    String message;
    
    Date datecreated;
    String strDateCreated;
    
    String reply;
    
    Date datereplied;
    String strDateReplied;
    
    String replyhasbeenread;
    
    String DateDiff;
    String name;
    

    //Ahora poner los constructores getCaregiverNote 
    
    // Constructor Date como Date
    MessagesData (String clientID, String caregiverID, String subject, String message, Date datecreated, String reply, Date datereplied, String replyhasbeenread){
        this.clientID = clientID;
		this.caregiverID = caregiverID;
        this.subject = subject;
        this.message = message;
        this.datecreated = datecreated;
        this.reply = reply;
        this.datereplied = datereplied;
        this.replyhasbeenread = replyhasbeenread;
    }
    
    //(constructor Date como String)
    MessagesData (String clientID, String caregiverID, String subject, String message, String strDateCreated, String reply, String strDateReplied, String replyhasbeenread){
        this.clientID = clientID;
		this.caregiverID = caregiverID;
        this.subject = subject;
        this.message = message;
        this.strDateCreated = strDateCreated;
        this.reply = reply;
        this.strDateReplied = strDateReplied;
        this.replyhasbeenread = replyhasbeenread;
    }
    
    
    // Constructor reducir y con diferencia de dias - DATE COMO DATE
    MessagesData (String name, String clientID, String caregiverID, String subject, String DateDiff,Date datecreated, String reply){
        this.name = name;
        this.clientID = clientID;
		this.caregiverID = caregiverID;
        this.subject = subject;
        this.DateDiff = DateDiff;
        this.datecreated = datecreated;
        this.reply = reply;
    }
    
    // Constructor reducir y con diferencia de dias - DATE COMO STRING
    MessagesData (String name, String clientID, String caregiverID, String subject, String DateDiff,String strDateCreated, String reply){
        this.name = name;
        this.clientID = clientID;
		this.caregiverID = caregiverID;
        this.subject = subject;
        this.DateDiff = DateDiff;
        this.strDateCreated = strDateCreated;
        this.reply = reply;
    }
    
    // Constructor no-reducidor y con diferencia de dias - DATE COMO DATE
    MessagesData (String name, String clientID, String caregiverID, String subject, String message, String DateDiff,Date datecreated, String reply, Date datereplied, String replyhasbeenread){
        this.name = name;
        this.clientID = clientID;
		this.caregiverID = caregiverID;
        this.subject = subject;
        this.message = message;
        this.DateDiff = DateDiff;
        this.datecreated = datecreated;
        this.reply = reply;
        this.datereplied = datereplied;
        this.replyhasbeenread = replyhasbeenread;
    }
    
    
    public static Vector<MessagesData> getCaregiverMessages(Connection connection, String caregiverID){
        
        System.out.println("ID?: " + caregiverID);
        
        String sql = "SELECT Client.firstname &  ' ' & Client.lastname AS name,Message.clientID,Message.caregiverID,datecreated,subject,DateDiff('d',[datecreated],DATE()) as DateDiff, reply FROM Message INNER JOIN Client ON Client.clientID = Message.clientID WHERE Message.caregiverID = ? ORDER BY datecreated desc;";
        
        System.out.println("getCaregiverMessages: " + sql);
        
        Vector<MessagesData> vec = new Vector<MessagesData>();
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, caregiverID);
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()){
                
                MessagesData msg = new MessagesData(
                    result.getString("name"),
                    result.getString("clientID"),
                    result.getString("caregiverID"),
                    result.getString("subject"),
                    result.getString("DateDiff"),
                    result.getDate("datecreated"),
                    result.getString("reply")
                );
                
                vec.addElement(msg);
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCaregiverMessages: " + sql + " Exception: " + e);
        }
        
        return vec;
        
    }
    
    public static Vector<MessagesData> getMessage(Connection connection, String caregiverID, String clientID, String subject){
        
        System.out.println("Info?: " + caregiverID + clientID + subject);
        
        String sql = "SELECT Client.firstname &  ' ' & Client.lastname AS name, DateDiff('d',[datecreated],DATE()) as DateDiff, Message.* FROM Message INNER JOIN Client ON Client.clientID = Message.clientID WHERE Message.caregiverID = ? AND Message.subject = ? AND Message.clientID = ? ORDER BY datecreated desc;";
        
        System.out.println("getMessage: " + sql);
        
        Vector<MessagesData> vec = new Vector<MessagesData>();
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, caregiverID);
            pstmt.setString(2, subject);
            pstmt.setString(3, clientID);
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()){
                
                MessagesData msg = new MessagesData(
                    result.getString("name"),
                    result.getString("clientID"),
                    result.getString("caregiverID"),
                    result.getString("subject"),
                    result.getString("message"),
                    result.getString("DateDiff"),
                    result.getDate("datecreated"),
                    result.getString("reply"),
                    result.getDate("datereplied"),
                    result.getString("replyhasbeenread")
                );
                
                vec.addElement(msg);
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getMessage: " + sql + " Exception: " + e);
        }
        
        return vec;
        
    }
    
    public static int insertReply(Connection connection, MessagesData message){
        
        String sql="UPDATE Message ";
        sql += "SET reply=? ";
        sql += "WHERE caregiverID = ? AND subject = ? AND clientID = ?;";
        System.out.println("insertReply: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
            stmtUpdate.setString(1,message.reply);
            stmtUpdate.setString(2,message.caregiverID);
            stmtUpdate.setString(3,message.subject);
            stmtUpdate.setString(4,message.clientID);

            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in insertReply: " + sql + " Exception: " + e);
        } 
        return n;
    }
}                    