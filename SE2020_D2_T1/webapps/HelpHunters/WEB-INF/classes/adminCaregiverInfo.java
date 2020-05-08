import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminCaregiverInfo extends HttpServlet {
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

        int caregiverID = Integer.parseInt(req.getParameter("caregiverID"));
        int incidences;

        adminCaregiverData  caregiver = adminCaregiverData.getCaregiver(connection, caregiverID);
      //  toClient.println(adminUtils.header("Caregiver Profile","Caregiver Profile", administratorID, firstname, lastname, email, password));
       toClient.println(adminUtils.header("Caregiver Profile","Caregiver Profile", administratorID,firstname, lastname, email, password));

        toClient.println("<script>caregiverInfo=[");

        if (caregiver.inc == 0){
          incidences = 0;
        } else {
          incidences = caregiver.inc;
        }
            toClient.print("{");
            toClient.print("\"caregiverID\":\"" + caregiver.caregiverID + "\"");
            toClient.print(",\"firstname\":\"" + caregiver.firstname + "\"");
            toClient.print(",\"lastname\":\"" + caregiver.lastname + "\"");
            toClient.print(",\"email\":\"" + caregiver.email + "\"");
            toClient.print(",\"gender\":\"" + caregiver.gender + "\"");
            toClient.print(",\"available\":\"" + caregiver.available + "\"");
            toClient.print(",\"hourlyrate\":\"" + caregiver.hourlyrate + "\"");
            toClient.print(",\"description\":\"" + caregiver.description + "\"");
            toClient.print(",\"birthdate\":\"" + caregiver.birthdate + "\"");
            toClient.print(",\"enrollmentstatus\":\"" + caregiver.enrollmentstatus + "\"");
            toClient.print(",\"incidences\":\"" + incidences + "\"");


            //toClient.print(",\"edit\":\"" + editButton + "\"");
            toClient.print("}");
        toClient.println("]</script>");
        toClient.println("<script src='js/adminCreateCaregiver2.js'></script>");
        toClient.println("<script src='js/adminCaregiverMoreInfoAjax.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



	}
}
