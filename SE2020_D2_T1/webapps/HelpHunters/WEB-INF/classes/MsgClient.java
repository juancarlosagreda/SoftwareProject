import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@SuppressWarnings("serial")
public class MsgClient extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsCori.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        
        PrintWriter toClient = res.getWriter();
		
		HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }
        
        String clientID = req.getParameter("clientID");
        
        CaregiverDashboardData clientInfo = CaregiverDashboardData.getClientInfo(connection, clientID);
        
        
        toClient.println("							<div class='message-reply' id='msgClient'>"); 
        toClient.println("										<div class='message-avatar'>");
        if (clientInfo.inactive == "1"){
             toClient.println("<i class='status-icon status-online'></i>");
        }  else {
             toClient.println("<i class='status-icon status-offline'></i>");
        } 
        toClient.println("<span></span>");
        toClient.println("</div>");
        toClient.println("										<div class='message-by'>");
        toClient.println("											<div class='message-by-headline'>");
        toClient.println("												<h5>"+clientInfo.name+"</h5>");
        toClient.println("											</div>");
        toClient.println("											<p>"+clientInfo.email+"</p>");
        
        
        toClient.println("											<p>Gender: "+clientInfo.gender+"</p>");
    
        toClient.println("										</div>");
    }
}