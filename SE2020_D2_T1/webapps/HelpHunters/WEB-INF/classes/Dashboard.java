import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Dashboard extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsCori.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

		
		HttpSession session = req.getSession(true);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }
       
        
       System.out.println("ESTOY PONIENDO INFO EN EL JS");
        
        // AQUI LLAMO A LA FUNCION
        String xyz = caregiverDataCori.getViewsStr(connection,login,"2020");
        System.out.println(xyz);
        toClient.println(CaregiverUtilsCori.headerCaregiverDashboardAntesDePerfil(login));
        
    
        toClient.println(CaregiverUtilsCori.headerCaregiverDashboardDespuesDePerfil(login, "dashboard"));
        
        int numReviews = CaregiverDashboardData.getNumberOfReviewsOfCaregiver(connection, login);
        
        int numProfileViews = CaregiverDashboardData.getNumberOfProfileViewsOfCaregiver(connection, login);
        
        String nameOfCaregiver = CaregiverDashboardData.getNameOfCaregiver(connection, login);
        
        toClient.println("	<!-- Dashboard Content");
        toClient.println("	================================================== -->");
        toClient.println("	<div class='dashboard-content-container' data-simplebar>");
        toClient.println("		<div class='dashboard-content-inner' >");
        toClient.println("");
        toClient.println("			<!-- Dashboard Headline -->");
        toClient.println("			<div class='dashboard-headline'>");
        toClient.println("				<h3>Hello, " + nameOfCaregiver +"!</h3>");
        toClient.println("				<span>We are glad to see you again!</span>");
        toClient.println("");
        toClient.println("				<!-- Breadcrumbs -->");
        toClient.println("				<nav id='breadcrumbs' class='dark'>");
        toClient.println("					<ul>");
        toClient.println("						<li><a href='#'>Home</a></li>");
        toClient.println("						<li>Dashboard</li>");
        toClient.println("					</ul>");
        toClient.println("				</nav>");
        toClient.println("			</div>");
        toClient.println("");
        toClient.println("			<!-- Fun Facts Container -->");
        toClient.println("			<div class='fun-facts-container'>");
        toClient.println("				<div class='fun-fact' data-fun-fact-color='#efa80f'>");
        toClient.println("					<div class='fun-fact-text'>");
        toClient.println("						<span>Reviews</span>");
        toClient.println("						<h4>"+ numReviews +"</h4>");
        toClient.println("					</div>");
        toClient.println("					<div class='fun-fact-icon'><i class='icon-material-outline-rate-review'></i></div>");
        toClient.println("				</div>");
        toClient.println("");
        toClient.println("				<!-- Last one has to be hidden below 1600px, sorry :( -->");
        toClient.println("				<div class='fun-fact' data-fun-fact-color='#2a41e6'>");
        toClient.println("					<div class='fun-fact-text'>");
        toClient.println("						<span>This Month Views</span>");
        toClient.println("						<h4>"+numProfileViews+"</h4>");
        toClient.println("					</div>");
        toClient.println("					<div class='fun-fact-icon'><i class='icon-feather-trending-up'></i></div>");
        toClient.println("				</div>");
        toClient.println("			</div>");
        toClient.println("");
        
        // COPIAR AQUI LO NUEVO

        toClient.println("");
        toClient.println("			<!-- Row -->");
        toClient.println("			<div class='row'>");
        toClient.println("");
        toClient.println("				<div class='col-xl-8'>");
        toClient.println("					<!-- Dashboard Box -->");
        toClient.println("					<div class='dashboard-box main-box-in-row'>");
        toClient.println("						<div class='headline'>");
        toClient.println("							<h3><i class='icon-feather-bar-chart-2'></i> Your Profile Views This Year</h3>");
        
        // AQUI SE PODRIA CAMBIAR LO DEL TIEMPO
        
        /*toClient.println("							<div class='sort-by'>");
        toClient.println("								<select class='selectpicker hide-tick'>");
        toClient.println("									<option>Last 6 Months</option>");
        toClient.println("									<option>This Year</option>");
        toClient.println("									<option>This Month</option>");
        toClient.println("								</select>");
        toClient.println("							</div>");*/
        toClient.println("						</div>");
        toClient.println("						<div class='content'>");
        toClient.println("							<!-- Chart -->");
        toClient.println("							<div class='chart'>");
        toClient.println("								<canvas id='chart' width='100' height='45'></canvas>");
        toClient.println("							</div>");
        toClient.println("						</div>");
        toClient.println("					</div>");
        toClient.println("					<!-- Dashboard Box / End -->");
        toClient.println("				</div>");
        toClient.println("			</div>");
        toClient.println("			<!-- Row / End -->");
        toClient.println("");
        toClient.println("			<!-- Row -->");
        toClient.println("			<!-- Row / End -->");
        toClient.println("");
        toClient.println("			<!-- Footer -->");
        toClient.println("			<div class='dashboard-footer-spacer'></div>");
        toClient.println("			<div class='small-footer margin-top-15'>");
        toClient.println("				<div class='small-footer-copyrights'>");
        toClient.println("					© 2020 <strong>Help Hunters</strong>. All Rights Reserved.");
        toClient.println("				</div>");
        toClient.println("				<ul class='footer-social-links'>");
        toClient.println("					<li>");
        toClient.println("						<a href='#' title='Facebook' data-tippy-placement='top'>");
        toClient.println("							<i class='icon-brand-facebook-f'></i>");
        toClient.println("						</a>");
        toClient.println("					</li>");
        toClient.println("					<li>");
        toClient.println("						<a href='#' title='Twitter' data-tippy-placement='top'>");
        toClient.println("							<i class='icon-brand-twitter'></i>");
        toClient.println("						</a>");
        toClient.println("					</li>");
        toClient.println("					<li>");
        toClient.println("						<a href='#' title='Google Plus' data-tippy-placement='top'>");
        toClient.println("							<i class='icon-brand-google-plus-g'></i>");
        toClient.println("						</a>");
        toClient.println("					</li>");
        toClient.println("					<li>");
        toClient.println("						<a href='#' title='LinkedIn' data-tippy-placement='top'>");
        toClient.println("							<i class='icon-brand-linkedin-in'></i>");
        toClient.println("						</a>");
        toClient.println("					</li>");
        toClient.println("				</ul>");
        toClient.println("				<div class='clearfix'></div>");
        toClient.println("			</div>");
        toClient.println("			<!-- Footer / End -->");
        toClient.println("");
        toClient.println("		</div>");
        toClient.println("	</div>");
        toClient.println("	<!-- Dashboard Content / End -->");
        toClient.println("");
        toClient.println("</div>");
        toClient.println("<!-- Dashboard Container / End -->");
        toClient.println("");
        toClient.println("</div>");
        toClient.println("<!-- Wrapper / End -->");
        toClient.println("");
        toClient.println("");
        toClient.println("<!-- Apply for a job popup");
        toClient.println("================================================== -->");
        toClient.println("<div id='small-dialog' class='zoom-anim-dialog mfp-hide dialog-with-tabs'>");
        toClient.println("");
        toClient.println("	<!--Tabs -->");
        toClient.println("	<div class='sign-in-form'>");
        toClient.println("");
        toClient.println("		<ul class='popup-tabs-nav'>");
        toClient.println("			<li><a href='#tab'>Add Note</a></li>");
        toClient.println("		</ul>");
        toClient.println("");
        toClient.println("		<div class='popup-tabs-container'>");
        toClient.println("");
        toClient.println("			<!-- Tab -->");
        toClient.println("			<div class='popup-tab-content' id='tab'>");
        toClient.println("");
        toClient.println("				<!-- Welcome Text -->");
        toClient.println("				<div class='welcome-text'>");
        toClient.println("					<h3>Do Not Forget 😎</h3>");
        toClient.println("				</div>");
        toClient.println("");
        toClient.println("				<!-- Form -->");
        toClient.println("				<form method='post' id='add-note'>");
        toClient.println("");
        toClient.println("					<select class='selectpicker with-border default margin-bottom-20' data-size='7' title='Priority'>");
        toClient.println("						<option>Low Priority</option>");
        toClient.println("						<option>Medium Priority</option>");
        toClient.println("						<option>High Priority</option>");
        toClient.println("					</select>");
        toClient.println("");
        toClient.println("					<textarea name='textarea' cols='10' placeholder='Note' class='with-border'></textarea>");
        toClient.println("");
        toClient.println("				</form>");
        toClient.println("");
        toClient.println("				<!-- Button -->");
        toClient.println("				<button class='button full-width button-sliding-icon ripple-effect' type='submit' form='add-note'>Add Note <i class='icon-material-outline-arrow-right-alt'></i></button>");
        toClient.println("");
        toClient.println("			</div>");
        toClient.println("");
        toClient.println("		</div>");
        toClient.println("	</div>");
        toClient.println("</div>");
        toClient.println("<!-- Apply for a job popup / End -->");
        toClient.println("");
        toClient.println("");
        toClient.println("<!-- Scripts");
        toClient.println("================================================== -->");
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
        toClient.println("");
        toClient.println("<!-- Snackbar // documentation: https://www.polonel.com/snackbar/ -->");
        toClient.println("<script>");
        toClient.println("// Snackbar for user status switcher");
        toClient.println("$('#snackbar-user-status label').click(function() {");
        toClient.println("	Snackbar.show({");
        toClient.println("		text: 'Your status has been changed!',");
        toClient.println("		pos: 'bottom-center',");
        toClient.println("		showAction: false,");
        toClient.println("		actionText: 'Dismiss',");
        toClient.println("		duration: 3000,");
        toClient.println("		textColor: '#fff',");
        toClient.println("		backgroundColor: '#383838'");
        toClient.println("	});");
        toClient.println("});");
        toClient.println("</script>");
        toClient.println("");
        toClient.println("<!-- Chart.js // documentation: http://www.chartjs.org/docs/latest/ -->");
        toClient.println("<script src='js/chart.min.js'></script>");
        toClient.println("<script>");
        toClient.println("	Chart.defaults.global.defaultFontFamily = 'Nunito';");
        toClient.println("	Chart.defaults.global.defaultFontColor = '#888';");
        toClient.println("	Chart.defaults.global.defaultFontSize = '14';");
        toClient.println("");
        toClient.println("	var ctx = document.getElementById('chart').getContext('2d');");
        toClient.println("");
        toClient.println("	var chart = new Chart(ctx, {");
        toClient.println("		type: 'line',");
        toClient.println("");
        toClient.println("		// The data for our dataset");
        toClient.println("		data: {");
        
        // AQUI PUSE VECTOR DEL 1 AL 12.
        
        toClient.println("			labels: [\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\",\"11\",\"12\"],");
        toClient.println("			// Information about the dataset");
        toClient.println("	   		datasets: [{");
        toClient.println("				label: 'Views',");
        //toClient.println("				data: [1,2],");
        toClient.println("				backgroundColor: 'rgba(42,65,232,0.08)',");
        toClient.println("				borderColor: '#2a41e8',");
        toClient.println("				borderWidth: \"3\",");    
        
        
        
        toClient.println("				data: "+xyz+",");
        
        
        toClient.println("				pointRadius: 5,");
        toClient.println("				pointHoverRadius:5,");
        toClient.println("				pointHitRadius: 10,");
        toClient.println("				pointBackgroundColor: '#fff',");
        toClient.println("				pointHoverBackgroundColor: '#fff',");
        toClient.println("				pointBorderWidth: '2',");
        toClient.println("			}]");
        toClient.println("		},");
        toClient.println("");
        toClient.println("		// Configuration options");
        toClient.println("		options: {");
        toClient.println("");
        toClient.println("		    layout: {");
        toClient.println("		      padding: 10,");
        toClient.println("		  	},");
        toClient.println("");
        toClient.println("			legend: { display: false },");
        toClient.println("			title:  { display: false },");
        toClient.println("");
        toClient.println("			scales: {");
        toClient.println("				yAxes: [{");
        toClient.println("					scaleLabel: {");
        toClient.println("						display: false");
        toClient.println("					},");
        toClient.println("					gridLines: {");
        toClient.println("						 borderDash: [6, 10],");
        toClient.println("						 color: '#d8d8d8',");
        toClient.println("						 lineWidth: 1,");
        toClient.println("	            	},");
        toClient.println("				}],");
        toClient.println("				xAxes: [{");
        toClient.println("					scaleLabel: { display: false },");
        toClient.println("					gridLines:  { display: false },");
        toClient.println("				}],");
        toClient.println("			},");
        toClient.println("");
        toClient.println("		    tooltips: {");
        toClient.println("		      backgroundColor: '#333',");
        toClient.println("		      titleFontSize: 13,");
        toClient.println("		      titleFontColor: '#fff',");
        toClient.println("		      bodyFontColor: '#fff',");
        toClient.println("		      bodyFontSize: 13,");
        toClient.println("		      displayColors: false,");
        toClient.println("		      xPadding: 10,");
        toClient.println("		      yPadding: 10,");
        toClient.println("		      intersect: false");
        toClient.println("		    }");
        toClient.println("		},");
        toClient.println("");
        toClient.println("");
        toClient.println("});");
        toClient.println("");
        toClient.println("</script>");
        toClient.println("");
        toClient.println("</body>");
        toClient.println("</html>");
        
    }


}