import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminCaregiverAnalytics extends HttpServlet {
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

        toClient.println(adminUtils.header("Caregiver Analytics","Caregiver Analytics",administratorID,firstname, lastname, email, password));

        Vector<adminCaregiverAnalyticsData> firstGraphInfo = adminCaregiverAnalyticsData.getHourlyRates(connection);

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
                      adminCaregiverAnalyticsData info = firstGraphInfo.elementAt(i);
                      if(i != firstGraphInfo.size()){
                        toClient.println("["+info.caregiverID+","+info.hourlyrate+"],");
                      } else {
                        toClient.println("["+info.caregiverID+","+info.hourlyrate+"]");
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
        toClient.println("['Number of Caregeivers', 'Hourly Rate'],");
        Vector<adminCaregiverAnalyticsData> secondGraphInfo = adminCaregiverAnalyticsData.getGenderCounts(connection);
        for(int j = 0; j < secondGraphInfo.size(); j++){
          adminCaregiverAnalyticsData info2 = secondGraphInfo.elementAt(j);

          String gen = "";
          if(info2.hourlyrate == 1){
            gen = "Female";
          } else {
            gen = "Male";
          }

          if(j != firstGraphInfo.size()){
            toClient.println("['" + gen + "'," + info2.caregiverID + "],");
          } else {
            toClient.println("['" + gen + "'," + info2.caregiverID + "]");
          }

        }
        toClient.println("]);");
        //We are done with the data we wanted to pass

        //Now the specifications for the graph
        toClient.println("var options = {");
        toClient.println("title: 'Number of Caregivers By Gender'");
        toClient.println("};");

        //Now we define the chart object.. note that it has a NEW ID
        toClient.println("var chart = new google.visualization.PieChart(document.getElementById('chart_div2'));");

        //Now we call the draw function from google API
        toClient.println("chart.draw(data2, options);");

        //We close the function
        toClient.println("}");
        toClient.println("</script>");

        toClient.println("<script src='js/adminCreateCaregiverAnalytics.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();

	}
}
