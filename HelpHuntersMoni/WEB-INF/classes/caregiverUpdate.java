import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class caregiverUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsMoni.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        //String caregiver = req.getParameter("login");
        HttpSession session = req.getSession(false);
		String login = null;
        int id = 0;
		String password = null;
        if (session != null) {
			login = (String)session.getAttribute("login");
            id = (int)session.getAttribute("id");
			password = (String)session.getAttribute("password");
            session.setAttribute("id", id);
            session.setAttribute("password", password);
            System.out.println("caregiver logged");
			System.out.println("caregivername login: " + login);
            System.out.println("caregiverid login: " + id);
            System.out.println("caregiverid password: " + password);
        }

        
        String newpassword=null;      

        if (password == req.getParameter(password) && req.getParameter(newpassword) != null){
            newpassword = req.getParameter(newpassword);
        } else {
            newpassword = password;
        }

		caregiverDataMoni update = new caregiverDataMoni(
		
					id,
                    req.getParameter("firstname"),
                    req.getParameter("lastname"),
					req.getParameter("email"),
					newpassword,
                    Integer.parseInt(req.getParameter("available")),
                    Float.parseFloat(req.getParameter("hourlyrate")),
					req.getParameter("description")

                );



		int count = Integer.parseInt(req.getParameter("caregiverCount"));
		System.out.println("caregiverCount:" + count);
		
		int provincetest = Integer.parseInt(req.getParameter("provinceID"));
		System.out.println("provinceID:" + provincetest);
		
		provinceDataMoni province = new provinceDataMoni(	
					id,
                    provincetest				
                );
				
				
		int n = caregiverDataMoni.updateCaregiver(connection, update);	
		
		if (count != 0) {
			int m = provinceDataMoni.updateCaregiverProvince(connection, province);
        } else {
            int m = provinceDataMoni.insertCaregiverProvince(connection, province);
        }
		
		res.sendRedirect("caregiverSettings?id=" + id + "&a=" + Math.random());
    }
}





