import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminNoticeInfo extends HttpServlet {
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

        int noticeID = Integer.parseInt(req.getParameter("noticeID"));

        adminNoticeData notice = adminNoticeData.getadminNoticeInfo(connection, noticeID);

        toClient.println(adminUtils.header("Notice " + noticeID, notice.subject,administratorID,firstname, lastname, email, password));
        toClient.println("<script>noticeData=[");
              toClient.print("{");
              toClient.print("\"noticeID\":\"" + notice.noticeID + "\"");
              toClient.print(",\"clientID\":\"" + notice.clientID + "\"");
              toClient.print(",\"subject\":\"" + notice.subject + "\"");
              toClient.print(",\"message\":\"" + notice.message + "\"");
              toClient.print(",\"reply\":\"" + notice.reply + "\"");
              toClient.print(",\"datecreated\":\"" + notice.datecreated + "\"");
              //toClient.print(",\"replyhasbeenread\":\"" + notice.replyhasbeenread + "\"");
              toClient.print("}");
        toClient.println("]</script>");
        toClient.println("<script src='js/adminCreateNoticeInfo.js'></script>");
        toClient.println("<script src='js/adminNoticeInfoAjax.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();



	}
}
