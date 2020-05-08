import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;


@SuppressWarnings("serial")
public class CaregiverRegister extends HttpServlet {
	Connection connection;
	
	public void init(ServletConfig config) throws ServletException {
        //Connect to the database cuando inicializamos el servlet
		//Usa el primero porque hay un input argument		
		super.init(config);
        connection = ConnectionUtilsMoni.getConnection(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		
		caregiverDataMoni caregiver = new caregiverDataMoni( 
		
					null,
                    request.getParameter("firstname"),
                    request.getParameter("lastname"),
					request.getParameter("email"),
					request.getParameter("password"),
                    Integer.parseInt(request.getParameter("radio")),
                    null,
                    Float.parseFloat(request.getParameter("hourlyrate")),
					request.getParameter("description"),
					Date.valueOf(request.getParameter("birthday") .toString()),
					null
					//Con el get parameter solo lees strings con lo que los floats y los ints hay que pasarlos a strings
					
                );
        int n = caregiverDataMoni.insertCaregiver(connection, caregiver);
		response.sendRedirect("register-confirmation.html");

        

    }
}
