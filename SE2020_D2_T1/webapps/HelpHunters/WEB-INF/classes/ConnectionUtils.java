import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;


@SuppressWarnings("serial")
public class ConnectionUtils {
    public static Connection getConnection(ServletConfig config) throws ServletException {
        Connection connection = null;;
        try {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //String url="jdbc:odbc:northbrick";
			      ServletContext context = config.getServletContext();
			      System.out.println("realPath: " + context.getRealPath("HelpHunters.accdb"));
			      String url = new String("jdbc:ucanaccess://" + context.getRealPath("HelpHunters.accdb"));

            //String url="jdbc:mysql://localhost:3306/HelpHunters?useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid";
            //String url = "jdbc:mysql://localhost:3306/Caregivers?useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid";
            connection = DriverManager.getConnection(url);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection getConnection() {
        Connection connection = null;;
        try {
          //String url="jdbc:mysql://localhost:3306/Caregivers?useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid";
          String url = "jdbc:ucanaccess:HelpHunters";
          connection = DriverManager.getConnection(url);
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
