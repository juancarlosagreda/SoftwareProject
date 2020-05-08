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

public class NotesData {
    int noteID;
    int caregiverID;
    String body;
    Date datecreated;
    String strDateCreated;
    String status;

    //Ahora poner los constructores getCaregiverNote 
    
    // Constructor Date como Date
    NotesData (int noteID, int caregiverID, String body, Date datecreated, String status){
        this.noteID = noteID;
		this.caregiverID = caregiverID;
        this.body = body;
        this.datecreated = datecreated;
        this.status = status;
    }
    
    //(constructor Date como String)
    NotesData (int noteID, int caregiverID, String body, String strDateCreated, String status){
        this.noteID = noteID;
		this.caregiverID = caregiverID;
        this.body = body;
        this.strDateCreated = strDateCreated;
        this.status = status;
    }
    
    
	
    //Vectores
    
    public static NotesData getNote(Connection connection, String noteID){
        
        String sql = "SELECT noteID, caregiverID, body, datecreated, status FROM CaregiverNotes WHERE noteID=?";
        
        System.out.println("getNote: " + sql);
        
        NotesData note = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, noteID);
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                note = new NotesData(
                    Integer.parseInt(result.getString("noteID")), Integer.parseInt(result.getString("caregiverID")),
                    result.getString("body"),
                    result.getDate("datecreated"),
                    result.getString("status")
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getNote: " + sql + " Exception: " + e);
        }
        
        return note;
        
    }
    
    public static Vector<NotesData> getCaregiverNotes(Connection connection, String caregiverID){
        
        System.out.println("ID?: " + caregiverID);
        
        String sql = "SELECT noteID, caregiverID, body, datecreated, status FROM CaregiverNotes WHERE caregiverID=?;";
        
        System.out.println("getCaregiverNote: " + sql);
        
        Vector<NotesData> vec = new Vector<NotesData>();
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, caregiverID);
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()){
                
                NotesData note = new NotesData(
                    Integer.parseInt(result.getString("noteID")), Integer.parseInt(result.getString("caregiverID")),
                    result.getString("body"),
                    result.getDate("datecreated"),
                    result.getString("status")
                );
                
                vec.addElement(note);
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCaregiverNote: " + sql + " Exception: " + e);
        }
        
        return vec;
        
    }
         

    //Editar una nota y updetearla
    public static int updateNote(Connection connection, NotesData note){
        
        String sql="UPDATE CaregiverNotes ";
        sql += "SET body=?, datecreated=?, status = ? ";
        sql += "WHERE caregiverID=? AND noteID=?";
        System.out.println("updateNote: " + sql);
        
        int n = 0;
        
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
			java.util.Date utilDateCreated = formatter.parse(note.strDateCreated);
			java.sql.Date sqlDateCreated = new java.sql.Date(utilDateCreated.getTime());
			
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
            stmtUpdate.setString(1,note.body);
            stmtUpdate.setDate(2,sqlDateCreated);
            stmtUpdate.setString(3,note.status);
            stmtUpdate.setInt(4,note.caregiverID);
            stmtUpdate.setInt(5,note.noteID);
            
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateNote: " + sql + " Exception: " + e);
        } catch(ParseException pe){
            pe.printStackTrace();
            System.out.println("Error in updateNote: " + sql + " Exception: " + pe);   			
        }
        return n;
    }
	
    //Insert una nueva nota en el sistema
    	public static int insertNote(Connection connection, NotesData note){
            
        System.out.println("Llegamos a intertNote");
        String sql="INSERT INTO CaregiverNotes VALUES (?, ?, ?, ?, ?);";
        System.out.println("insertNote: " + sql);
        System.out.println("prueba en Data: " + note.noteID + note.caregiverID);
        int n = 0;
		
        try {
			SimpleDateFormat nDate = new SimpleDateFormat("yyyy-MM-dd");		
			java.util.Date utilNewDateCreated = nDate.parse(note.strDateCreated);
			java.sql.Date sqlNewDateCreated = new java.sql.Date(utilNewDateCreated.getTime());
	
            PreparedStatement stmtInsert= connection.prepareStatement(sql);
            
			stmtInsert.setInt(1, note.noteID);
			stmtInsert.setInt(2, note.caregiverID);
			stmtInsert.setString(3, note.body);
			stmtInsert.setDate(4, sqlNewDateCreated);
			stmtInsert.setString(5, note.status);
							
            n = stmtInsert.executeUpdate();
            stmtInsert.close();
			
			
        } catch(SQLException e){
            e.printStackTrace();
			
            System.out.println("Error in insertEmployee: " + sql + " Exception: " + e);
        } catch(ParseException pe){
            pe.printStackTrace();
            System.out.println("Error in insertEmployee: " + sql + " Exception: " + pe);   			
        }
        return n;
		

    }
}                    