import javax.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TeresaTerminateJob extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        int employmentID = Integer.parseInt(request.getParameter("employmentID"));


        TeresaResponseTerminate res = new TeresaResponseTerminate("");


        String stringCon = "jdbc:mysql://34.76.88.212/Caregivers?user=equipo&password=Tecnun2020";
        try {
            Connection con = DriverManager.getConnection(stringCon);
            //Tengo que poner de manera automática el enddate.
            int registers = TeresaEmploymentRecordData.terminate(con, employmentID);
            System.out.println("The number of changed registers is: " + registers);
            //registers should be 1
            if (registers == 1){
                res.setDescription("This job has been successfully terminated!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
        }

        String json = new Gson().toJson(res);
        System.out.println("Wrote Json");

        response.getWriter().write(json);
        System.out.println("Sending response to the javascript file");

    }

}

