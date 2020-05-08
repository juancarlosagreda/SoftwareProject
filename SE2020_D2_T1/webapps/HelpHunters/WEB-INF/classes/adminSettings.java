import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminSettings extends HttpServlet {
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

        toClient.println(adminUtils.header("Settings","My account", administratorID,firstname, lastname, email, password));

        //We need to pass on a JSON object for our js to interpret it...
        //We already have the data within this class :)
        toClient.println("<script>adminData=[");
        toClient.print("{");
        toClient.print("\"administratorID\":\"" + administratorID + "\"");
        toClient.print(",\"firstname\":\"" + firstname + "\"");
        toClient.print(",\"lastname\":\"" + lastname + "\"");
        toClient.print(",\"email\":\"" + email + "\"");
        toClient.print(",\"password\":\"" + password + "\"");
        toClient.print("}");
        toClient.println("]</script>");

        toClient.println("<script src='js/adminCreateSettings.js'></script>");
        toClient.println("<script src='js/adminSettingsAjax.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



	}
}
