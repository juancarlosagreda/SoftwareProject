 import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CheckLoginMoni extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsMoni.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String logged = check(connection, login, password);
		int loggedid = check2(connection, login, password);
        System.out.println("check Login logged: " + logged);
		System.out.println("check Login loggedid: " + loggedid);
        System.out.println("check Login login, password: " + login + " " + password);
        if (logged != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("login", logged);
			session.setAttribute("id", loggedid);
			session.setAttribute("password", password);
            res.sendRedirect("caregiverReviews");
        } else {
            res.sendRedirect("INICIO.html");
            }
           
        }
		
	String check(Connection connection, String login, String password) {
        String sql = "SELECT firstname, lastname FROM Caregiver";
        sql += " WHERE email=? and password=?";
        System.out.println("check Login: " + sql);
        String name = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                name = result.getString("firstname") + " " + result.getString("lastname");
           }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in check login: " + sql + " Exception: " + e);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception in check login. Exception: " + e);
        }
        return name;
    }
	
	int check2(Connection connection, String login, String password) {
		String sql = "Select caregiverID FROM Caregiver";
        sql += " WHERE email=? and password=?";
		System.out.println("check Login: " + sql);
		int caregiverid = 0;
		try {
            PreparedStatement pstmt2 = connection.prepareStatement(sql);
            pstmt2.setString(1, login);
            pstmt2.setString(2, password);
            ResultSet result = pstmt2.executeQuery();
            if(result.next()) {
                caregiverid = result.getInt("caregiverID");
			}
            result.close();
            pstmt2.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in check2 login: " + sql + " Exception: " + e);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception in check2 login. Exception: " + e);
        }
        return caregiverid;
    }
}
    
