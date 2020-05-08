import javax.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class TeresaNoticeList extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entro al perfil");

        HttpSession session = request.getSession(true);

        // Get session information.

        String name = (String)session.getAttribute("login");
        int id = (int)session.getAttribute("loginid");

        System.out.println("Nombre en sesión " + name);
        System.out.println("Nombre en sesión " + id);

        Vector<TeresaNoticeData> v = null;

        String stringCon = "jdbc:mysql://34.76.88.212/Caregivers?user=equipo&password=Tecnun2020";
        try {
            Connection con = DriverManager.getConnection(stringCon);
            v = TeresaNoticeData.getNotices(con, id);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception when connecting to Driver: " + e);

            //Mejorar la forma de cachar la excepción.
        }

        String json = new Gson().toJson(v);
        System.out.println("Wrote Json");

        response.getWriter().write(json);
        System.out.println("Terminé la petición voy a regresar la respuesta");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int noticeID = Integer.parseInt(request.getParameter("noticeID"));
        int n = 0;
        TeresaResponseTerminate res = new TeresaResponseTerminate("");

        String stringCon = "jdbc:mysql://34.76.88.212/Caregivers?user=equipo&password=Tecnun2020";
        try {
            Connection con = DriverManager.getConnection(stringCon);
            n = TeresaNoticeData.readNotice(con, noticeID);
            if (n == 1) {
                res.setDescription("Database was successfully updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception when connecting to Driver: " + e);

            //Mejorar la forma de cachar la excepción.
        }

        String json = new Gson().toJson(res);
        System.out.println("Wrote Json");

        response.getWriter().write(json);
        System.out.println("Terminé la petición voy a regresar la respuesta");

    }
}
