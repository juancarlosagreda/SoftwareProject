import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminSettingsAjax extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        //We have to request the session when we login
        // HttpSession session = req.getSession(true);
        // int administratorID = Integer.parseInt(session.getAttribute("id").toString());
        // String firstname = session.getAttribute("fname").toString();
        // String lastname = session.getAttribute("lname").toString();
        // String email = session.getAttribute("usr").toString();
        // String password = session.getAttribute("pw").toString();

        //For the AJAX to work we need a request handler.. we choose to get the 'sex' parameter
        int administratorID = Integer.parseInt(req.getParameter("id"));
        String firstname = req.getParameter("f");
        String lastname = req.getParameter("l");
        String email = req.getParameter("e");
        String password = req.getParameter("p");

        if(password != null){
          int n = adminData.updateSettingsPW(connection, administratorID, firstname, lastname, email, password);
          toClient.print("[{\"linesUpdated\":\"" + n + "\"}]");
          res.sendRedirect("adminLogin");
        } else {
          int n = adminData.updateSettings(connection, administratorID, firstname, lastname, email);
          toClient.print("[{\"linesUpdated\":\"" + n + "\"}]");
          res.sendRedirect("adminLogin");

        }
    }
	}
