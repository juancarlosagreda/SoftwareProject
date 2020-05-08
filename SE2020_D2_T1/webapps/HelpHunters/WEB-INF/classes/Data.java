
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Data {
  /*  int    orderId;
    String    customerId;
    String companyName;
    Date    orderDate;

    Data (int orderId, String customerId, String companyName, Date orderDate) {
        this.orderId    = orderId;
        this.customerId   = customerId;
        this.companyName = companyName;
        this.orderDate = orderDate;
    }
    public static Vector<Data> getList(Connection connection) {
        Vector<Data> vec = new Vector<Data>();
        String sql = "Select Id, CustomerId, Date FROM s";
        System.out.println("getList: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                Data order = new Data(
                    Integer.parseInt(result.getString("ID")),
                    result.getString("CustomerId"),
                    null,
                    result.getDate("Date")
                );
                vec.addElement(order);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    public static Data getData(Connection connection, int id) {
        String sql = "Select  FROM ";
        sql += " WHERE s.CustomerID = Customers.CustomerID AND ID=?";
        System.out.println("get: " + sql);
        Data order = null;;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                order = new Data(
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
    public static int updateHeader(Connection connection, Data order) {
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
    public static Vector<DetailData> getDetail(Connection connection, int id) {
        Vector<DetailData> vec = new Vector<DetailData>();
        String sql = "Select ID, [ Details].ProductID, ProductName, [ Details].UnitPrice as UnitPrice, Quantity, Discount FROM [ Details], Products";
        sql += " WHERE [ Details].ProductID = Products.ProductID AND ID = ?";
        System.out.println("getDetail: " + sql);
        try {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                DetailData orderDetail = new DetailData(
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

    public static Vector<OrderDetailData> getSimpleOrderDetail(Connection connection, int id) {
        Vector<OrderDetailData> vec = new Vector<OrderDetailData>();
        String sql = "Select OrderID, ProductID, UnitPrice, Quantity, Discount FROM OrderDetailsOld";
        sql += " WHERE OrderID = ?";
        //System.out.println("getSimpleOrderDetail: " + sql);
        try {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                OrderDetailData orderDetail = new OrderDetailData(
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
    public static int updateUnitPriceOrderDetail(Connection connection, OrderDetailData orderDetail) {
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
    public static int insertOrderDetail(Connection connection, OrderDetailData orderDetail) {
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

class OrderDetailData {
    int    orderId;
    String    productId;
    String productName;
    float    unitPrice;
    float    quantity;
    float    discount;

    OrderDetailData (int orderId, String productId, String productName, float unitPrice, float quantity, float discount) {
        this.orderId    = orderId;
        this.productId   = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }*/
}

// To insert current time:
//        pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
//        pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
// https://stackoverflow.com/questions/24736427/how-to-get-date-from-a-resultset
