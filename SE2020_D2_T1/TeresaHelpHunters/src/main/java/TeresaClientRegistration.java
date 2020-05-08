import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

public class TeresaClientRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter( "lastname");
		int gender =  Integer.parseInt(request.getParameter( "genero"));
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		boolean error = false;
		String descriptionError = "";

		// Check first and last name

		if (firstname != null && lastname != null) {
			firstname = firstname.trim();
			lastname = lastname.trim();
			if (firstname.equals("") == true || lastname.equals("") == true) {
				error = true;
				descriptionError = descriptionError.concat("The fields containing the first and last names must not be empty. ");
			}
		} else {
			error = true;
			descriptionError = descriptionError.concat("The fields containing the first and last names must not be empty. ");
		}

		// Now check the email address

		email = email.trim();
		if (email.equals("") == true || email.contains("@") == false) {
			error = true;
			descriptionError = descriptionError.concat("Please introduce a valid email account. ");
		}


		// Now check the passwords provided in the form

		password1 = password1.trim();
		password2 = password2.trim();
		if ((password1.equals("") == true) || (password2.equals("") == true) || (password1.length() < 8) || (password2.length() < 8) ||
				(password1.equals(password2) == false)) {
			error = true;
			descriptionError = descriptionError.concat("The password must be at least 8 characters long and both passwords must match. ");
		}


		System.out.println(descriptionError);

		TeresaResponse res = new TeresaResponse(error, "");

		if (error == false) {
			String stringCon = "jdbc:mysql://34.76.88.212/Caregivers?user=equipo&password=Tecnun2020";
			try {
				Connection con = DriverManager.getConnection(stringCon);
				TeresaClientData client = new TeresaClientData(firstname, lastname, gender, email, password1);

				try{
					int n = TeresaClientData.RegisterClient(con, client);
					res.setDescription("Your registration was successful! Welcome to our Community!");

				}catch (Exception e){
					System.out.println("There is an error in the database");
					res.setDescription("We can not register your user account!");
				}

			} catch (SQLException throwables) {
				throwables.printStackTrace();
				System.out.println(throwables);
			}
		} else {
			res.setDescription(descriptionError);
		}


		String json = new Gson().toJson(res);
		System.out.println("Wrote Json");

		response.getWriter().write(json);
		System.out.println("Terminé la petición voy a regresar la respuesta");

	}

}
