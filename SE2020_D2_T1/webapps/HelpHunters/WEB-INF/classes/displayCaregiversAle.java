import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class displayCaregiversAle extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config); 
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
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
		toClient.println(clientUtilsAle.header(login));
		toClient.println("<div class='margin-top-90'></div>");
		toClient.println("<div class='container'>");
		toClient.println("<div class='row'>");
	//	toClient.println("<div class='col-xl-3 col-lg-4'>");
	//	toClient.println("<div class='sidebar-container'>");
	//	toClient.println("<div class='sidebar-widget'>");
	//	toClient.println("<h3> <strong>"+province+"</strong> </h3>");
	//	toClient.println("</div>");
	//	toClient.println("<div class='sidebar-widget'>");
	//	toClient.println("<h3>Salary (Per hour)</h3>");
	//	toClient.println("<div class='margin-top-55'></div>");
	//	toClient.println("<input class='range-slider' type='text' value='' data-slider-currency='$' data-slider-min='5' data-slider-max='100' data-slider-step='5' data-slider-value='[5,100]'/>");
	//	toClient.println("</div>");
	//	toClient.println("</div>");
	//	toClient.println("</div>");
		toClient.println("<div class='col-xl-9 col-lg-8 content-left-offset'>");
		toClient.println("<h2 class='page-title'> Caregivers in "+province+"</h2>");
		toClient.println("<div class='notify-box margin-top-15'>");
	//	toClient.println("<div class='switch-container'>");
	//	toClient.println("</div>");
	//	toClient.println("<div class='sort-by'>");
	//	toClient.println("<span>Sort by:</span>");
	//	toClient.println("<select class='selectpicker hide-tick'>");
	//	toClient.println("<option>Rating</option>");
	//	toClient.println("<option>Newest</option>");
	//	toClient.println("<option>Oldest</option>");
	//	toClient.println("<option>Random</option>");
	//	toClient.println("</select>");
	//	toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div id = 'listgoeshere' class='listings-container compact-list-layout margin-top-35'>");
		toClient.println("<script> caregiverDataItem=[");
		
        Vector<caregiverDataAle> caregiverList;
        caregiverList = caregiverDataAle.getCaregiverProvinceList(connection, province);
		
        for(int i=0; i< caregiverList.size(); i++){
			System.out.println("loop: " +i);
            caregiverDataAle caregiver = caregiverList.elementAt(i);
			if (i!=0) {
                toClient.print(",");
            }
			toClient.print("{");
			toClient.print("\"province\":\"" + caregiver.province + "\"");
            toClient.print(",\"firstname\":\"" + caregiver.firstname + "\"");
            toClient.print(",\"lastname\":\"" + caregiver.lastname + "\"");
            toClient.print(",\"caregiverID\":\"" + caregiver.caregiverID + "\"");
            toClient.print(",\"hourlyrate\":\"" + caregiver.hourlyrate + "\"");
            toClient.print(",\"description\":\"" + caregiver.description + "\"");
            toClient.print("}");
				
        }
		
		toClient.println("]</script>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<script src='displayCaregiversAle.js'></script>");
        toClient.println(clientUtilsAle.footer(login));
        toClient.close();
    }
}