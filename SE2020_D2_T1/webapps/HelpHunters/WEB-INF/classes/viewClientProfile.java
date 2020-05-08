import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class viewClientProfile extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsMoni.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		
		Integer clientID = Integer.parseInt(req.getParameter("clientID"));
		System.out.println("prueba: " + clientID);
		
		clientDataMoni client = clientDataMoni.getClient(connection, clientID);
		employmentDataMoni hires = employmentDataMoni.getHires(connection, clientID);
		reviewsDataMoni average = reviewsDataMoni.getAveragePerClient(connection, clientID);
		
        String caregiver = req.getParameter("login");
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
		

			
			toClient.println(CaregiverUtilsMoni.header(login));
			toClient.println("<div class='single-page-header freelancer-header' data-background-image='images/single-freelancer.jpg'>");
			toClient.println("<div class='container'>");
			toClient.println("<div class='row'>");
			toClient.println("<div class='col-md-12'>");
			toClient.println("<div class='single-page-header-inner'>");
			toClient.println("<div class='left-side'>");
			toClient.println("<div class='header-image freelancer-avatar'><img src='images/random.png' alt=''></div>");
			toClient.println("<div class='header-details'>");
			toClient.println("<h3>" + client.firstname + " " + client.lastname + " <span> Client </span></h3>");

			if (average.average != 0){
				toClient.println("<ul>");
				toClient.println("<li><div class='star-rating' data-rating='"+ average.average +"'></div></li>");
				toClient.println("</ul>");
			} else {
				toClient.println("<ul>");
				toClient.println("<li>No ratings yet</li>");
				toClient.println("</ul>");
			}
			
			toClient.println("</div>");
			toClient.println("</div>");
			toClient.println("</div>");
			toClient.println("</div>");
			toClient.println("</div>");
			toClient.println("</div>");
			toClient.println("</div>");
			

			toClient.println("<div class='container'>");
			toClient.println("<div class='row'>");

			toClient.println("<div class='col-xl-8 col-lg-8 content-right-offset'>");

			toClient.println("<div class='boxed-list margin-bottom-60'>");
			toClient.println("<div class='boxed-list-headline'>");
			toClient.println("<h3><i class='icon-material-outline-thumb-up'></i> Reviews</h3>");
			toClient.println("</div>");
			toClient.println("<ul class='boxed-list-ul'>");
			
		
		Vector<clientDataMoni> clientReview;
		clientReview = clientDataMoni.getClientReviews(connection, clientID);	
			for(int i=0; i< clientReview.size(); i++){
					clientDataMoni review= clientReview.elementAt(i);
					toClient.println("<li>");
					toClient.println("<div class='boxed-list-item'>");
					toClient.println("<!-- Content -->");
					toClient.println("<div class='item-content'>");
					toClient.println("<h4> " + review.firstname + " " + review.lastname + " <span></span></h4>");
					toClient.println("<div class='item-details margin-top-10'>");
					toClient.println("<div class='star-rating' data-rating='" + review.numberofstars + "'></div>");
					toClient.println("<div class='detail-item'><i class='icon-material-outline-date-range'></i>" + review.ratedate + "</div>");
					toClient.println("</div>");
					toClient.println("<div class='item-description'>");
					toClient.println("<p> " + review.comment + " </p>");
					toClient.println("</div>");
					toClient.println("</div>");
					toClient.println("</div>");
					toClient.println("</li>");
			}


		toClient.println("</ul>");


		toClient.println("</div>");

		toClient.println("</div>");

		toClient.println("<div class='col-xl-4 col-lg-4'>");
		toClient.println("<div class='sidebar-container'>");

		toClient.println("<div class='profile-overview'>");
		toClient.println("<div class='overview-item'><strong>"+ hires.hires +"</strong><span>Hires</span></div>");
		toClient.println("</div>");


		toClient.println("</div>");
		toClient.println("</div>");

		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");

		toClient.println("<div class='margin-top-15'></div>");


		
		toClient.println(CaregiverUtilsMoni.footer(login));
		toClient.println(CaregiverUtilsMoni.script(login));


		}
	
}	