import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminIncidenceAnalytics extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        //We have to request the session when we login
        HttpSession session = req.getSession(true);
        int administratorID = Integer.parseInt(session.getAttribute("id").toString());
        String firstname = session.getAttribute("fname").toString();
        String lastname = session.getAttribute("lname").toString();
        String email = session.getAttribute("usr").toString();
        String password = session.getAttribute("pw").toString();
        toClient.println(adminUtils.header("Incidences Analytics","Analytics", administratorID,firstname, lastname, email, password));





        toClient.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");

        toClient.println("<script type='text/javascript'>");
        toClient.println("google.charts.load('current', {'packages':['corechart']});");
        toClient.println("google.charts.setOnLoadCallback(drawPIE);"); //Note that we call a different function
        toClient.println("function drawPIE() {"); //And call the new function here
        toClient.println("var data2 = google.visualization.arrayToDataTable([");
        toClient.println("['Classification', 'Number of Notices'],");

        Vector<adminIncidenceAnalyticsData> noticesperc = adminIncidenceAnalyticsData.percentageofincidences(connection);

        for(int i=0; i < noticesperc.size(); i++){
            adminIncidenceAnalyticsData not = noticesperc.elementAt(i);
        if (i!= noticesperc.size()) {
          toClient.println("['Classification "+not.classification+"',"+not.notices+"],");
        }else {
          toClient.println("['Classification"+not.classification+"',"+not.notices+"],");

        }
        }
        toClient.println("]);");
        toClient.println("var options = {");
        toClient.println("title: 'Type of notices'");
      //  toClient.println("legend: 'none'");
        toClient.println("};");

        //Now we define the chart object
        toClient.println("var chart = new google.visualization.PieChart(document.getElementById('pie'));");

        //Now we call the draw function from google API
        toClient.println("chart.draw(data2, options);");

        toClient.print("}");

        toClient.println("</script>");



        toClient.println("<script src='js/adminCreateIncidenceAnalytics.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



      }
      }
