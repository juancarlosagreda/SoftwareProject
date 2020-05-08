import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CaregiversLogin extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsCori.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        String logged = check(connection, login, password);
        System.out.println("check Login logged: " + logged);
        System.out.println("check Login caregiverID: " + login + " password:" + password);
		
        if (logged != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("login", logged);
            
            // Poner a donde lo quieres mandar (dashboard.html hasta que este listo el servlet)
            res.sendRedirect("Dashboard");
        } else {
            PrintWriter toClient = res.getWriter();
            toClient.println(CaregiverUtilsCori.headerLogInCaregiver());
            if (login != null) {
              toClient.println("<center>Incorrect User-Password Combination</center>");
            }
            toClient.println(loginFormCaregiver());
            toClient.println(CaregiverUtilsCori.footerLogInCaregiver());
            toClient.close();
        }
    }
    
    String check(Connection connection, String email, String password) {
        String sql = "SELECT caregiverID FROM Caregiver";
        sql += " WHERE email=? and password=?";
        System.out.println("check Login: " + sql);
        String caregiverID = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                caregiverID = result.getString("caregiverID");
           }
            System.out.println("CaregiverID -> " + caregiverID);
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in check login: " + sql + " Exception: " + e);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception in check login. Exception: " + e);
        }
        return caregiverID;
    }

    String loginFormCaregiver() {
		String out = "";
        
        // PONER ACCION.

        out += "<form method='get' id='caregiver-login-form'  action='CaregiversLogin'>";
        out += "<div class='input-with-icon-left'>";
        out += "<i class='icon-material-baseline-mail-outline'></i>";
        out += "<input type='text' class='input-text with-border' name='email' id='email' placeholder='Email Address' required>";
        out += "</div>";

        out += "<div class='input-with-icon-left'>";
        out += "<i class='icon-material-outline-lock'></i>";
        out += "<input type='password' class='input-text with-border' name='password' id='password' placeholder='Password' required>";
        out += "</div>";
        out += "<a href='#' class='forgot-password'>Forgot Password?</a>";
        out += "</form>";

        out += "<!-- Button -->";
        out += "<button class='button full-width button-sliding-icon ripple-effect' type='submit' form='caregiver-login-form'>Log In <i class='icon-material-outline-arrow-right-alt'></i></button>";

        return out;
    }
}