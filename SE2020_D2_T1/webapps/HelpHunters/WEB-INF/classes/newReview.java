import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class newReview extends HttpServlet {
	Connection connection;
	
	public void init(ServletConfig config) throws ServletException {	
		super.init(config);
        connection = ConnectionUtilsMoni.getConnection(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter toClient = response.getWriter();
        String caregiver = request.getParameter("login");
        HttpSession session = request.getSession(false);
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

		reviewsDataMoni newReview = new reviewsDataMoni( 
		
					Integer.parseInt(request.getParameter("employmentID")),
                    Integer.parseInt(request.getParameter("radio")),
					request.getParameter("message2")


                ); 
				
        int n = reviewsDataMoni.insertReview(connection, newReview);
		response.sendRedirect("caregiverReviews");

        

    }
}
