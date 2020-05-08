import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class caregiverData {
    
	/*Integer caregiverID;
    String firstname;
    String lastname;
    String email;
    String password;
    Integer  gender;
    Integer   available;
    float  hourlyrate;
    String description;
    Date   birthdate;
    Integer     enrollmentstatus;

    caregiverData (Integer caregiverID, String firstname, String lastname, String email, String password, Integer  gender, Integer  available, float  hourlyrate, String description, Date birthdate, Integer  enrollmentstatus) {
		this.caregiverID = caregiverID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.available = available;
        this.hourlyrate = hourlyrate;
        this.description = description;
        this.birthdate = birthdate;
        this.enrollmentstatus = enrollmentstatus;
    }
	
   public static int insertCaregiver(Connection connection, caregiverData caregiver) {
        String sql ="INSERT INTO Caregiver (firstname, lastname, email, password, gender, hourlyrate, description, birthdate)"
            + "VALUES (?,?,?,?,?,?,?,?)";
        System.out.println("insertCaregiver: " + sql);
        int n = 0;
		try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, caregiver.firstname);
            ps.setString(2, caregiver.lastname);
            ps.setString(3, caregiver.email);
            ps.setString(4, caregiver.password);
            ps.setInt(5, caregiver.gender);
            ps.setFloat(6, caregiver.hourlyrate);
            ps.setString(7, caregiver.description);
            ps.setDate(8, caregiver.birthdate);
            n = ps.executeUpdate();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertCaregiver: " + sql + " Exception: " + e);
        }
        return n;
    }*/
    String month;
    String views;
    
    caregiverData (String month, String views){
        this.month = month;
        this.views = views;
    }
    
    public static Vector<caregiverData> getViews(Connection connection, String caregiverID, String year){
        
        System.out.println("ID?: " + caregiverID);
        
        String sql = "SELECT Mes, count(*) as views from (SELECT caregiverID, MONTH(viewdate) as Mes, YEAR(viewdate) as view_year FROM ProfileViews) AS TABLA1 WHERE caregiverID=? and view_year=? GROUP BY Mes;";
        
        System.out.println("getViews: " + sql);
        
        Vector<caregiverData> vec = new Vector<caregiverData>();
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, caregiverID);
            pstmt.setString(2, year);
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()){
                
                caregiverData x = new caregiverData(
                    result.getString("Mes"),
                    result.getString("views")
                );
                System.out.println("views:" +x.views);
                vec.addElement(x);
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getViews: " + sql + " Exception: " + e);
        }
        
        return vec;
        
    }
    
    public static String getViewsStr(Connection connection, String caregiverID,String year){
        
        System.out.println("Estoy en getViewsStr");
        //String[] months = { "January", "February", "March", "April", "May", "June", "July", "Agosto", "September", "October","November","December"};
        
        Vector<caregiverData> vec = caregiverData.getViews(connection,caregiverID,year);
        int size = vec.size();
        System.out.println("Estoy en getViewsStr y getViews me da un vector de tamano:" + size);
        String str = "[";
        
        int encontro = 0;
        String valorAGuardar = "";
        for (int i=1; i<=12; i++){  
            encontro = 0;
            for (int j=0; j<vec.size(); j++){
                caregiverData x = vec.elementAt(j);
                if (Integer.parseInt(x.month) == i){
                    encontro = 1;
                    valorAGuardar = x.views;
                }
            }
            if (encontro == 1){
                str += valorAGuardar;
                encontro = 0;
            } else {
                str += "0";
            }
            str +=",";
        }
        
        str += "]";
        System.out.println(str);
        
        return str;
        
    }
    
    
	
	

    /*public static Vector<ProductData> getProductList(Connection connection) {
        Vector<ProductData> vec = new Vector<ProductData>();
        String sql = "Select ProductId, ProductName, Products.SupplierId as SupplierId, CompanyName, UnitPrice FROM Products, Suppliers";
        sql += " WHERE Products.SupplierId = Suppliers.SupplierId";
        System.out.println("getProductList: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                ProductData product = new ProductData(
                    result.getString("ProductId"),
                    result.getString("ProductName"),
                    Integer.parseInt(result.getString("SupplierId")),
                    result.getString("CompanyName"),
                    Float.parseFloat(result.getString("UnitPrice"))
                );
                vec.addElement(product);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProductList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    public static Vector<ProductData> getCategoryProductList(Connection connection, String id) {
        Vector<ProductData> vec = new Vector<ProductData>();
        String sql = "Select ProductId, ProductName, Products.SupplierId as SupplierId, CompanyName, UnitPrice FROM Products, Suppliers";
        sql += " WHERE Products.SupplierId = Suppliers.SupplierId AND CategoryID=?";
        System.out.println("getProductList: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                ProductData product = new ProductData(
                    result.getString("ProductId"),
                    result.getString("ProductName"),
                    Integer.parseInt(result.getString("SupplierId")),
                    result.getString("CompanyName"),
                    Float.parseFloat(result.getString("UnitPrice"))
                );
                vec.addElement(product);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProductList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    public static ProductData getProduct(Connection connection, String id) {
        String sql = "Select ProductId, ProductName, SupplierId, UnitPrice FROM Products";
        sql += " WHERE ProductId=?";
        System.out.println("getProduct: " + sql);
        ProductData product = null;;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                product = new ProductData(
                    result.getString("ProductId"),
                    result.getString("ProductName"),
                    Integer.parseInt(result.getString("SupplierId")),
                    null,
                    Float.parseFloat(result.getString("UnitPrice"))
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProduct: " + sql + " Exception: " + e);
        }
        return product;
    }
    public static int updateProduct(Connection connection, ProductData product) {
        String sql ="UPDATE Products "
            + "SET ProductName = ?, SupplierId = ?, UnitPrice = ?"
            + " WHERE ProductId = ?";
        System.out.println("updateProduct: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,product.productName);
            stmtUpdate.setInt(2,product.supplierId);
            stmtUpdate.setFloat(3,product.unitPrice);
            stmtUpdate.setString(4,product.productId);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateProduct: " + sql + " Exception: " + e);
        }
        return n;
    }*/
}
