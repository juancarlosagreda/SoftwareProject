import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminWarning extends HttpServlet {
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




        toClient.println(adminUtils.header("Warnings","Warnings", administratorID,firstname, lastname, email, password));
        toClient.println("<script>warningsData=[");
        Vector<adminWarningData> warningList = adminWarningData.getadminWarningData(connection);
        for(int i=0; i < warningList.size(); i++){
            adminWarningData warning = warningList.elementAt(i);
            if (i!=0) {
                toClient.print(",");
            }

            toClient.print("{");
            toClient.print("\"warningID\":\"" + warning.warningID + "\"");
            toClient.print(",\"noticeID\":\"" + warning.noticeID + "\"");
            toClient.print(",\"subject\":\"" + warning.subject + "\"");
            toClient.print(",\"message\":\"" + warning.message + "\"");
            toClient.print(",\"datecreated\":\"" + warning.datecreated + "\"");
            toClient.print(",\"hasbeenread\":\"" + warning.hasbeenread + "\"");


            toClient.print("}");
        }
        toClient.println("]</script>");
        toClient.println("<script src='js/admiCreateWarningList.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



	}
}
