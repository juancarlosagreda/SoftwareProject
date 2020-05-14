import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class caregiverSettings extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
		super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        //String caregiver = req.getParameter("login");
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
		toClient.println("<li><a href='#'><i class='icon-material-outline-business-center'></i> Jobs</a>");
		toClient.println("<ul>");
		toClient.println("<li><a href='caregiverJobs'>Manage Jobs </a></li>");
		toClient.println("<li><a href='caregiverWarnings'>Warnings </a></li>");
		toClient.println("</ul>");
		toClient.println("</li>");
		toClient.println("</ul>");
		toClient.println("<ul data-submenu-title='Account'>");
		toClient.println("<li class='active'><a href='caregiverSettings'><i class='icon-material-outline-settings'></i> Settings</a></li>");
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
		toClient.println("<h3>Settings</h3>");
		toClient.println("<nav id='breadcrumbs' class='dark'>");
		toClient.println("<ul>");
		toClient.println("<li><a href='INICIOMoni.html'>Home</a></li>");
		toClient.println("<li><a href='#'>Dashboard</a></li>");
		toClient.println("<li>Settings</li>");
		toClient.println("</ul>");
		toClient.println("</nav>");
		toClient.println("</div>");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-xl-12'>");
		toClient.println("<div class='dashboard-box margin-top-0'>");
		toClient.println("<div class='headline'>");
		toClient.println("<h3><i class='icon-material-outline-account-circle'></i> My Account </h3>");
		toClient.println("</div>");
		
		
        caregiverDataMoni caregiver = caregiverDataMoni.getCaregiver(connection, id);		
		provinceDataMoni CaregiverInProvince = provinceDataMoni.countCaregiverInProvince(connection, id);	
		
		toClient.println("<form method='get' action='caregiverUpdate' id='form1'>");
		toClient.println("<div class='content with-padding padding-bottom-0'>");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col'>");
		toClient.println("<div class='row'>");								
		toClient.println("<div class='col-xl-6'>");
		toClient.println("<div class='submit-field'>");
		toClient.println("<input type='hidden' id='caregiverCount' name='caregiverCount' value='" + CaregiverInProvince.count + "'>");
		toClient.println("<h5> First Name </h5>");
		toClient.println("<input type='text' class='with-border' value='" + caregiver.firstname + "' name='firstname' readonly>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='col-xl-6'>");
		toClient.println("<div class='submit-field'>");
		toClient.println("<h5>Last Name</h5>");
		toClient.println("<input type='text' class='with-border' value='" + caregiver.lastname + "' name='lastname' readonly>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='col-xl-6'>");
		toClient.println("<div class='submit-field'>");
		toClient.println("<h5>Status</h5>");
		toClient.println("<div class='account-type'>");
		
		if (caregiver.available == 1){
			toClient.println("<div>");
			toClient.println("<input type='radio' name='available' id='1' value='1' class='account-type-radio' checked/>");
			toClient.println("<label for='1' class='ripple-effect-dark'><i class='#'></i> Available</label>");
			toClient.println("</div>");
			toClient.println("<div>");
			toClient.println("<input type='radio' name='available' id='0' value='0' class='account-type-radio'/>");
			toClient.println("<label for='0' class='ripple-effect-dark'><i class='#'></i> Unavailable</label>");
			toClient.println("</div>");
		} else if (caregiver.available == 0){
			toClient.println("<div>");
			toClient.println("<input type='radio' name='available' id='1' value='1' class='account-type-radio'/>");
			toClient.println("<label for='1' class='ripple-effect-dark'><i class='#'></i> Available</label>");
			toClient.println("</div>");
			toClient.println("<div>");
			toClient.println("<input type='radio' name='available' id='0' value='0' class='account-type-radio' checked/>");
			toClient.println("<label for='0' class='ripple-effect-dark'><i class='#'></i> Unavailable</label>");
			toClient.println("</div>");
		}
		
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='col-xl-6'>");
		toClient.println("<div class='submit-field'>");
		toClient.println("<h5>Email</h5>");
		toClient.println("<input type='text' name='email' class='with-border' value='" + caregiver.email + "'>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='col-xl-12'>");
		toClient.println("<div class='dashboard-box'>");
		toClient.println("<div class='headline'>");
		toClient.println("<h3><i class='icon-material-outline-face'></i> My Profile</h3>");
		toClient.println("</div>");
		toClient.println("<div class='content'>");
		toClient.println("<ul class='fields-ul'>");
		toClient.println("<li>");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-xl-4'>");
		toClient.println("<div class='submit-field'>");
		toClient.println("<div class='bidding-widget'>");
		toClient.println("<span class='bidding-detail'>Set your <strong>minimal hourly rate</strong></span>");
		toClient.println("<div class='bidding-value margin-bottom-10'>$<span id='biddingVal'></span></div>");
		toClient.println("<input class='bidding-slider' name='hourlyrate' type='text' value='' data-slider-handle='custom' data-slider-currency='€' data-slider-min='5' data-slider-max='150' data-slider-value='" + caregiver.hourlyrate + "' data-slider-step='1' data-slider-tooltip='hide' />");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</li>");
		toClient.println("<li>");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-xl-6'>");
		toClient.println("<div class='submit-field'>");
		toClient.println("<h5>Province</h5>");
		toClient.println("<select name='provinceID' id='provinceID' class='selectpicker' form='form1'>");
		toClient.println("<option value='1'>Araba/Alava</option>");
		toClient.println("<option value='2'>Albacete</option>");
		toClient.println("<option value='3'>Alicante/Alacant</option>");
		toClient.println("<option value='4'>Almería</option>");
		toClient.println("<option value='5'>Avila</option>");
		toClient.println("<option value='6'>Badajoz</option>");
		toClient.println("<option value='7'>Balears, Illes</option>");
		toClient.println("<option value='8'>Barcelona</option>");
		toClient.println("<option value='9'>Burgos</option>");
		toClient.println("<option value='10'>Caceres</option>");
		toClient.println("<option value='11'>Cadiz</option>");
		toClient.println("<option value='12'>Castellon/Castello</option>");
		toClient.println("<option value='13'>Ciudad Real</option>");
		toClient.println("<option value='14'>Cordoba</option>");
		toClient.println("<option value='15'>Coruña, A</option>");
		toClient.println("<option value='16'>Cuenca</option>");
		toClient.println("<option value='17'>Girona</option>");
		toClient.println("<option value='18'>Granada</option>");
		toClient.println("<option value='19'>Guadalajara</option>");
		toClient.println("<option value='20'>Gipuzkoa</option>");
		toClient.println("<option value='21'>Huelva</option>");
		toClient.println("<option value='22'>Huesca</option>");
		toClient.println("<option value='23'>Jaen</option>");
		toClient.println("<option value='24'>Leon</option>");
		toClient.println("<option value='25'>Lleida</option>");
		toClient.println("<option value='26'>Rioja, La</option>");
		toClient.println("<option value='27'>Lugo</option>");
		toClient.println("<option value='28'>Madrid</option>");
		toClient.println("<option value='29'>Malaga</option>");
		toClient.println("<option value='30'>Murcia</option>");
		toClient.println("<option value='31'>Navarra</option>");
		toClient.println("<option value='32'>Ourense</option>");
		toClient.println("<option value='33'>Asturias</option>");
		toClient.println("<option value='34'>Palencia</option>");
		toClient.println("<option value='35'>Palmas, Las</option>");
		toClient.println("<option value='36'>Pontevedra</option>");
		toClient.println("<option value='37'>Salamanca</option>");
		toClient.println("<option value='38'>Santa Cruz de Tenerife</option>");
		toClient.println("<option value='39'>Cantabria</option>");
		toClient.println("<option value='40'>Segovia</option>");
		toClient.println("<option value='41'>Sevilla</option>");
		toClient.println("<option value='42'>Soria</option>");
		toClient.println("<option value='43'>Tarragona</option>");
		toClient.println("<option value='44'>Teruel</option>");
		toClient.println("<option value='45'>Toledo</option>");
		toClient.println("<option value='46'>Valencia</option>");
		toClient.println("<option value='47'>Valladolid</option>");
		toClient.println("<option value='48'>Bizkaia</option>");
		toClient.println("<option value='49'>Zamora</option>");
		toClient.println("<option value='50'>Zaragoza</option>");
		toClient.println("<option value='51'>Ceuta</option>");
		toClient.println("<option value='52'>Melilla</option>");
		toClient.println("</select>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='col-xl-12'>");
		toClient.println("<div class='submit-field'>");
		toClient.println("<h5>Introduce Yourself</h5>");
		toClient.println("<textarea cols='30' rows='5' class='with-border' name='description'>" + caregiver.description + "</textarea>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</li>");
		toClient.println("</ul>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='col-xl-12'>");
		toClient.println("<div id='test1' class='dashboard-box'>");
		toClient.println("<div class='headline'>");
		toClient.println("<h3><i class='icon-material-outline-lock'></i> Password & Security</h3>");
		toClient.println("</div>");
		toClient.println("<div class='content with-padding'>");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-xl-4'>");
		toClient.println("<div class='submit-field'>");
		toClient.println("<h5>Current Password</h5>");
		toClient.println("<input type='password' class='with-border'>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<div class='col-xl-4'>");
		toClient.println("<div class='submit-field'>");
		toClient.println("<h5>New Password</h5>");
		toClient.println("<input type='password' name='newpassword' class='with-border'>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");

		toClient.println("<div class='col-xl-12'>");
		toClient.println("<input type='submit' class='button ripple-effect big margin-top-30' value='Save Changes' onclick='Message()'>");
		toClient.println("</div>");
		toClient.println("</div>");
        toClient.println("</form>");
		
        toClient.println(CaregiverUtilsMoni.footer(login));
		toClient.println(CaregiverUtilsMoni.script(login));

		toClient.println("<script>");
		toClient.println("function Message() {");
		toClient.println("alert('Your changes have been saved');");
		toClient.println("}");
		toClient.println("</script>");
		
		
        toClient.close();
    }
}
