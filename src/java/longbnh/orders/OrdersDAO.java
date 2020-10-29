/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import longbnh.util.DBHelper;

/**
 *
 * @author DELL
 */
public class OrdersDAO implements Serializable {

    private String orderID;

    public boolean createOrder(String userID, float total, Date orderDate, String name, String phone, String address, 
            int paymentID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert Orders(userID, total, orderDate, name, phone, address, paymentID) values(?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);

                stm.setString(1, userID);
                stm.setFloat(2, total);
                stm.setDate(3, orderDate);
                stm.setString(4, name);
                stm.setString(5, phone);
                stm.setString(6, address);
                stm.setInt(7, paymentID);

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

    public void getLastOrderID(float total, Date orderDate, String name, String phone, String address) throws
            NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select max(orderID) as orderID from Orders where total = ? and orderDate = ? "
                        + "and name = ? and phone = ? and address = ?";
                stm = con.prepareStatement(sql);

                stm.setFloat(1, total);
                stm.setDate(2, orderDate);
                stm.setString(3, name);
                stm.setString(4, phone);
                stm.setString(5, address);

                rs = stm.executeQuery();
                if (rs.next()) {
                    this.orderID = rs.getString("orderID");
                }
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
    }

    public String getOrderID() {
        return orderID;
    }
}
