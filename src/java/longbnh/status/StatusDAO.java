/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.status;

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
public class StatusDAO implements Serializable {

    private List<StatusDTO> listStatus;

    public boolean loadCategory() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select statusID, statusName from Status";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int statusID = rs.getInt("statusID");
                    String statusName = rs.getString("statusName");
                    if (this.listStatus == null) {
                        this.listStatus = new ArrayList<>();
                    }
                    StatusDTO dto = new StatusDTO(statusID, statusName);
                    this.listStatus.add(dto);
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

    public List<StatusDTO> getListStatus() {
        return listStatus;
    }
}
