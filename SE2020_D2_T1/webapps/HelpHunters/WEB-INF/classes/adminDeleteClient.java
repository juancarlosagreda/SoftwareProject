import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminDeleteClient extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        //We have to request the session when we login
        // HttpSession session = req.getSession(true);
        // int administratorID = Integer.parseInt(session.getAttribute("id").toString());
        // String firstname = session.getAttribute("fname").toString();
        // String lastname = session.getAttribute("lname").toString();
        // String email = session.getAttribute("usr").toString();
        // String password = session.getAttribute("pw").toString();

        //For the AJAX to work we need a request handler.. we choose to get the 'clientID' parameter
        int clientID = Integer.parseInt(req.getParameter("clientID"));

        int n = adminClientData.deleteClient(connection, clientID);
        //We have a response for debugging purposes :)
        toClient.print("[{\"linesDeleted\":\"" + n + "\"}]");


            }
	}
