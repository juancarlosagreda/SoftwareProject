import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class TeresaCheckLogin extends HttpServlet {
    String stringCon = "jdbc:mysql://34.76.88.212/Caregivers?user=equipo&password=Tecnun2020";
    Connection connection = DriverManager.getConnection(stringCon);

    public TeresaCheckLogin() throws SQLException {
    }


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        System.out.println("Inicio de la sesión.");
        res.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        System.out.println(login + " " + password);
        String logged = check(connection, login, password);

        int loggedid = check2(connection, login, password);
        System.out.println("check Login logged: " + logged);
		System.out.println("check Login loggedid: " + loggedid);
        System.out.println("check Login login, password: " + login + " " + password);
        if (logged != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("login", logged);
			session.setAttribute("loginid", loggedid);
            //res.sendRedirect("clientHome");
            res.sendRedirect("clientHome");
        } else {
            res.sendRedirect("indexTeresa.html");
            }
           
        }
		
		String check(Connection connection, String login, String password) {
        String sql = "Select firstname, lastname FROM Client";
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
		String sql = "Select clientID FROM Client";
        sql += " WHERE email=? and password=?";
		System.out.println("check Login: " + sql);
		int clientid = 0;
		try {
            PreparedStatement pstmt2 = connection.prepareStatement(sql);
            pstmt2.setString(1, login);
            pstmt2.setString(2, password);
            ResultSet result = pstmt2.executeQuery();
            if(result.next()) {
                clientid = result.getInt("clientID");
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
        return clientid;
    }
    }
    
