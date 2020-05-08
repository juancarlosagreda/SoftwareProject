import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeresaClientData {

    int clientID;
    String firstname;
    String lastname;
    int gender;
    String email;
    String password;

    public TeresaClientData(String firstname, String lastname, int gender, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int RegisterClient (Connection con, TeresaClientData client) throws Exception {
        String sql = "INSERT INTO Client (firstname, lastname, email, password, gender)" +
                " VALUES (?, ?, ?, ?, ?)";
        System.out.println("Insert a new client into the database: " + sql);
        int n = 0;
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, client.firstname);
            statement.setString(2, client.lastname);
            statement.setString(3, client.email);
            statement.setString(4, client.password);
            statement.setInt(5, client.gender);
            n = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error in insertOrderDetail: " + sql + " Exception: " + e);

            //e.printStackTrace();
            throw new Exception("There is an error");
        }
        return n;
    }

    public static TeresaClientData getInfo (Connection con, int id, boolean getPassword){
        String sql = "Select * FROM Client WHERE clientID = ?";
        System.out.println("Conseguir información: " + sql);
        TeresaClientData client = null;
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                // Si se pasa el parámetro getPassword como true entonces se guarda la contraseña en el objeto client
                String password = null;
                if(getPassword){
                    password= result.getString("password");
                }

                client = new TeresaClientData(
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getInt("gender"),
                        result.getString("email"),
                        password);
            }
            result.close();
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error en getInfo con la excepción: " + e);
        }

        return client;
    }


    public static TeresaClientData updateClientData(Connection connection, TeresaClientData client, boolean updatePass){
        try {
            System.out.println("Actualizando datos " + client.clientID  + client.firstname +  " " + client.lastname +  " " + client.email + " " + client.password);

            if(updatePass){
                String sql = "UPDATE Client SET firstname = ?, lastname = ?, email = ?, password = ? WHERE clientID = ?;";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, client.firstname);
                statement.setString(2, client.lastname);
                statement.setString(3, client.email);
                statement.setString(4, client.password);
                statement.setInt(5, client.clientID);
                statement.executeUpdate();
            }else{
                String sql = "UPDATE Client SET firstname = ?, lastname = ?, email = ? WHERE clientID = ?;";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, client.firstname);
                statement.setString(2, client.lastname);
                statement.setString(3, client.email);
                statement.setInt(4, client.clientID);
                statement.executeUpdate();
            }

            System.out.println("Aquí debió  actualizar el usuario");


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
