import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TeresaFileNotice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String subject = request.getParameter("subject");
        String message = request.getParameter("notice");
        int classification = Integer.parseInt(request.getParameter("classification"));
        int employmentID = Integer.parseInt(request.getParameter("employmentID"));
        boolean error = false;
        String descriptionerror = "";


        TeresaResponse res = new TeresaResponse(error, "");

        if (subject != null){
            subject = subject.trim();
            if (subject.equals("")) {
                error = true;
                descriptionerror += "The subject cannot be empty.";
            }
        } else {
            error = true;
            descriptionerror += "The subject must not be empty. ";
        }

        if (message != null){
            message = message.trim();
            if(message.equals("")) {
                error = true;
                descriptionerror += "The message must not be empty. ";
            }
        } else {
            error = true;
            descriptionerror += "The message must not be empty. ";
        }


        if (error == false) {
            String stringCon = "jdbc:mysql://34.76.88.212/Caregivers?user=equipo&password=Tecnun2020";
            try {
                Connection con = DriverManager.getConnection(stringCon);
                TeresaNoticeData notice = new TeresaNoticeData(subject, message, classification, employmentID);
                int n = TeresaNoticeData.InsertClient(con, notice);
                res.setDescription("Your notice was sent successfully! We will reply soon.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println(throwables);
            }

        } else {
            res.setOk(error);
            res.setDescription(descriptionerror);
        }

            String json = new Gson().toJson(res);
            System.out.println("Wrote Json");

            response.getWriter().write(json);
            System.out.println("Sending response to the javascript file");

    }
}
