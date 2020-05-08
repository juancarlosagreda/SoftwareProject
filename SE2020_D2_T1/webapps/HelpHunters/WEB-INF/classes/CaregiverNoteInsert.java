import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CaregiverNoteInsert extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsCori.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		
		HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }

		//Esto se va a llamar desde NotesCaregiver, con el boton de add note
        
        String noteID = req.getParameter("noteID");        
        String caregiverID = login;
        
        
		//Tengo que poner el header asociado a esta parte
        toClient.println(CaregiverUtilsCori.headerCaregiverDashboardAntesDePerfil(login));
        
        toClient.println(CaregiverUtilsCori.headerCaregiverDashboardDespuesDePerfil(login,"notes"));

        
        toClient.println("	<!-- Dashboard Content");
        toClient.println("	================================================== -->");
        toClient.println("	<div class='dashboard-content-container' data-simplebar>");
        toClient.println("		<div class='dashboard-content-inner' >");
        toClient.println("");
        toClient.println("			<!-- Dashboard Headline -->");
        toClient.println("			<div class='dashboard-headline'>");
        toClient.println("				<h3>Notes</h3>");
        toClient.println("");
        toClient.println("				<!-- Breadcrumbs -->");
        toClient.println("				<nav id='breadcrumbs' class='dark'>");
        toClient.println("					<ul>");
        toClient.println("						<li><a href='#'>Home</a></li>");
        toClient.println("						<li><a href='#'>Dashboard</a></li>");
        toClient.println("						<li>Notes</li>");
        toClient.println("					</ul>");
        toClient.println("				</nav>");
        toClient.println("			</div>");
        toClient.println("");
        toClient.println("			<!-- Row -->");
        toClient.println("			<div class='row'>");
        toClient.println("");

        toClient.println("<form method='GET' action='CaregiverAddNote'>");
        toClient.println("				<!-- Dashboard Box -->");
        toClient.println("				<div class='col-xl-12'>");
        toClient.println("					<div class='dashboard-box margin-top-0'>");
        toClient.println("");
        toClient.println("						<!-- Headline -->");
        toClient.println("						<div class='headline'>");
        toClient.println("							<h3><i class='icon-material-outline-account-circle'></i> My Notes</h3>");
        toClient.println("						</div>");
        toClient.println("");
        toClient.println("						<div class='content with-padding padding-bottom-0'>");
        toClient.println("");
        toClient.println("							<div class='row'>");
        toClient.println("");
        toClient.println("								<div class='col'>");
        toClient.println("									<div class='row'>");
        toClient.println("");
        toClient.println("										<div class='col-xl-6'>");
        toClient.println("											<div class='submit-field'>");
        toClient.println("												<h5>Note ID</h5>");
        toClient.println("												<input type='text' class='with-border' name='noteID' id='noteID'>");
        toClient.println("											</div>");
        toClient.println("										</div>");
        toClient.println("");
        toClient.println("										<div class='col-xl-6'>");
        toClient.println("											<div class='submit-field'>");
        toClient.println("												<h5>Write your note here: </h5>");
        toClient.println("												<input type='text' class='with-border' name='body' id='body'>");
        toClient.println("											</div>");
        toClient.println("										</div>");
        toClient.println("");
        toClient.println("										<div class='col-xl-6'>");
        toClient.println("											<div class='submit-field'>");
        toClient.println("												<h5>Select the date</h5>");
        toClient.println("												<input type='date' class='with-border' name='datecreated' id='datecreated'>");
        toClient.println("											</div>");
        toClient.println("										</div>");
        toClient.println("");
        toClient.println("										<div class='col-xl-6'>");
        toClient.println("											<div class='submit-field'>");
        toClient.println("												<h5>Write the priority of your note</h5>");
        toClient.println("												<select name='status' id='status' class='selectpicker with-border' data-size='7' title='Select Status Type' data-live-search='true'>");
        toClient.println("<option>Low Priority</option>");
        toClient.println("<option>Medium Priority</option>");
        toClient.println("<option>High Priority</option>");
        toClient.println("</select>");
        toClient.println("				</div>");
        toClient.println("										</div>");
        toClient.println("");
        toClient.println("									</div>");
        toClient.println("								</div>");
        toClient.println("							</div>");
        toClient.println("");
        toClient.println("						</div>");
        toClient.println("					</div>");
        toClient.println("				</div>");
        toClient.println("");
        toClient.println("");
        toClient.println("				<!-- Button -->");
        toClient.println("				<div class='col-xl-12'>");
        toClient.println("					<button class='button ripple-effect big margin-top-30'> Create Note </button>");
        toClient.println("				</div>");
        toClient.println("</form>");
        toClient.println("			</div>");
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

        

        

        toClient.println(CaregiverUtilsCori.footerCaregiverDashboard());
		toClient.close();
		
	}
	
}