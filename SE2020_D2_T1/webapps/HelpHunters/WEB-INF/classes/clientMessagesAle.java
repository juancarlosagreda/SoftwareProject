import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class clientMessagesAle extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsAle.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String client = req.getParameter("login");
        HttpSession session = req.getSession(false);
        String login = null;
		int loginid = 0;
        if (session != null) {
            login = (String)session.getAttribute("login");
			loginid = (int)session.getAttribute("loginid");
			session.setAttribute("login", login);
			session.setAttribute("loginid", loginid);
            System.out.println("clientMessages logged");
            System.out.println("clientMessages login: " + login);
			System.out.println("clientMessages loginid: " + loginid);
        }
		toClient.println(clientUtilsAle.header(login));
		toClient.println("<div class='margin-top-90'></div>");
		toClient.println("<div class='dashboard-container'>");
		toClient.println("<div class='dashboard-content-container' data-simplebar>");
		toClient.println("<div class='dashboard-content-inner' >");
		toClient.println("<div id='listgoeshere'>");
		toClient.println("<div class='dashboard-headline'>");
		toClient.println("<h3>Inbox</h3>");
		toClient.println("<nav id='breadcrumbs' class='dark'>");
		toClient.println("<ul>");
		toClient.println("<li>Home</li>");
		toClient.println("<li>Messages</li>");
		toClient.println("</ul>");
		toClient.println("</nav>");
		toClient.println("</div>");
		//toClient.println("<div class='messages-container margin-top-0'>");
		//toClient.println("<div class='messages-container-inner'>");
		//toClient.println("<div class='messages-inbox'>");
		//toClient.println("<div class='messages-headline'>");
		//toClient.println("<div class='input-with-icon'>");
		//toClient.println("<center><h3>Caregivers</h3></center>");
		toClient.println("</div>");
		toClient.println("</div>");
		//toClient.println("<div id='listgoeshere'>");
		toClient.println("<script> messageDataItem=[");
		
		Vector<messageDataAle> messages;
        messages = messageDataAle.getMessages(connection, loginid);
        for(int i=0; i< messages.size(); i++){
        messageDataAle message = messages.elementAt(i);
		if (i!=0) {
                toClient.print(",");
            }
			toClient.print("{");
			toClient.print("\"firstname\":\"" + message.firstname + "\"");
            toClient.print(",\"lastname\":\"" + message.lastname + "\"");
            toClient.print(",\"subject\":\"" + message.subject + "\"");
            toClient.print(",\"message\":\"" + message.message + "\"");
            toClient.print(",\"reply\":\"" + message.reply + "\"");
            toClient.print(",\"datecreated\":\"" + message.datecreated + "\"");
            toClient.print("}");
		}
	
		toClient.println("]</script>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='clearfix'></div>");
		//toClient.println("</div>");
		//toClient.println("</div>");
		//toClient.println("</div>");
		//toClient.println("</div>");
		//toClient.println("</div>");
		toClient.println("<div class='margin-top-90'></div>");
		toClient.println("<script src='displayMessagesAle.js'></script>");
		toClient.println(clientUtilsAle.footer(login));
        toClient.close();

    }
}