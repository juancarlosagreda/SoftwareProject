import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class adminNotice extends HttpServlet {
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

        toClient.println(adminUtils.header("Notices","Notices",administratorID,firstname, lastname, email, password));
        toClient.println("<script>noticesData=[");
        Vector<adminNoticeData> noticeList = adminNoticeData.getadminNoticeData(connection);
            for(int i = 0; i < noticeList.size(); i++){
              adminNoticeData notice = noticeList.elementAt(i);
              if (i!=0) {
                  toClient.print(",");
              }
              toClient.print("{");
              toClient.print("\"noticeID\":\"" + notice.noticeID + "\"");
              toClient.print(",\"clientID\":\"" + notice.clientID + "\"");
              toClient.print(",\"subject\":\"" + notice.subject + "\"");
              toClient.print(",\"message\":\"" + notice.message + "\"");
              toClient.print(",\"reply\":\"" + notice.reply + "\"");
              toClient.print(",\"datecreated\":\"" + notice.datecreated + "\"");
              //toClient.print(",\"replyhasbeenread\":\"" + notice.replyhasbeenread + "\"");
              toClient.print("}");
          }
        toClient.println("]</script>");
        toClient.println("<script src='js/adminCreateNoticeList.js'></script>");
        toClient.println("<script src='js/adminNoticeListAjax.js'></script>");
        toClient.println(adminUtils.footer());
        toClient.close();
	}
}
