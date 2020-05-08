import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date; 

@SuppressWarnings("serial")
public class CaregiverAddNote extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsCori.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
		
		HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }

		int caregiverID = Integer.parseInt(login);
        
        NotesData note = new NotesData(
            Integer.parseInt(req.getParameter("noteID")),
                    caregiverID,
					req.getParameter("body"),
					req.getParameter("datecreated"),
					req.getParameter("status")
                );

        System.out.println("prueba prueba en Controller: " + note.noteID + note.caregiverID);
        int n = NotesData.insertNote(connection, note);
        res.sendRedirect("NotesCaregiver");
    }
}