import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CaregiverNoteEdit extends HttpServlet {
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
		
        String noteID = req.getParameter("noteID");
        NotesData note = NotesData.getNote(connection,noteID);
        
        String caregiverID = login;
       
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

        
        toClient.println("<form method='GET' action='CaregiverNotesUpdate'>");
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
       
        toClient.println("												<input type='hidden' class='with-border' name='noteID' value='" + note.noteID + "'>");

        toClient.println("");
        toClient.println("										<div class='col-xl-6'>");
        toClient.println("											<div class='submit-field'>");
        toClient.println("												<h5>Update your note</h5>");
        toClient.println("												<input type='text' class='with-border' name='body' id='body' value='" + note.body + "'>");
        toClient.println("											</div>");
        toClient.println("										</div>");
        toClient.println("");
        toClient.println("										<div class='col-xl-6'>");
        toClient.println("											<div class='submit-field'>");
        toClient.println("												<h5>Select the date</h5>");
        toClient.println("												<input type='date' class='with-border' name='datecreated' id='datecreated' value='" + note.datecreated + "'>");
        toClient.println("											</div>");
        toClient.println("										</div>");
        toClient.println("");
        toClient.println("");
        toClient.println("										<div class='col-xl-6'>");
        toClient.println("											<div class='submit-field'>");
        toClient.println("												<h5>Update Status</h5>");
        toClient.println("												<select name='status' id='status' class='selectpicker with-border' data-size='7' title='Select Status Type' data-live-search='true' value = '"+ note.status +"'>");
        
        switch (note.status) {
              case "High Priority":
                toClient.println("<option>Low Priority</option>");
                toClient.println("<option>Medium Priority</option>");
                toClient.println("<option selected>High Priority</option>");
                break;
              case "Medium Priority":
                toClient.println("<option>Low Priority</option>");
                toClient.println("<option selected>Medium Priority</option>");
                toClient.println("<option>High Priority</option>");
                break;
              default:
                toClient.println("<option selected>Low Priority</option>");
                toClient.println("<option>Medium Priority</option>");
                toClient.println("<option>High Priority</option>");
            }   
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
        toClient.println("					<button class='button ripple-effect big margin-top-30'>Save Changes</button>");
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
    
    /*public void doGet(PrintWriter toClient, Connection connection, String noteID) {
        
        //NotesData note = NotesData(connection, caregiverID);  
        
        toClient.println("<form method='get' action='CaregiverNotesUpdate'>");
        toClient.println("<input type='hidden' name='caregiverID' value='" + caregiverID + "'>");
        toClient.println("<table border='1'>");   
        toClient.println("<tr>");
        toClient.println("<th>Body</th>");
        toClient.println("<th>Date Created</th>");
        toClient.println("<th>Status</th>");
        toClient.println("</tr>");
        toClient.println("<body>");
        toClient.println("<tr>");
        toClient.println("<td> <input name='body' value='" + note.body + "'></td>");
        toClient.println("<td> <input name='datecreated' value='" + note.datecreated + "'></td>");  
        toClient.println("<td> <input name='status' value='" + note.status + "'></td>");  
        toClient.println("</tr>");
        toClient.println("</body>");
        toClient.println("</table>");
        toClient.println("<div style='text-align:right;'> <butto type='submit'> Save changes </button></div>");
        toClient.println("</form>");

    }
*/    
}