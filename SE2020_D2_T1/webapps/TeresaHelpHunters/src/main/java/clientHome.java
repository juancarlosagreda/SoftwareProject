import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class clientHome extends HttpServlet {
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
		//String client = req.getParameter("login");
		HttpSession session = req.getSession(false);
		String login = null;
		int loginid = 0;
		if (session != null) {
			login = (String)session.getAttribute("login");
			loginid = (int)session.getAttribute("loginid");
			session.setAttribute("login", login);
			session.setAttribute("loginid", loginid);
			System.out.println("clientHome logged");
			System.out.println("clientHome login: " + login);
			System.out.println("clientHome loginid: " + loginid);
		}
		toClient.println(clientUtils.header(login));
		toClient.println("<div class='intro-banner dark-overlay big-padding'>");
		toClient.println("<div class='transparent-header-spacer'></div>");
		toClient.println("<div class='container'>");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-md-12'>");
		toClient.println("<div class='banner-headline-alt'>");
		toClient.println("<h3>Welcome back, " +login+"</h3>");
		toClient.println("<span>Select a province and find someone who will care for you or your loved ones</span>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("<form name='province' id = 'province' action = 'displayCaregivers' >");
		toClient.println("<div class='row'>");
		toClient.println("<div class='col-md-12'>");
		toClient.println("<div class='intro-banner-search-form margin-top-95'>");
		toClient.println("<div class='intro-search-field'>");
		toClient.println("<label for ='intro-keywords' class='field-title ripple-effect'>Where?</label>");
		toClient.println("<select class='selectpicker' name = 'province' action = 'displayCaregivers' >");
		toClient.println("<option value = 'Araba/Alava' >Araba/Alava</option>");
		toClient.println("<option value = 'Albacete' >Albacete</option>");
		toClient.println("<option value 'Alicante/Alacant' >Alicante/Alacant</option>");
		toClient.println("<option value = 'Almeria' >Almeria</option>");
		toClient.println("<option value = 'Avila' >Avila</option>");
		toClient.println("<option value = 'Badajoz' >Badajoz</option>");
		toClient.println("<option value = 'Balears, Illes' >Balears, Illes</option>");
		toClient.println("<option value = 'Barcelona' >Barcelona</option>");
		toClient.println("<option value = 'Burgos' >Burgos</option>");
		toClient.println("<option value = 'Caceres' >Caceres</option>");
		toClient.println("<option value = 'Cadiz' >Cadiz</option>");
		toClient.println("<option value = Castellon/Castello>Castellon/Castello</option>");
		toClient.println("<option value = 'Ciudad Real' >Ciudad Real</option>");
		toClient.println("<option value = 'Cordoba' >Cordoba</option>");
		toClient.println("<option value = ' Coruna, A '>Coruna, A</option>");
		toClient.println("<option value = 'Cuenca' >Cuenca</option>");
		toClient.println("<option value = 'Girona' >Girona</option>");
		toClient.println("<option value = 'Granada' >Granada</option>");
		toClient.println("<option value = 'Guadalajara' >Guadalajara</option>");
		toClient.println("<option value = 'Gipuzkoa' >Gipuzkoa</option>");
		toClient.println("<option value = 'Huelva' >Huelva</option>");
		toClient.println("<option value = 'Huesca' >Huesca</option>");
		toClient.println("<option value = 'Jaen' >Jaen</option>");
		toClient.println("<option value = 'Leon' >Leon</option>");
		toClient.println("<option value = 'Lleida' >Lleida</option>");
		toClient.println("<option value = 'Rioja, La' >Rioja, La</option>");
		toClient.println("<option value = 'Lugo' >Lugo</option>");
		toClient.println("<option value = 'Madrid' >Madrid</option>");
		toClient.println("<option value = 'Malaga' >Malaga</option>");
		toClient.println("<option value = 'Murcia' >Murcia</option>");
		toClient.println("<option value = 'Navarra' >Navarra</option>");
		toClient.println("<option value = 'Ourense' >Ourense</option>");
		toClient.println("<option value = '>Asturias</option>");
		toClient.println("<option value = 'Palencia' >Palencia</option>");
		toClient.println("<option value = 'Palmas, Las' >Palmas, Las</option>");
		toClient.println("<option value = 'Pontevedra' >Pontevedra</option>");
		toClient.println("<option value = 'Salamanca' >Salamanca</option>");
		toClient.println("<option value = 'Santa Cruz de Tenerife ' >Santa Cruz de Tenerife</option>");
		toClient.println("<option value = 'Cantabria' >Cantabria</option>");
		toClient.println("<option value = 'Segovia' >Segovia</option>");
		toClient.println("<option value = 'Sevilla' >Sevilla</option>");
		toClient.println("<option value = 'Soria' >Soria</option>");
		toClient.println("<option value = 'Tarragona' >Tarragona</option>");
		toClient.println("<option value = 'Teruel' >Teruel</option>");
		toClient.println("<option value = 'Toledo' >Toledo</option>");
		toClient.println("<option value = 'Valencia/Valencia' >Valencia/Valencia</option>");
		toClient.println("<option value = 'Valladolid' >Valladolid</option>");
		toClient.println("<option value = 'Bizkaia' >Bizkaia</option>");
		toClient.println("<option value = 'Zamora' >Zamora</option>");
		toClient.println("<option value = 'Zaragoza' >Zaragoza</option>");
		toClient.println("<option value = 'Ceuta' >Ceuta</option>");
		toClient.println("<option value = 'Melilla' >Melilla</option>");
		toClient.println("</select>");
		toClient.println("</div>");
		toClient.println("<div class='intro-search-button'>");
		toClient.println("<button class='button ripple-effect' form='province' value = 'Submit' >Search</button>");
		toClient.println("</form>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println("</div>");
		toClient.println(clientUtils.footer(login));
		toClient.close();
	}
}