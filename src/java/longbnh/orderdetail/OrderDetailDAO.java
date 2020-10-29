/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longbnh.orders.OrdersTrackingObject;
import longbnh.product.ProductDTO;
import longbnh.util.DBHelper;

/**
 *
 * @author DELL
 */
public class OrderDetailDAO implements Serializable {

    private OrdersTrackingObject orderTracking;

    public boolean addCakeToOrder(String orderID, int productID, int quantity, float totalPrice) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert OrderDetail(orderID, productID, quantity, priceTotal) values (?,?,?,?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setInt(2, productID);
                stm.setInt(3, quantity);
                stm.setFloat(4, totalPrice);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean getTrackingOrder(String orderID, String userID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select O.orderDate, O.total, O.name, O.phone, O.address, "
                        + "OD.quantity, P.name as productName, P.image, P.price, PAY.paymentName "
                        + "from OrderDetail OD, Orders O, Product P, Payment PAY where "
                        + "PAY.paymentID = O.paymentID and O.orderID = OD.orderID and OD.productID = P.productID and "
                        + "OD.orderID = ? and O.userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, userID);

                rs = stm.executeQuery();
                List<ProductDTO> listProduct = new ArrayList<>();

                Date orderDate = null;
                float total = 0;
                String name = null;
                String phone = null;
                String address = null;
                String payment = null;

                while (rs.next()) {
                    orderDate = rs.getDate("orderDate");
                    total = rs.getFloat("total");
                    name = rs.getString("name");
                    phone = rs.getString("phone");
                    address = rs.getString("address");
                    payment = rs.getString("paymentName");
                
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    listProduct.add(new ProductDTO(productName, image, price, quantity));
                }
                this.orderTracking = new OrdersTrackingObject(name, phone, address, payment, total, orderDate, listProduct);
                return true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public OrdersTrackingObject getOrderTracking() {
        return orderTracking;
    }
}
