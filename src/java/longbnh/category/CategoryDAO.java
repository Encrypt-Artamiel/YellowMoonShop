/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.category;

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
public class CategoryDAO implements Serializable {
    private List<CategoryDTO> listCategory;
    
    public boolean loadCategory() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select categoryID, name from Category";
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                while(rs.next()){
                    int categoryID = rs.getInt("categoryID");
                    String categoryName = rs.getString("name");
                    if(this.listCategory == null){
                        this.listCategory = new ArrayList<>();
                    }
                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName);
                    this.listCategory.add(dto);
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

    public List<CategoryDTO> getListCategory() {
        return listCategory;
    }
}
