import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminNoIncperCaregiver extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        //We have to request the session when we login
        HttpSession session = req.getSession(true);
        int administratorID = Integer.parseInt(session.getAttribute("id").toString());
        String firstname = session.getAttribute("fname").toString();
        String lastname = session.getAttribute("lname").toString();
        String email = session.getAttribute("usr").toString();
        String password = session.getAttribute("pw").toString();
        String idStr = req.getParameter("caregiverID");
        int id = Integer.parseInt(idStr);



        toClient.println(adminUtils.header("Incidents","Incidents", administratorID,firstname, lastname, email, password));

        toClient.println("<div class='welcome-text'><h3>There are no Incidences for this Caretaker!</h3>" );
        toClient.println("<center><div class ='col-xl-2'><a href='adminCaregiver' button class='button full-width button-sliding-icon ripple-effect'> Back to Caregivers");
        toClient.println("<i class='icon-material-outline-arrow-right-alt'></i></a></button></div>");
        toClient.println(adminUtils.footer());
        toClient.close();
}
}
