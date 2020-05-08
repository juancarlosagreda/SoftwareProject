import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CaregiverMsg extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtilsCori.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

		
		HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }
        toClient.println(CaregiverUtilsCori.headerCaregiverDashboardAntesDePerfil(login));
        
        toClient.println(CaregiverUtilsCori.headerCaregiverDashboardDespuesDePerfil(login,"messages"));
        

        toClient.println("");
        toClient.println("");
        toClient.println("	<!-- Dashboard Content");
        toClient.println("	================================================== -->");
        toClient.println("	<div class='dashboard-content-container' data-simplebar>");
        toClient.println("		<div class='dashboard-content-inner' >");
        toClient.println("");
        toClient.println("			<!-- Dashboard Headline -->");
        toClient.println("			<div class='dashboard-headline'>");
        toClient.println("				<h3>Messages</h3>");
        toClient.println("");
        toClient.println("				<!-- Breadcrumbs -->");
        toClient.println("				<nav id='breadcrumbs' class='dark'>");
        toClient.println("					<ul>");
        toClient.println("						<li><a href='#'>Home</a></li>");
        toClient.println("						<li>Messages</li>");
        toClient.println("					</ul>");
        toClient.println("				</nav>");
        toClient.println("			</div>");
        toClient.println("");
        toClient.println("				<div class='messages-container margin-top-0'>");
        toClient.println("");
        toClient.println("					<div class='messages-container-inner'>");
        toClient.println("");
        toClient.println("						<!-- Messages -->");
        toClient.println("						<div class='messages-inbox'>");
        toClient.println("							<div class='messages-headline'>");
        toClient.println("								<div class='input-with-icon'>");
        toClient.println("								</div>");
        toClient.println("							</div>");
        toClient.println("");
        toClient.println("							<ul>");

        
        
        Vector<MessagesData> caregiverMessagesData;
        caregiverMessagesData = MessagesData.getCaregiverMessages(connection,login);
            
        // EMPIEZA EL BUCLE 
        
        System.out.println("Llegamos al bucle de tamaño:" + caregiverMessagesData.size());
        int x = caregiverMessagesData.size();
        
        if (x == 0){
            toClient.println("								<li>");

                // PONER LINK AQUI! ------>

                toClient.println("									<a href='#'>");

                toClient.println("										<div class='message-avatar'></div>");
                toClient.println("										<div class='message-by'>");
                toClient.println("											<div class='message-by-headline'>");

                toClient.println("												<h5>Empty Message Box</h5>");

                toClient.println("											</div>");
                toClient.println("										</div>");
                toClient.println("									</a>");
                toClient.println("								</li>");
        } else {
            for(int i=0; i<caregiverMessagesData.size(); i++){
                MessagesData message = caregiverMessagesData.elementAt(i);

                System.out.println("En el bucle:" + i);

                toClient.println("								<li>");

                // PONER LINK AQUI! ------>
                String link = "CaregiverMsgBox?clientID="+message.clientID+"&subject="+message.subject;

                toClient.println("									<a href='"+link+"'>");

                toClient.println("										<div class='message-avatar'></div>");
                toClient.println("										<div class='message-by'>");
                toClient.println("											<div class='message-by-headline'>");

                toClient.println("												<h5>"+message.name+"</h5>");

                if (Integer.parseInt(message.DateDiff) > 10){
                    toClient.println("												<span>"+message.datecreated+"</span>");
                } else if (Integer.parseInt(message.DateDiff) == 0) {
                    toClient.println("												<span>Today</span>");
                } else {
                
                    toClient.println("												<span>"+message.DateDiff+" Days Ago </span>");
                }

                toClient.println("											</div>");
                toClient.println("											<p>"+message.subject+"</p>");
                toClient.println("										</div>");
                toClient.println("									</a>");
                toClient.println("								</li>");
            }
        }
        
        // TERMINA EL BUCLE
        
        
        toClient.println("<!--SE PUEDE PONER <li class='active-message'> en vez de solo li!!! -->");
        toClient.println("							</ul>");
        toClient.println("						</div>");
        toClient.println("						<!-- Messages / End -->");
        toClient.println("");
        toClient.println("						<!-- Message Content -->");
        toClient.println("");
        toClient.println("						<!-- Message Content -->");
        toClient.println("");
        toClient.println("					</div>");
        toClient.println("			</div>");
        toClient.println("			<!-- Messages Container / End -->");
        toClient.println("");
        toClient.println("");
        toClient.println("");
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