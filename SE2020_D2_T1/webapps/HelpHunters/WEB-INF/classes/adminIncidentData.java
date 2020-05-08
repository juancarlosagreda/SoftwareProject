
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class adminIncidentData {
    int    noticeID;
    int employmentID;
    String subject;
    String message;
    Date datecreated;
    String reply;
    Date datereplied;
    int classification;
    int caregiverID;
    int clientID;



    adminIncidentData (int noticeID, int employmentID, String subject, String message, Date datecreated,
                    String reply, Date datereplied, int classification) {
        this.noticeID = noticeID;
        this.employmentID = employmentID;
        this.subject = subject;
        this.message = message;
        this.datecreated = datecreated;
        this.reply=reply;
        this.datereplied = datereplied;
        this.classification = classification;

}
    adminIncidentData(int noticeID, int clientID, int caregiverID){
      this.noticeID=noticeID;
      this. clientID = clientID;
      this. caregiverID = caregiverID;

    }

    adminIncidentData(int employmentID, int caregiverID){

      this. employmentID =employmentID ;
      this.caregiverID=caregiverID;
    }



public static Vector<adminIncidentData> getadminIncidentData(Connection connection) {
        Vector<adminIncidentData> vec = new Vector<adminIncidentData>();
        String sql = "Select * FROM Notice WHERE classification=1";
        //String sql = "Select Caregiver.*, EmploymentRecord.status FROM Client LEFT JOIN EmploymentRecord ORDER BY Client.caregiverID DESC";
        System.out.println("Get incident list SQL: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                int noticeID = Integer.parseInt(result.getString("Notice.noticeID"));
                adminIncidentData incident = new adminIncidentData(
                    noticeID,
                    Integer.parseInt(result.getString("employmentID")),
                    result.getString("subject"),
                    result.getString("message"),
                    result.getDate("datecreated"),
                    result.getString("reply"),
                    result.getDate("datereplied"),
                    Integer.parseInt(result.getString("classification"))
                );
                vec.addElement(incident);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in get incident List: " + sql + " Exception: " + e);
        }
        return vec;
    }


    public static Vector<adminIncidentData> getListbyCaregiver(Connection connection, int caregiverID) {
        Vector<adminIncidentData> vec = new Vector<adminIncidentData>();
        String sql = "Select * FROM Notice WHERE classification = 1 AND employmentID IN (SELECT employmentID FROM EmploymentRecord WHERE caregiverID = ?)";
        System.out.println("getList: " + sql);
        try {
          PreparedStatement pstmt = connection.prepareStatement(sql);
          pstmt.setInt(1, caregiverID);
          ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                adminIncidentData incpercare = new adminIncidentData(
                    Integer.parseInt(result.getString("noticeID")),
                    Integer.parseInt(result.getString("employmentID")),
                    result.getString("subject"),
                    result.getString("message"),
                    result.getDate("datecreated"),
                    result.getString("reply"),
                    result.getDate("datereplied"),
                    Integer.parseInt(result.getString("classification"))
                );
                vec.addElement(incpercare);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getList: " + sql + " Exception: " + e);
        }
        return vec;
    }


    public static adminIncidentData getIncident(Connection connection, int noticeID) {
        adminIncidentData incidentData = null;
        String sql = "Select * FROM Notice WHERE noticeID = ?";
        System.out.println("getList: " + sql);
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, noticeID);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                 incidentData = new adminIncidentData(
                 noticeID,
                 Integer.parseInt(result.getString("employmentID")),
                 result.getString("subject"),
                 result.getString("message"),
                 result.getDate("datecreated"),
                 result.getString("reply"),
                 result.getDate("datereplied"),
                 Integer.parseInt(result.getString("classification"))               );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getList: " + sql + " Exception: " + e);
        }
        return incidentData;
    }

    public static adminIncidentData getParticipants(Connection connection, int noticeID) {
        adminIncidentData incParticipants = null;
        String sql = "Select clientID, caregiverID FROM EmploymentRecord WHERE employmentID IN (SELECT employmentID FROM Notice WHERE noticeID =?)";
        System.out.println("getNames: " + sql);
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, noticeID);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                 incParticipants = new adminIncidentData(
                 noticeID,
                 Integer.parseInt(result.getString("clientID")),
                 Integer.parseInt(result.getString("caregiverID"))              );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getNames: " + sql + " Exception: " + e);
        }
        return incParticipants;
    }

    public static adminIncidentData getEmployment(Connection connection, int noticeID) {
        adminIncidentData employment = null;
        String sql = "Select employmentID, caregiverID FROM EmploymentRecord WHERE employmentID IN(SELECT employmentID FROM Notice WHERE noticeID=?)";
        System.out.println("getempID: " + sql);
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, noticeID);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                 employment = new adminIncidentData(
                 Integer.parseInt(result.getString("employmentID")),
                 Integer.parseInt(result.getString("caregiverID"))
             );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getempID: " + sql + " Exception: " + e);
        }
        return employment;
    }


    public static String getempID(Connection connection, int noticeID) {
            String sql = "Select employmentID FROM EmploymentRecord";
            sql += " WHERE noticeID=?";
            System.out.println("getempID: " + sql);
            String empID = null;;
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                System.out.println("product noticeID: " + noticeID);
                pstmt.setInt(1, noticeID);
                ResultSet result = pstmt.executeQuery();
                if(result.next()) {
                    empID  = result.getString("employmentID");
                    System.out.println("empID: " + empID);
                }
                result.close();
                pstmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Error in getempID: " + sql + " Exception: " + e);
            }
            return empID;
        }

    /*public static int deleteCaregiver(Connection connection, int noticeID) {
        String sql ="DELETE FROM Caregiver WHERE noticeID = ?";
        System.out.println("update: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1, noticeID);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in update: " + sql + " Exception: " + e);
        }
        return n;
    }*/

    /*public static int getincidences(Connection connection, int id) {
        int inc = 0;

       String sql = "Select noticeID FROM Notice WHERE classification = 0 AND employmentID IN (SELECT employmentID FROM EmploymentRecord WHERE incidentID = ?)";
      String sql= "SELECT employmentID FROM EmploymentRecord WHERE caregiverID =? ";
        System.out.println("getincidences: " + sql);
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            Integer.parseInt(result.getString("employmentID"));
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getincidences: " + sql + " Exception: " + e);
        }
        return inc;
    }*/
    /*public static adminIncidentData getadminCaregiverData(Connection connection, int id) {
        String sql = "Select  FROM ";
        sql += " WHERE s.CustomerID = Customers.CustomerID AND ID=?";
        System.out.println("get: " + sql);
        adminCaregiverData order = null;;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                order = new adminCaregiverData(
                    Integer.parseInt(result.getString("ID")),
                    result.getString("CustomerId"),
                    null,
                    result.getDate("Date")
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getHeader: " + sql + " Exception: " + e);
        }
        return order;
    }
    public static int updateHeader(Connection connection, adminCaregiverData order) {
        String sql ="UPDATE s "
            + "SET CustomerId = ?,"
            + " Date = ?"
            + " WHERE Id = ?";
        System.out.println("update: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,order.customerId);
            stmtUpdate.setDate(2,order.orderDate);
            stmtUpdate.setInt(3,order.orderId);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in update: " + sql + " Exception: " + e);
        }
        return n;
    }
    public static Vector<DetailadminCaregiverData> getDetail(Connection connection, int id) {
        Vector<DetailadminCaregiverData> vec = new Vector<DetailadminCaregiverData>();
        String sql = "Select ID, [ Details].ProductID, ProductName, [ Details].UnitPrice as UnitPrice, Quantity, Discount FROM [ Details], Products";
        sql += " WHERE [ Details].ProductID = Products.ProductID AND ID = ?";
        System.out.println("getDetail: " + sql);
        try {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                DetailadminCaregiverData orderDetail = new DetailadminCaregiverData(
                    Integer.parseInt(result.getString("OrderID")),
                    result.getString("ProductID"),
                    result.getString("ProductName"),
                    Float.parseFloat(result.getString("UnitPrice")),
                    Float.parseFloat(result.getString("Quantity")),
                    Float.parseFloat(result.getString("Discount"))
                );
                vec.addElement(orderDetail);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getOrderDetail: " + sql + " Exception: " + e);
        }
        return vec;
    }

    public static Vector<OrderDetailadminCaregiverData> getSimpleOrderDetail(Connection connection, int id) {
        Vector<OrderDetailadminCaregiverData> vec = new Vector<OrderDetailadminCaregiverData>();
        String sql = "Select OrderID, ProductID, UnitPrice, Quantity, Discount FROM OrderDetailsOld";
        sql += " WHERE OrderID = ?";
        //System.out.println("getSimpleOrderDetail: " + sql);
        try {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                OrderDetailadminCaregiverData orderDetail = new OrderDetailadminCaregiverData(
                    Integer.parseInt(result.getString("OrderID")),
                    result.getString("ProductID"),
                    "",
                    Float.parseFloat(result.getString("UnitPrice")),
                    Float.parseFloat(result.getString("Quantity")),
                    Float.parseFloat(result.getString("Discount"))
                );
                vec.addElement(orderDetail);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getSimpleOrderDetail: " + sql + " Exception: " + e);
        }
        return vec;
    }
    public static int updateUnitPriceOrderDetail(Connection connection, OrderDetailadminCaregiverData orderDetail) {
        String sql ="UPDATE [Order Details] "
            + "SET UnitPrice = ?"
            + " WHERE OrderID = ?";
        System.out.println("updateUnitPriceOrderDetail: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setFloat(1,orderDetail.unitPrice);
            stmtUpdate.setInt(2,orderDetail.orderId);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateUnitPriceOrderDetail: " + sql + " Exception: " + e);
        }
        return n;
    }
    public static int insertOrderDetail(Connection connection, OrderDetailadminCaregiverData orderDetail) {
        String sql ="INSERT INTO [Order Details] (OrderID, ProductID, UnitPrice, Quantity, Discount) "
            + "VALUES (?, ?, ?, ?, ?)";
        System.out.println("updateProduct: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1,orderDetail.orderId);
            stmtUpdate.setString(2,orderDetail.productId);
            stmtUpdate.setFloat(3,orderDetail.unitPrice);
            stmtUpdate.setFloat(4,orderDetail.quantity);
            stmtUpdate.setFloat(5,orderDetail.discount);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertOrderDetail: " + sql + " Exception: " + e);
        }
        return n;
    }

}

class OrderDetailadminCaregiverData {
    int    orderId;
    String    productId;
    String productName;
    float    unitPrice;
    float    quantity;
    float    discount;

    OrderDetailadminCaregiverData (int orderId, String productId, String productName, float unitPrice, float quantity, float discount) {
        this.orderId    = orderId;
        this.productId   = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }*/
}
