import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date; 

@SuppressWarnings("serial")
public class sendReply extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsCori.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
		
		HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }

		String reply = req.getParameter("reply");
        String clientID = req.getParameter("clientID");
        String subject = req.getParameter("subject");
        String caregiverID = login;
        
        Vector<MessagesData> messageData;
        messageData = MessagesData.getMessage(connection,caregiverID,clientID,subject);
        
        MessagesData message = messageData.elementAt(0);
        
        message.reply = reply;

        int n = MessagesData.insertReply(connection, message);
        res.sendRedirect("CaregiverMsgBox?clientID="+clientID+"&subject="+subject);
    }
}