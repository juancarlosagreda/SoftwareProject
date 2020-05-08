import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class getEmploymentID extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        String noticestr = req.getParameter("notID");
        int noticeID = Integer.parseInt(noticestr);
      String employmentJson = req.getParameter("json");

              if (employmentJson != null) {

            adminIncidentData employment = adminIncidentData.getEmployment(connection, noticeID);
            toClient.print("{");
            toClient.print("\"caregiverID\":\"" + employment.caregiverID + "\"");
            toClient.print(",\"employmentID\":\"" + employment.employmentID + "\"");
            toClient.print("}");
          } else {
                      String empID = adminIncidentData.getempID(connection, noticeID);
                      toClient.println(empID);
                  }
        toClient.close();
    }
}
