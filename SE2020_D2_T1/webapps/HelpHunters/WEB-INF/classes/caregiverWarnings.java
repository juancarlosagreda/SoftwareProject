import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class caregiverWarnings extends HttpServlet {
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
		toClient.println("<h3>Warnings</h3>");
		toClient.println("<nav id='breadcrumbs' class='dark'>");
		toClient.println("<ul>");
		toClient.println("<li><a href='INICIOMoni.html'>Home</a></li>");
		toClient.println("<li><a href='#'>Dashboard</a></li>");
		toClient.println("<li>Warnings</li>");
		toClient.println("</ul>");
		toClient.println("</nav>");
		toClient.println("</div>");
		
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-xl-12'>");
		toClient.println("<div class='dashboard-box margin-top-0'>");
		toClient.println("<div class='headline'>");
		toClient.println("<h3><i class='icon-material-outline-assignment'></i> Warnings </h3>");
		toClient.println("</div>");
		
		toClient.println("<div class='content'>");
		toClient.println("<ul class='dashboard-box-list' id='warningList'>");
		
		toClient.println("<script>warnings=[");
        Vector<warningDataMoni> caregiverWarnings;
		caregiverWarnings = warningDataMoni.getCaregiverWarnings(connection, id);

        for(int i=0; i< caregiverWarnings.size(); i++){
				warningDataMoni warning = caregiverWarnings.elementAt(i);
				if (i!=0) {
					toClient.print(",");
				}
				
				toClient.print("{");

				toClient.print("\"subject\":\"" + warning.subject + "\"");
				toClient.print(",\"message\":\"" + warning.message + "\"");
				toClient.print(",\"datecreated\":\"" + warning.datecreated + "\"");
				toClient.print(",\"noticeID\":\"" + warning.noticeID + "\"");
				toClient.print(",\"hasbeenread\":\"" + warning.hasbeenread + "\"");
				toClient.print("}");

				/*toClient.println("<li>");
				toClient.println("<div class='invoice-list-item'>");
				toClient.println("<strong>" + warning.subject + "</strong>");
				toClient.println("<ul>");
				toClient.println("<li>" + warning.message + "</li>");
				toClient.println("<li>" + warning.datecreated + "</li>");
				toClient.println("<div id='hide'>");
				toClient.println("<form name='profile' action='seen' method='GET' id='form'>");
				toClient.println("<input type='hidden' id='seen' name='seen' value='1'>");
				toClient.println("<input type='hidden' id='noticeID' name='noticeID' value='" + warning.noticeID + "'>");
				toClient.println("<input type='submit' id='button1' class='button dark ripple-effect button-sliding-icon' value='Seen' onclick='HideForm()'>");
				toClient.println("</form>");
				toClient.println("</div>");
				toClient.println("</ul>");
				toClient.println("</div>");
				toClient.println("</li>");*/

		}
		toClient.println("]</script>");
        toClient.println("<script src='createWarningList.js'></script>");


		toClient.println("</ul>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");

        toClient.println(CaregiverUtilsMoni.footer("Products"));
		toClient.println("</div>");
		toClient.println(CaregiverUtilsMoni.script(login));


        toClient.close();
		
    }
}