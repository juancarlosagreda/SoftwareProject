import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;


@SuppressWarnings("serial")
public class ConnectionUtilsCori {
    public static Connection getConnection(ServletConfig config) throws ServletException {
        Connection connection = null;;
        try {
			ServletContext context = config.getServletContext();
			System.out.println("realPath: " + context.getRealPath("HelpHuntersCori.mdb"));
            String url=new String("jdbc:ucanaccess://" + context.getRealPath("HelpHuntersCori.mdb"));
            connection=DriverManager.getConnection(url); 
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection getConnection() {
        Connection connection = null;;
        try {
            String url="jdbc:ucanaccess:HelpHuntersCori";
            connection=DriverManager.getConnection(url); 
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