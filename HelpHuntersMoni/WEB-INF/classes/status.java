import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class status extends HttpServlet {
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
		
		int value = Integer.parseInt(req.getParameter("value"));
		int employmentID = Integer.parseInt(req.getParameter("employmentID"));
		System.out.println("prueba: " + employmentID);
		
		employmentDataMoni status = new employmentDataMoni(

                    value,
					employmentID

                );
				
		int n = employmentDataMoni.updateEmploymentStatus(connection, status);			
		res.sendRedirect("caregiverJobs?id=" + id + "&a=" + Math.random());
		
    }
}