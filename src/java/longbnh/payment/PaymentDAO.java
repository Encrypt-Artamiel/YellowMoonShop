/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.payment;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longbnh.util.DBHelper;

/**
 *
 * @author DELL
 */
public class PaymentDAO implements Serializable {

    private List<PaymentDTO> listPayment;

    public boolean loadPaymentMethod() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select paymentID, paymentName from Payment";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int paymentID = rs.getInt("paymentID");
                    String paymentName = rs.getString("paymentName");
                    if(this.listPayment == null){
                        this.listPayment = new ArrayList<>();
                    }
                    this.listPayment.add(new PaymentDTO(paymentID, paymentName));
                }
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

    public List<PaymentDTO> getListPayment() {
        return listPayment;
    }
}
