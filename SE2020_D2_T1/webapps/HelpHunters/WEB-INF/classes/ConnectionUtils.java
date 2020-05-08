import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;

//USE INSTRUCTIONS:
  /*
      1) Choose the operating system that you own
      2) Based on your OS, look and read at BOTH methods in this class that you have to configure
      3) Comment out the OS that DOES NOT correspond to your own
      4) Remove the comments that correspond to your OS (Except the INSTRUCTIONS)
  */
@SuppressWarnings("serial")
public class ConnectionUtils {
    public static Connection getConnection(ServletConfig config) throws ServletException {
        Connection connection = null;;
        try {
//Conf PC   //FOR PC REMOVE COMMENTS AND ADD COMMENTS TO MAC CONFIGURATIONS
            /*
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            ServletContext context = config.getServletContext();
            System.out.println("realPath: " + context.getRealPath("HelpHunters.accdb"));
            String url = new String("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};Dbq=" + context.getRealPath("HelpHunters.accdb"));
            connection=DriverManager.getConnection(url);
            */
            //UNTIL HERE

//Conf MAC  //FOR MAC OSX REMOVE COMMENTS AND ADD COMMENTS TO PC CONFIGURATIONS
			      ServletContext context = config.getServletContext();
			      System.out.println("realPath: " + context.getRealPath("HelpHunters.accdb"));
			      String url = new String("jdbc:ucanaccess://" + context.getRealPath("HelpHunters.accdb"));
            connection = DriverManager.getConnection(url);
            //UNTIL HERE

        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection getConnection() {
        Connection connection = null;;
        try {
//Conf PC   //FOR PC REMOVE COMMENTS AND ADD COMMENTS TO MAC CONFIGURATIONS
            /*
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:HelpHunters";
            connection=DriverManager.getConnection(url);
            */
            //UNTIL HERE

//Conf MAC  //FOR MAC OSX REMOVE COMMENTS AND ADD COMMENTS TO PC CONFIGURATIONS
            String url = "jdbc:ucanaccess:HelpHunters";
            connection = DriverManager.getConnection(url);
            //UNTIL HERE
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection close(Connection connection) {
        try {
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
