import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class displayMessageAle extends HttpServlet {
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
            System.out.println("displayMessage logged");
            System.out.println("displayMessage login: " + login);
			System.out.println("displayMessage loginid: " + loginid);
        }
		toClient.println(clientUtilsAle.header(login));
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String reply = req.getParameter("reply");
		String subject = req.getParameter("subject");
		String message = req.getParameter("message");
		String datecreated = req.getParameter("datecreated");
		
		
		toClient.println("<div class='margin-top-90'></div>");
		toClient.println("<div class='dashboard-container'>");
		toClient.println("<div class='dashboard-content-container' data-simplebar>");
		toClient.println("<div class='dashboard-content-inner' >");
		toClient.println("<div class='dashboard-headline'>");
		toClient.println("<h3>Messages</h3>");
		toClient.println("<nav id='breadcrumbs' class='dark'>");
		toClient.println("<ul>");
		toClient.println("<li>Home</li>");
		toClient.println("<li>Messages</li>");
		toClient.println("</ul>");
		toClient.println("</nav>");
		toClient.println("</div>");
		toClient.println("<div class='messages-container margin-top-0'>");
		toClient.println("<div class='messages-container-inner'>");
		toClient.println("<div class='message-content'>");
		toClient.println("<div class='messages-headline'>");
		toClient.println("<h4><b>"+firstname+" "+lastname+"</b></h4>");
		toClient.println("</div>");
		toClient.println("<div class='messages-headline'>");
		toClient.println("<h4><b>Subject: "+subject+"</b></h4>");
		toClient.println("</div>");
		toClient.println("<div class='message-content-inner'>");
		toClient.println("<div class='message-time-sign'>");
		toClient.println("<span>"+datecreated+"</span>");
		toClient.println("</div>");
		toClient.println("<div class='message-bubble me'>");
		toClient.println("<div class='message-bubble-inner'>");
		toClient.println("<div class='message-avatar'><img src='images/user-avatar-small-01.jpg' alt='' /></div>");
		toClient.println("<div class='message-text'><p>"+message+"</p></div>");
		toClient.println("</div>");
		toClient.println("<div class='clearfix'></div>");
		toClient.println("</div>");
		toClient.println("<div class='message-bubble'>");
		toClient.println("<div class='message-bubble-inner'>");
		toClient.println("<div class='message-avatar'><img src='images/user-avatar-small-02.jpg' alt='' /></div>");
		toClient.println("<div class='message-text'><p>"+reply+"</p></div>");
		toClient.println("</div>");
		toClient.println("<div class='clearfix'></div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='clearfix'></div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='dashboard-footer-spacer' style='padding-top: 160px;'></div>");
		toClient.println(clientUtilsAle.footer(login));
        toClient.close();

    }
}