
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminLoginValidation extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        HttpSession session = req.getSession(false);
        //First we get the username and password
        String username = req.getParameter("emailaddress");
        String password = req.getParameter("password");

        //We get ready to print out stuff
        PrintWriter toClient = res.getWriter();

        //We now use adminLoginData object to perform our query
        int n = adminData.getAdminAccess(connection, username, password);
        adminData admin = null;

        if(n == 0){
          //if there are no matches then we print the same login page
  				toClient.println(adminLoginPrintPage.printLoginError());
  				//we also have the method printLogin();
        } else {
          //We get all of the admin info...
          admin = adminData.getadminData(connection, username, password);
          //We create a session
          session = req.getSession(true);
          //Now we set the attributes of the Session
          session.setAttribute("id", admin.administratorID);
          session.setAttribute("fname", admin.firstname);
          session.setAttribute("lname", admin.lastname);
          session.setAttribute("usr", username);
          session.setAttribute("pw", password);

          //Once the session is created we redirect to adminDashboard
          //It would be useful to include session atributes in utils to save up the process
          res.sendRedirect("adminRequest");
        }

			  toClient.close();

	}

}
