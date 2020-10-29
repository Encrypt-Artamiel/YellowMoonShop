/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import longbnh.util.DBHelper;

/**
 *
 * @author DELL
 */
public class UsersDAO implements Serializable {

    private UsersDTO user = null;

    public boolean checkLogin(String userID, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select userID, password, name, phone, address, roleID "
                        + "from Users where userID = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int roleID = rs.getInt("roleID");
                    this.user = new UsersDTO(userID, password, name, phone, address, roleID);
                    return true;
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
        return false;
    }

    public boolean createAccount(String userID, String password, String name) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert Users(userID, password, name, roleID) values (?,?,?,?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                stm.setString(3, name);
                stm.setInt(4, 2);

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

    public UsersDTO getUser() {
        return user;
    }
}
