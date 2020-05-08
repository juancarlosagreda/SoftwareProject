import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminClientInfo extends HttpServlet {
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

        int clientID = Integer.parseInt(req.getParameter("clientID"));

        adminClientData client = adminClientData.getadminClient(connection, clientID);

        toClient.println(adminUtils.header("Client Profile","Client Profile", administratorID, firstname, lastname, email, password));

        toClient.println("<script>clientData=[");
            toClient.print("{");
            toClient.print("\"clientID\":\"" + client.clientID + "\"");
            toClient.print(",\"firstname\":\"" + client.firstname + "\"");
            toClient.print(",\"lastname\":\"" + client.lastname + "\"");
            toClient.print(",\"email\":\"" + client.email + "\"");
            toClient.print(",\"gender\":\"" + client.gender + "\"");
            toClient.print(",\"status\":\"" + client.status + "\"");
            //toClient.print(",\"edit\":\"" + editButton + "\"");
            toClient.print("}");
        toClient.println("]</script>");
        toClient.println("<script src='js/adminCreateClient.js'></script>");
        toClient.println("<script src='js/adminClientInfoAjax.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



	}
}
