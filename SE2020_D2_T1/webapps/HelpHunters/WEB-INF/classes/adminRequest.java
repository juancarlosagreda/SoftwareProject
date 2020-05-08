import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminRequest extends HttpServlet {
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

        String prov;

        toClient.println(adminUtils.header("Welcome back "+ firstname+ "!","Requests", administratorID,firstname, lastname, email, password));
        toClient.println("<script>requestsData=[");
        Vector<adminRequestData> requestsList = adminRequestData.getadminRequestData(connection);
        for(int i=0; i < requestsList.size(); i++){
            adminRequestData request = requestsList.elementAt(i);
            if (i!=0) {
                toClient.print(",");
            }
            if (request.province == "Albacete"){
              prov = "Albacete";
            } else {
              prov = request.province;
            }

            toClient.print("{");
            toClient.print("\"caregiverID\":\"" + request.caregiverID + "\"");
            toClient.print(",\"firstname\":\"" + request.firstname + "\"");
            toClient.print(",\"lastname\":\"" + request.lastname + "\"");
            toClient.print(",\"email\":\"" + request.email + "\"");
            toClient.print(",\"gender\":\"" + request.gender + "\"");
            toClient.print(",\"hourlyrate\":\"" + request.hourlyrate + "\"");
            toClient.print(",\"description\":\"" + request.description + "\"");
            toClient.print(",\"birthdate\":\"" + request.birthdate + "\"");
            toClient.print(",\"enrollmentstatus\":\"" + request.enrollmentstatus + "\"");
            toClient.print(",\"prov\":\"" + prov + "\"");

            toClient.print("}");
        }
        toClient.println("]</script>");
        toClient.println("<script src='js/admiCreateRequestList.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



	}
}
