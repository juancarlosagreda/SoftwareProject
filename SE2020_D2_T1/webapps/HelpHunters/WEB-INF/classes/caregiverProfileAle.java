import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class caregiverProfileAle extends HttpServlet {
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
            System.out.println("clientHome logged");
            System.out.println("clientHome login: " + login);
			System.out.println("clientHome loginid: " + loginid);
        }
	String firstname = req.getParameter("firstname");
	String lastname = req.getParameter("lastname");
	int caregiverID = Integer.parseInt(req.getParameter("caregiverID"));
	float hourlyrate = Float.parseFloat(req.getParameter("hourlyrate"));
	String description = req.getParameter("description");
	
        toClient.println(clientUtilsAle.header(login));
		toClient.println("<div class='single-page-header freelancer-header' data-background-image='images/single-freelancer.jpg'>");
		toClient.println("<div class='container'>");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-md-12'>");
		toClient.println("<div class='single-page-header-inner'>");
		toClient.println("<div class='left-side'>");
		toClient.println("<div class='header-image freelancer-avatar'><img src='images/random.png' alt=''></div>");
		toClient.println("<div class='header-details'>");
		toClient.println("<h3> "+ firstname +" "+ lastname +" <span> Caregiver</span></h3>");
		toClient.println("<ul>");
		toClient.println("<li><div class='verified-badge-with-title'>Verified</div></li>");
		toClient.println("</ul>");
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
		toClient.println("<div class='single-page-section'>");
		toClient.println("<h3 class='margin-bottom-25'>About Me</h3>");
		toClient.println("<p> "+ description +" </p>");
		toClient.println("</div>");
		toClient.println("<div class='boxed-list margin-bottom-60'>");
		toClient.println("<div class='boxed-list-headline'>");
		toClient.println("<h3><i class='icon-material-outline-thumb-up'></i> Reviews</h3>");
		toClient.println("</div>");
		toClient.println("<ul class='boxed-list-ul'>");
		Vector<reviewDataAle> caregiverReviews;
        caregiverReviews = reviewDataAle.getCaregiverReviews(connection, caregiverID);
         for(int i=0; i< caregiverReviews.size(); i++){
                reviewDataAle review = caregiverReviews.elementAt(i);
				toClient.println("<li>");
				toClient.println("<div class='boxed-list-item'>");
				toClient.println("<!-- Content -->");
				toClient.println("<div class='item-content'>");
				toClient.println("<h4>"+ review.firstname + " " + review.lastname +"</h4>");
				toClient.println("<div class='item-details margin-top-10'>");
				toClient.println("<div class='star-rating' data-rating='"+ review.numberofstars +"'></div>");
				toClient.println("<div class='detail-item'><i class='icon-material-outline-date-range'></i> "+ review.ratedate +"</div>");
				toClient.println("</div>");
				toClient.println("<div class='item-description'>");
				toClient.println("<p>"+ review.comment +"</p>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("</li>");
        }
		toClient.println("</ul>");
		toClient.println("<div class='clearfix'></div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='col-xl-4 col-lg-4'>");
		toClient.println("<div class='sidebar-container'>");
		toClient.println("<!-- Button -->");
		toClient.println("<a href='#' class='apply-now-button popup-with-zoom-anim margin-bottom-50'> Offer a Job <i class='icon-material-outline-arrow-right-alt'></i></a>");
		toClient.println("<a href='#small-dialog' class='apply-now-button popup-with-zoom-anim margin-bottom-50'> Send a Message <i class='icon-material-outline-arrow-right-alt'></i></a>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='margin-top-15'></div>");
		
		toClient.println("<div id='small-dialog' class='zoom-anim-dialog mfp-hide dialog-with-tabs'>");
		toClient.println("<div class='sign-in-form'>");
		toClient.println("<ul class='popup-tabs-nav'>");
		toClient.println("<li><a href='#tab'>Send a Message</a></li>");
		toClient.println("</ul>");
		toClient.println("<div class='popup-tabs-container'>");
		toClient.println("<div class='popup-tab-content' id='tab'>");
		toClient.println("<div class='welcome-text'>");
		toClient.println("<h3>Send a Message!</h3>");
		toClient.println("</div>");
		toClient.println("<form name = 'messages' id = 'sendMessage' method='post' action = 'sendMessageAle'>");
		toClient.println("<input type='hidden' id='caregiverID' name='caregiverID' value='"+ caregiverID + "'>");
		toClient.println("<input type='text' id='subject' name='subject' placeholder = 'Subject' >");
		toClient.println("<textarea name='message' id = 'message' cols='10' placeholder='Type your message here!' class='with-border'></textarea>");
		toClient.println("<input class='button margin-top-35 full-width button-sliding-icon ripple-effect' type = 'submit' value = 'Send' </input>");
		toClient.println("</form>");
		toClient.println("</div>");
		toClient.println("<div class='popup-tab-content' id='loginn'>");
		toClient.println("<div class='welcome-text'>");
		toClient.println("<h3>Discuss Your Project With Tom</h3>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		
        toClient.println(clientUtilsAle.footer(login));
		
        toClient.close();
		int n = caregiverDataAle.viewCProfile(connection, caregiverID);
    }
}