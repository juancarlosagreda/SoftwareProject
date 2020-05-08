import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class displayCaregivers extends HttpServlet {
    Connection connection;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");

		String stringCon = "jdbc:mysql://34.76.88.212/Caregivers?user=equipo&password=Tecnun2020";
		try {
			connection = DriverManager.getConnection(stringCon);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in establishing connection: " + e);
		}

        PrintWriter toClient = res.getWriter();
        //String cliente = req.getParameter("login");
        HttpSession session = req.getSession(false);
		String province = req.getParameter("province");
        String login = null;
		int loginid = 0;
        if (session != null) {
            login = (String)session.getAttribute("login");
			loginid = (int)session.getAttribute("loginid");
			session.setAttribute("login", login);
			session.setAttribute("loginid", loginid);
            System.out.println("displayCaregivers logged");
            System.out.println("displayCaregivers login: " + login);
			System.out.println("displayCaregivers loginid: " + loginid);
        }
		toClient.println(clientUtils.header(login));
		toClient.println("<div class='margin-top-90'></div>");
		toClient.println("<div class='container'>");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-xl-3 col-lg-4'>");
		toClient.println("<div class='sidebar-container'>");
		toClient.println("<div class='sidebar-widget'>");
		toClient.println("<h3> <strong>"+province+"</strong> </h3>");
		toClient.println("</div>");
		toClient.println("<div class='sidebar-widget'>");
		toClient.println("<h3>Salary (Per hour)</h3>");
		toClient.println("<div class='margin-top-55'></div>");
		toClient.println("<input class='range-slider' type='text' value='' data-slider-currency='$' data-slider-min='5' data-slider-max='100' data-slider-step='5' data-slider-value='[5,100]'/>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='col-xl-9 col-lg-8 content-left-offset'>");
		toClient.println("<h2 class='page-title'> Caregivers in your area</h2>");
		toClient.println("<div class='notify-box margin-top-15'>");
		toClient.println("<div class='switch-container'>");
		toClient.println("</div>");
		toClient.println("<div class='sort-by'>");
		toClient.println("<span>Sort by:</span>");
		toClient.println("<select class='selectpicker hide-tick'>");
		toClient.println("<option>Rating</option>");
		toClient.println("<option>Newest</option>");
		toClient.println("<option>Oldest</option>");
		toClient.println("<option>Random</option>");
		toClient.println("</select>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='listings-container compact-list-layout margin-top-35'>");
		toClient.println("<ul>");
		
        Vector<caregiverData> caregiverList;
        caregiverList = caregiverData.getCaregiverProvinceList(connection, province);
        for(int i=0; i< caregiverList.size(); i++){
                caregiverData caregiver = caregiverList.elementAt(i);
				toClient.println("<li>");
				toClient.println("<div class='job-listing'>");
				toClient.println("<form method = 'get' name = 'caregiverform' action = 'caregiverProfile' >");
				toClient.println("<input type='hidden' id='firstname' name='firstname' value='"+ caregiver.firstname + "'>");
				toClient.println("<input type='hidden' id='lastname' name='lastname' value='"+ caregiver.lastname + "'>");
				toClient.println("<input type='hidden' id='caregiverID' name='caregiverID' value='"+ caregiver.caregiverID + "'>");
				toClient.println("<input type='hidden' id='hourlyrate' name='hourlyrate' value='"+ caregiver.hourlyrate + "'>");
				toClient.println("<input type='hidden' id='description' name='description' value='"+ caregiver.description + "'>");
				toClient.println("<div class='job-listing-details'>");
				toClient.println("<div class='job-listing-company-logo'>");
				toClient.println("<img src='images/mariacristina.jpg' alt=''>");
				toClient.println("</div>");
				toClient.println("<div class='job-listing-description'>");
				toClient.println("<h3 class='job-listing-title'> " + caregiver.firstname + " " + caregiver.lastname + "</h3>");
				toClient.println("<div class='job-listing-footer'>");
				toClient.println("<ul>");
				toClient.println("<li><i class='icon-material-outline-location-on'></i> "+province+"</li>");
				toClient.println("</ul>");
				toClient.println("</div>");
				toClient.println("</div>");
				toClient.println("<input class='button ripple-effect' type = 'submit' value = 'View Profile' </input>");
				toClient.println("</div>");
				toClient.println("</form>");
				toClient.println("</div>");
				toClient.println("</li>");
				

        }
		toClient.println("</ul>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
        toClient.println(clientUtils.footer(login));
        toClient.close();
    }
}