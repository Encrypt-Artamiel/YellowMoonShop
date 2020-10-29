/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.record;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import longbnh.util.DBHelper;

/**
 *
 * @author DELL
 */
public class RecordDAO implements Serializable {

    public boolean recordUpdate(String userID, int productID, Date dayUpdate) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert Record(userID, productID, dayUpdate) values (?,?,?)";
                stm = con.prepareStatement(sql);
                
                stm.setString(1, userID);
                stm.setInt(2, productID);
                stm.setDate(3, dayUpdate);
                
                int row = stm.executeUpdate();
                if(row > 0){
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
}
