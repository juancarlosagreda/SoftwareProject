import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CaregiverMsgBox extends HttpServlet {
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
       
        String clientID = req.getParameter("clientID");
        String subject = req.getParameter("subject");
        String caregiverID = login;
        toClient.println(CaregiverUtilsCori.headerCaregiverDashboardAntesDePerfil(login));
        
     
        toClient.println(CaregiverUtilsCori.headerCaregiverDashboardDespuesDePerfil(login, "messages"));
        
        

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
                
                String temp = caregiverMessagesData.elementAt(i).reply;

                System.out.println("En el bucle:" + i);
                
                if (message.clientID == clientID && message.subject == subject && message.caregiverID == caregiverID){
                    toClient.println("								<li class='active-message'>");
                } else {
                    toClient.println("								<li>");
                }

                // PONER LINK AQUI! ------>
                String link = "CaregiverMsgBox?clientID="+message.clientID+"&subject="+message.subject;

                toClient.println("									<a href='"+link+"'>");

                toClient.println("										<div class='message-avatar'>");
                
                if (isNull(temp) || temp == null || temp == "null"){
                    toClient.println("<img src='urgent.png' alt=''>");
                }
                
                toClient.println("</div>");
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
        
        Vector<MessagesData> messageData;
        messageData = MessagesData.getMessage(connection,caregiverID,clientID,subject);
        
        MessagesData message = messageData.elementAt(0);
        
        String temp = messageData.elementAt(0).reply;
        
        toClient.println("							</ul>");
        toClient.println("						</div>");
        
        toClient.println("						<!-- Messages / End -->");
        toClient.println("");
        toClient.println("						<!-- Message Content -->");
        toClient.println("						<div class='message-content'>");
        toClient.println("");
        toClient.println("							<div class='messages-headline'>");
        
        
        toClient.println("								<h4>"+message.name+"</h4>"); 
        
        
        //toClient.println("								<a href='#' class='message-action'><i class='icon-feather-trash-2'></i> Delete Conversation</a>");
        
        // BOTON DE AJAX 
        toClient.println("<script src=showMsgClient.js></script>");
        toClient.println("								<button class='message-action' id = 'button_ajax1' type='submit' onclick='viewMsgClient()'>Client Info</button>");
        
        
        toClient.println("							</div>");
        toClient.println("");
        toClient.println("							<!-- Message Content Inner -->");
        toClient.println("							<div class='message-content-inner'>");
        toClient.println("									<!-- Time Sign -->");
        toClient.println("									<div class='message-time-sign'>");
        toClient.println("										<span>"+message.datecreated+"</span>");
        toClient.println("									</div>");
        toClient.println("");
        
        // MENSAJE CLIENTE 
        
        toClient.println("									<div class='message-bubble'>");
        toClient.println("										<div class='message-bubble-inner'>");
        toClient.println("											<div class='message-avatar'></div>");
        toClient.println("											<div class='message-text'><p>"+message.message+"</p></div>");
        toClient.println("										</div>");
        toClient.println("										<div class='clearfix'></div>");
        toClient.println("									</div>");
        toClient.println("");
        
        //CONTESTACION
        
        toClient.println("									<div class='message-bubble me'>");
        toClient.println("										<div class='message-bubble-inner'>");
        toClient.println("											<div class='message-avatar'></div>");
        
        
        
        //System.out.println("Antes del if y el valor para temp es: " + temp);
        
        //System.out.println("Prueba 1: " + temp.isEmpty());
        System.out.println("Prueba 2: " + isNull(temp));
        System.out.println("Prueba 3: " + (temp == null));
        System.out.println("Prueba 4: " + (temp == "null"));
        System.out.println("Prueba 4: " + temp);
        
        if (isNull(temp) || temp == null || temp == "null" || temp == "Null"){
            System.out.println("Entraste al if!");
            
            
        } else {
            toClient.println("											<div class='message-text'><p>"+message.reply+"</p></div>");
        }
        
        toClient.println("										</div>");
        toClient.println("										<div class='clearfix'></div>");
        toClient.println("									</div>");
        toClient.println("");
        toClient.println("");
        toClient.println("											</div>");
        toClient.println("										</div>");
        toClient.println("										<div class='clearfix'></div>");
        toClient.println("									</div>");
         toClient.println("							<!-- Reply Area -->");
        
        if (isNull(temp) || temp == null || temp == "null" || temp == "Null"){
            
            System.out.println("ENTRASTE A DONDE SE PONRIA EL FORMULARIO");
            toClient.println("<form action='sendReply' method='GET'>");  
            toClient.println("							<div class='message-reply'>");      
            toClient.println("								<textarea name='reply' id='reply' cols='1' rows='1' placeholder='Your Message' data-autoresize></textarea>");
            toClient.println("								<button class='button ripple-effect'>Send</button>");  
            toClient.println("							</div>");
            
            toClient.println("								<input type = 'hidden' name='clientID' id='clientID' value='"+clientID +"'>");
            
            toClient.println("								<input type = 'hidden' name='subject' id='subject' value='"+subject +"'>");
            
            toClient.println("</form>");  
        }
        
        // INICIO AJAX
        toClient.println("<input type='hidden' id='cID_AJAX' value='" + clientID + "'></input>");
        
        toClient.println("							<div class='message-reply' id='msgClient'>"); 
        toClient.println("							</div>");
        toClient.println("<script src=showMsgClient.js></script>");
        
        //FIN AJAX
        
        toClient.println("						</div>");
        toClient.println("						<!-- Message Content -->");
        toClient.println("");
        toClient.println("					</div>");
        toClient.println("			</div>");
        
        toClient.println("							</div>");
        toClient.println("							<!-- Message Content Inner / End -->");
        
        
       
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
    
    private boolean isNull(Object obj) {
    return obj == null;
}


}