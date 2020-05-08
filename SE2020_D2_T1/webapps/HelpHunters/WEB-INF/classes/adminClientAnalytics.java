import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminClientAnalytics extends HttpServlet {
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

        toClient.println(adminUtils.header("Client Analytics","Client Analytics",administratorID,firstname, lastname, email, password));

        Vector<adminClientAnalyticsData> firstGraphInfo = adminClientAnalyticsData.getClientHourlyRates(connection);
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
                  toClient.println("['Amount of Caregivers', 'Hourly Rate'],");
                  //Now we put in our arrayToDataTable
                  //Remember that there will be no JS read unless we put it in toClient.println
                  for(int i = 0; i < firstGraphInfo.size(); i++){
                      adminClientAnalyticsData info = firstGraphInfo.elementAt(i);
                      if(i != firstGraphInfo.size()){
                        toClient.println("["+info.param+","+info.count+"],");
                      } else {
                        toClient.println("["+info.param+","+info.count+"]");
                      }
                  }
                  toClient.println("]);");
                  //We are done with the data we wanted to pass

                  //Now the specifications for the graph
                  toClient.println("var options = {");
                  toClient.println("title: 'Caregivers vs Hourly Rates',");
                  toClient.println("hAxis: {title: 'Caregiver Count', minValue: 0, maxValue: 5},");
                  toClient.println("vAxis: {title: 'Hourly Rate', minValue: 0, maxValue: 5},");
                  toClient.println("legend: 'none'");
                  toClient.println("};");

                  //Now we define the chart object
                  toClient.println("var chart = new google.visualization.Histogram(document.getElementById('chart_div'));");

                  //Now we call the draw function from google API
                  toClient.println("chart.draw(data, options);");

                  //We close the function
                  toClient.println("}");
        toClient.println("</script>"); //Here we close the script tag


        //SECOND CHART
        toClient.println("<script type='text/javascript'>");
        toClient.println("google.charts.load('current', {'packages':['corechart']});");
        toClient.println("google.charts.setOnLoadCallback(drawChart2);"); //Note that we call a different function
        toClient.println("function drawChart2() {"); //And call the new function here
        toClient.println("var data2 = google.visualization.arrayToDataTable([");
        toClient.println("['Number of Clients', 'Hourly Rate'],");
        Vector<adminClientAnalyticsData> secondGraphInfo = adminClientAnalyticsData.getGenderCounts(connection);
        for(int j = 0; j < secondGraphInfo.size(); j++){
          adminClientAnalyticsData info2 = secondGraphInfo.elementAt(j);

          String gen = "";
          if(info2.count == 1){
            gen = "Female";
          } else {
            gen = "Male";
          }

          if(j != firstGraphInfo.size()){
            toClient.println("['" + gen + "'," + info2.param + "],");
          } else {
            toClient.println("['" + gen + "'," + info2.param + "]");
          }

        }
        toClient.println("]);");
        //We are done with the data we wanted to pass

        //Now the specifications for the graph
        toClient.println("var options = {");
        toClient.println("title: 'Number of Clients By Gender'");
        toClient.println("};");

        //Now we define the chart object.. note that it has a NEW ID
        toClient.println("var chart = new google.visualization.PieChart(document.getElementById('chart_div2'));");

        //Now we call the draw function from google API
        toClient.println("chart.draw(data2, options);");

        //We close the function
        toClient.println("}");
        toClient.println("</script>");

        toClient.println("<script src='js/adminCreateClientAnalytics.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



	}
}
