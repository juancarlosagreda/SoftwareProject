import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class CaregiverNotesUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        //res.setContentType("text/html");
		System.out.println(" ENTRASTE A CAREGIVERNOTESUPDATE! ");
		HttpSession session = req.getSession(false);
        String login = null;
        int id = 0;
        if (session != null) {
            id = (int)session.getAttribute("id");
            login = String.valueOf(id);
            System.out.println("logged");
            System.out.println("login: " + login);
        }
		
        int caregiverID = Integer.parseInt(login);
        String noteID = req.getParameter("noteID");
        System.out.println("Note Id es:"+ noteID);
        
        NotesData note = new NotesData(
                    Integer.parseInt(req.getParameter("noteID")),
                    caregiverID,
                    req.getParameter("body"),
                    req.getParameter("datecreated"),
                    req.getParameter("status")
                );
        
        int n = NotesData.updateNote(connection, note);
        res.sendRedirect("NotesCaregiver");
    }
}