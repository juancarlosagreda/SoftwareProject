import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminClient extends HttpServlet {
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



              //Now we start to print our html page and perform our queries
              toClient.println(adminUtils.header("Clients","Clients", administratorID,firstname, lastname, email, password));
              toClient.println("<script>clientsData=[");
              Vector<adminClientData> clientList = adminClientData.getadminClientData(connection);
              for(int i=0; i < clientList.size(); i++){
                  adminClientData client = clientList.elementAt(i);
                  if (i!=0) {
                      toClient.print(",");
                  }

                  //We assign the status based on our pre-established parameters
                  String stat = "Available";
                  String color = "green";

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
              toClient.println("]</script>");
              toClient.println("<script src='js/adminCreateClientList.js'></script>");
              toClient.println("<script src='js/firstAjax.js'></script>");
              toClient.println(adminUtils.footer());
              toClient.close();


            }
	}
