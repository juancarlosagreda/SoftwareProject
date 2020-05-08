
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

@SuppressWarnings("serial")
public class sendWarning extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        HttpSession session = req.getSession(false);

        int id = Integer.parseInt(req.getParameter("id"));
        String subject =  req.getParameter("subject");
        String message = req.getParameter("message");
        //First we get the username and password
adminWarningData warning = new adminWarningData (
         id,
         subject,
         message);
    //  Date datecreated = "21/07/2019";

int n = adminWarningData.createWarning(connection, warning);

res.sendRedirect("adminIncident");

        //We get ready to print out stuff


    //adminWarningData newwarning =  adminWarningData.createWarning( connection,  noticeID);


        //  session.setAttribute("noticeID", noticeID);
        //  session.setAttribute("subject", subject);
        //  session.setAttribute("message", message);

          //Once the session is created we redirect to adminDashboard
          //It would be useful to include session atributes in utils to save up the process
        //  res.sendRedirect("adminIncident");


	}

}
