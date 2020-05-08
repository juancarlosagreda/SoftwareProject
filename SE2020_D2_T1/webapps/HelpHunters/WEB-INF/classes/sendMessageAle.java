import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class sendMessageAle extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsAle.getConnection(config);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        //String client = req.getParameter("login");
        HttpSession session = req.getSession(false);
        String login = null;
		int loginid = 0;
        if (session != null) {
            login = (String)session.getAttribute("login");
			loginid = (int)session.getAttribute("loginid");
			session.setAttribute("login", login);
			session.setAttribute("loginid", loginid);
            System.out.println("sendMessage logged");
            System.out.println("sendMessage login: " + login);
			System.out.println("sendMessage loginid: " + loginid);
        }
     Integer caregiverID = Integer.parseInt(req.getParameter("caregiverID"));
	 String subject = req.getParameter("subject");
	 String messages = req.getParameter("message");

	  messageDataAle message = new messageDataAle(
					loginid,
					caregiverID,
					subject,
                    messages
					);
					
    int n = messageDataAle.sendMessage(connection, message);
	caregiverDataAle caregiver = caregiverDataAle.getCaregiver(connection, caregiverID);
	res.sendRedirect("caregiverProfileAle?firstname="+caregiver.firstname+"&lastname="+caregiver.lastname+"&caregiverID="+caregiverID+"&hourlyrate="+caregiver.hourlyrate+"&description="+caregiver.description+"");
       
    }
}