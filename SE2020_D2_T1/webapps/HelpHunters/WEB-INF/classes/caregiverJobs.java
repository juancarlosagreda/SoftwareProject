import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class caregiverJobs extends HttpServlet {
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
		toClient.println("<li><a href='Dashboard'><i class='icon-material-outline-dashboard'></i> Dashboard</a></li>");
		toClient.println("<li><a href='CaregiverMsg'><i class='icon-material-outline-question-answer'></i> Messages </a></li>");
		toClient.println("<li><a href='NotesCaregiver'><i class='icon-material-outline-note-add'></i> Notes </a></li>");
		toClient.println("<li ><a href='caregiverReviews'><i class='icon-material-outline-rate-review'></i> Reviews</a></li>");
		toClient.println("</ul>");
		toClient.println("<ul data-submenu-title='Organize and Manage'>");
		toClient.println("<li class='active-submenu'><a href='#'><i class='icon-material-outline-business-center'></i> Jobs</a>");
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
		toClient.println("<h3>Manage Jobs</h3>");
		toClient.println("<nav id='breadcrumbs' class='dark'>");
		toClient.println("<ul>");
		toClient.println("<li><a href='INICIO.html'>Home</a></li>");
		toClient.println("<li><a href='Dashboard'>Dashboard</a></li>");
		toClient.println("<li>Manage Jobs</li>");
		toClient.println("</ul>");
		toClient.println("</nav>");
		toClient.println("</div>");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-xl-12'>");
		toClient.println("<div class='dashboard-box margin-top-0'>");
		toClient.println("<div class='headline'>");
		toClient.println("<h3><i class='icon-material-outline-business-center'></i> My Job Offers</h3>");
		toClient.println("</div>");
		toClient.println("<div class='content'>");
		toClient.println("<ul class='dashboard-box-list' id='jobList'>");

		toClient.println("<script>jobs=[");
        Vector<caregiverDataMoni> caregiverJob;
		caregiverJob = caregiverDataMoni.getCaregiverJobs(connection, id);
		
		
        for(int i=0; i< caregiverJob.size(); i++){
                caregiverDataMoni jobs = caregiverJob.elementAt(i);
				if (i!=0) {
					toClient.print(",");
				}
				
				toClient.print("{");

				toClient.print("\"firstname\":\"" + jobs.firstname + "\"");
				toClient.print(",\"lastname\":\"" + jobs.lastname + "\"");
				toClient.print(",\"clientID\":\"" + jobs.clientID + "\"");
				toClient.print(",\"employmentID\":\"" + jobs.employmentID + "\"");
				toClient.print("}");

			
				/*toClient.println("<li>");
				toClient.println("<div class='job-listing'>");
				toClient.println("<div class='job-listing-details'>");
				toClient.println("<div class='job-listing-description'>");
				toClient.println("<h3 class='job-listing-title'><a href='#'>" + jobs.firstname + " " + jobs.lastname + "</a> <span class='dashboard-status-button green'>Pending</span></h3>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("<div class='buttons-to-right always-visible'>");
				toClient.println("<form name='profile' action='viewClientProfile' method='GET'>");
				toClient.println("<input type='hidden' id='clientID' name='clientID' value='" + jobs.clientID + "'>");
				toClient.println("<input type='submit' class='button ripple-effect' value='View Profile'> ");
				toClient.println("</form>");
				toClient.println("<div class='margin-top-15'></div>");
				toClient.println("<form name='status' action='accept' method='GET'>");
				toClient.println("<input type='hidden' id='employmentID' name='employmentID' value='" + jobs.employmentID + "'>");
				toClient.println("<p><input type='submit' class='button dark ripple-effect' value='Accept'>");
				toClient.println("<input type='submit' class='button dark ripple-effect' value='Decline' formaction='decline'></p>");
				toClient.println("</form>");
				toClient.println("<div class='margin-top-15'></div>");
				toClient.println("</div>");
				toClient.println("</li>");*/

        }
		toClient.println("]</script>");
        toClient.println("<script src='js/createJobList.js'></script>");

		toClient.println("</ul>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");

        toClient.println(CaregiverUtilsMoni.footer(login));
		toClient.println("</div>");
		toClient.println(CaregiverUtilsMoni.script(login));

		/*toClient.println("<script language='javascript' type='text/javascript'>");
		toClient.println("function Client()");
		toClient.println("{");
		toClient.println("var ID = document.getElementById('clientID').value;");
		toClient.println("alert(ID);");
		toClient.println("return false;");
		toClient.println("}");
		toClient.println("<script/>");*/
        toClient.close();
    }
}