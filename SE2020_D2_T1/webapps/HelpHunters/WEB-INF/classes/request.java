
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

@SuppressWarnings("serial")
public class request extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        HttpSession session = req.getSession(false);

        int num = Integer.parseInt(req.getParameter("value"));
        int id = Integer.parseInt(req.getParameter("caregiverID"));

adminRequestData request = new adminRequestData (
         num, id);

int n = adminRequestData.updateCaregiver(connection, request);
if (num== 1){
res.sendRedirect("adminCaregiver");
} else {
  res.sendRedirect("adminRequest");
}



	}

}
