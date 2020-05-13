import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class insertReviewAle extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
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
            System.out.println("insertReview logged");
            System.out.println("insertReview login: " + login);
			System.out.println("insertReview loginid: " + loginid);
        }
     Integer employmentID = Integer.parseInt(req.getParameter("employmentID"));
	 System.out.println("prueba: " + employmentID);
	 int rating = Integer.parseInt(req.getParameter("rating"));
	 String comment = req.getParameter("message2");
	 
	  reviewDataAle review = new reviewDataAle(
					employmentID,
					rating,
                    comment
					);
					
        int n = reviewDataAle.insertReview(connection, review);
    res.sendRedirect("clientReviewsPage");
       
    }
}