import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminCaregiver extends HttpServlet {
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


        String stat;
        String color;
        int incidences;

        toClient.println(adminUtils.header("Caregivers","Caregivers", administratorID,firstname, lastname, email, password));
        toClient.println("<script>caregiversData=[");
        Vector<adminCaregiverData> caregiverList = adminCaregiverData.getadminCaregiverData(connection);
        for(int i=0; i < caregiverList.size(); i++){
            adminCaregiverData caregiver = caregiverList.elementAt(i);
            if (i!=0) {
                toClient.print(",");
            }

            if (caregiver.available == 0 ){
                stat = "Unavailable";
                color = "red";
            } else {
                stat = "Available";
                color = "green";
            }
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
            toClient.print(",\"available\":\"" + stat + "\"");
            toClient.print(",\"color\":\"" + color + "\"");
            toClient.print(",\"hourlyrate\":\"" + caregiver.hourlyrate + "\"");
            toClient.print(",\"description\":\"" + caregiver.description + "\"");
            toClient.print(",\"birthdate\":\"" + caregiver.birthdate + "\"");
            toClient.print(",\"enrollmentstatus\":\"" + caregiver.enrollmentstatus + "\"");
            toClient.print(",\"incidences\":\"" + incidences+ "\"");

            toClient.print("}");
        }
        toClient.println("]</script>");
        toClient.println("<script src='js/admiCreateCaregiverList.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



	}
}
