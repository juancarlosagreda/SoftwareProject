import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminJobAnalytics extends HttpServlet {
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

        toClient.println(adminUtils.header("Job Analytics","Analytics", administratorID,firstname, lastname, email, password));


                Vector<adminJobAnalyticsData> job = adminJobAnalyticsData.gethoursperwork(connection);
                //First we include the google API dependencies..
                toClient.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
                //Then we start our JS to draw the table, and we will link it in the PAGE's JS with a division! (id='chart_div')
                toClient.println("<script type='text/javascript'>"); //We open the JS tag...
                    //More dependencies
                    toClient.println("google.charts.load('current', {'packages':['corechart']});");
                    toClient.println("google.charts.setOnLoadCallback(drawChart);");

                      //We start the function
                      toClient.println("function drawChart() {");
                        //Define the variable that will go to the table
                        toClient.println("var data = google.visualization.arrayToDataTable([");
                        //The first parameters will be the title of each axis
                          toClient.println("['caregiverID', 'Hours per week'],");
                          //Now we put in our arrayToDataTable

                for(int i=0; i < job.size(); i++){
                    adminJobAnalyticsData hoursweek = job.elementAt(i);
                if (i!= job.size()) {
                  toClient.println("['job:"+hoursweek.employmentID+"',"+hoursweek.hoursperweek+"],");
                }else {
                  toClient.println("['job:"+hoursweek.employmentID+"',"+hoursweek.hoursperweek+"],");

                }
                }
                toClient.println("]);");

                toClient.println("var options = {");
                toClient.println("title: 'Hours/Works',");
                toClient.println("hAxis: {title: 'Hours per Week', minValue: 0, maxValue: 15},");
                toClient.println("vAxis: {title: 'Works', minValue: 0, maxValue: 10},");
                toClient.println("legend: 'none'");
                toClient.println("};");

                //Now we define the chart object
                toClient.println("var chart = new google.visualization.Histogram(document.getElementById('bar'));");

                //Now we call the draw function from google API
                toClient.println("chart.draw(data, options);");

                toClient.print("}");

              toClient.println("</script>");

              toClient.println("<script type='text/javascript'>");
              toClient.println("google.charts.load('current', {'packages':['corechart']});");
              toClient.println("google.charts.setOnLoadCallback(drawPIE);"); //Note that we call a different function
              toClient.println("function drawPIE() {"); //And call the new function here
              toClient.println("var data2 = google.visualization.arrayToDataTable([");
              toClient.println("['Status', 'Number of jobs'],");

              Vector<adminJobAnalyticsData> job2 = adminJobAnalyticsData.statusofjobs(connection);

              for(int i=0; i < job2.size(); i++){
                  adminJobAnalyticsData stat = job2.elementAt(i);
              if (i!= job2.size()) {
                toClient.println("['Status "+stat.status+"',"+stat.jobs+"],");
              }else {
                toClient.println("['Status"+stat.status+"',"+stat.jobs+"],");

              }
              }
              toClient.println("]);");
              toClient.println("var options = {");
              toClient.println("title: 'Status/Works',");
            //  toClient.println("legend: 'none'");
              toClient.println("};");

              //Now we define the chart object
              toClient.println("var chart = new google.visualization.PieChart(document.getElementById('pie'));");

              //Now we call the draw function from google API
              toClient.println("chart.draw(data2, options);");

              toClient.print("}");

              toClient.println("</script>");



              toClient.println("<script src='js/adminCreateJobAnalytics2.js'></script>");
              toClient.println(adminUtils.footer());
              toClient.close();



              }

              }
