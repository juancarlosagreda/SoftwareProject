import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class NotesCaregiver extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

		
		HttpSession session = req.getSession(false);
        String login = null;
        int id = 0;
        if (session != null) {
            id = (int)session.getAttribute("id");
            login = String.valueOf(id);
            System.out.println("logged");
            System.out.println("login: " + login);
        }
        toClient.println(CaregiverUtilsCori.headerCaregiverDashboardAntesDePerfil(login));
        
        
        toClient.println(CaregiverUtilsCori.headerCaregiverDashboardDespuesDePerfil(login,"notes"));
        
        toClient.println("	<!-- Dashboard Content");
        toClient.println("	================================================== -->");
        toClient.println("	<div class='dashboard-content-container' data-simplebar>");
        toClient.println("		<div class='dashboard-content-inner' >");
        toClient.println("");
        toClient.println("			<!-- Dashboard Headline -->");
        toClient.println("			<div class='dashboard-headline'>");
        toClient.println("				<h3> Notes </h3>");
        toClient.println("");
        toClient.println("				<!-- Breadcrumbs -->");
        toClient.println("				<nav id='breadcrumbs' class='dark'>");
        toClient.println("					<ul>");
        toClient.println("						<li><a href='#'>Home</a></li>");
        toClient.println("						<li>Notes</li>");
        toClient.println("					</ul>");
        toClient.println("				</nav>");
        toClient.println("			</div>");
        toClient.println("");
        
        
        
        
        toClient.println("			<!-- Row -->");
        
        
        toClient.println("			<div class='row'>");
        toClient.println("");
        
        toClient.println("				<div class='col-xl-4'>");
        toClient.println("");
        toClient.println("					<!-- Dashboard Box -->");
        toClient.println("					<!-- If you want adjust height of two boxes");
        toClient.println("						 add to the lower box 'main-box-in-row'");
        toClient.println("						 and 'child-box-in-row' to the higher box -->");
        toClient.println("					<div class='dashboard-box child-box-in-row'>");
        toClient.println("						<div class='headline'>");
        toClient.println("							<h3><i class='icon-material-outline-note-add'></i> Notes</h3>");
        toClient.println("						</div>");
        toClient.println("");
        toClient.println("						<div class='content with-padding'>");
        

        Vector<NotesData> caregiverNotesList;
        caregiverNotesList = NotesData.getCaregiverNotes(connection,login);
        
        String priority = null;
            
        // EMPIEZA EL BUCLE 
        System.out.println("Llegamos al bucle de tamaño:" + caregiverNotesList.size());
        
        NotesData prueba = caregiverNotesList.elementAt(0);
        System.out.println("test:" + prueba.noteID);
        
        for(int i=0; i<caregiverNotesList.size(); i++){
            NotesData note = caregiverNotesList.elementAt(i);
            
            System.out.println("En el bucle:" + i);
        
            toClient.println("							<!-- Note -->");
            toClient.println("							<div class='dashboard-note'>");
            toClient.println("								<p>"+note.body+"</p>");
            toClient.println("								<div class='note-footer'>");
            

            switch (note.status) {
              case "High Priority":
                priority = "high";
                break;
              case "Medium Priority":
                priority = "medium";
                break;
              default:
                priority = "low";
            }    
            
//            if (note.status == "High Priority"){
//                priority = "high";
//            } else if (note.status == "Medium Priority"){
//                priority = "medium";
//            } else {
//                priority = "low";
//            }


            toClient.println("<span class='note-priority "+priority+"'>"+ note.status +"</span>");
            toClient.println("									<div class='note-buttons'>");
            toClient.println("										<a href='CaregiverNoteEdit?noteID="+note.noteID+"' title='Edit' data-tippy-placement='top'><i class='icon-feather-edit'></i></a>");
            toClient.println("									</div>");
            toClient.println("								</div>");
            toClient.println("							</div>");
        }
        
        // TERMINA EL BUCLE
        
        
        toClient.println("");
        toClient.println("						</div>");
        toClient.println("							<div class='add-note-button'>");
        toClient.println("								<a href='CaregiverNoteInsert'>Add Note<i class='icon-material-outline-arrow-right-alt'></i></a>");
        toClient.println("							</div>");
        toClient.println("					</div>");
        toClient.println("					<!-- Dashboard Box / End -->");
        toClient.println("				</div>");
        toClient.println("			</div>");
        toClient.println("			<!-- Row / End -->");
        toClient.println("");
        toClient.println("			<!-- Row -->");
        toClient.println("			<div class='row'>");        
        toClient.println("");
        toClient.println("");
        toClient.println("");
        toClient.println("			</div>");
        toClient.println("			<!-- Row / End -->");
        toClient.println("");
        toClient.println("			<!-- Footer -->");
        toClient.println("			<div class='dashboard-footer-spacer'></div>");
        toClient.println("			<div class='small-footer margin-top-15'>");
        toClient.println("				<div class='small-footer-copyrights'>");
        toClient.println("					© 2020 <strong>Help Hunters</strong>. All Rights Reserved.");
        toClient.println("				</div>");
        toClient.println("				<ul class='footer-social-links'>");
        toClient.println("					<li>");
        toClient.println("						<a href='#' title='Facebook' data-tippy-placement='top'>");
        toClient.println("							<i class='icon-brand-facebook-f'></i>");
        toClient.println("						</a>");
        toClient.println("					</li>");
        toClient.println("					<li>");
        toClient.println("						<a href='#' title='Twitter' data-tippy-placement='top'>");
        toClient.println("							<i class='icon-brand-twitter'></i>");
        toClient.println("						</a>");
        toClient.println("					</li>");
        toClient.println("					<li>");
        toClient.println("						<a href='#' title='Google Plus' data-tippy-placement='top'>");
        toClient.println("							<i class='icon-brand-google-plus-g'></i>");
        toClient.println("						</a>");
        toClient.println("					</li>");
        toClient.println("					<li>");
        toClient.println("						<a href='#' title='LinkedIn' data-tippy-placement='top'>");
        toClient.println("							<i class='icon-brand-linkedin-in'></i>");
        toClient.println("						</a>");
        toClient.println("					</li>");
        toClient.println("				</ul>");
        toClient.println("				<div class='clearfix'></div>");
        toClient.println("			</div>");
        toClient.println("			<!-- Footer / End -->");
        toClient.println("");
        toClient.println("		</div>");
        toClient.println("	</div>");
        toClient.println("	<!-- Dashboard Content / End -->");

        toClient.println(CaregiverUtilsCori.footerCaregiverDashboard());
        
    }


}