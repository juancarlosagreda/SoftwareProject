import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminIncidenceInfo extends HttpServlet {
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
        String idStr = req.getParameter("noticeID");
        int id = Integer.parseInt(idStr);



        toClient.println(adminUtils.header("Incident "+id ,"Incidents", administratorID,firstname, lastname, email, password));
        toClient.println("<script>incidentInfo=[");

        adminIncidentData employment=adminIncidentData.getEmployment(connection, id);
        adminIncidentData participants = adminIncidentData.getParticipants(connection, id);
        adminIncidentData description = adminIncidentData.getIncident(connection, id);

            toClient.print("{");
            toClient.print("\"clientID\":\"" + participants.clientID + "\"");
            toClient.print(",\"caregiverID\":\"" + participants.caregiverID + "\"");
            toClient.print(",\"noticeID\":\"" + description.noticeID + "\"");

            toClient.print(",\"empID\":\"" + employment.employmentID + "\"");
            toClient.print(",\"careID\":\"" + employment.caregiverID + "\"");


            toClient.print(",\"employmentID\":\"" + description.employmentID + "\"");
            toClient.print(",\"subject\":\"" + description.subject + "\"");
            toClient.print(",\"message\":\"" + description.message + "\"");
            toClient.print(",\"datecreated\":\"" + description.datecreated + "\"");
            toClient.print(",\"reply\":\"" + description.reply + "\"");
            toClient.print(",\"datereplied\":\"" + description.datereplied + "\"");
            toClient.print(",\"classification\":\"" + description.classification + "\"");
            toClient.print("}");



        toClient.println("]</script>");
        toClient.println("<script src='js/adminCreateIncidentInfo.js'></script>");
        toClient.println("<script src='js/adminCreateIncidentInfoAJAX.js'></script>");

        toClient.println(adminUtils.footer());
        toClient.close();



    }
    }
