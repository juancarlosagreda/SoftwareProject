import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class caregiverReviews extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
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
		toClient.println("<div class='dashboard-container'>");
		toClient.println("<div class='dashboard-sidebar'>");
		toClient.println("<div class='dashboard-sidebar-inner' data-simplebar>");
		toClient.println("<div class='dashboard-nav-container'>");
		toClient.println("<a href='#' class='dashboard-responsive-nav-trigger'>");
		toClient.println("<span class='hamburger hamburger--collapse' >");
		toClient.println("<span class='hamburger-box'>");
		toClient.println("<span class='hamburger-inner'></span>");
		toClient.println("</span>");
		toClient.println("</span>");
		toClient.println("<span class='trigger-title'>Dashboard Navigation</span>");
		toClient.println("</a>");
		toClient.println("<div class='dashboard-nav'>");
		toClient.println("<div class='dashboard-nav-inner'>");
		toClient.println("<ul data-submenu-title='Start'>");
		toClient.println("<li><a href='dashboard'><i class='icon-material-outline-dashboard'></i> Dashboard</a></li>");
		toClient.println("<li><a href='caregiverMessages'><i class='icon-material-outline-question-answer'></i> Messages </a></li>");
		toClient.println("<li class='active'><a href='caregiverReviews'><i class='icon-material-outline-rate-review'></i> Reviews</a></li>");
		toClient.println("</ul>");
		toClient.println("<ul data-submenu-title='Organize and Manage'>");
		toClient.println("<li><a href='#'><i class='icon-material-outline-business-center'></i> Jobs</a>");
		toClient.println("<ul>");
		toClient.println("<li><a href='caregiverJobs'>Manage Jobs </a></li>");
		toClient.println("<li><a href='caregiverWarnings'>Warnings </a></li>");
		toClient.println("</ul>");
		toClient.println("</li>");
		toClient.println("</ul>");
		toClient.println("<ul data-submenu-title='Account'>");
		toClient.println("<li><a href='caregiverSettings'><i class='icon-material-outline-settings'></i> Settings</a></li>");
		toClient.println("<li><a href='LogoutMoni'><i class='icon-material-outline-power-settings-new'></i> Logout</a></li>");
		toClient.println("</ul>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");	
		toClient.println("<div class='dashboard-content-container' data-simplebar>");
		toClient.println("<div class='dashboard-content-inner' >");
		toClient.println("<div class='dashboard-headline'>");
		toClient.println("<h3>Reviews</h3>");
		toClient.println("<nav id='breadcrumbs' class='dark'>");
		toClient.println("<ul>");
		toClient.println("<li><a href='INICIOMoni.html'>Home</a></li>");
		toClient.println("<li><a href='#'>Dashboard</a></li>");
		toClient.println("<li>Reviews</li>");
		toClient.println("</ul>");
		toClient.println("</nav>");
		toClient.println("</div>");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-xl-6'>");
		toClient.println("<div class='dashboard-box margin-top-0'>");
		toClient.println("<div class='headline'>");
		toClient.println("<h3><i class='icon-material-outline-business'></i> My Reviews</h3>");
		toClient.println("</div>");
		toClient.println("<div class='content'>");
		toClient.println("<ul class='dashboard-box-list'>");

        Vector<reviewsDataMoni> CaregiverReview;
		CaregiverReview = reviewsDataMoni.getCaregiverReviews(connection, id);
        for(int i=0; i< CaregiverReview.size(); i++){
                reviewsDataMoni reviews = CaregiverReview.elementAt(i);
				toClient.println("<li>");
				toClient.println("<div class='boxed-list-item'>");
				toClient.println("<div class='item-content'>");
				toClient.println("<h4>" + reviews.firstname + " " + reviews.lastname + "</h4>");
				toClient.println("<div class='item-details margin-top-10'>");
				toClient.println("<div class='star-rating' data-rating=" + reviews.numberofstars + "></div>");
				toClient.println("<div class='detail-item'><i class='icon-material-outline-date-range'></i>" + reviews.ratedate + "</div>");
				toClient.println("</div>");
				toClient.println("<div class='item-description'>");
				toClient.println("<p>" + reviews.comment + "</p>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("</li>");
				toClient.println("<li>");

        }

		toClient.println("</ul>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");	
		toClient.println("<div class='col-xl-6'>");
		toClient.println("<div class='dashboard-box margin-top-0'>");
		toClient.println("<div class='headline'>");
		toClient.println("<h3><i class='icon-material-outline-business'></i> Rate Clients</h3>");
		toClient.println("</div>");
		toClient.println("<div class='content'>");
		toClient.println("<ul class='dashboard-box-list'>");
	
		Vector<reviewsDataMoni> CaregiverPendingReview;
		CaregiverPendingReview = reviewsDataMoni.getPendingReviews(connection, id);
        for(int i=0; i< CaregiverPendingReview.size(); i++){
                reviewsDataMoni pendingReview = CaregiverPendingReview.elementAt(i);
				toClient.println("<li>");
				toClient.println("<div class='boxed-list-item'>");
				toClient.println("<div class='item-content'>");
				toClient.println("<h4>" + pendingReview.firstname + " " + pendingReview.lastname + "</h4>");
				toClient.println("<span class='company-not-rated margin-bottom-5'>Not Rated</span>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("<a href='#small-dialog-2' id= '" + pendingReview.clientID + "' class='popup-with-zoom-anim button ripple-effect margin-top-5 margin-bottom-10'><i class='icon-material-outline-thumb-up'></i> Leave a Review</a>");
				toClient.println("</li>");

				toClient.println("<div id='small-dialog-2' class='zoom-anim-dialog mfp-hide dialog-with-tabs'>");
				toClient.println("<div class='sign-in-form'>");
				toClient.println("<ul class='popup-tabs-nav'>");
				toClient.println("</ul>");
				toClient.println("<div class='popup-tabs-container'>");
				toClient.println("<div class='popup-tab-content' id='tab2'>");
				toClient.println("<div class='welcome-text'>");
				toClient.println("<h3>Leave a Review</h3>");
				toClient.println("<span> Rate "+ pendingReview.firstname +" "+ pendingReview.lastname +" as a client. </span>");
				toClient.println("</div>");
				toClient.println("<form method='post' id='leave-review-form' action='newReview' name='review'> ");
				toClient.println("<div class='feedback-yes-no'>");
				toClient.println("<strong>Your Rating</strong>");
				toClient.println("<div class='leave-rating'>");
				toClient.println("<input type='hidden' id='employmentID' name='employmentID' value='"+ pendingReview.employmentID + "'>");
				toClient.println("<input type='radio' name='radio' id='rating-radio-1' value='5' required>");
				toClient.println("<label for='rating-radio-1' class='icon-material-outline-star'></label>");
				toClient.println("<input type='radio' name='radio' id='rating-radio-2' value='4' required>");
				toClient.println("<label for='rating-radio-2' class='icon-material-outline-star'></label>");
				toClient.println("<input type='radio' name='radio' id='rating-radio-3' value='3' required>");
				toClient.println("<label for='rating-radio-3' class='icon-material-outline-star'></label>");
				toClient.println("<input type='radio' name='radio' id='rating-radio-4' value='2' required>");
				toClient.println("<label for='rating-radio-4' class='icon-material-outline-star'></label>");
				toClient.println("<input type='radio' name='radio' id='rating-radio-5' value='1' required>");
				toClient.println("<label for='rating-radio-5' class='icon-material-outline-star'></label>");
				toClient.println("</div><div class='clearfix'></div>");
				toClient.println("</div>");
				toClient.println("<textarea class='with-border' placeholder='Comment' name='message2' id='message2' cols='7' required></textarea>");
				toClient.println("</form>");
				toClient.println("<button class='button full-width button-sliding-icon ripple-effect' type='submit' form='leave-review-form'>Leave a Review <i class='icon-material-outline-arrow-right-alt'></i></button>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("</div>");
				
        }
		
		toClient.println("</ul>");
		toClient.println("</div>");
		toClient.println("</div>");	
		toClient.println("</div>");
		toClient.println("</div>");	
		toClient.println("</div>");
        toClient.println(CaregiverUtilsMoni.footer(login));			
		toClient.println(CaregiverUtilsMoni.script(login));			
        toClient.close();
    }
}