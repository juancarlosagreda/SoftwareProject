import javax.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TeresaClientProfile extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entro al perfil");

        HttpSession session = request.getSession(true);

        // Traer datos de perfil

        String name = (String)session.getAttribute("login");
        int id = (int)session.getAttribute("loginid");

        System.out.println("Nombre en sesión " + name);
        System.out.println("Nombre en sesión " + id);

        TeresaResponseProfile res = null;

        //Necesito el nombre, apellido, email y género del cliente.

        //Necesito el número de veces que aparece su client id en la tabla EmploymentRecord
        //y la suma de todas las horas de los trabajos que ha contratado ese cliente.

        String stringCon = "jdbc:mysql://34.76.88.212/Caregivers?user=equipo&password=Tecnun2020";
        try {
            Connection con = DriverManager.getConnection(stringCon);
            TeresaClientData client = TeresaClientData.getInfo(con, id, false);

            int[] infoemployments = TeresaEmploymentRecordData.countEmployments(con, id);

            System.out.println("name: " + client.firstname + " last name: " + client.lastname + " email: " +
                    client.email + " gender: " + client.gender);
            System.out.println("Number of employment offers: " + infoemployments);

            res = new TeresaResponseProfile(client.firstname, client.lastname, client.email, client.gender, infoemployments[0], infoemployments[1]);

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


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter( "lastname");
        String email = request.getParameter("email");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        System.out.println("Datos Recibidos: " +  firstname + " " +  lastname +  " " + email + " " +  currentPassword + " " + newPassword);

        HttpSession session = request.getSession(true);
        int id = (int)session.getAttribute("loginid");

        String stringCon = "jdbc:mysql://34.76.88.212/Caregivers?user=equipo&password=Tecnun2020";
        Connection connection;
        try {

            System.out.println("id de sesion es: " + id);

            if(newPassword == null){
                System.out.println("No hay nueva contra");
            }else{
                System.out.println("Por alguna razón hay contra");

            }



            connection = DriverManager.getConnection(stringCon);

            TeresaClientData client = TeresaClientData.getInfo(connection, id, true);

            client.setLastname(lastname);
            client.setFirstname(firstname);
            client.setEmail(email);
            client.setClientID(id);

            System.out.println("El cliente es pass " + client.password + " usuario envio " + currentPassword);
            if(newPassword != null){
                // TODO Terminar es funcionalidad
                System.out.println("El usuario está intentado cambiar la contraseña");
                if (client.password.equals(currentPassword) ) {
                    System.out.println(" Ok la contraseña coincide procedemos a verificar que tenga 8 caracteres.");
                    newPassword = newPassword.trim();
                    if (newPassword.length() >= 8) {
                        client.setPassword(newPassword);
                        TeresaClientData.updateClientData(connection, client, true);
                        TeresaResponse res = new TeresaResponse(true,"Your personal information was updated successfully!");
                        String json = new Gson().toJson(res);
                        response.getWriter().write(json);
                    } else {
                        TeresaResponse res = new TeresaResponse(true,"The password has to be at least 8 characters long.");
                        String json = new Gson().toJson(res);
                        response.getWriter().write(json);
                    }
                }else{
                    TeresaResponse res = new TeresaResponse(true,"Please validate your current password, it's incorrect");
                    String json = new Gson().toJson(res);
                    response.getWriter().write(json);
                }
            }else{
                System.out.println("Solo vamos a cambiar los datos de firstname, lastname, email");
                TeresaClientData.updateClientData(connection, client, false);

                TeresaResponse res = new TeresaResponse(false, "Your personal information was updated successfully!");
                String json = new Gson().toJson(res);
                response.getWriter().write(json);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            TeresaResponse res = new TeresaResponse(true,"Sorry, an unexpeted error have ocurred");
            String json = new Gson().toJson(res);
            response.getWriter().write(json);

        }
    }

}