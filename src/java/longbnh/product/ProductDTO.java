/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.product;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class ProductDTO implements Serializable {

    private int productID;
    private String name;
    private String image;
    private String description;
    private float price;
    private int quantity;
    private Date createDate;
    private Date expirationDate;
    private int status;
    private int categoryID;

    public ProductDTO(String name, String image, float price, int quantity) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(int productID, String name, String image, String description, float price, int quantity, Date createDate, Date expirationDate, int status, int categoryID) {
        this.productID = productID;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.status = status;
        this.categoryID = categoryID;
    }


    public ProductDTO() {
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }


    public int getCategoryID() {
        return categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }
}
