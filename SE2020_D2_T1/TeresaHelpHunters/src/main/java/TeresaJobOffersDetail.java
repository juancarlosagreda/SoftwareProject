import javax.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class TeresaJobOffersDetail extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entro al perfil");

        HttpSession session = request.getSession(true);

        // Traer datos de perfil

        String name = (String)session.getAttribute("login");
        int id = (int)session.getAttribute("loginid");

        System.out.println("Nombre en sesión " + name);
        System.out.println("Nombre en sesión " + id);

        Vector<TeresaEmploymentRecordData> v = null;

        String stringCon = "jdbc:mysql://34.76.88.212/Caregivers?user=equipo&password=Tecnun2020";
        try {
            Connection con = DriverManager.getConnection(stringCon);
            v = TeresaEmploymentRecordData.getEmployments(con, id);


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception when connecting to Driver: " + e);

        }

        String json = new Gson().toJson(v);
        System.out.println("Wrote Json");

        response.getWriter().write(json);
        System.out.println("Terminé la petición voy a regresar la respuesta");

    }

}