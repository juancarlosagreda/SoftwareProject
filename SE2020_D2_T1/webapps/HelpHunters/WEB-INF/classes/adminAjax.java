import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminAjax extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        //We have to request the session when we login
        // HttpSession session = req.getSession(true);
        // int administratorID = Integer.parseInt(session.getAttribute("id").toString());
        // String firstname = session.getAttribute("fname").toString();
        // String lastname = session.getAttribute("lname").toString();
        // String email = session.getAttribute("usr").toString();
        // String password = session.getAttribute("pw").toString();

        //For the AJAX to work we need a request handler.. we choose to get the 'sex' parameter
        String gender = req.getParameter("sex");




              //This is where the AJAX response will start
              //We need to make cases for each string response (convert to int first)

              //if(gender.equals("Male"))

              if(gender.equals("Male")){
                //toClient.println("clientsData=[");
                Vector<adminClientData> clientList = adminClientData.getadminClientDataByGender(connection,2);
                toClient.print("[");
                for(int i=0; i < clientList.size(); i++){
                    adminClientData client = clientList.elementAt(i);
                    if (i!=0) {
                        toClient.print(",");
                    }

                    //We assign the status based on our pre-established parameters
                    String stat;
                    String color;
                    if (client.status == 4 || client.status == 2){
                        stat = "Unavailable";
                        color = "red";
                    } else {
                        stat = "Available";
                        color = "green";
                    }

                    toClient.print("{");
                    toClient.print("\"clientID\":\"" + client.clientID + "\"");
                    toClient.print(",\"firstname\":\"" + client.firstname + "\"");
                    toClient.print(",\"lastname\":\"" + client.lastname + "\"");
                    toClient.print(",\"email\":\"" + client.email + "\"");
                    toClient.print(",\"gender\":\"" + client.gender + "\"");
                    toClient.print(",\"status\":\"" + stat + "\"");
                    toClient.print(",\"color\":\"" + color + "\"");
                    toClient.print("}");
                }
                toClient.println("]");
              } else if (gender.equals("Female")) {
                toClient.println("[");

                Vector<adminClientData> clientList = adminClientData.getadminClientDataByGender(connection,1);
                for(int i=0; i < clientList.size(); i++){
                    adminClientData client = clientList.elementAt(i);
                    if (i!=0) {
                        toClient.print(",");
                    }

                    //We assign the status based on our pre-established parameters
                    String stat;
                    String color;
                    if (client.status == 4 || client.status == 2){
                        stat = "Unavailable";
                        color = "red";
                    } else {
                        stat = "Available";
                        color = "green";
                    }

                    toClient.print("{");
                    toClient.print("\"clientID\":\"" + client.clientID + "\"");
                    toClient.print(",\"firstname\":\"" + client.firstname + "\"");
                    toClient.print(",\"lastname\":\"" + client.lastname + "\"");
                    toClient.print(",\"email\":\"" + client.email + "\"");
                    toClient.print(",\"gender\":\"" + client.gender + "\"");
                    toClient.print(",\"status\":\"" + stat + "\"");
                    toClient.print(",\"color\":\"" + color + "\"");
                    toClient.print("}");
                }
                toClient.println("]");
              } else {
                toClient.println("[");
                Vector<adminClientData> clientList = adminClientData.getadminClientData(connection);
                for(int i=0; i < clientList.size(); i++){
                    adminClientData client = clientList.elementAt(i);
                    if (i!=0) {
                        toClient.print(",");
                    }

                    //We assign the status based on our pre-established parameters
                    String stat;
                    String color;
                    if (client.status == 4 || client.status == 2){
                        stat = "Unavailable";
                        color = "red";
                    } else {
                        stat = "Available";
                        color = "green";
                    }

                    toClient.print("{");
                    toClient.print("\"clientID\":\"" + client.clientID + "\"");
                    toClient.print(",\"firstname\":\"" + client.firstname + "\"");
                    toClient.print(",\"lastname\":\"" + client.lastname + "\"");
                    toClient.print(",\"email\":\"" + client.email + "\"");
                    toClient.print(",\"gender\":\"" + client.gender + "\"");
                    toClient.print(",\"status\":\"" + stat + "\"");
                    toClient.print(",\"color\":\"" + color + "\"");
                    toClient.print("}");
                }
                toClient.println("]");
              }

            }
	}
