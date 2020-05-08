import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class seen extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsMoni.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        //String caregiver = req.getParameter("login");
        HttpSession session = req.getSession(false);
		String login = null;
        int id = 0;
        if (session != null) {
			login = (String)session.getAttribute("login");
            id = (int)session.getAttribute("id");
			session.setAttribute("id", id);
            System.out.println("caregiver logged");
			System.out.println("caregivername login: " + login);
            System.out.println("caregiverid login: " + id);
        }
		
        
        int read=Integer.parseInt(req.getParameter("value"));
		int noticeID=Integer.parseInt(req.getParameter("noticeID"));
		
		warningDataMoni seen = new warningDataMoni(

                    read,
					noticeID

                );
				
		int n = warningDataMoni.updateSeen(connection, seen);			
		res.sendRedirect("caregiverWarnings?id=" + id + "&a=" + Math.random());
		
    }
}
