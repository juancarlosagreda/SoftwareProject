import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class CaregiverProvince extends HttpServlet {
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
      String prov = "Albacete";



        toClient.println(adminUtils.header("Provincez","Provincez", administratorID,firstname, lastname, email, password));
        toClient.println("<script>provinceData=[");
        Vector<adminCaregiverData> caregivprov = adminCaregiverData.getListperProvince(connection, prov);
        for(int i=0; i < caregivprov.size(); i++){
            adminCaregiverData caregiver = caregivprov.elementAt(i);
            if (i!=0) {
                toClient.print(",");
            }
            toClient.print("{");
            toClient.print("\"provincename\":\"" + caregiver.provincename + "\"");
            toClient.print(",\"caregiverID\":\"" + caregiver.caregiverID + "\"");
            toClient.print(",\"firstname\":\"" + caregiver.firstname + "\"");
            toClient.print(",\"lastname\":\"" + caregiver.lastname + "\"");
            toClient.print(",\"email\":\"" + caregiver.email + "\"");
            toClient.print(",\"gender\":\"" + caregiver.gender + "\"");
            toClient.print(",\"available\":\"" + caregiver.available + "\"");
            toClient.print(",\"hourlyrate\":\"" + caregiver.hourlyrate + "\"");
            toClient.print(",\"description\":\"" + caregiver.description + "\"");
            toClient.print(",\"birthdate\":\"" + caregiver.hourlyrate + "\"");
            toClient.print(",\"enrollmentstatus\":\"" + caregiver.enrollmentstatus + "\"");


            toClient.print("}");
        }
        toClient.println("]</script>");
        toClient.println("<script src='js/adminprovinceList.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



	}
}
