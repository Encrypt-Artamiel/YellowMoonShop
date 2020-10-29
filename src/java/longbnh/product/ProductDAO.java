/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class ProductDAO implements Serializable {

    private int totalPage;
    private List<ProductDTO> list;
    private ProductDTO product;

    public boolean createNewCake(String name, String image, String description, float price, int quantity,
            Date createDate, Date expirationDate, int categoryID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert Product(name, image, description, price, quantity, createDate, expirationDate, statusID, categoryID)"
                        + " values(?,?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);

                stm.setString(1, name);
                stm.setString(2, image);
                stm.setString(3, description);
                stm.setFloat(4, price);
                stm.setInt(5, quantity);
                stm.setDate(6, createDate);
                stm.setDate(7, expirationDate);
                stm.setInt(8, 1);
                stm.setInt(9, categoryID);

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

    public void countTotalPage() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select count(productID) as total from Product";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                if (rs.next()) {
                    double totalProduct = rs.getInt("total");
                    this.totalPage = (int) Math.ceil(totalProduct / 20);
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

    public void getListProductToUpdate(int currentPage) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select productID, name, description, image, price, quantity, createDate, expirationDate, statusID, categoryID "
                        + "from Product "
                        + "order by createDate offset ? rows fetch next ? rows only";
                stm = con.prepareStatement(sql);
                stm.setInt(1, currentPage * 20);
                stm.setInt(2, 20);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date createDate = rs.getDate("createDate");
                    Date expirationDate = rs.getDate("expirationDate");
                    int status = rs.getInt("statusID");
                    int categoryID = rs.getInt("categoryID");
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    ProductDTO dto = new ProductDTO(productID, name, image, description, price, quantity, createDate, expirationDate, status, categoryID);
                    list.add(dto);
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

    public boolean getProductByID(int productID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select productID, name, image, description, price, quantity, createDate, expirationDate, statusID, categoryID "
                        + "from Product where productID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date createDate = rs.getDate("createDate");
                    Date expirationDate = rs.getDate("expirationDate");
                    int status = rs.getInt("statusID");
                    int categoryID = rs.getInt("categoryID");
                    this.product = new ProductDTO(productID, name, image, description, price, quantity, createDate, expirationDate, status, categoryID);
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

    public boolean updateCake(String name, String image, float price, int categoryID, int quantity, Date createDate,
            Date expiredDate, int status, int productID) throws NamingException, SQLException {//status
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Product set name = ?, image = ?, price = ?, categoryID = ?, quantity = ?, "
                        + "createDate = ?, expirationDate = ?, statusID = ? where productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, image);
                stm.setFloat(3, price);
                stm.setInt(4, categoryID);
                stm.setInt(5, quantity);
                stm.setDate(6, createDate);
                stm.setDate(7, expiredDate);
                stm.setInt(8, status);
                stm.setInt(9, productID);

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

    public void loadAllProduct(int currentPage) throws NamingException, SQLException { // status
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select productID, name, image, description, price, quantity, createDate, expirationDate, statusID, categoryID "
                        + "from Product where statusID = 1 and quantity > 0 and GETDATE() < expirationDate "
                        + "order by productID offset ? rows fetch next 20 rows only";
                stm = con.prepareStatement(sql);
                stm.setInt(1, currentPage * 20);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date createDate = rs.getDate("createDate");
                    Date expirationDate = rs.getDate("expirationDate");
                    int status = rs.getInt("statusID");
                    int categoryID = rs.getInt("categoryID");
                    ProductDTO dto = new ProductDTO(productID, name, image, description, price, quantity, createDate, expirationDate, status, categoryID);
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
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

    public void searchProduct(String searchValue, float minPrice, float maxPrice, int categoryID, int currentPage)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                if (maxPrice > 0) {
                    String sql = "Select productID, name, description, image, price,"
                            + " quantity, createDate, expirationDate, statusID, categoryID"
                            + " from product where name like ?"
                            + " and categoryID = ? and price between ? and ? and statusID = 1 and quantity > 0"
                            + " and GETDATE() < expirationDate"
                            + " order by createDate offset ? rows fetch next 20 rows only";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + searchValue + "%");
                    stm.setInt(2, categoryID);
                    stm.setFloat(3, minPrice);
                    stm.setFloat(4, maxPrice);
                    stm.setInt(5, currentPage * 20);
                } else {
                    String sql = "Select productID, name, description, image, price,"
                            + " quantity, createDate, expirationDate, statusID, categoryID"
                            + " from product where name like ?"
                            + " and categoryID = ? and price > ? and statusID = 1 and quantity > 0"
                            + " and GETDATE() < expirationDate"
                            + " order by createDate offset ? rows fetch next 20 rows only";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + searchValue + "%");
                    stm.setInt(2, categoryID);
                    stm.setFloat(3, minPrice);
                    stm.setInt(4, currentPage * 20);
                }
                double totalCake = 0;
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date createDate = rs.getDate("createDate");
                    Date expirationDate = rs.getDate("expirationDate");
                    int status = rs.getInt("statusID");
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    ProductDTO dto = new ProductDTO(productID, name, image, description, price, quantity, createDate, expirationDate, status, categoryID);
                    list.add(dto);
                    totalCake++;
                }
                this.totalPage = (int) Math.ceil(totalCake / 20);

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

    public boolean checkValidProduct(int productID, int quantity) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select productID from Product where productID = ? and statusID = 1 and quantity >= ? "
                        + "and GETDATE() < expirationDate ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                stm.setInt(2, quantity);
                rs = stm.executeQuery();
                if (rs.next()) {
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

    public boolean updateQuantityAfterOrder(int productID, int quantity) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Product set quantity = quantity - ? where productID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, productID);

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

    public int getTotalPage() {
        return totalPage;
    }

    public List<ProductDTO> getList() {
        return list;
    }

    public ProductDTO getProduct() {
        return product;
    }
}
