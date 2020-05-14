import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class clientReviewsPage extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config); 
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        //String cliente = req.getParameter("login");
		//String province = req.getParameter("province");
        HttpSession session = req.getSession(false);
        String login = null;
		int loginid = 0;
        if (session != null) {
            login = (String)session.getAttribute("login");
			loginid = (int)session.getAttribute("loginid");
			session.setAttribute("login", login);
			session.setAttribute("loginid", loginid);
            System.out.println("clientReviewsPage logged");
            System.out.println("clientReviewsPage login: " + login);
			System.out.println("clientReviewsPage loginid: " + loginid);
        }
		toClient.println("<!doctype html>");
		toClient.println("<html lang='en'>");
		toClient.println("<head>");
		toClient.println("<title> Help Hunters</title>");
		toClient.println("<meta charset='utf-8'>");
		toClient.println("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>");
		toClient.println("<link rel='stylesheet' href='css/style.css'>");
		toClient.println("<link rel='stylesheet' href='css/colors/blue.css'>");
		toClient.println("</head>");
		toClient.println("<body class='gray'>");
		toClient.println("<div id='wrapper'>");
		toClient.println("<header id='header-container' class='fullwidth dashboard-header not-sticky'>");
		
		toClient.println("<div id='header'>");
		toClient.println("<div class='container'>");
		toClient.println("<div class='left-side'>");
		toClient.println("<div id='logo'>");
		toClient.println("<a href='index.html'><img src='images/logo.png' alt=''></a>");
		toClient.println("</div>");
		toClient.println("</div>");
		
		toClient.println("<div class='right-side'>");
		toClient.println("<div class='header-widget'>");
		toClient.println("<div class='header-notifications user-menu'>");
		toClient.println("<div class='header-notifications-trigger'>");
		toClient.println("<a href='#'><div class='user-avatar status-online'><img src='images/random.png' alt=''></div></a>");
		toClient.println("</div>");
		
		toClient.println("<div class='header-notifications-dropdown'>");
		toClient.println("<div class='user-status'>");
		toClient.println("<div class='user-details'>");
		toClient.println("<div class='user-avatar status-online'><img src='images/random.png' alt=''></div>");
		toClient.println("<div class='user-name'>");
		toClient.println("" + login + "");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		
		toClient.println("<ul class='user-menu-small-nav'>");
		toClient.println("<li><a href='clientProfile'><i class='icon-material-outline-face'></i> View my Profile </a></li>");
		toClient.println("<li><a href='clientReviewsPage'><i class='icon-material-outline-rate-review'></i> Reviews</a></li>");
		toClient.println("<li><a href='clientMessagesAle'><i class='icon-material-outline-dashboard'></i> Messages</a></li>");
		toClient.println("<li><a href='ViewNotices.html'><i class='icon-feather-send'></i> Notices</a></li>");
		toClient.println("<li><a href='JobOffersDetail.html'><i class='icon-material-outline-business-center'></i> Job Offers</a></li>");
		toClient.println("<li><a href='ClientSettings.html'><i class='icon-material-outline-settings'></i> Settings</a></li>");
		toClient.println("<li><a onclick = 'logout()'><i class='icon-material-outline-power-settings-new'></i> Logout</a></li>");
		
		toClient.println("</ul>");
		
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</header>");
		toClient.println("<div class='clearfix'></div>");
		
		toClient.println("<div class='dashboard-content-container' data-simplebar>");
		toClient.println("<div class='dashboard-content-inner' >");
		toClient.println("<div class='dashboard-headline'>");
		toClient.println("<h3>Reviews</h3>");
		
		toClient.println("<nav id='breadcrumbs' class='dark'>");
		toClient.println("<ul>");
		toClient.println("<li><a href='#'>Home</a></li>");
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
        Vector<reviewDataAle> myReviews;
        myReviews = reviewDataAle.getMyReviews(connection, loginid);
         for(int i=0; i< myReviews.size(); i++){
                reviewDataAle review = myReviews.elementAt(i);
				toClient.println("<li>");
				toClient.println("<div class='boxed-list-item'>");
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
		toClient.println("</div>");
		toClient.println("</div>");
		
		
		
		toClient.println("<div class='clearfix'></div>");
		toClient.println("<div class='clearfix'></div>");
		
		
		
		
		
		toClient.println("</div>");
		toClient.println("<div class='col-xl-6'>");
		toClient.println("<div class='dashboard-box margin-top-0'>");
		
		
		toClient.println("<div class='headline'>");
		toClient.println("<h3><i class='icon-material-outline-face'></i> Rate Caregivers (Pending) </h3>");
		toClient.println("</div>");
		toClient.println("<div class='content'>");
		toClient.println("<ul class='dashboard-box-list'>");
		Vector<reviewDataAle> pendingReviews;
		pendingReviews = reviewDataAle.getPendingReviews(connection, loginid);
		 for(int i=0; i< pendingReviews.size(); i++){
				reviewDataAle pendingreview = pendingReviews.elementAt(i);
				toClient.println("<li>");
				toClient.println("<div class='boxed-list-item'>");
				toClient.println("<div class='item-content'>");
				toClient.println("<h4>" + pendingreview.firstname + " " + pendingreview.lastname + "</h4>");
				toClient.println("<span class='company-not-rated margin-bottom-5'>Not Rated</span>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("<a href='#small-dialog-2' class='popup-with-zoom-anim button ripple-effect margin-top-5 margin-bottom-10'><i class='icon-material-outline-thumb-up'></i> Leave a Review</a>");
				
				toClient.println("<div id='small-dialog-2' class='zoom-anim-dialog mfp-hide dialog-with-tabs'>");
				toClient.println("<div class='sign-in-form'>");
				toClient.println("<ul class='popup-tabs-nav'>");
				toClient.println("</ul>");
				toClient.println("<div class='popup-tabs-container'>");
				toClient.println("<!-- Tab -->");
				toClient.println("<div class='popup-tab-content' id='tab2'>");
				toClient.println("<!-- Welcome Text -->");
				toClient.println("<div class='welcome-text'>");
				toClient.println("<h3>Leave a Review</h3>");
				toClient.println("<span>Rate this service! </span>");
				toClient.println("</div>");
				toClient.println("<!-- Form -->");
				toClient.println("<form method='post' id='insertReviewAle' action='insertReviewAle'>");
				toClient.println("<div class='feedback-yes-no'>");
				toClient.println("<strong>Your Rating</strong>");
				toClient.println("<div class='leave-rating'>");
				toClient.println("<input type='hidden' id='employmentID' name='employmentID' value='"+ pendingreview.employmentID + "'>");
				toClient.println("<input type='radio' name='rating' id='rating-radio-1' value='5' required>");
				toClient.println("<label for='rating-radio-1' class='icon-material-outline-star'></label>");
				toClient.println("<input type='radio' name='rating' id='rating-radio-2' value='4' required>");
				toClient.println("<label for='rating-radio-2' class='icon-material-outline-star'></label>");
				toClient.println("<input type='radio' name='rating' id='rating-radio-3' value='3' required>");
				toClient.println("<label for='rating-radio-3' class='icon-material-outline-star'></label>");
				toClient.println("<input type='radio' name='rating' id='rating-radio-4' value='2' required>");
				toClient.println("<label for='rating-radio-4' class='icon-material-outline-star'></label>");
				toClient.println("<input type='radio' name='rating' id='rating-radio-5' value='1' required>");
				toClient.println("<label for='rating-radio-5' class='icon-material-outline-star'></label>");
				toClient.println("</div><div class='clearfix'></div>");
				toClient.println("</div>");
				toClient.println("<textarea class='with-border' placeholder='Comment' name='message2' id='message2' cols='7' required></textarea>");
				toClient.println("</form>");
				toClient.println("<!-- Button -->");
				toClient.println("<button class='button full-width button-sliding-icon ripple-effect' type='submit' form='insertReviewAle'>Leave a Review <i class='icon-material-outline-arrow-right-alt'></i></button>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("</li>");
		 }
		toClient.println("</ul>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");		
		toClient.println("</div>");	
		
		toClient.println("<div class='dashboard-footer-spacer'></div>");
		toClient.println("<div class='small-footer margin-top-15'>");
		toClient.println("<div class='small-footer-copyrights'>");
		toClient.println("2020 <strong>Help Hunters</strong>. All Rights Reserved.");
		toClient.println("</div>");
		toClient.println("<ul class='footer-social-links'>");
		toClient.println("<li>");
		toClient.println("<a href='#' title='Facebook' data-tippy-placement='top'>");
		toClient.println("<i class='icon-brand-facebook-f'></i>");
		toClient.println("</a>");
		toClient.println("</li>");
		toClient.println("<li>");
		toClient.println("<a href='#' title='Twitter' data-tippy-placement='top'>");
		toClient.println("<i class='icon-brand-twitter'></i>");
		toClient.println("</a>");
		toClient.println("</li>");
		toClient.println("<li>");
		toClient.println("<a href='#' title='Google Plus' data-tippy-placement='top'>");
		toClient.println("<i class='icon-brand-google-plus-g'></i>");
		toClient.println("</a>");
		toClient.println("</li>");
		toClient.println("<li>");
		toClient.println("<a href='#' title='LinkedIn' data-tippy-placement='top'>");
		toClient.println("<i class='icon-brand-linkedin-in'></i>");
		toClient.println("</a>");
		toClient.println("</li>");
		toClient.println("</ul>");
		toClient.println("<div class='clearfix'></div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		
		toClient.println("<script src='js/jquery-3.4.1.min.js'></script>");
		toClient.println("<script src='js/jquery-migrate-3.1.0.min.js'></script>");
		toClient.println("<script src='js/mmenu.min.js'></script>");
		toClient.println("<script src='js/tippy.all.min.js'></script>");
		toClient.println("<script src='js/simplebar.min.js'></script>");
		toClient.println("<script src='js/bootstrap-slider.min.js'></script>");
		toClient.println("<script src='js/bootstrap-select.min.js'></script>");
		toClient.println("<script src='js/snackbar.js'></script>");
		toClient.println("<script src='js/clipboard.min.js'></script>");
		toClient.println("<script src='js/counterup.min.js'></script>");
		toClient.println("<script src='js/magnific-popup.min.js'></script>");
		toClient.println("<script src='js/slick.min.js'></script>");
		toClient.println("<script src='js/custom.js'></script>");
		
		
		toClient.println("<!-- Snackbar // documentation: https://www.polonel.com/snackbar/ -->");
		toClient.println("<script>");
		toClient.println("// Snackbar for user status switcher");
		toClient.println("$('#snackbar-user-status label').click(function() {");
		toClient.println("Snackbar.show({");
		toClient.println("text: 'Your status has been changed!',");
		toClient.println("pos: 'bottom-center',");
		toClient.println("showAction: false,");
		toClient.println("actionText: 'Dismiss',");
		toClient.println("duration: 3000,");
		toClient.println("textColor: '#fff',");
		toClient.println("backgroundColor: '#383838'");
		toClient.println("});");
		toClient.println("});");
		toClient.println("</script>");
		toClient.println("<!-- Chart.js // documentation: http://www.chartjs.org/docs/latest/ -->");
		toClient.println("<script src='js/chart.min.js'></script>");
		toClient.println("<script>");
		toClient.println("Chart.defaults.global.defaultFontFamily = 'Nunito';");
		toClient.println("Chart.defaults.global.defaultFontColor = '#888';");
		toClient.println("Chart.defaults.global.defaultFontSize = '14';");
		toClient.println("var ctx = document.getElementById('chart').getContext('2d');");
		toClient.println("var chart = new Chart(ctx, {");
		toClient.println("type: 'line',");
		toClient.println("// The data for our dataset");
		toClient.println("data: {");
		toClient.println("labels: ['January', 'February', 'March', 'April', 'May', 'June'],");
		toClient.println("// Information about the dataset");
		toClient.println("datasets: [{");
		toClient.println("label: 'Views',");
		toClient.println("backgroundColor: 'rgba(42,65,232,0.08)',");
		toClient.println("borderColor: '#2a41e8',");
		toClient.println("borderWidth: '3',");
		toClient.println("data: [196,132,215,362,210,252],");
		toClient.println("pointRadius: 5,");
		toClient.println("pointHoverRadius:5,");
		toClient.println("pointHitRadius: 10,");
		toClient.println("pointBackgroundColor: '#fff',");
		toClient.println("pointHoverBackgroundColor: '#fff',");
		toClient.println("pointBorderWidth: '2',");
		toClient.println("}]");
		toClient.println("},");
		toClient.println("// Configuration options");
		toClient.println("options: {");
		toClient.println("layout: {");
		toClient.println("padding: 10,");
		toClient.println("},");
		toClient.println("legend: { display: false },");
		toClient.println("title:  { display: false },");
		toClient.println("scales: {");
		toClient.println("yAxes: [{");
		toClient.println("scaleLabel: {");
		toClient.println("display: false");
		toClient.println("},");
		toClient.println("gridLines: {");
		toClient.println("borderDash: [6, 10],");
		toClient.println("color: '#d8d8d8',");
		toClient.println("lineWidth: 1,");
		toClient.println("},");
		toClient.println("}],");
		toClient.println("xAxes: [{");
		toClient.println("scaleLabel: { display: false },");
		toClient.println("gridLines:  { display: false },");
		toClient.println("}],");
		toClient.println("},");
		toClient.println("tooltips: {");
		toClient.println("backgroundColor: '#333',");
		toClient.println("titleFontSize: 13,");
		toClient.println("titleFontColor: '#fff',");
		toClient.println("bodyFontColor: '#fff',");
		toClient.println("bodyFontSize: 13,");
		toClient.println("displayColors: false,");
		toClient.println("xPadding: 10,");
		toClient.println("yPadding: 10,");
		toClient.println("intersect: false");
		toClient.println("}");
		toClient.println("},");
		toClient.println("});");
		toClient.println("</script>");
		toClient.println("</body>");
		toClient.println("</html>");
        toClient.close();
    }
}