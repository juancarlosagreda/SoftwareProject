import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminIncperCaregiver extends HttpServlet {
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
        toClient.println("<script>incidentsData=[");
        Vector<adminIncidentData> incpercaregiv = adminIncidentData.getListbyCaregiver(connection, id);
        for(int i=0; i < incpercaregiv.size(); i++){
            adminIncidentData incident = incpercaregiv.elementAt(i);
            if (i!=0) {
                toClient.print(",");
            }
            toClient.print("{");
            toClient.print("\"noticeID\":\"" + incident.noticeID + "\"");
            toClient.print(",\"employmentID\":\"" + incident.employmentID + "\"");
            toClient.print(",\"subject\":\"" + incident.subject + "\"");
            toClient.print(",\"message\":\"" + incident.message + "\"");
            toClient.print(",\"datecreated\":\"" + incident.datecreated + "\"");
            toClient.print(",\"reply\":\"" + incident.reply + "\"");
            toClient.print(",\"datereplied\":\"" + incident.datereplied + "\"");
            toClient.print(",\"classification\":\"" + incident.classification + "\"");
            toClient.print("}");
        }
        toClient.println("]</script>");
        toClient.println("<script src='js/adminCreateIncidentList.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



	}
}
