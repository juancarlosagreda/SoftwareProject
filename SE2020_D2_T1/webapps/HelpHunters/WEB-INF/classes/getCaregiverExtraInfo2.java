import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class getCaregiverExtraInfo2 extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        String idstr = req.getParameter("caregiverID");
        int id = Integer.parseInt(idstr);
        String provinceJson = req.getParameter("json");
       //int id = Integer.parseInt(Cid);
        if (provinceJson != null) {
              adminCaregiverData prov = adminCaregiverData.getprovince(connection, id);
          



              toClient.print("{");
              toClient.print("\"caregiverID\":\"" + prov.caregiverID + "\"");
              toClient.print(",\"provincia2\":\"" + prov.provincename + "\"");



              toClient.print("}");

            } else {

              String provin = adminCaregiverData.getprov(connection, id);
              toClient.println(provin);

            }
            toClient.close();

            }
	}
